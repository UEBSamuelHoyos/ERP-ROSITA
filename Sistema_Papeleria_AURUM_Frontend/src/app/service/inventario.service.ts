import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Inventario } from '../clases/inventario';

@Injectable({
  providedIn: 'root'
})
export class InventarioService {

  private baseUrl = 'http://localhost:8080/api/Inventario'; // URL del backend

  constructor(private http: HttpClient) { }

  getInventarioList(): Observable<Inventario[]> {
    return this.http.get<Inventario[]>(`${this.baseUrl}`);
  }

  getInventarioById(id: number): Observable<Inventario> {
    return this.http.get<Inventario>(`${this.baseUrl}/${id}`);
  }
}
