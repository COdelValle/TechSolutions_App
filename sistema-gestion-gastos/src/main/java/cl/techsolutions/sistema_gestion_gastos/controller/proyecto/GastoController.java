package cl.techsolutions.sistema_gestion_gastos.controller.proyecto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;
import cl.techsolutions.sistema_gestion_gastos.model.presupuesto.Presupuesto;
import cl.techsolutions.sistema_gestion_gastos.model.proyecto.EstadoGasto;
import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Gasto;
import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Proyecto;
import cl.techsolutions.sistema_gestion_gastos.service.presupuesto.PresupuestoService;
import cl.techsolutions.sistema_gestion_gastos.service.proyecto.GastoService;
import cl.techsolutions.sistema_gestion_gastos.service.proyecto.ProyectoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/beta-1.0/departamentos/id/{id_departamento}/gastos")
public class GastoController {
    @Autowired
    private GastoService gastoService;

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private PresupuestoService presupuestoService;

    // Método para obtener todos los gastos de un proyecto
    @GetMapping
    public List<Gasto> obtenerGastosDepartamento(@PathVariable int id_departamento) {
        // Obtener todos los proyectos del departamento
        List<Proyecto> proyectos = proyectoService.get_proyectos_by_departamento(id_departamento);

        // Recolectar todos los gastos de todos los proyectos
        List<Gasto> todosLosGastos = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            todosLosGastos.addAll(proyecto.getGastos());
        }

        return todosLosGastos;
    }

    //Método para obtener un gasto
    @GetMapping("/{id_gasto}")
    public Gasto obtener_gasto(@PathVariable int id_gasto) {
        return gastoService.get_gasto_by_id(id_gasto);
    }
    

    // Método para agregar un nuevo gasto a un proyecto
    @PostMapping
    public Gasto agregar_gasto(@RequestBody Gasto gasto, @PathVariable int id_departamento) {
        int id_proyecto = gasto.getProyecto().getId();
        Proyecto proyecto = proyectoService.get_proyecto_by_id(id_proyecto).get();
        gasto.setProyecto(proyecto);
        return gastoService.save_gasto(gasto);
    }

    // Método para actualizar el estado de un gasto
    @PutMapping("/{id_gasto}/estado/{estado}")
    public String actualizar_estado_gasto(@PathVariable int id_departamento,@PathVariable int id_gasto, @PathVariable EstadoGasto estado) {
        Gasto gasto = gastoService.get_gasto_by_id(id_gasto);
        if(gasto.getEstado() == EstadoGasto.RECHAZADO){
            return "El rechazo de un gasto no puede ser revertido";
        }else if(gasto.getEstado() == EstadoGasto.APROBADO && estado == EstadoGasto.PENDIENTE){
            return "El gasto una vez aprovado ya no puede revertirse, sin embargo, puede rechazarce";
        }
        gasto.setEstado(estado);
        gastoService.update_gasto(gasto);
        Departamento departamento = gasto.getProyecto().getDepartamento();
        List<Proyecto> proyectos = departamento.getProyectos();
        Presupuesto presupuesto = departamento.getPresupuesto();
        double monto_utilizado = 0;
        for (Proyecto proyecto : proyectos) {
            for (Gasto g : proyecto.getGastos()) {
                if (g.getEstado()==EstadoGasto.APROBADO) {
                    monto_utilizado += g.getMonto();
                }
            }   
        }
        presupuesto.setMonto_utilizado(monto_utilizado);
        presupuestoService.update_presupuesto(presupuesto);
        return "El gasto con ID " + id_gasto + " ha sido actualizado al estado: " + gasto.getEstado();
    }
}
