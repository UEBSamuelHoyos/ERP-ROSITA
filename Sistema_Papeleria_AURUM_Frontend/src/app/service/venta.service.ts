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
    const payload = {
        clienteId: venta.cliente.id,
        productoId: venta.producto.id,
        cantidad: venta.cantidad
    };
    console.log('Enviando datos al backend:', payload); // Imprimir los datos enviados
    return this.http.post<Venta>(`${this.baseUrl}`, payload);
  }

  getVentaById(id: number): Observable<Venta> {
    return this.http.get<Venta>(`${this.baseUrl}/${id}`);
  }
}
