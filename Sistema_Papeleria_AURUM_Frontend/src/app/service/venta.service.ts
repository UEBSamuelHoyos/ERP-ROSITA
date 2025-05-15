import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Venta } from '../clases/venta';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  private baseUrl = 'http://localhost:8080/api/ventas'; // URL del backend

  constructor(private http: HttpClient) { }

  getVentas(): Observable<Venta[]> {
    return this.http.get<Venta[]>(`${this.baseUrl}`);
  }

  createVenta(venta: Venta): Observable<Venta> {
    // El payload ya es correcto desde el componente
    return this.http.post<Venta>(`${this.baseUrl}`, venta);
  }

  getVentaById(id: number): Observable<Venta> {
    return this.http.get<Venta>(`${this.baseUrl}/${id}`);
  }
}
