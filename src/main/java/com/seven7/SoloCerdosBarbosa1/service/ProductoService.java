package com.seven7.SoloCerdosBarbosa1.service;

import com.seven7.SoloCerdosBarbosa1.dto.ProductoDTO;
import com.seven7.SoloCerdosBarbosa1.exception.NotFoundException;
import com.seven7.SoloCerdosBarbosa1.mapper.Mapper;
import com.seven7.SoloCerdosBarbosa1.model.Producto;
import com.seven7.SoloCerdosBarbosa1.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> traerProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {
        Producto prod = Producto.builder()
                .nombre(productoDto.getNombre())
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .cantidad(productoDto.getCantidad())
                .build();
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {


        //busccar si exite el producto
        Producto prod = repo.findById(id)
        .orElseThrow(() -> new NotFoundException("producto no encontrado"));

        prod.setNombre(productoDto.getNombre());
        prod.setCategoria(productoDto.getCategoria());
        prod.setCantidad(productoDto.getCantidad());
        prod.setPrecio(productoDto.getPrecio());

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void eliminarProducto(Long id) {

        if(!repo.existsById(id)){
            throw new NotFoundException("producto no encontrado para eliminar");
        }

        repo.deleteById(id);

    }
}
