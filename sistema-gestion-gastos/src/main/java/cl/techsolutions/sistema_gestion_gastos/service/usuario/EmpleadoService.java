package cl.techsolutions.sistema_gestion_gastos.service.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Empleado;
import cl.techsolutions.sistema_gestion_gastos.repository.usuarios.EmpleadoRepository;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    //Método para obtener todos los empleados:
    public List<Empleado> get_empleados() {
        return empleadoRepository.findAll();
    }

    //Método para obtener un empleado por su ID:
    public Optional<Empleado> get_empleado_by_id(int id) {
        return empleadoRepository.findById(id);
    }

    //Método para buscar un empleado por su email:
    public Optional<Empleado> get_empleado_by_email(String email) {
        return empleadoRepository.findByEmail(email);
    }

    //Método para guardar un empleado:
    public Empleado save_empleado(Empleado empleado) {
        if (empleadoRepository.existsById(empleado.getId())) {
            throw new RuntimeException("Empleado ya existe con ID: " + empleado.getId());
        }
        return empleadoRepository.save(empleado);
    }

    //Método para eliminar un empleado por su ID:
    public void delete_empleado(int id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Empleado no encontrado con ID: " + id);
        }
    }
}
