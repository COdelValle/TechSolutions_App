package cl.techsolutions.sistema_gestion_gastos.repository.usuarios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Usuario;

/**
 * <b>UsuarioRepository</b> es un repositorio de Spring Data JPA para la entidad {@link Usuario}.
 * <p>
 * Permite realizar operaciones CRUD y consultas personalizadas sobre usuarios del sistema de gestión de gastos.
 * </p>
 * 
 * <h3>Métodos principales:</h3>
 * <ul>
 *   <li><b>findByEmail(String email)</b>: Busca un usuario por su correo electrónico.</li>
 *   <li>Métodos CRUD heredados de {@link JpaRepository}.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Este repositorio permite acceder a los datos comunes de todos los tipos de usuario.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Método para buscar un usuario por su correo electrónico
    Optional<Usuario> findByEmail(String email);
}
