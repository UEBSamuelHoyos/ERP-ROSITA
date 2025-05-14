import { Component, OnInit } from '@angular/core';
import { Inventario } from 'src/app/clases/inventario';
import { InventarioService } from 'src/app/service/inventario.service';

@Component({
  selector: 'app-inventario',
  templateUrl: './inventario.component.html',
  styleUrls: ['./inventario.component.scss']
})
export class InventarioComponent implements OnInit {

  categoria: string = '';
  fechaIngreso: Date = new Date();
  nombreProducto: string = '';
  idProducto: number = 0;
  cantidadProducto: number = 0;

  inventario: Inventario[] = [];

  constructor(private inventarioService: InventarioService) {}

  ngOnInit(): void {
    this.listInventario();
  }

  listInventario() {
    this.inventarioService.getInventarioList().subscribe(
      data => {
        this.inventario = data;
        console.log(this.inventario);
      }
    );
  }

  addInventario() {
    let item = new Inventario(this.categoria, this.fechaIngreso, this.nombreProducto, this.idProducto, this.cantidadProducto);
    console.log(item);
    this.inventarioService.createInventario(item).subscribe(
      res => console.log(res)
    );
  }

  deleteInventario(id: number) {
    console.log(id);
    this.inventarioService.deleteInventario(id).subscribe(
      () => this.listInventario()
    );
  }
}
