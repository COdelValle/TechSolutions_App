package cl.techsolutions.sistema_gestion_gastos.controller.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Empleado;
import cl.techsolutions.sistema_gestion_gastos.service.usuario.EmpleadoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/beta-1.0/usuarios/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    // Método para obtener a todos los Empleados que pertenecen al departamento:
    @GetMapping("/departamento/{id_departamento}")
    public List<Empleado> get_empleado_by_departamento(@PathVariable int id_departamento) {
        return empleadoService.get_empleados_by_departamento(id_departamento);
    }

    // Método para registrar un nuevo empleado
    @PostMapping("/registrar")
    public Empleado registrar_empleado(@RequestBody Empleado emp) {
        return empleadoService.save_empleado(emp);
    }
}
