package cl.techsolutions.sistema_gestion_gastos.service.departamento;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.techsolutions.sistema_gestion_gastos.model.departamento.Departamento;
import cl.techsolutions.sistema_gestion_gastos.repository.departamento.DepartamentoRepository;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    //Método para obtener todos los departamentos:
    public List<Departamento> get_departamentos() {
        return departamentoRepository.findAll();
    }

    //Método para obtener un departamento por su ID:
    public Optional<Departamento> get_departamento_by_id(int id) {
        return departamentoRepository.findById(id);
    }

    //Método para buscar un departamento por su nombre:
    public Optional<Departamento> get_departamento_by_nombre(String nombre) {
        return departamentoRepository.findByNombre(nombre);
    }

    //Método para guardar un departamento:
    public Departamento save_departamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    //Método para eliminar un departamento por su ID:
    public void delete_departamento(int id) {
        departamentoRepository.deleteById(id);
    }
}
