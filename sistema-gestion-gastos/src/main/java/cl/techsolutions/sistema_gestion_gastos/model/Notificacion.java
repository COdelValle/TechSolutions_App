package cl.techsolutions.sistema_gestion_gastos.model;

import java.time.LocalDateTime;

import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Notificacion</b> es una clase que representa una notificación enviada a un usuario dentro del sistema de gestión de gastos.
 * <p>
 * Esta entidad almacena información sobre el título, mensaje, fecha de creación, estado de lectura y el destinatario de la notificación.
 * </p>
 * 
 * <h3>Atributos:</h3>
 * <ul>
 *   <li><b>id</b>: Identificador único de la notificación.</li>
 *   <li><b>titulo</b>: Título de la notificación, no puede estar vacío.</li>
 *   <li><b>mensaje</b>: Mensaje de la notificación, no puede estar vacío.</li>
 *   <li><b>fechaCreacion</b>: Fecha y hora en que se creó la notificación.</li>
 *   <li><b>leida</b>: Indica si la notificación ha sido leída por el usuario.</li>
 *   <li><b>destinatario</b>: {@link Usuario} que recibe la notificación, no puede ser nulo.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>Las notificaciones pueden ser consultadas y marcadas como leídas por el usuario destinatario.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Size(min = 1, max = 100)
    private String titulo;

    @Column(nullable = false)
    @Size(min = 1, max = 500)
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private boolean leida = false;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario destinatario;
}
