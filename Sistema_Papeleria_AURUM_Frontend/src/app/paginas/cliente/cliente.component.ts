import { Component, NgModule, OnInit } from '@angular/core';
import { Cliente } from 'src/app/clases/cliente';
import { ClienteService } from 'src/app/service/cliente.service';


@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent implements OnInit {

  id: number = 1;
  cedula: string = '';
  nombreCompleto: string = '';
  direccion: string = '';
  telefono: string = ''; 

  cliente: Cliente[] = [];

  constructor(private ClienteService: ClienteService) {}

  ngOnInit(): void {
    this.listCliente();
  }

  listCliente() {
    this.ClienteService.getClienteList().subscribe(
      data => {
        this.cliente = data;
        console.log(this.cliente);
      }
    );
  }

  addCliente() {
    let cliente = new Cliente(this.id , this.nombreCompleto, this.cedula, this.direccion, this.telefono);
    console.log(cliente);
    this.ClienteService.createCliente(cliente).subscribe(
      res => console.log(res)
    );
  }
}
