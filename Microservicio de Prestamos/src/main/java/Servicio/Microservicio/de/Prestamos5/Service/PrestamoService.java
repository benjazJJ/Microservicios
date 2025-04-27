package Servicio.Microservicio.de.Prestamos5.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.Microservicio.de.Prestamos5.Model.Prestamo;
import Servicio.Microservicio.de.Prestamos5.Repository.PrestamoRepository;

@Service
public class PrestamoService {
    @Autowired
    PrestamoRepository prestamoRepository;

    //Funcion para obtener los prestamos
    public List<Prestamo> getPrestamo(){
        return prestamoRepository.buscarPrestamos();
    }

    //Funcion para buscar un prestamo en especifico
    public Prestamo getPrestamoId(int id){
        return prestamoRepository.buscarPrestamoPorId(id);
    }

    //Funcion para guardar un prestamo
    public String savePrestamo(Prestamo pres){
        prestamoRepository.guardarPrestamo(pres);
        return "Prestamo Guardado";
    }

    //Funcion para modificar un prestamo
    public List<Prestamo> updatePrestamo(Prestamo pres){
        prestamoRepository.modificarPrestamo(pres);
        return prestamoRepository.buscarPrestamos();
    }

    //Funcion para eliminar un prestamo 
    public String deletePrestamo(int id){
        prestamoRepository.eliminarPrestamo(id);
        return "Prestamo Eliminado";
    }


}
