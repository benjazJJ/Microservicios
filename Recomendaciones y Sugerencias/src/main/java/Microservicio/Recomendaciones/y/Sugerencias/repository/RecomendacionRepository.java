package Microservicio.Recomendaciones.y.Sugerencias.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import Microservicio.Recomendaciones.y.Sugerencias.model.RecomendacionesySugerencias;


public interface RecomendacionRepository extends JpaRepository<RecomendacionesySugerencias,Integer>{

}
