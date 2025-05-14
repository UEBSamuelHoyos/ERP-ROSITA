import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Proveedores } from '../clases/proveedores';

@Injectable({
  providedIn: 'root'
})
export class ProveedoresService {

private api : string = 'http://localhost:8080/api/Proveedores';


  constructor(private http: HttpClient) { }
getProveedoresList():Observable<Proveedores []>{
  return this.http.get<Proveedores[]>(this.api);

}

createProveedor(Proveedores : Proveedores):Observable<Proveedores>{
  return this.http.post<Proveedores>(this.api,Proveedores);
}

deleteProveedor(id : number):Observable<any>{
  return this.http.delete(this.api+'/'+id);    
  
}
}
