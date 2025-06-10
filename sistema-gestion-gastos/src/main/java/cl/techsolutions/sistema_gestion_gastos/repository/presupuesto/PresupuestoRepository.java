package cl.techsolutions.sistema_gestion_gastos.repository.presupuesto;

import cl.techsolutions.sistema_gestion_gastos.model.presupuesto.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>PresupuestoRepository</b> es un repositorio de Spring Data JPA para la entidad {@link Presupuesto}.
 * <p>
 * Permite realizar operaciones CRUD y consultas personalizadas sobre los presupuestos registrados en el sistema de gestión de gastos.
 * </p>
 * 
 * <h3>Métodos principales:</h3>
 * <ul>
 *   <li>Métodos CRUD heredados de {@link JpaRepository}.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Este repositorio permite acceder a los datos específicos de los presupuestos asociados a departamentos y tipos de presupuesto.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
public interface PresupuestoRepository extends JpaRepository<Presupuesto, Integer> {
}
