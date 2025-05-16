import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Proveedores } from '../clases/proveedores';

export interface ProductoCompraDTO {
  productoId?: number;
  nombre?: string; // Nuevo campo para producto nuevo
  cantidad: number;
  precioUnitario: number;
  proveedorId: number;
}

export interface CompraProveedorRequest {
  productos: ProductoCompraDTO[];
  montoTotal: number;
}

@Injectable({
  providedIn: 'root'
})
export class ProveedoresService {
  private api: string = 'http://localhost:8080/api/proveedores';

  constructor(private http: HttpClient) { }

  getProveedoresList(): Observable<Proveedores[]> {
    return this.http.get<Proveedores[]>(this.api);
  }

  createProveedor(proveedor: Proveedores): Observable<Proveedores> {
    return this.http.post<Proveedores>(this.api, proveedor);
  }

  deleteProveedor(id: number): Observable<any> {
    return this.http.delete(`${this.api}/${id}`);
  }

  comprarProductos(id: number, compra: CompraProveedorRequest): Observable<any> {
    return this.http.post(`${this.api}/${id}/comprar`, compra);
  }
}
