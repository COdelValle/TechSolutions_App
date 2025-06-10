package cl.techsolutions.sistema_gestion_gastos.repository;

import cl.techsolutions.sistema_gestion_gastos.model.Notificacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByDestinatarioId(int usuarioId);
}
