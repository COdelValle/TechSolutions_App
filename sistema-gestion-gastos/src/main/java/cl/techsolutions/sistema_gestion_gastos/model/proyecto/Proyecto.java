package cl.techsolutions.sistema_gestion_gastos.model.proyecto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * <b>Proyecto</b> es una clase que representa un proyecto gestionado dentro del sistema de gestión de gastos.
 * <p>
 * Esta entidad almacena información relevante sobre un proyecto, incluyendo su nombre, descripción, fechas y relaciones con otras entidades.
 * </p>
 * 
 * <h3>Atributos:</h3>
 * <ul>
 *   <li><b>id</b>: Identificador único del proyecto.</li>
 *   <li><b>nombre</b>: Nombre del proyecto, no puede estar vacío.</li>
 *   <li><b>descripcion</b>: Descripción opcional del proyecto.</li>
 *   <li><b>fecha_inicio</b>: Fecha de inicio del proyecto, no puede ser nula.</li>
 *   <li><b>fecha_fin</b>: Fecha de finalización del proyecto (opcional).</li>
 *   <li><b>departamento</b>: {@link Departamento} al que pertenece el proyecto, no puede ser nulo.</li>
 *   <li><b>gastos</b>: Lista de {@link Gasto} asociados al proyecto.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Un proyecto pertenece a un solo departamento, pero puede tener múltiples gastos asociados.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private Date fecha_inicio;

    private Date fecha_fin;

    @JsonBackReference("departamento-proyecto")
    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "proyecto")
    private List<Gasto> gastos;
}
