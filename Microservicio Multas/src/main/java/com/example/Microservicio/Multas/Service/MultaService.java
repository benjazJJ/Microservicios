package com.example.Microservicio.Multas.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Microservicio.Multas.Model.Multa;
import com.example.Microservicio.Multas.Repository.MultaRepository;
import com.example.Microservicio.Multas.WebClient.MultasClient;
import com.example.Microservicio.Multas.WebClient.MultasMult;

@Service
public class MultaService {
    @Autowired
    private MultaRepository multaRepository;

    @Autowired
    private MultasMult multasMult;

    @Autowired
    private MultasClient multasClient;

    public List<Multa> obtenerTodasLasMultas() {
        return multaRepository.findAll();
    }

    public Optional<Multa> obtenerMultaPorId(Long id) {
        return multaRepository.findById(id);
    }

    public Multa crearMulta(Multa multa) {
        // Validar que el runUsuario exista en Cuentas
        if (!multasClient.validarUsuarioPorRut(multa.getRunUsuario())) {
            throw new RuntimeException(
                    "No se puede asignar la multa porque el usuario con RUT " + multa.getRunUsuario() + " no existe.");
        }

        // Verificar si la devolución existe (por idDevolucion)
        Map<String, Object> mult = multasMult.getDevolucionById(multa.getIdDevolucion());
        if (mult == null || mult.isEmpty()) {
            throw new RuntimeException("Devolución no encontrada. No se puede asignar la multa.");
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
