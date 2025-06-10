package cl.techsolutions.sistema_gestion_gastos.repository.presupuesto;

import cl.techsolutions.sistema_gestion_gastos.model.presupuesto.TipoPresupuesto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>TipoPresupuestoRepository</b> es un repositorio de Spring Data JPA para la entidad {@link TipoPresupuesto}.
 * <p>
 * Permite realizar operaciones CRUD y consultas personalizadas sobre los tipos de presupuesto registrados en el sistema de gestión de gastos.
 * </p>
 * 
 * <h3>Métodos principales:</h3>
 * <ul>
 *   <li>Métodos CRUD heredados de {@link JpaRepository}.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Este repositorio permite acceder a los datos específicos de los tipos de presupuesto y sus presupuestos asociados.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
public interface TipoPresupuestoRepository extends JpaRepository<TipoPresupuesto, Integer> {
}
