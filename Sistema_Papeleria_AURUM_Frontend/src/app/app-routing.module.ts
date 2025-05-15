import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteComponent } from './paginas/cliente/cliente.component';
import { HomeComponent } from './paginas/home/home.component';
import { CuentasComponent } from './paginas/cuentas/cuentas.component';
import { LoginComponent } from './paginas/login/login.component';
import { ProductosComponent } from './paginas/productos/productos.component';
import { ProveedoresComponent } from './paginas/proveedores/proveedores.component';
import { FacturasComponent } from './paginas/facturas/facturas.component';
import { DevolucionesComponent } from './paginas/devoluciones/devoluciones.component';
import { VentaComponent } from './paginas/venta/venta.component';
import { EmpleadosComponent } from './paginas/empleados/empleados.component';
import { ImpuestosComponent } from './paginas/impuestos/impuestos.component';
import { InventarioComponent } from './paginas/inventario/inventario.component';

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
    path: "home",
    component: HomeComponent
  },
    {
    path: "Productos",
    component: ProductosComponent
  },
    {
    path: "Facturas",
    component: FacturasComponent
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
   {
    path: "Impuestos",
    component: ImpuestosComponent
  },
   {
    path: "inventario",
    component: InventarioComponent
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
