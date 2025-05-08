package Servicio.Comentarios.y.Resenas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Servicio.Comentarios.y.Resenas.Model.Comentario;
import Servicio.Comentarios.y.Resenas.Service.ComentarioService;

@RestController
@RequestMapping("/api/v1/reseñas")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    //Metodo para obtener todos los comentarios
    @GetMapping
    public ResponseEntity<List<Comentario>> listarComentarios(){
        //Guardar en una lista nuevos elementos
        List<Comentario> comentarios = comentarioService.getComentarios();
        if(comentarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comentarios);
    }

    //Metodo para guardar un Comentario
    @PostMapping
    public ResponseEntity<Comentario> guardarComentario(@RequestBody Comentario comentario){
        //creamos un objeto nuevo
        Comentario com = comentarioService.saveComentario(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(com);
    }

    //Metodo para buscar Comentario por Id
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> buscarComentarioPorId(@PathVariable Integer id){
        try {
            //Verificar si el Comentario existe
            Comentario com = comentarioService.getComentarioPorID(id);
            return ResponseEntity.ok(com);
        } catch (Exception e) {
            // Si no lo encuentra envio codigo not found
            return ResponseEntity.notFound().build();
        }
    }

    //Metodo para eliminar Comentario por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarComentarioPorId(@PathVariable Integer id){
        try {
            //Verificar si el Comentario existe
            Comentario com = comentarioService.getComentarioPorID(id);
            comentarioService.deleteComentario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // No existe el Comentario
            return ResponseEntity.notFound().build();
        }
    }

    //Metodo para actualizar un Comentario por su id
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentarioPorId(@PathVariable Integer id, @RequestBody Comentario com){
        try {
            //Verifico si el Comentario existe
            Comentario comentario2 = comentarioService.getComentarioPorID(id);
            //Si existe el comentario modifico uno de sus valores
            comentario2.setIdComentario(id);
            comentario2.setComentario((com.getComentario()));
            comentario2.setNombreLibro(com.getNombreLibro());
            comentario2.setFechaComentario(com.getFechaComentario());

            //Actualizar registro
            comentarioService.saveComentario(comentario2);
            return ResponseEntity.ok(comentario2);

        } catch (Exception e) {
            // Si no encuentra el comentario
            return ResponseEntity.notFound().build();
        }
    }

}
