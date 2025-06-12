package cl.techsolutions.sistema_gestion_gastos.service.presupuesto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cl.techsolutions.sistema_gestion_gastos.model.presupuesto.TipoPresupuesto;
import cl.techsolutions.sistema_gestion_gastos.repository.presupuesto.TipoPresupuestoRepository;

@Service
public class TipoPresupuestoService {
    @Autowired
    private TipoPresupuestoRepository tipoPresupuestoRepository;

    // Método para obtener todos los tipos de presupuesto:
    public List<TipoPresupuesto> get_tipos_presupuesto() {
        return tipoPresupuestoRepository.findAll();
    }

    // Método para obtener un tipo de presupuesto por su ID:
    public Optional<TipoPresupuesto> get_tipo_presupuesto_by_id(int id) {
        return tipoPresupuestoRepository.findById(id);
    }

    // Método para agregar un tipo de presupuesto:
    public TipoPresupuesto save_tipo_presupuesto(TipoPresupuesto tipoPresupuesto) {
        if (tipoPresupuestoRepository.existsById(tipoPresupuesto.getId())) {
            throw new RuntimeException("Tipo de presupuesto ya existe con ID: " + tipoPresupuesto.getId());
        }
        try {
            return tipoPresupuestoRepository.save(tipoPresupuesto);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Error al guardar el tipo de presupuesto: " + ex.getMostSpecificCause().getMessage());
        }
    }

    // Método para eliminar un tipo de presupuesto por su ID:
    public void delete_tipo_presupuesto(int id) {
        if (tipoPresupuestoRepository.existsById(id)) {
            tipoPresupuestoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tipo de presupuesto no encontrado con ID: " + id);
        }
    }
}
