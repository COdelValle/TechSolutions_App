package cl.techsolutions.sistema_gestion_gastos.controller.proyecto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.model.proyecto.EstadoGasto;
import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Gasto;
import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Proyecto;
import cl.techsolutions.sistema_gestion_gastos.service.proyecto.GastoService;
import cl.techsolutions.sistema_gestion_gastos.service.proyecto.ProyectoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/beta-1.0/departamentos/id/{id_departamento}/proyectos/id/{id_proyecto}/gastos")
public class GastoController {
    @Autowired
    private GastoService gastoService;

    @Autowired
    private ProyectoService proyectoService;

    // Método para obtener todos los gastos de un proyecto
    @GetMapping
    public List<Gasto> obtener_gastos(@PathVariable int id_departamento, @PathVariable int id_proyecto) {
        return proyectoService.get_proyecto_by_id(id_proyecto).get().getGastos();
    }

    // Método para agregar un nuevo gasto a un proyecto
    @PostMapping
    public Gasto agregar_gasto(@RequestBody Gasto gasto, @PathVariable int id_departamento, @PathVariable int id_proyecto) {
        Proyecto proyecto = proyectoService.get_proyecto_by_id(id_proyecto).get();
        gasto.setProyecto(proyecto);
        return gastoService.save_gasto(gasto);
    }

    // Método para actualizar el estado de un gasto
    @PutMapping("/{id}/estado")
    public String actualizar_estado_gasto(@PathVariable int id, @RequestParam EstadoGasto estado) {
        Gasto gasto = gastoService.get_gasto_by_id(id);
        gasto.setEstado(estado);
        return "El gasto con ID " + id + " ha sido actualizado al estado: " + estado;
    }
}
