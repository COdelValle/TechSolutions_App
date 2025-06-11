package cl.techsolutions.sistema_gestion_gastos.service.usuario;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        // Validación de nombre y apellido
        String nombre = empleado.getNombre().strip();
        String apellido = empleado.getApellido().strip();
        if (nombre.isEmpty() || apellido.isEmpty()) {
            throw new RuntimeException("Nombre y apellido no pueden estar vacíos");
        }else if (nombre.length() < 2 || apellido.length() < 2) {
            throw new RuntimeException("Nombre y apellido deben tener al menos 2 caracteres");
        }
        // Validación de departamento
        if (empleado.getDepartamento() == null || empleado.getDepartamento().getId() <= 0) {
            throw new RuntimeException("Departamento no puede ser nulo o tener ID inválido");
        }
        // Generación automática de contraseña
        empleado.setContraseña(generar_contraseña_aleatoria(10)); // Por ejemplo, 10 caracteres
        // Generación del email
        empleado.setEmail(crear_email_empleado(empleado));
        // Validación de email único
        if (empleadoRepository.existsByEmail(empleado.getEmail())) {
            throw new RuntimeException("Empleado ya existe con email: " + empleado.getEmail());
        }
        try {
            return empleadoRepository.save(empleado);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Error al guardar el empleado: " + ex.getMostSpecificCause().getMessage());
        }
    }

    //Método para autenticar a un empleado:
    public Optional<Empleado> authenticar_empleado(String email, String contraseña) {
        return empleadoRepository.findByEmail(email).filter(empleado -> empleado.getContraseña().equals(contraseña));
    }

    //Método para eliminar un empleado por su ID:
    public void delete_empleado(int id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Empleado no encontrado con ID: " + id);
        }
    }

    // Método para generar una contraseña aleatoria
    private String generar_contraseña_aleatoria(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return sb.toString();
    }

    // Método para crear un email a partir del nombre, apellido y departamento del empleado
    private String crear_email_empleado(Empleado empleado) {
        String nombre = empleado.getNombre().strip();
        String apellido = empleado.getApellido().strip();
        String departamento_nombre = empleado.getDepartamento().getNombre().strip();
        int numero_aleatorio = (int) (Math.random() * 99) + 1;
        String email =
            nombre.substring(0, 2)+"."+apellido+numero_aleatorio+
            "@tsemp"+departamento_nombre.substring(0, 2)+".com";
        return email.toLowerCase();
    }
}
