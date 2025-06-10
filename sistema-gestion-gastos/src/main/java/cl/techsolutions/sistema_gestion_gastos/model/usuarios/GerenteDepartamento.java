package cl.techsolutions.sistema_gestion_gastos.model.usuarios;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>GerenteDepartamento</b> es una clase que representa al gerente responsable de un {@link Departamento} dentro del sistema de gestión de gastos.
 * <p>
 * Esta clase extiende la clase {@link Usuario} y agrega una relación directa con la entidad {@link Departamento}.
 * </p>
 * 
 * <h3>Atributos:</h3>
 * <ul>
 *   <li><b>departamento</b>: El {@link Departamento} al que está asignado el gerente, no puede ser nulo.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * <ul>
 *   <li>El gerente de departamento puede aprobar o rechazar solicitudes de gasto realizadas por empleados de su departamento.</li>
 * </ul>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GerenteDepartamento extends Usuario{
    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;
}
