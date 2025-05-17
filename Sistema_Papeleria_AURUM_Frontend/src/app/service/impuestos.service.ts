import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Impuesto {
  id?: number;
  cantidadVentas: number;
  porcentajeImpuesto: number;
  totalImpuesto: number;
  anioFiscal: number;
  nombreImpuesto: string;
}

@Injectable({
  providedIn: 'root'
})
export class ImpuestosService {
  private api = 'http://localhost:8080/api/impuestos';

  constructor(private http: HttpClient) {}

  getImpuestos(): Observable<Impuesto[]> {
    return this.http.get<Impuesto[]>(this.api);
  }
}
