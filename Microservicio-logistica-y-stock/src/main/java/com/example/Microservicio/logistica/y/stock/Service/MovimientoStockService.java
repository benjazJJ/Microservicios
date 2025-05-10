package com.example.Microservicio.logistica.y.stock.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Microservicio.logistica.y.stock.Model.MovimientoStock;
import com.example.Microservicio.logistica.y.stock.Model.Producto;
import com.example.Microservicio.logistica.y.stock.Repository.MovimientoStrockRepository;
import com.example.Microservicio.logistica.y.stock.Repository.ProductoRepository;

@Service
public class MovimientoStockService {
    @Autowired
    private MovimientoStrockRepository movimientoStockRepository;

    @Autowired
    private ProductoRepository productoRepository;

    //Obtener todos los moviemientos de stock
    public List<MovimientoStock> obtenerTodos(){
        return movimientoStockRepository.findAll();
    }

    //Obtener movimiento por producto
    public List<MovimientoStock> obtenerPorProducto(Long id){
        return movimientoStockRepository.findByProductoId(id);
    }

    //Registrar un nuevo movimiento (Entrada o salida)
    public MovimientoStock registrar(MovimientoStock movimientoStock){
        if(movimientoStock.getCantidad() <= 0){
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        
        if(!movimientoStock.getTipoMovimiento().equalsIgnoreCase("Entrada") &&
            !movimientoStock.getTipoMovimiento().equalsIgnoreCase("Salida")){
            throw new IllegalArgumentException("El tipo de movimiento debe ser 'Entrada' o 'Salida'");
        }

        Producto producto = productoRepository.findById(movimientoStock.getProducto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id: " + movimientoStock.getProducto().getId()));

        int nuevaCantidad = producto.getCantidadDisponible();

        if(movimientoStock.getTipoMovimiento().equalsIgnoreCase("Entrada")){
            nuevaCantidad += movimientoStock.getCantidad();
        } else { //saldida
            if(producto.getCantidadDisponible() < movimientoStock.getCantidad()){
                throw new IllegalArgumentException("No hay suficiente stock disponible para realizar la salida");
            }
            nuevaCantidad -= movimientoStock.getCantidad();
        }
        producto.setCantidadDisponible(nuevaCantidad);
        productoRepository.save(producto);

        //Asignamos el producto actualizado y la fecha si no viene
        movimientoStock.setProducto(producto);
        if(movimientoStock.getFechaMovimiento() == null){
            movimientoStock.setFechaMovimiento(String.valueOf(System.currentTimeMillis()));
        }
        return movimientoStockRepository.save(movimientoStock);
    }

    //Eliminar movimiento por ID
    public void eliminarMovimiento(Long id){
        if(!movimientoStockRepository.existsById(id)){
            throw new IllegalArgumentException("No existe movimiento con id: " + id);
        }
        movimientoStockRepository.deleteById(id);
    }
}
