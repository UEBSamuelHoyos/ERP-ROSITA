import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Empleados } from '../clases/empleados';

@Injectable({
  providedIn: 'root'
})
export class EmpleadosService {

  private api: string = 'http://localhost:8080/api/Empleados';

  constructor(private http: HttpClient) {}

  getEmpleadosList(): Observable<Empleados[]> {
    return this.http.get<Empleados[]>(this.api);
  }

  createEmpleado(empleado: Empleados): Observable<Empleados> {
    return this.http.post<Empleados>(this.api, empleado);
  }

  deleteEmpleado(id: number): Observable<any> {
    return this.http.delete(this.api + '/' + id);
  }
}
