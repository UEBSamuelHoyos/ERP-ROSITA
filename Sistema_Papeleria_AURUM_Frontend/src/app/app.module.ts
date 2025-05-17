import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ClienteComponent } from './paginas/cliente/cliente.component';
import { TabsComponent } from './core/components/tabs/tabs.component';
import { ImpuestosComponent } from './paginas/impuestos/impuestos.component'; 
import { ProveedoresComponent } from './paginas/proveedores/proveedores.component';
import { EmpleadosComponent } from './paginas/empleados/empleados.component';
import { ProductosComponent } from './paginas/productos/productos.component';
import { DevolucionesComponent } from './paginas/devoluciones/devoluciones.component';
import { InventarioComponent } from './paginas/inventario/inventario.component';
import { HomeComponent } from './paginas/home/home.component';
import { VentaComponent } from './paginas/venta/venta.component';
import { FacturasComponent } from './paginas/facturas/facturas.component';
import {LoginComponent} from './paginas/login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    ClienteComponent,
    TabsComponent,
    InventarioComponent,
    ImpuestosComponent,
    ProveedoresComponent,
    EmpleadosComponent,
    ProductosComponent,
    DevolucionesComponent,
    VentaComponent,
    HomeComponent,
    FacturasComponent,
    LoginComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
