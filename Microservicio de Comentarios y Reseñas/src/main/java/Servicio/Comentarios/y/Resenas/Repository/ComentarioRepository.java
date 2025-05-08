package Servicio.Comentarios.y.Resenas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Servicio.Comentarios.y.Resenas.Model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {
    @Query("SELECT c FROM Comentario c WHERE c.idComentario = :idComentario")
    Optional<Comentario> buscarPorIdComentario(@Param("idComentario") int idComentario);
}
     
