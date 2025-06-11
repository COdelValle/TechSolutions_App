package cl.techsolutions.sistema_gestion_gastos.service.proyecto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Gasto;
import cl.techsolutions.sistema_gestion_gastos.repository.proyecto.GastoRepository;

@Service
public class GastoService {
    @Autowired
    private GastoRepository gastoRepository;

    // Método para obtener todos los gastos:
    public List<Gasto> get_gastos() {
        return gastoRepository.findAll();
    }

    // Método para obtener un gasto por su ID:
    public Gasto get_gasto_by_id(int id) {
        return gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado con ID: " + id));
    }

    // Método para agregar un gasto:
    public Gasto save_gasto(Gasto gasto) {
        if (gastoRepository.existsById(gasto.getId())) {
            throw new RuntimeException("Gasto ya existe con ID: " + gasto.getId());
        }
        return gastoRepository.save(gasto);
    }

    // Método para eliminar un gasto por su ID:
    public void delete_gasto(int id) {
        if (gastoRepository.existsById(id)) {
            gastoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Gasto no encontrado con ID: " + id);
        }
    }

    // Método para actualizar un gasto:
    public Gasto update_gasto(Gasto gasto) {
        int id = gasto.getId();
        if (!gastoRepository.existsById(id)) {
            throw new RuntimeException("Gasto no encontrado con ID: " + id);
        }
        return gastoRepository.save(gasto);
    }
}
