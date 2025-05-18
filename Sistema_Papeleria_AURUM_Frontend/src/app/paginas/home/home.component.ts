import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  seleccionado = Array(11).fill(false);
  colorDesactivado = "#555555";
  colorActivado = "#000000";
  usuario: string = '';

  rutas = [
    "/login",
    "/Home",        // 0
    "/Productos",     // 1
    "/Venta",        // 2
    "/Cliente",      // 3
    "/inventario",   // 4
    "/Facturas",      // 5
    "/Proveedores",  // 6
    "/Empleados",    // 7
    "/Impuestos",    // 8
    "/Devoluciones", // 9
    "/Cuentas"       // 10
  ];

  constructor(private router: Router) {
    this.usuario = localStorage.getItem('usuario') || '';

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        const index = this.rutas.indexOf(event.urlAfterRedirects);
        this.seleccionado = Array(11).fill(false);
        if (index !== -1) {
          this.seleccionado[index] = true;
        }
      }
    });
  }

  navegar(direccion: string) {
    this.router.navigate([direccion]);
  }

  mostrarImpuestos(): boolean {
    return this.usuario !== 'gladys'; // Gladys no ve el botón
  }

  
}
