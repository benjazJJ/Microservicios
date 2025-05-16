package com.example.Microservicio.logistica.y.stock.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Microservicio.logistica.y.stock.Model.Producto;
import com.example.Microservicio.logistica.y.stock.Repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    //Obtener todos los productos
    public List<Producto> obtenerTodos(){
        return productoRepository.findAll();
    }
    
    //Guardar nuevo producto
    public Producto guardar(Producto producto){
        if(producto.getNombre() == null || producto.getNombre().isBlank()){
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        if(productoRepository.findByNombreIgnoreCase(producto.getNombre()).isPresent()){
            throw new IllegalArgumentException("Ya existe un producto con el nombre: " + producto.getNombre());
        }
        return productoRepository.save(producto);
    }

    //Buscar producto por ID
    public Producto obtenerPorId(Long id){
        return productoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("No se encontro el producto con ID: " + id));
    }

    //Actualizar producto existente
    public Producto actualizar(Long id, Producto actualizado){
        Producto existente = obtenerPorId(id);

        existente.setNombre(actualizado.getNombre());
        existente.setDescripcion(actualizado.getDescripcion());
        existente.setCategoria(actualizado.getCategoria());
        existente.setCantidadDisponible(actualizado.getCantidadDisponible());

        return productoRepository.save(existente);
    }

    //Eliminar producto por ID
    public void eliminar(Long id){
       if(!productoRepository.existsById(id)){ 
        throw new IllegalArgumentException("No existe producto con id: " + id);
        }
        productoRepository.deleteById(id);
    }

}
