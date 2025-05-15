import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Productos } from '../clases/productos';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {
  private baseUrl = 'http://localhost:8080/api/productos'; // URL del backend

  constructor(private http: HttpClient) {}

  getProductos(): Observable<Productos[]> {
    return this.http.get<Productos[]>(`${this.baseUrl}`);
  }

  getProductoById(id: number): Observable<Productos> {
    return this.http.get<Productos>(`${this.baseUrl}/${id}`);
  }

  createProducto(producto: Productos): Observable<Productos> {
    return this.http.post<Productos>(`${this.baseUrl}`, producto);
  }

  updateProducto(id: number, producto: Productos): Observable<Productos> {
    return this.http.put<Productos>(`${this.baseUrl}/${id}`, producto);
  }

  deleteProducto(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
