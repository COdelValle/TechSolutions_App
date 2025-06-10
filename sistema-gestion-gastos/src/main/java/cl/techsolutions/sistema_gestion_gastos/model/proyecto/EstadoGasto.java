package cl.techsolutions.sistema_gestion_gastos.model.proyecto;

/**
 * <b>EstadoGasto</b> es un enumerador que representa los posibles estados de una solicitud de gasto en el sistema de gestión de gastos.
 * <p>
 * Los estados definen el ciclo de vida de una solicitud de gasto, desde su creación hasta su aprobación o rechazo.
 * </p>
 * 
 * <h3>Valores:</h3>
 * <ul>
 *   <li><b>PENDIENTE</b>: El gasto ha sido solicitado y está a la espera de aprobación.</li>
 *   <li><b>APROBADO</b>: El gasto ha sido revisado y aprobado por el gerente de departamento.</li>
 *   <li><b>RECHAZADO</b>: El gasto ha sido revisado y rechazado por el gerente de departamento.</li>
 * </ul>
 * 
 * <h3>Notas:</h3>
 * 
 * @author Catalina Ormeño
 * @since 1.0-beta
 */
public enum EstadoGasto {
    PENDIENTE,
    APROBADO,
    RECHAZADO
}
