import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Cuenta {
  id: number;
  clienteId?: number;
  proveedorId?: number;
  monto: number;
  estado: string;
  tipo?: 'COBRAR' | 'PAGAR'; // tipo es opcional para compatibilidad
}

@Component({
  selector: 'app-cuentas',
  templateUrl: './cuentas.component.html',
  styleUrls: ['./cuentas.component.scss']
})
export class CuentasComponent implements OnInit { // <-- Corrige el nombre de la clase aquí
  cuentasCobrar: Cuenta[] = [];
  cuentasPagar: Cuenta[] = [];
  loading = false;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.listarCuentas();
  }

  listarCuentas() {
    this.loading = true;
    this.http.get<Cuenta[]>('http://localhost:8080/api/cuentas').subscribe({
      next: data => {
        // Soporta ambos: con o sin campo 'tipo'
        this.cuentasCobrar = data.filter(c =>
          (c.tipo === 'COBRAR') ||
          (c.tipo === undefined && c.clienteId !== undefined && c.clienteId !== null)
        );
        this.cuentasPagar = data.filter(c =>
          (c.tipo === 'PAGAR') ||
          (c.tipo === undefined && c.proveedorId !== undefined && c.proveedorId !== null)
        );
        this.loading = false;
      },
      error: error => {
        this.loading = false;
        console.error('Error al obtener las cuentas:', error);
        // Muestra el mensaje de error real del backend si existe
        if (error && error.error && typeof error.error === 'string') {
          alert('Error al obtener las cuentas: ' + error.error);
        } else {
          alert('Error al obtener las cuentas. El backend respondió con error 500. Revisa la consola/log del backend para más detalles.');
        }
      }
    });
  }

  pagarCuentaCobrar(id: number) {
    this.http.put(`http://localhost:8080/api/cuentas/${id}/pagar`, {}).subscribe(() => this.listarCuentas());
  }

  pagarCuentaPagar(id: number) {
    this.http.put(`http://localhost:8080/api/cuentas/${id}/pagar`, {}).subscribe(() => this.listarCuentas());
  }
}
