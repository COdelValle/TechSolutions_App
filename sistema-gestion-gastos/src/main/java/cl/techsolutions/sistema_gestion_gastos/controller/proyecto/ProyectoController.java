package cl.techsolutions.sistema_gestion_gastos.controller.proyecto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;
import cl.techsolutions.sistema_gestion_gastos.model.proyecto.Proyecto;
import cl.techsolutions.sistema_gestion_gastos.service.departamento.DepartamentoService;
import cl.techsolutions.sistema_gestion_gastos.service.proyecto.ProyectoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/beta-1.0/departamentos/id/{id_departamento}/proyectos")
public class ProyectoController {
    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private ProyectoService proyectoService;

    // Método para obtener todos los proyectos de un departamento
    @GetMapping
    public List<Proyecto> obtener_proyectos(@PathVariable int id_departamento){
        Departamento departamento = departamentoService.get_departamento_by_id(id_departamento).get();
        return departamento.getProyectos();
    }

    // Método para obtener un proyecto por su id
    @GetMapping("/id/{id_proyecto}")
    public Proyecto obtener_proyecto_id(@PathVariable int id_proyecto) {
        return proyectoService.get_proyecto_by_id(id_proyecto).get();
    }
    
    // Método para obtener un proyecto por su nombre
    @GetMapping("/nombre/{nombre_proyecto}")
    public Proyecto obtener_proyecto_nombre(@PathVariable String nombre_proyecto) {
        return proyectoService.get_proyectos_by_nombre(nombre_proyecto).get();
    }
    
    // Método para agregar un nuevo proyecto a un departamento
    @PostMapping
    public Proyecto agregar_proyecto(@PathVariable int id_departamento, @RequestBody Proyecto proyecto) {
        Departamento departamento = departamentoService.get_departamento_by_id(id_departamento).get();
        proyecto.setDepartamento(departamento);
        return proyectoService.save_proyecto(proyecto);
    }
}