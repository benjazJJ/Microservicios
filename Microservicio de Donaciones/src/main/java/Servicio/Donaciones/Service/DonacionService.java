package Servicio.Donaciones.Service;

import Servicio.Donaciones.Model.Donaciones;
import Servicio.Donaciones.Repository.DonacionesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DonacionService {

    @Autowired
    private DonacionesRepository donacionesRepository;

    // listar todas las donaciones
    public List<Donaciones> listarDonaciones() {
        return donacionesRepository.findAll();
    }

    //Buscar una donación por su ID
    public Donaciones buscarDonacionesPorId(int id) {
        return donacionesRepository.findById(id).get();
    }

    //Guardar una nueva donación
    public Donaciones guardarDonacion(Donaciones donacion) {
        return donacionesRepository.save(donacion);
    }

    //eliminar una donación por ID
    public void eliminarDonacion(int id) {
        donacionesRepository.deleteById(id);
    }

}

