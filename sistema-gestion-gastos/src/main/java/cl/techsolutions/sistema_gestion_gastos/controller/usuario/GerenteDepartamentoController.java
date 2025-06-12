package cl.techsolutions.sistema_gestion_gastos.controller.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.techsolutions.sistema_gestion_gastos.model.usuarios.GerenteDepartamento;
import cl.techsolutions.sistema_gestion_gastos.service.usuario.GerenteDepartamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/beta-1.0/usuarios/g-departamento")
public class GerenteDepartamentoController {
    @Autowired
    private GerenteDepartamentoService gerenteDepartamentoService;

    // Método para obtener a todos los Gerentes de departamento que pertenecen al departamento:
    @GetMapping("/departamento/{id_departamento}")
    public List<GerenteDepartamento> get_gerente_by_departamento(@PathVariable int id_departamento) {
        return gerenteDepartamentoService.get_gerentes_by_departamento(id_departamento);
    }

    // Método para registrar un nuevo empleado
    @PostMapping("/registrar")
    public GerenteDepartamento registrar_gerente(@RequestBody GerenteDepartamento gerd) {
        return gerenteDepartamentoService.save_gerente(gerd);
    }
}
