package com.example.Microservicio.Multas.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Microservicio.Multas.Model.Multa;
import com.example.Microservicio.Multas.Repository.MultaRepository;

@Service
public class MultaService {
    @Autowired
    private MultaRepository multaRepository;

    public List<Multa> obtenerTodasLasMultas() {
        return multaRepository.findAll();
    }

    public Optional<Multa> obtenerMultaPorId(Long id) {
        return multaRepository.findById(id);
    }

    public Multa crearMulta(Multa multa) {
        return multaRepository.save(multa);
    }

    public Optional<Multa> actualizarMulta(Long id, Multa multaActualizada) {
        return multaRepository.findById(id).map(multa -> {
            multa.setRunUsuario(multaActualizada.getRunUsuario());
            multa.setFechaInicio(multaActualizada.getFechaInicio());
            multa.setFechaFin(multaActualizada.getFechaFin());
            multa.setMotivo(multaActualizada.getMotivo());
            return multaRepository.save(multa);
        });
    }

    public boolean eliminarMulta(Long id) {
        if (multaRepository.existsById(id)) {
            multaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //asignar multa automática según días de atraso
    public Multa asignarMultaAutomatica(String runUsuario, LocalDate fechaEsperada, LocalDate fechaReal) {
        long diasRetraso = ChronoUnit.DAYS.between(fechaEsperada, fechaReal);
        if (diasRetraso <= 0) {
            throw new IllegalArgumentException("No hay atraso para aplicar una multa");
        }

        String motivo;
        int diasSancion;

        if (diasRetraso <= 3) {
            motivo = "Retraso leve: suspensión de 3 días";
            diasSancion = 3;
        } else if (diasRetraso <= 7) {
            motivo = "Retraso moderado: suspensión de 7 días";
            diasSancion = 7;
        } else {
            motivo = "Retraso grave: suspensión de 15 días";
            diasSancion = 15;
        }

        Multa multa = new Multa();
        multa.setRunUsuario(runUsuario);
        multa.setFechaInicio(LocalDate.now());
        multa.setFechaFin(LocalDate.now().plusDays(diasSancion));
        multa.setMotivo(motivo);

        return multaRepository.save(multa);
    }
}
