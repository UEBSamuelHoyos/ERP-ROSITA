import { Component, OnInit } from '@angular/core';
import { Venta } from 'src/app/clases/venta';
import { VentaService } from 'src/app/service/venta.service';
import { Cliente } from 'src/app/clases/cliente';
import { Productos } from 'src/app/clases/productos';

@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.scss']
})
export class VentaComponent implements OnInit {

  ventas: Venta[] = [];
  nuevaVenta: Venta = new Venta(0, new Cliente(0, '', '', '', ''), new Productos(0, '', '', 0, 0, 0), 0, 0, 0, 0, new Date());

  constructor(private ventaService: VentaService) {}

  ngOnInit(): void {
    this.listarVentas();
  }

  listarVentas(): void {
    this.ventaService.getVentas().subscribe(
      data => {
        this.ventas = data;
        console.log('Ventas:', this.ventas);
      },
      error => {
        console.error('Error al obtener las ventas:', error);
      }
    );
  }

  agregarVenta(): void {
    this.ventaService.createVenta(this.nuevaVenta).subscribe(
      data => {
        console.log('Venta creada:', data);
        this.listarVentas(); // Actualizar la lista de ventas
      },
      error => {
        console.error('Error al crear la venta:', error);
      }
    );
  }
}
