package com.example.Microservicio.logistica.y.stock.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Microservicio.logistica.y.stock.Model.Producto;
import com.example.Microservicio.logistica.y.stock.Service.ProductoService;

@RestController
@RequestMapping("api/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarTodos(){
        return productoService.obtenerTodos();
    }

    @PostMapping
    public Producto guardar (@RequestBody Producto producto){
        return productoService.guardar(producto);
    }

    @GetMapping("/{id}")
    public Producto buscarPorId(@PathVariable Long id){
        return productoService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto){
        return productoService.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        productoService.eliminar(id);
    }
}
