package cl.techsolutions.sistema_gestion_gastos.service.proyecto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Proyecto;
import cl.techsolutions.sistema_gestion_gastos.repository.proyecto.ProyectoRepository;

@Service
public class ProyectoService {
    @Autowired
    private ProyectoRepository proyectoRepository;

    // Método para obtener todos los proyectos:
    public List<Proyecto> get_proyectos() {
        return proyectoRepository.findAll();
    }

    // Método para obtener un proyecto por su ID:
    public Optional<Proyecto> get_proyecto_by_id(int id) {
        return proyectoRepository.findById(id);
    }

    // Método para buscar proyectos por nombre:
    public Optional<Proyecto> get_proyectos_by_nombre(String nombre) {
        return proyectoRepository.findByNombre(nombre);
    }

    // Método para agregar un proyecto:
    public Proyecto save_proyecto(Proyecto proyecto) {
        if (proyectoRepository.existsById(proyecto.getId())) {
            throw new RuntimeException("Proyecto ya existe con ID: " + proyecto.getId());
        }
        return proyectoRepository.save(proyecto);
    }

    // Método para eliminar un proyecto por su ID:
    public void delete_proyecto(int id) {
        proyectoRepository.deleteById(id);
    }

    // Método para actualizar un proyecto:
    public Proyecto update_proyecto(Proyecto proyecto) {
        int id = proyecto.getId();
        if (proyectoRepository.existsById(id)) {
            return proyectoRepository.save(proyecto);
        } else {
            throw new RuntimeException("Proyecto no encontrado con ID: " + id);
        }
    }
}
