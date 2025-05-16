package com.encuesta.service;

import com.encuesta.model.EncuestaSatisfaccion;
import com.encuesta.repository.EncuestaSatisfaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EncuestaSatisfaccionService {

    @Autowired
    private EncuestaSatisfaccionRepository repository;

    public EncuestaSatisfaccion crear(EncuestaSatisfaccion encuesta) {
        encuesta.setFecha(LocalDateTime.now());
        return repository.save(encuesta);
    }

    public List<EncuestaSatisfaccion> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<EncuestaSatisfaccion> obtenerPorId(int id) {
        return repository.findById(id);
    }

    public void eliminarPorId(int id) {
        repository.deleteById(id);
    }
}
