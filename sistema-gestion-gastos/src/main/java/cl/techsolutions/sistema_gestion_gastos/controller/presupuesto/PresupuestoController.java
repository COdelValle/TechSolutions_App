package cl.techsolutions.sistema_gestion_gastos.controller.presupuesto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;
import cl.techsolutions.sistema_gestion_gastos.model.presupuesto.Presupuesto;
import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Gasto;
import cl.techsolutions.sistema_gestion_gastos.service.departamento.DepartamentoService;
import cl.techsolutions.sistema_gestion_gastos.service.presupuesto.PresupuestoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/beta-1.0/departamentos/id/{id_departamento}/presupuestos")
public class PresupuestoController {
    @Autowired
    private PresupuestoService presupuestoService;

    @Autowired
    private DepartamentoService departamentoService;

    // Método para obtener todos los presupuestos de un departamento
    @GetMapping
    public ResponseEntity<Map<String, Object>> getPresupuestoDepartamento(@PathVariable int id_departamento) {
        
        Departamento departamento = departamentoService.get_departamento_by_id(id_departamento).get();
        
        Map<String, Object> response = new HashMap<>();
        response.put("departamento", departamento.getNombre());
        response.put("presupuestoTotal", departamento.getPresupuesto().getMonto_total());
        response.put("presupuestoUtilizado", departamento.getPresupuesto().getMonto_utilizado());
        
        List<Map<String, Object>> proyectos = departamento.getProyectos().stream()
            .map(p -> {
                Map<String, Object> proyectoMap = new HashMap<>();
                proyectoMap.put("nombre", p.getNombre());
                proyectoMap.put("gastoTotal", p.getGastos().stream()
                    .mapToDouble(Gasto::getMonto)
                    .sum());
                return proyectoMap;
            })
            .collect(Collectors.toList());
        
        response.put("proyectos", proyectos);
        
        return ResponseEntity.ok(response);
    }
    
    // Método para agregar un nuevo presupuesto a un departamento
    @PostMapping
    public Presupuesto agregar_presupuesto(@RequestBody Presupuesto presupuesto, @PathVariable int id_departamento) {
        Departamento departamento = departamentoService.get_departamento_by_id(id_departamento).get();
        presupuesto.setDepartamento(departamento);
        departamento.setPresupuesto(presupuesto);
        return presupuestoService.save_presupuesto(presupuesto);
    }

    // Método para obtener un presupuesto por su ID
    @GetMapping("/id/{id_presupuesto}")
    public Presupuesto obtener_presupuesto_por_id(@PathVariable int id_presupuesto) {
        return presupuestoService.get_presupuesto_by_id(id_presupuesto).get();
    }

    // Método para actualizar un presupuesto
    @PutMapping("/{id_presupuesto}")
    public Presupuesto actualizar_presupuesto(@PathVariable int id,@RequestBody Presupuesto presupuesto) {
        return presupuestoService.update_presupuesto(presupuesto);
    }
}
