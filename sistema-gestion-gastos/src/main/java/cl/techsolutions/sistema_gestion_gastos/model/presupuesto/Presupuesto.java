package cl.techsolutions.sistema_gestion_gastos.model.presupuesto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Presupuesto</b> es una clase que representa la asignación de recursos financieros a un departamento dentro del sistema de gestión de gastos.
 * <p>
 * Esta entidad almacena información sobre el tipo de presupuesto, los montos asignados y utilizados, el periodo de vigencia y el departamento asociado.
 * </p>
 * 
 * <h3>Atributos:</h3>
 * <ul>
 *   <li><b>id</b>: Identificador único del presupuesto.</li>
 *   <li><b>tipo_presupuesto</b>: {@link TipoPresupuesto} asociado al presupuesto, no puede ser nulo.</li>
 *   <li><b>monto_total</b>: Monto total asignado al presupuesto, no puede ser nulo.</li>
 *   <li><b>monto_utilizado</b>: Monto del presupuesto que ya ha sido utilizado.</li>
 *   <li><b>fecha_inicio</b>: Fecha de inicio de vigencia del presupuesto, no puede ser nula.</li>
 *   <li><b>fecha_fin</b>: Fecha de fin de vigencia del presupuesto, no puede ser nula.</li>
 *   <li><b>departamento</b>: {@link Departamento} al que pertenece el presupuesto, no puede ser nulo.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Un presupuesto está asociado a un solo departamento y a un solo tipo de presupuesto.</li>
 *   <li>El monto utilizado puede ser actualizado a medida que se registran gastos.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Presupuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference("tipo-presupuesto-presupuesto")
    @ManyToOne
    @JoinColumn(name = "tipo_presupuesto_id", nullable = false)
    private TipoPresupuesto tipo_presupuesto;

    @Column(nullable = false)
    private double monto_total;

    private double monto_utilizado;

    @Column(nullable = false)
    private Date fecha_inicio;

    @Column(nullable = false)
    private Date fecha_fin;

    @JsonBackReference("departamento-presupuesto")
    @OneToOne(mappedBy = "presupuesto")
    private Departamento departamento;
}
