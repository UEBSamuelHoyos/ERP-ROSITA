import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Inventario } from '../clases/inventario';

@Injectable({
  providedIn: 'root'
})
export class InventarioService {

  private api: string = 'http://localhost:8080/api/Inventario';

  constructor(private http: HttpClient) {}

  getInventarioList(): Observable<Inventario[]> {
    return this.http.get<Inventario[]>(this.api);
  }

  createInventario(inventario: Inventario): Observable<Inventario> {
    return this.http.post<Inventario>(this.api, inventario);
  }

  deleteInventario(id: number): Observable<any> {
    return this.http.delete(this.api + '/' + id);
  }
}
