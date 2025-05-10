package com.example.Microservicio.logistica.y.stock.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Microservicio.logistica.y.stock.Model.MovimientoStock;
import com.example.Microservicio.logistica.y.stock.Service.MovimientoStockService;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoStockController {
    @Autowired
    private MovimientoStockService movimientoService;

    @GetMapping
    public List<MovimientoStock> listarTodos() {
        return movimientoService.obtenerTodos();
    }

    @GetMapping("/producto/{id}")
    public List<MovimientoStock> movimientosPorProducto(@PathVariable Long id) {
        return movimientoService.obtenerPorProducto(id);
    }

    @PostMapping
    public MovimientoStock registrar(@RequestBody MovimientoStock movimiento) {
        return movimientoService.registrar(movimiento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        movimientoService.eliminarMovimiento(id);
    }
}
