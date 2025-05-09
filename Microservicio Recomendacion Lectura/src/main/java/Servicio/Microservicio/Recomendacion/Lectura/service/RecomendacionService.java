package Servicio.Microservicio.Recomendacion.Lectura.service;

import Servicio.Microservicio.Recomendacion.Lectura.model.Recomendacion;
import Servicio.Microservicio.Recomendacion.Lectura.repository.RecomendacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecomendacionService {

    @Autowired
    private RecomendacionRepository repo;

    public Recomendacion guardar(Recomendacion r) {
        return repo.save(r);
    }

    public List<Recomendacion> obtenerTodas() {
        return repo.findAll();
    }

    public Optional<Recomendacion> obtenerPorId(int id) {
        return repo.findById(id);
    }

    public List<Recomendacion> obtenerPorCategoria(String categoria) {
        return repo.findByCategoria(categoria);
    }

    public void eliminar(int id) {
        repo.deleteById(id);
    }
}
