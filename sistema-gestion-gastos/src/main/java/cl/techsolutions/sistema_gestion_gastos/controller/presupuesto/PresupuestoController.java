package cl.techsolutions.sistema_gestion_gastos.controller.presupuesto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;
import cl.techsolutions.sistema_gestion_gastos.model.presupuesto.Presupuesto;
import cl.techsolutions.sistema_gestion_gastos.service.departamento.DepartamentoService;
import cl.techsolutions.sistema_gestion_gastos.service.presupuesto.PresupuestoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/beta-1.0/departamentos/id/{id_departamento}/presupuestos")
public class PresupuestoController {
    @Autowired
    private PresupuestoService presupuestoService;

    @Autowired
    private DepartamentoService departamentoService;

    // Método para obtener todos los presupuestos de un departamento
    @GetMapping
    public List<Presupuesto> obtener_presupuestos(@PathVariable int id_departamento) {
        Departamento departamento = departamentoService.get_departamento_by_id(id_departamento).get();
        return departamento.getPresupuestos();
    }
    
    // Método para agregar un nuevo presupuesto a un departamento
    @PostMapping
    public Presupuesto agregar_presupuesto(@RequestBody Presupuesto presupuesto, @PathVariable int id_departamento) {
        Departamento departamento = departamentoService.get_departamento_by_id(id_departamento).get();
        presupuesto.setDepartamento(departamento);
        return presupuestoService.save_presupuesto(presupuesto);
    }
    
}
