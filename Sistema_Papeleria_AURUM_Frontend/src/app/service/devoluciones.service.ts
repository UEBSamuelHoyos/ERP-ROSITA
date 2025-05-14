import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Devoluciones } from '../clases/devoluciones';

@Injectable({
  providedIn: 'root'
})
export class DevolucionesService {

  private api: string = 'http://localhost:8080/api/Devoluciones';

  constructor(private http: HttpClient) {}

  getDevolucionesList(): Observable<Devoluciones[]> {
    return this.http.get<Devoluciones[]>(this.api);
  }

  createDevolucion(devolucion: Devoluciones): Observable<Devoluciones> {
    return this.http.post<Devoluciones>(this.api, devolucion);
  }

  deleteDevolucion(id: number): Observable<any> {
    return this.http.delete(this.api + '/' + id);
  }
}
