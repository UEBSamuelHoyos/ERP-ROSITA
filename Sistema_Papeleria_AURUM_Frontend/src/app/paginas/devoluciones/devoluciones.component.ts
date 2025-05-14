import { Component, OnInit } from '@angular/core';
import { Devoluciones } from 'src/app/clases/devoluciones';
import { DevolucionesService } from 'src/app/service/devoluciones.service';

@Component({
  selector: 'app-devoluciones',
  templateUrl: './devoluciones.component.html',
  styleUrls: ['./devoluciones.component.scss']
})
export class DevolucionesComponent implements OnInit {

  id: number = 1;
  facturaId: number = 0;
  productoId: number = 0;
  cantidad: number = 0;
  motivo: string = '';

  devoluciones: Devoluciones[] = [];

  constructor(private devolucionesService: DevolucionesService) {}

  ngOnInit(): void {
    this.listDevoluciones();
  }

  listDevoluciones() {
    this.devolucionesService.getDevolucionesList().subscribe(
      data => {
        this.devoluciones = data;
        console.log(this.devoluciones);
      }
    );
  }

  addDevolucion() {
    let devolucion = new Devoluciones(this.id, this.facturaId, this.productoId, this.cantidad, this.motivo);
    console.log(devolucion);
    this.devolucionesService.createDevolucion(devolucion).subscribe(
      res => console.log(res)
    );
  }

  deleteDevolucion(id: number) {
    console.log(id);
    this.devolucionesService.deleteDevolucion(id).subscribe(
      () => this.listDevoluciones()
    );
  }
}
