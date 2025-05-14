import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Productos } from '../clases/productos';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  private api: string = 'http://localhost:8080/api/Productos';

  constructor(private http: HttpClient) {}

  getProductosList(): Observable<Productos[]> {
    return this.http.get<Productos[]>(this.api);
  }

  createProducto(producto: Productos): Observable<Productos> {
    return this.http.post<Productos>(this.api, producto);
  }

  deleteProducto(id: number): Observable<any> {
    return this.http.delete(this.api + '/' + id);
  }
}
