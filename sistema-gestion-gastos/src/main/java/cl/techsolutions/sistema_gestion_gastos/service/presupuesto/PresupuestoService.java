package cl.techsolutions.sistema_gestion_gastos.service.presupuesto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.techsolutions.sistema_gestion_gastos.model.presupuesto.Presupuesto;
import cl.techsolutions.sistema_gestion_gastos.repository.presupuesto.PresupuestoRepository;

@Service
public class PresupuestoService {
    @Autowired
    private PresupuestoRepository presupuestoRepository;

    // Método para obtener todos los presupuestos:
    public List<Presupuesto> get_presupuestos() {
        return presupuestoRepository.findAll();
    }

    // Método para obtener un presupuesto por su ID:
    public Optional<Presupuesto> get_presupuesto_by_id(int id) {
        return presupuestoRepository.findById(id);
    }

    // Método para agregar un presupuesto:
    public Presupuesto save_presupuesto(Presupuesto presupuesto) {
        if (presupuestoRepository.existsById(presupuesto.getId())) {
            throw new RuntimeException("Presupuesto ya existe con ID: " + presupuesto.getId());
        }
        return presupuestoRepository.save(presupuesto);
    }

    // Método para eliminar un presupuesto por su ID:
    public void delete_presupuesto(int id) {
        if (presupuestoRepository.existsById(id)) {
            presupuestoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Presupuesto no encontrado con ID: " + id);
        }
    }

    // Método para actualizar un presupuesto:
    public Presupuesto update_presupuesto(Presupuesto presupuesto) {
        int id = presupuesto.getId();
        if (!presupuestoRepository.existsById(id)) {
            throw new RuntimeException("Presupuesto no encontrado con ID: " + id);
        }
        return presupuestoRepository.save(presupuesto);
    }
}
