package cl.techsolutions.sistema_gestion_gastos.repository.usuarios;

import cl.techsolutions.sistema_gestion_gastos.model.usuarios.GerenteDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * <b>GerenteDepartamentoRepository</b> es un repositorio de Spring Data JPA para la entidad {@link GerenteDepartamento}.
 * <p>
 * Permite realizar operaciones CRUD y consultas personalizadas sobre los gerentes de departamento en el sistema de gestión de gastos.
 * </p>
 * 
 * <h3>Métodos principales:</h3>
 * <ul>
 *   <li><b>findByEmail(String email)</b>: Busca un gerente de departamento por su correo electrónico.</li>
 *   <li>Métodos CRUD heredados de {@link JpaRepository}.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Este repositorio permite acceder a los datos específicos de los gerentes de departamento.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
public interface GerenteDepartamentoRepository extends JpaRepository<GerenteDepartamento, Integer> {
    // Método para buscar un Gerente de departamento por su correo electrónico
    Optional<GerenteDepartamento> findByEmail(String email);
}
