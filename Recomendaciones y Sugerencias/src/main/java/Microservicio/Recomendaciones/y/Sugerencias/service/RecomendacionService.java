package Microservicio.Recomendaciones.y.Sugerencias.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Microservicio.Recomendaciones.y.Sugerencias.model.RecomendacionesySugerencias;
import Microservicio.Recomendaciones.y.Sugerencias.repository.RecomendacionRepository;

@Service
public class RecomendacionService {

    @Autowired
    private RecomendacionRepository repository;

    public RecomendacionesySugerencias crear(RecomendacionesySugerencias sugerencia) {
        sugerencia.setFechaEnvio(new Date(System.currentTimeMillis()));
        return repository.save(sugerencia);
    }

    public List<RecomendacionesySugerencias> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<RecomendacionesySugerencias> obtenerPorId(int id) {
        return repository.findById(id);
    }

    public void eliminarPorId(int id) {
        repository.deleteById(id);
    }

}
