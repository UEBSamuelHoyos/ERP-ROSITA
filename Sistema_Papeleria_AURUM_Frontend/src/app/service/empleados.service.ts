import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Empleado {
  id?: number;
  cedula: string;
  nombreCompleto: string;
  cargo: string;
  telefono: string;
  // Elimina o comenta la línea de fechaRegistro si no existe en el backend
  // fechaRegistro?: string;
}

export interface RegistroAsistencia {
  id: number;
  fechaHora: string;
  // Puedes agregar más campos si los necesitas
}

@Injectable({
  providedIn: 'root'
})
export class EmpleadosService {
  private api = 'http://localhost:8080/api/Empleados';

  constructor(private http: HttpClient) {}

  getEmpleados(): Observable<Empleado[]> {
    return this.http.get<Empleado[]>(this.api);
  }

  createEmpleado(empleado: Empleado): Observable<Empleado> {
    return this.http.post<Empleado>(this.api, empleado);
  }

  deleteEmpleado(id: number): Observable<any> {
    return this.http.delete(`${this.api}/${id}`);
  }

  registrarAsistencia(id: number): Observable<any> {
    return this.http.post(`${this.api}/${id}/asistencia`, {});
  }

  getAsistencias(id: number): Observable<RegistroAsistencia[]> {
    return this.http.get<RegistroAsistencia[]>(`${this.api}/${id}/asistencias`);
  }
}
