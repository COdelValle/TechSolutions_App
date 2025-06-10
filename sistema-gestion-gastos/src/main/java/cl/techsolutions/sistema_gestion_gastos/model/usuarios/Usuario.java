package cl.techsolutions.sistema_gestion_gastos.model.usuarios;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Usuario</b> es una clase abstracta que representa un usuario del sistema de gestión de gastos.
 * <p>
 * Esta clase puede ser extendida por otras clases que representen tipos específicos de usuarios.
 * </p>
 * 
 * <h3>Atributos:</h3>
 * <ul>
 * <li><b>id</b>: Identificador único del usuario.</li>
 * <li><b>nombre</b>: Nombre del usuario, no puede estar vacío.</li>
 * <li><b>email</b>: Correo electrónico del usuario, debe tener un formato válido y ser único.</li>
 * <li><b>contraseña</b>: Contraseña del usuario, no puede estar vacía y debe tener al menos 6 caracteres. Puede ser generada automáticamente.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    protected String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    @Column(nullable = false, unique = true)
    protected String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Column(nullable = false)
    protected String contraseña;
}
