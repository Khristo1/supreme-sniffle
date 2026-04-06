package com.seven7.SoloCerdosBarbosa1.service;

import com.seven7.SoloCerdosBarbosa1.dto.DetalleVentaDTO;
import com.seven7.SoloCerdosBarbosa1.dto.VentaDTO;
import com.seven7.SoloCerdosBarbosa1.exception.NotFoundException;
import com.seven7.SoloCerdosBarbosa1.mapper.Mapper;
import com.seven7.SoloCerdosBarbosa1.model.Cliente;
import com.seven7.SoloCerdosBarbosa1.model.DetalleVenta;
import com.seven7.SoloCerdosBarbosa1.model.Producto;
import com.seven7.SoloCerdosBarbosa1.model.Venta;
import com.seven7.SoloCerdosBarbosa1.repository.ClienteRepository;
import com.seven7.SoloCerdosBarbosa1.repository.ProductoRepository;
import com.seven7.SoloCerdosBarbosa1.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService {

   @Autowired
   private VentaRepository ventaRepo;
   @Autowired
   private ProductoRepository productoRepo;
   @Autowired
   private ClienteRepository clienteRepo;


    @Override
    public List<VentaDTO> traerVentas() {
        List<Venta> ventas = ventaRepo.findAll();
        List<VentaDTO> ventasDto = new ArrayList<>();

        for (Venta v : ventas) {
            try {
                VentaDTO dto = Mapper.toDTO(v);
                ventasDto.add(dto);
            } catch (Exception e) {
                System.err.println("Error al mapear venta " + v.getId() + ": " + e.getMessage());
            }
        }

        return ventasDto;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {
        if (ventaDto == null) throw new RuntimeException("No se puede crear una venta vacia");
        if (ventaDto.getIdCliente() == null) throw new RuntimeException("Debe indicar el cliente");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        // Buscar Cliente
        Cliente cli = clienteRepo.findById(ventaDto.getIdCliente())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        // Crear Venta
        Venta vent = new Venta();
        vent.setFecha(ventaDto.getFecha());
        vent.setEstado(ventaDto.getEstado());
        vent.setCliente(cli);
        vent.setTotal(ventaDto.getTotal());

        // Lista de detalles
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for (DetalleVentaDTO detDTO : ventaDto.getDetalle()) {
            // 🔧 CORRECCIÓN: Buscar producto por NOMBRE (como está en tu código actual)
            Producto p = productoRepo.findByNombre(detDTO.getNombreProd())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detDTO.getNombreProd()));

            // Crear detalle
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProd(p);
            detalleVent.setPrecio(detDTO.getPrecio());
            detalleVent.setCantProd(detDTO.getCantProd());
            detalleVent.setVenta(vent);

            detalles.add(detalleVent);
            totalCalculado = totalCalculado + (detDTO.getPrecio() * detDTO.getCantProd());
        }

        vent.setDetalle(detalles);
        vent.setTotal(totalCalculado); // Asegurar que el total se calcula

        // Guardar
        vent = ventaRepo.save(vent);

        return Mapper.toDTO(vent);
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
        //buscar si la venta existe
        Venta v = ventaRepo.findById(id).orElse(null);
        if(v == null) throw new RuntimeException("venta no encontrada");

        if (ventaDto.getFecha()!=null) {
            v.setFecha(ventaDto.getFecha());
        }
        if (ventaDto.getEstado()!=null) {
            v.setEstado(ventaDto.getEstado());
        }
        if (ventaDto.getTotal()!=null) {
            v.setTotal(ventaDto.getTotal());
        }
        if (ventaDto.getIdCliente()!=null) {
            Cliente cli = clienteRepo.findById(ventaDto.getIdCliente()).orElse(null);
            if (cli == null) throw new NotFoundException("Cliente no encontrado");
            v.setCliente(cli);
        }
        ventaRepo.save(v);

        VentaDTO ventasalida = Mapper.toDTO(v);

        return ventasalida;
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if(v == null) throw new RuntimeException("venta no encontrada");
        ventaRepo.delete(v);

    }
}
