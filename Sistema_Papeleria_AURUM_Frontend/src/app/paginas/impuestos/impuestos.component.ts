import { Component, OnInit } from '@angular/core';
import { ImpuestosService, Impuesto } from 'src/app/service/impuestos.service';

@Component({
  selector: 'app-impuestos',
  templateUrl: './impuestos.component.html',
  styleUrls: ['./impuestos.component.scss']
})
export class ImpuestosComponent implements OnInit {

  impuestos: Impuesto[] = [];
  mostrarReporte = false;

  // Para el reporte de ingresos y egresos
  mostrarIngresosEgresos = false;
  totalVentas = 0;
  totalCompras = 0;
  ingresosNetos = 0;

  constructor(private impuestosService: ImpuestosService) {}

  ngOnInit(): void {
    this.listarImpuestos();
    this.calcularIngresosEgresos();
  }

  listarImpuestos() {
    this.impuestosService.getImpuestos().subscribe(data => {
      this.impuestos = data;
    });
  }

  getTotalImpuestos(): number {
    return this.impuestos.reduce((acc, imp) => acc + (imp.totalImpuesto || 0), 0);
  }

  calcularIngresosEgresos() {
    // Simulación: reemplaza por llamadas a tus servicios reales si existen
    // Por ejemplo, podrías inyectar un servicio de ventas y otro de compras
    this.totalVentas = 65334.6; // ejemplo, reemplaza por tu dato real
    this.totalCompras = 70272702; // ejemplo, reemplaza por tu dato real
    this.ingresosNetos = this.totalVentas - this.totalCompras;
  }
}
