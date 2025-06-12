package cl.techsolutions.sistema_gestion_gastos.service.usuario;

import java.security.SecureRandom;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;
import cl.techsolutions.sistema_gestion_gastos.model.usuarios.GerenteDepartamento;
import cl.techsolutions.sistema_gestion_gastos.repository.departamento.DepartamentoRepository;
import cl.techsolutions.sistema_gestion_gastos.repository.usuarios.GerenteDepartamentoRepository;

@Service
public class GerenteDepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private GerenteDepartamentoRepository gerenteDepartamentoRepository;

    //Método para obtener todos los Gerentes:
    public List<GerenteDepartamento> get_gerentes() {
        return gerenteDepartamentoRepository.findAll();
    }

    //Método para obtener los gerentes segun departamento
    public List<GerenteDepartamento> get_gerentes_by_departamento(int id_departamento){
        Departamento departamento = departamentoRepository.findById(id_departamento).get();
        return gerenteDepartamentoRepository.findByDepartamento(departamento);
    }

    //Método para guardar un empleado:
    public GerenteDepartamento save_gerente(GerenteDepartamento gerente) {
        if (gerenteDepartamentoRepository.existsById(gerente.getId())) {
            throw new RuntimeException("G.Departamento ya existe con ID: " + gerente.getId());
        }
        // Validación de nombre y apellido
        String nombre = gerente.getNombre().strip();
        String apellido = gerente.getApellido().strip();
        if (nombre.isEmpty() || apellido.isEmpty()) {
            throw new RuntimeException("Nombre y apellido no pueden estar vacíos");
        }else if (nombre.length() < 2 || apellido.length() < 2) {
            throw new RuntimeException("Nombre y apellido deben tener al menos 2 caracteres");
        }
        // Validación de departamento
        int id = gerente.getDepartamento().getId();
        gerente.setDepartamento(departamentoRepository.findById(id).get());
        if (gerente.getDepartamento() == null || id <= 0) {
            throw new RuntimeException("Departamento no puede ser nulo o tener ID inválido");
        }
        // Generación automática de contraseña
        gerente.setContraseña(generar_contraseña_aleatoria(14));
        // Generación del email
        gerente.setEmail(crear_email_gerente(gerente));
        // Validación de email único
        if (gerenteDepartamentoRepository.existsByEmail(gerente.getEmail())) {
            throw new RuntimeException("Gerente ya existe con email: " + gerente.getEmail());
        }
        try {
            return gerenteDepartamentoRepository.save(gerente);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Error al guardar el gerente: " + ex.getMostSpecificCause().getMessage());
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
    private String crear_email_gerente(GerenteDepartamento gerente) {
        String nombre = gerente.getNombre().strip();
        String apellido = gerente.getApellido().strip();
        String departamento_nombre = gerente.getDepartamento().getNombre().strip();
        int numero_aleatorio = (int) (Math.random() * 99) + 1;
        String email =
            nombre.substring(0, 2)+"."+apellido+numero_aleatorio+
            "@tsgdep"+departamento_nombre.substring(0, 2)+".com";
        return email.toLowerCase();
    }
}
