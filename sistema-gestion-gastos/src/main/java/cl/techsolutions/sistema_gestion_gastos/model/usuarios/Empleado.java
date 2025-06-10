package cl.techsolutions.sistema_gestion_gastos.model.usuarios;

import java.util.List;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;
import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Gasto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Empleado</b> es una clase que representa a un empleado del sistema de gestión de gastos.
 * <p>
 * Esta clase extiende la clase {@link Usuario} y agrega una relación con la entidad {@link Departamento}.
 * </p>
 * 
 * <h3>Atributos:</h3>
 * <ul>
 * <li><b>departamento</b>: El departamento al que pertenece el empleado, no puede ser nulo.</li>
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
public class Empleado extends Usuario {
    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @OneToMany
    @JoinColumn(name = "empleado_id")
    private List<Gasto> gastos;
}
