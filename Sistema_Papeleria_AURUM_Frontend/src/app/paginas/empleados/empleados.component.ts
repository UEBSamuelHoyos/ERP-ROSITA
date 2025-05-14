import { Component, OnInit } from '@angular/core';
import { Empleados } from 'src/app/clases/empleados';
import { EmpleadosService } from 'src/app/service/empleados.service';

@Component({
  selector: 'app-empleados',
  templateUrl: './empleados.component.html',
  styleUrls: ['./empleados.component.scss']
})
export class EmpleadosComponent implements OnInit {

  id: number = 1;
  cedula: string = '';
  nombreCompleto: string = '';
  cargo: string = '';
  telefono: string = '';

  

  empleados: Empleados[] = [];

  constructor(private empleadosService: EmpleadosService) {}

  ngOnInit(): void {
    this.listEmpleados();
  }

  listEmpleados() {
    this.empleadosService.getEmpleadosList().subscribe(
      data => {
        this.empleados = data;
        console.log(this.empleados);
      }
    );
  }

  addEmpleado() {
    let empleado = new Empleados(this.id, this.cedula, this.nombreCompleto, this.cargo, this.telefono);
    console.log(empleado);
    this.empleadosService.createEmpleado(empleado).subscribe(
      res => console.log(res)
    );
  }

  deleteEmpleado(id: number) {
    console.log(id);
    this.empleadosService.deleteEmpleado(id).subscribe(
      () => this.listEmpleados()
    );
  }
}
