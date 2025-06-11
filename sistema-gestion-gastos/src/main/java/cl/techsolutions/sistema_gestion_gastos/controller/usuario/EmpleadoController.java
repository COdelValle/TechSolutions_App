package cl.techsolutions.sistema_gestion_gastos.controller.usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.model.usuarios.Empleado;
import cl.techsolutions.sistema_gestion_gastos.service.usuario.EmpleadoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/beta-1.0/usuarios/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    // Método para registrar un nuevo empleado
    @PostMapping("/registrar")
    public Empleado registrar_empleado(@RequestBody Empleado emp) {
        return empleadoService.save_empleado(emp);
    }
    
    // Método para autenticar un empleado
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Empleado emp){
        Optional<Empleado> empleado = empleadoService.authenticar_empleado(emp.getEmail(), emp.getContraseña());
        Map<String, String> respuesta = new HashMap<>();
        if (empleado.isPresent()) {
            respuesta.put("result", "OK");
            respuesta.put("email", empleado.get().getEmail());
        }else{
            respuesta.put("result", "ERROR");
        }
        return respuesta;
    }
}
