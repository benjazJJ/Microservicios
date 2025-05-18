package com.ayuda.service;

import com.ayuda.model.SolicitudAyuda;
import com.ayuda.repository.SolicitudAyudaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudAyudaService {

    @Autowired
    private SolicitudAyudaRepository repository;

    public SolicitudAyuda crear(SolicitudAyuda solicitud) {
        solicitud.setFechaEnvio(LocalDateTime.now());
        return repository.save(solicitud);
    }

    public List<SolicitudAyuda> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<SolicitudAyuda> obtenerPorId(int id) {
        return repository.findById(id);
    }

    public void eliminarPorId(int id) {
        repository.deleteById(id);
    }
}
