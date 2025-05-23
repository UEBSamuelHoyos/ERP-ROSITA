import { Component, OnInit } from '@angular/core';
import { VentaService } from 'src/app/service/venta.service';
import { ProductosService } from 'src/app/service/productos.service';
import { Router } from '@angular/router';

interface ProductoVenta {
  idProducto: number;
  cantidad: number;
  precioUnitario?: number; // <-- Agrega este campo opcional
}

interface Venta {
  id?: number;
  clienteId: number;
  clienteNombre?: string;
  descuento?: number;
  total?: number;
  fecha?: string;
  productos: ProductoVenta[];
  descuentoAplicado?: boolean; // <-- Nuevo campo
}

interface ProductoLista {
  id: number;
  nombre: string;
  seleccionado?: boolean;
  cantidadSeleccionada?: number;
}

@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.scss']
})
export class VentaComponent implements OnInit {

  ventas: Venta[] = [];
  clienteId: number = 0;
  listaProductos: ProductoLista[] = [];
  ventaCredito: boolean = false; // Nuevo campo para venta a crédito

  constructor(
    private ventaService: VentaService,
    private productosService: ProductosService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.listarVentas();
    this.cargarProductos();
  }

  listarVentas(): void {
    this.ventaService.getVentas().subscribe(
      data => {
        // Asegura que los datos recibidos son un array y cada venta tiene un array de productos
        this.ventas = (Array.isArray(data) ? data : []).map(venta => ({
          ...venta,
          productos: Array.isArray(venta.productos) ? venta.productos : []
        }));
      },
      error => {
        console.error('Error al obtener las ventas:', error);
      }
    );
  }

  cargarProductos(): void {
    this.productosService.getProductos().subscribe(
      data => {
        this.listaProductos = data.map(p => ({
          id: p.id,
          nombre: p.nombre,
          seleccionado: false,
          cantidadSeleccionada: 1
        }));
      }
    );
  }

  agregarVenta(): void {
    const productosSeleccionados = this.listaProductos
      .filter(p => p.seleccionado && typeof p.cantidadSeleccionada === 'number' && p.cantidadSeleccionada > 0)
      .map(p => ({
        idProducto: p.id,
        cantidad: p.cantidadSeleccionada as number
      }));

    if (!this.clienteId || productosSeleccionados.length === 0) {
      console.error('Debe seleccionar un cliente y al menos un producto');
      return;
    }

    const ventaPayload: any = {
      clienteId: this.clienteId,
      productos: productosSeleccionados
    };

    const esVentaCredito = this.ventaCredito; // Guarda el valor antes de resetear

    if (esVentaCredito) {
      ventaPayload.ventaCredito = true;
    }

    this.ventaService.createVenta(ventaPayload).subscribe(
      data => {
        this.clienteId = 0;
        this.listaProductos.forEach(p => { p.seleccionado = false; p.cantidadSeleccionada = 1; });
        this.ventaCredito = false;
        this.listarVentas();

        if (esVentaCredito) {
          this.router.navigate(['/cuentas']);
        }
      },
      error => {
        if (error && error.error && typeof error.error === 'string') {
          alert('Error al crear la venta: ' + error.error);
        } else {
          console.error('Error al crear la venta:', error);
        }
      }
    );
  }
}
