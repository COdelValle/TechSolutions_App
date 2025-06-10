package cl.techsolutions.sistema_gestion_gastos.repository.departamento;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>DepartamentoRepository</b> es un repositorio de Spring Data JPA para la entidad {@link Departamento}.
 * <p>
 * Permite realizar operaciones CRUD y consultas personalizadas sobre los departamentos registrados en el sistema de gestión de gastos.
 * </p>
 * 
 * <h3>Métodos principales:</h3>
 * <ul>
 *   <li><b>findByNombre(String nombre)</b>: Busca un departamento por su nombre.</li>
 *   <li>Métodos CRUD heredados de {@link JpaRepository}.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Este repositorio permite acceder a los datos específicos de los departamentos y sus relaciones con empleados, gerentes, presupuestos y proyectos.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    //Método para encontrar un departamento por su nombre
    Optional<Departamento> findByNombre(String nombre);
}
