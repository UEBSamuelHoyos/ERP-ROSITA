import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/clases/cliente';
import { ClienteService } from 'src/app/service/cliente.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent implements OnInit{

id : string = '';
cedula : string = '';
nombreCompleto : string = '';
direccion : string = '';
telefono : string = '';
  
cliente : Cliente [] = [];

constructor(private ClienteService: ClienteService){}

  ngOnInit(): void {
    this.listCliente();
   
  }
  listCliente(){
    this.ClienteService.getClienteList().subscribe(
      data => {

          this.cliente = data;
          console.log(this.cliente);
      }
    );
  }
}
