import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ClienteComponent } from './paginas/cliente/cliente.component';
import { TabsComponent } from './core/components/tabs/tabs.component'; 



@NgModule({
  declarations: [
    AppComponent,
    ClienteComponent,
    TabsComponent

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
