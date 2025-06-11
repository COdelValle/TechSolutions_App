package cl.techsolutions.sistema_gestion_gastos.controller.departamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;
import cl.techsolutions.sistema_gestion_gastos.service.departamento.DepartamentoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/beta-1.0/departamentos")
public class DepartamentoController {
    @Autowired
    private DepartamentoService departamentoService;

    // Método para obtener todos los departamentos
    @GetMapping
    public List<Departamento> obtener_departamentos(){
        return departamentoService.get_departamentos();
    }

    // Método para obtener un departamento por su ID
    @GetMapping("/id/{id}")
    public Departamento buscar_departamento_id(@PathVariable int id){
        return departamentoService.get_departamento_by_id(id).get();
    }

    // Método para obtener un departamento por su nombre
    @GetMapping("/nombre/{nombre_departamento}")
    public Departamento buscar_departamento_nombre(@PathVariable String nombre){
        return departamentoService.get_departamento_by_nombre(nombre).get();
    }

    // Método para agregar un nuevo departamento
    @PostMapping
    public Departamento agregar_departamento(@RequestBody Departamento departamento) {
        return departamentoService.save_departamento(departamento);
    }
}
