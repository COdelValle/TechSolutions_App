package cl.techsolutions.sistema_gestion_gastos.controller.usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.dto.LoginDTO;
import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Usuario;
import cl.techsolutions.sistema_gestion_gastos.service.usuario.UsuarioService;

@RestController
@RequestMapping("/api/beta-1.0/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

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
    public Map<String, String> login(@RequestBody LoginDTO loginDTO){
        Optional<Usuario> usuario = usuarioService.authenticar_usuario(loginDTO.getEmail(), loginDTO.getContraseña());
        Map<String, String> respuesta = new HashMap<>();
        if (usuario.isPresent()) {
            respuesta.put("result", "OK");
            respuesta.put("email", usuario.get().getEmail());
        }else{
            respuesta.put("result", "ERROR");
        }
        return respuesta;
    }
}
