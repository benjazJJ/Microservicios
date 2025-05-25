package com.sugerencias.service;

import com.sugerencias.model.SugerenciaMejora;
import com.sugerencias.repository.SugerenciaMejoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SugerenciaMejoraService {

    @Autowired
    private SugerenciaMejoraRepository repository;

    public SugerenciaMejora crear(SugerenciaMejora sugerencia) {
        sugerencia.setFechaEnvio(new Date(System.currentTimeMillis()));
        return repository.save(sugerencia);
    }

    public List<SugerenciaMejora> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<SugerenciaMejora> obtenerPorId(int id) {
        return repository.findById(id);
    }

    public void eliminarPorId(int id) {
        repository.deleteById(id);
    }
}
