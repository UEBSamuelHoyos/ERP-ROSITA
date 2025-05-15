import { Component, OnInit } from '@angular/core';
import { FacturasService, Factura } from 'src/app/service/facturas.service';

@Component({
  selector: 'app-facturas',
  templateUrl: './facturas.component.html',
  styleUrls: ['./facturas.component.scss']
})
export class FacturasComponent implements OnInit {
  facturas: Factura[] = [];
  loading = false;

  constructor(private facturasService: FacturasService) {}

  ngOnInit(): void {
    this.cargarFacturas();
  }

  cargarFacturas() {
    this.loading = true;
    this.facturasService.getFacturas().subscribe({
      next: data => {
        this.facturas = data;
        this.loading = false;
      },
      error: err => {
        this.loading = false;
        alert('Error al cargar facturas');
      }
    });
  }
}
