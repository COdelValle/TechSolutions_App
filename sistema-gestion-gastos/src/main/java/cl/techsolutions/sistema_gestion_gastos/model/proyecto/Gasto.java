package cl.techsolutions.sistema_gestion_gastos.model.proyecto;

import java.util.Date;

import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Empleado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * <b>Gasto</b> es una clase que representa un gasto registrado dentro de un proyecto en el sistema de gestión de gastos.
 * <p>
 * Esta entidad almacena información sobre el monto, la fecha, el proyecto asociado, el empleado que lo registró y el estado de la solicitud.
 * </p>
 * 
 * <h3>Atributos:</h3>
 * <ul>
 *   <li><b>id</b>: Identificador único del gasto.</li>
 *   <li><b>monto</b>: Monto del gasto, no puede ser nulo.</li>
 *   <li><b>fecha</b>: Fecha en que se realizó el gasto, no puede ser nula.</li>
 *   <li><b>proyecto</b>: {@link Proyecto} al que pertenece el gasto, no puede ser nulo.</li>
 *   <li><b>registrador</b>: {@link Empleado} que registró el gasto, no puede ser nulo.</li>
 *   <li><b>estado</b>: Estado de la solicitud del gasto, puede ser PENDIENTE, APROBADO o RECHAZADO.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>El estado del gasto es {@link EstadoGasto#PENDIENTE} por defecto al ser creado.</li>
 *   <li>El gasto debe ser aprobado o rechazado por un gerente de departamento.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El monto es obligatorio")
    @Column(nullable = false)
    private double monto;

    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado registrador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoGasto estado = EstadoGasto.PENDIENTE;
}