import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/clases/cliente';
import { ClienteService } from 'src/app/service/cliente.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent implements OnInit{
  
cliente : Cliente [] = [];

constructor(private ClienteService: ClienteService){}

  ngOnInit(): void {
   
  }
  listCliente(){
    this.ClienteService.getClienteList();
  }
}
