package cl.techsolutions.sistema_gestion_gastos.controller.presupuesto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.model.presupuesto.TipoPresupuesto;
import cl.techsolutions.sistema_gestion_gastos.service.presupuesto.TipoPresupuestoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/beta-1.0/tipo-presupuesto")
public class TipoPresupuestoController {
    @Autowired
    private TipoPresupuestoService tipoPresupuestoService;

    @PostMapping
    public TipoPresupuesto agregar_tipo_presupuesto(@RequestBody TipoPresupuesto tipo) {
        return tipoPresupuestoService.save_tipo_presupuesto(tipo);
    }
}
