package cl.techsolutions.sistema_gestion_gastos.repository.proyecto;

import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>GastoRepository</b> es un repositorio de Spring Data JPA para la entidad {@link Gasto}.
 * <p>
 * Permite realizar operaciones CRUD y consultas personalizadas sobre los gastos registrados en el sistema de gestión de gastos.
 * </p>
 * 
 * <h3>Métodos principales:</h3>
 * <ul>
 *   <li>Métodos CRUD heredados de {@link JpaRepository}.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Este repositorio permite acceder a los datos específicos de los gastos asociados a proyectos.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
public interface GastoRepository extends JpaRepository<Gasto, Integer> {
}
