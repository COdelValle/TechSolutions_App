package cl.techsolutions.sistema_gestion_gastos.repository.usuarios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Empleado;

/**
 * <b>EmpleadoRepository</b> es un repositorio de Spring Data JPA para la entidad {@link Empleado}.
 * <p>
 * Permite realizar operaciones CRUD y consultas personalizadas sobre empleados en el sistema de gestión de gastos.
 * </p>
 * 
 * <h3>Métodos principales:</h3>
 * <ul>
 *   <li><b>findByEmail(String email)</b>: Busca un empleado por su correo electrónico.</li>
 *   <li>Métodos CRUD heredados de {@link JpaRepository}.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Este repositorio permite acceder a los datos específicos de los empleados.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    // Método para buscar un empleado por su correo electrónico
    Optional<Empleado> findByEmail(String email);
    // Método para verificar si un empleado existe por su correo electrónico
    boolean existsByEmail(String email);
}
