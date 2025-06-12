package cl.techsolutions.sistema_gestion_gastos.repository.proyecto;

import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Proyecto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>ProyectoRepository</b> es un repositorio de Spring Data JPA para la entidad {@link Proyecto}.
 * <p>
 * Permite realizar operaciones CRUD y consultas personalizadas sobre proyectos en el sistema de gestión de gastos.
 * </p>
 * 
 * <h3>Métodos principales:</h3>
 * <ul>
 *   <li><b>findByNombre(String nombre)</b>: Busca un proyecto por su nombre.</li>
 *   <li>Métodos CRUD heredados de {@link JpaRepository}.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Este repositorio permite acceder a los datos específicos de los proyectos.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    //Método para encontrar un proyecto por su nombre
    Optional<Proyecto> findByNombre(String nombre);
    List<Proyecto> findByDepartamentoId(int id_departamento);
}
