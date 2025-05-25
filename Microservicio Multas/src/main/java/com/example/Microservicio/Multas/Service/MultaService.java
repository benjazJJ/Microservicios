package com.example.Microservicio.Multas.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Microservicio.Multas.Model.Multa;
import com.example.Microservicio.Multas.Repository.MultaRepository;
import com.example.Microservicio.Multas.WebClient.MultasMult;

@Service
public class MultaService {
    @Autowired
    private MultaRepository multaRepository;

    @Autowired
    private MultasMult multasMult;

    public List<Multa> obtenerTodasLasMultas() {
        return multaRepository.findAll();
    }

    public Optional<Multa> obtenerMultaPorId(Long id) {
        return multaRepository.findById(id);
    }

    public Multa crearMulta(Multa multa) {
        // verificar si el prestamo existe consultando al microservicio Prestamo
        Map<String, Object> mult = multasMult.getDevolucionById(multa.getIdDevolucion());
        // verifico si me trajo el Prestamo o no
        if (mult == null || mult.isEmpty()) {
            throw new RuntimeException("Prestamo no encontrado. No se puede agregar la devolucion");
        }
        return multaRepository.save(multa);
    }

    public Optional<Multa> actualizarMulta(Long id, Multa multaActualizada) {
        return multaRepository.findById(id).map(multa -> {
            multa.setRunUsuario(multaActualizada.getRunUsuario());
            multa.setTipoSancion(multaActualizada.getTipoSancion());
            multa.setSancion(multaActualizada.getSancion());
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

    


    
}
