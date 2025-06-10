package cl.techsolutions.sistema_gestion_gastos.model.departamento;

import java.util.List;

import cl.techsolutions.sistema_gestion_gastos.model.presupuesto.Presupuesto;
import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Proyecto;
import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Empleado;
import cl.techsolutions.sistema_gestion_gastos.model.usuarios.GerenteDepartamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Departamento</b> es una clase que representa una unidad organizacional dentro del sistema de gestión de gastos.
 * <p>
 * Esta entidad almacena información sobre el nombre, la descripción y mantiene relaciones con empleados, gerentes, presupuestos y proyectos asociados.
 * </p>
 * 
 * <h3>Atributos:</h3>
 * <ul>
 *   <li><b>id</b>: Identificador único del departamento.</li>
 *   <li><b>nombre</b>: Nombre del departamento, no puede estar vacío.</li>
 *   <li><b>descripcion</b>: Descripción del departamento, no puede estar vacía y tiene un máximo de 255 caracteres.</li>
 *   <li><b>empleados</b>: Lista de {@link Empleado} que pertenecen al departamento.</li>
 *   <li><b>gerentes_departamento</b>: Lista de {@link GerenteDepartamento} asignados al departamento.</li>
 *   <li><b>presupuestos</b>: Lista de {@link Presupuesto} asociados al departamento.</li>
 *   <li><b>proyectos</b>: Lista de {@link Proyecto} gestionados por el departamento.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Un departamento puede tener múltiples empleados, gerentes, presupuestos y proyectos asociados.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    @Column(nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "departamento")
    private List<Empleado> empleados;

    @OneToMany(mappedBy = "departamento")
    private List<GerenteDepartamento> gerentes_departamento;

    @OneToMany(mappedBy = "departamento")
    private List<Presupuesto> presupuestos;

    @OneToMany(mappedBy = "departamento")
    private List<Proyecto> proyectos;
}
