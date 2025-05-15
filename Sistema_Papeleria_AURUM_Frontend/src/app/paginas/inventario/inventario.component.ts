import { Component, OnInit } from '@angular/core';
import { Inventario } from 'src/app/clases/inventario';
import { InventarioService } from 'src/app/service/inventario.service';

@Component({
  selector: 'app-inventario',
  templateUrl: './inventario.component.html',
  styleUrls: ['./inventario.component.scss']
})
export class InventarioComponent implements OnInit {

  inventario: Inventario[] = [];

  constructor(private inventarioService: InventarioService) {}

  ngOnInit(): void {
    this.listarInventario();
  }

  listarInventario(): void {
    this.inventarioService.getInventarioList().subscribe(
      data => {
        this.inventario = data;
        console.log('Inventario:', this.inventario);
      },
      error => {
        console.error('Error al obtener el inventario:', error);
      }
    );
  }
}
