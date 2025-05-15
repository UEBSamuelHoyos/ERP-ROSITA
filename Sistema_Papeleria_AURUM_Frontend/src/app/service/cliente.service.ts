import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from '../clases/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
 private api : string = 'http://localhost:8080/api/Cliente';

  constructor(private http: HttpClient) { }
  getClienteList():Observable<Cliente []>{
    return this.http.get<Cliente[]>(this.api);
  }
  createCliente(Cliente : Cliente):Observable<Cliente>{
    return this.http.post<Cliente>(this.api,Cliente);
  }
  deleteCliente(id : number):Observable<any>{
  return this.http.delete(this.api+'/'+id);    

  }
  getCliente(id: number):Observable<Cliente>{
    return this.http.get<Cliente>(this.api+'/'+id);
  }
  updateCliente(id: number, cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.api}/${id}`, cliente); // Corrected endpoint
  }
  buscarClientePorCedula(cedula: string): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.api}/cedula/${cedula}`); // Ensure endpoint matches backend
  }
  buscarClientePorNombre(nombre: string): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.api}/buscar?nombre=${nombre}`); // Corrected endpoint
  }
  buscarClientePorId(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.api}/buscarPorId/${id}`); // Ensure endpoint matches backend
  }
}
