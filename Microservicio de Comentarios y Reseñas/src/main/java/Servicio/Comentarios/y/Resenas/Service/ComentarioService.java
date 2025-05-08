package Servicio.Comentarios.y.Resenas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.Comentarios.y.Resenas.Model.Comentario;
import Servicio.Comentarios.y.Resenas.Repository.ComentarioRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    //Obtener todos los Comentarios
    public List<Comentario> getComentarios(){
        return comentarioRepository.findAll();
    }

    //Guardar un nuevo Comentario
    public Comentario saveComentario(Comentario comentario){
        return comentarioRepository.save(comentario);
    }

    //Buscar Comentario por Id
    public Comentario getComentarioPorID(int idComentario){
        return comentarioRepository.findById(idComentario).get();
    }

    //Eliminar Comentario
    public void deleteComentario(int idComentario){
        comentarioRepository.deleteById(idComentario);
    }

}
