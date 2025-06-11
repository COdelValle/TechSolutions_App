package cl.techsolutions.sistema_gestion_gastos.model.presupuesto;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>TipoPresupuesto</b> es una clase que representa los diferentes tipos de presupuesto que pueden existir en el sistema de gestión de gastos.
 * <p>
 * Esta entidad almacena el nombre del tipo de presupuesto y mantiene la relación con los presupuestos asociados a este tipo.
 * </p>
 * 
 * <h3>Atributos:</h3>
 * <ul>
 *   <li><b>id</b>: Identificador único del tipo de presupuesto.</li>
 *   <li><b>nombre</b>: Nombre del tipo de presupuesto, no puede estar vacío y debe ser único.</li>
 *   <li><b>presupuestos</b>: Lista de {@link Presupuesto} asociados a este tipo de presupuesto.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Un tipo de presupuesto puede estar asociado a múltiples presupuestos.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TipoPresupuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "tipo_presupuesto")
    private List<Presupuesto> presupuestos;
}
