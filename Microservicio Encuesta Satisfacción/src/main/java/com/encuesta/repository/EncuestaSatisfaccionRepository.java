package com.encuesta.repository;

import com.encuesta.model.EncuestaSatisfaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncuestaSatisfaccionRepository extends JpaRepository<EncuestaSatisfaccion, Integer> {
}
