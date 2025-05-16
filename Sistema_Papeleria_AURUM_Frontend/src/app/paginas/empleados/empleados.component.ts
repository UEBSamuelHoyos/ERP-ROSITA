import { Component, OnInit } from '@angular/core';
import { EmpleadosService, Empleado, RegistroAsistencia } from 'src/app/service/empleados.service';

@Component({
  selector: 'app-empleados',
  templateUrl: './empleados.component.html',
  styleUrls: ['./empleados.component.scss']
})
export class EmpleadosComponent implements OnInit {

  empleados: Empleado[] = [];
  asistencias: RegistroAsistencia[] = [];
  cedula: string = '';
  nombreCompleto: string = '';
  cargo: string = '';
  telefono: string = '';
  selectedEmpleadoId: number | null = null;

  constructor(private empleadosService: EmpleadosService) {}

  ngOnInit(): void {
    this.listarEmpleados();
  }

  listarEmpleados() {
    this.empleadosService.getEmpleados().subscribe(data => {
      this.empleados = data;
    });
  }

  agregarEmpleado() {
    const empleado: Empleado = {
      cedula: this.cedula,
      nombreCompleto: this.nombreCompleto,
      cargo: this.cargo,
      telefono: this.telefono
    };
    this.empleadosService.createEmpleado(empleado).subscribe(() => {
      this.listarEmpleados();
      this.cedula = '';
      this.nombreCompleto = '';
      this.cargo = '';
      this.telefono = '';
    });
  }

  eliminarEmpleado(id: number | undefined) {
    if (id == null) return;
    this.empleadosService.deleteEmpleado(id).subscribe(() => {
      this.listarEmpleados();
      if (this.selectedEmpleadoId === id) {
        this.selectedEmpleadoId = null;
        this.asistencias = [];
      }
    });
  }

  registrarAsistencia(id: number | undefined) {
    if (id == null) return;
    this.empleadosService.registrarAsistencia(id).subscribe(() => {
      if (this.selectedEmpleadoId === id) {
        this.verAsistencias(id);
      }
    });
  }

  verAsistencias(id: number | undefined) {
    if (id == null) return;
    this.selectedEmpleadoId = id;
    this.empleadosService.getAsistencias(id).subscribe(data => {
      this.asistencias = data;
    });
  }
}
