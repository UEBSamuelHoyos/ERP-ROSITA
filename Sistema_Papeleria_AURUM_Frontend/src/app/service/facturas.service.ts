import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface VentaFactura {
  id: number;
  clienteId: number;
  clienteNombre: string;
  descuento: number;
  total: number;
  fecha: string;
}

export interface Factura {
  id: number;
  clienteId: number;
  fecha: string;
  total: number;
  estado: string;
  ventas: VentaFactura[];
}

@Injectable({
  providedIn: 'root'
})
export class FacturasService {
  private baseUrl = 'http://localhost:8080/api/facturas';

  constructor(private http: HttpClient) {}

  getFacturas(): Observable<Factura[]> {
    return this.http.get<Factura[]>(this.baseUrl);
  }

  getFacturaById(id: number): Observable<Factura> {
    return this.http.get<Factura>(`${this.baseUrl}/${id}`);
  }
}
