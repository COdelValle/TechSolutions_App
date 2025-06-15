package cl.techsolutions.sistema_gestion_gastos.controller.usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.dto.LoginDTO;
import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Empleado;
import cl.techsolutions.sistema_gestion_gastos.model.usuarios.GerenteDepartamento;
import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Usuario;
import cl.techsolutions.sistema_gestion_gastos.service.usuario.EmpleadoService;
import cl.techsolutions.sistema_gestion_gastos.service.usuario.GerenteDepartamentoService;
import cl.techsolutions.sistema_gestion_gastos.service.usuario.UsuarioService;
import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("/api/beta-1.0/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private GerenteDepartamentoService gerenteDepartamentoService;

    // Méodo para obtener un usuario por su id
    @GetMapping("/id/{id}")
    public Usuario get_usuario_by_id(@PathVariable int id) {
        return usuarioService.get_usuario_by_id(id).get();
    }

    // Método para obtener un usuario por el email
    @GetMapping("/email/{email}")
    public Usuario get_usuario_by_email(@PathVariable String email) {
        return usuarioService.get_usuario_by_email(email).get();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        Optional<Usuario> usuario = usuarioService.authenticar_usuario(loginDTO.getEmail(), loginDTO.getContraseña());

        if (usuario.isPresent()) {
            Optional<? extends Usuario> usuarioCompleto = obtener_rol(usuario.get());

            if (usuarioCompleto.isPresent()) {
                return ResponseEntity.ok(usuarioCompleto.get());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error: No se pudo determinar el rol del usuario.");
            }
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Credenciales inválidas");
        }
    }

    //Método para obtener el rol de un usuario:
    private Optional<? extends Usuario> obtener_rol(Usuario usuario){
        List<Empleado> empleados = empleadoService.get_empleados();
        for (Empleado empleado : empleados) {
            if (empleado.getId() == usuario.getId()) {
                return Optional.of(empleado);
            }    
        }
        List<GerenteDepartamento> gerentesD = gerenteDepartamentoService.get_gerentes();
        for (GerenteDepartamento gerenteDepartamento : gerentesD) {
            if (gerenteDepartamento.getId()==usuario.getId()) {
                return Optional.of(gerenteDepartamento);
            }
        }
        return Optional.empty();
    }
}
