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

  rutas = [
    "/login",
    "/Home",        // 0
    "/Productos",     // 1
    "/Venta",        // 2
    "/Cliente",      // 3
    "/inventario",   // 4
    "/Factura",      // 5
    "/Proveedores",  // 6
    "/Empleados",    // 7
    "/Impuestos",    // 8
    "/Devoluciones", // 9
    "/Cuentas"       // 10
  ];

  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        console.log("Evento", event);

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
    console.log(direccion);
  }
}