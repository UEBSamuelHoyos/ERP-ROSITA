import { Component, OnInit } from '@angular/core';
import { Proveedores } from 'src/app/clases/proveedores';
import { ProveedoresService } from 'src/app/service/proveedores.service';

@Component({
  selector: 'app-proveedores',
  templateUrl: './proveedores.component.html',
  styleUrls: ['./proveedores.component.scss']
})
export class ProveedoresComponent implements OnInit {

  id: number = 1;
  cedula: string = '';
  nombreCompleto: string = '';
  direccion: string = '';
  telefono: string = '';

  proveedores: Proveedores[] = [];

  constructor(private ProveedoresService: ProveedoresService) {}

  ngOnInit(): void {
    this.listProveedores();
  }

  listProveedores() {
    this.ProveedoresService.getProveedoresList().subscribe(
      data => {
        this.proveedores = data;
        console.log(this.proveedores);
      }
    );
  }

  addProveedor() {
    let proveedor = new Proveedores(this.id, this.cedula, this.nombreCompleto, this.direccion, this.telefono);
    console.log(proveedor);
    this.ProveedoresService.createProveedor(proveedor).subscribe(
      res => console.log(res)
    );
  }

  deleteProveedor(id: number) {
    console.log(id);
    this.ProveedoresService.deleteProveedor(id).subscribe(
      () => this.listProveedores()
    );
  }
}
