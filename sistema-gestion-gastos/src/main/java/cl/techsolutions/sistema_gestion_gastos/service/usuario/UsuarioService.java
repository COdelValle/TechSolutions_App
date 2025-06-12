package cl.techsolutions.sistema_gestion_gastos.service.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Usuario;
import cl.techsolutions.sistema_gestion_gastos.repository.usuarios.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    //Método para autenticar a un usuario:
    public Optional<Usuario> authenticar_usuario(String email, String contraseña) {
        return usuarioRepository.findByEmail(email).filter(usuario -> usuario.getContraseña().equals(contraseña));
    }

    //Método para obtener un usuario por su ID:
    public Optional<Usuario> get_usuario_by_id(int id) {
        return usuarioRepository.findById(id);
    }

    //Método para buscar un usuario por su email:
    public Optional<Usuario> get_usuario_by_email(String email) {
        return usuarioRepository.findByEmail(email);
    }

    //Método para eliminar un usuario por su ID:
    public void delete_gerente(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
}
