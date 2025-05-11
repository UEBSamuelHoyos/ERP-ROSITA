import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteComponent } from './paginas/cliente/cliente.component';
import { HomeComponent } from './paginas/home/home.component';
import { CuentasComponent } from './paginas/cuentas/cuentas.component';
import { LoginComponent } from './paginas/login/login.component';
import { ProductoComponent } from './paginas/producto/producto.component';
import { ProveedoresComponent } from './paginas/proveedores/proveedores.component';
import { FacturaComponent } from './paginas/factura/factura.component';
import { DevolucionesComponent } from './paginas/devoluciones/devoluciones.component';
import { VentaComponent } from './paginas/venta/venta.component';
import { EmpleadosComponent } from './paginas/empleados/empleados.component';

const routes: Routes = [
  {
    path: "",
    component: LoginComponent
  },
  {
    path: "Cliente",
    component: ClienteComponent
  },

    {
    path: "Proveedores",
    component: ProveedoresComponent
  },
    {
    path: "Cuentas",
    component: CuentasComponent
  },
    {
    path: "Home",
    component: HomeComponent
  },
    {
    path: "Producto",
    component: ProductoComponent
  },
    {
    path: "Factura",
    component: FacturaComponent
  },
    
    {
    path: "Devoluciones",
    component: DevolucionesComponent
  },
    {
    path: "Venta",
    component: VentaComponent
  },
  {
    path: "Empleados",
    component: EmpleadosComponent
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
