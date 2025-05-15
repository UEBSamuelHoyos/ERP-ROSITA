import { Component, NgModule, OnInit } from '@angular/core';
import { Cliente } from 'src/app/clases/cliente';
import { ClienteService } from 'src/app/service/cliente.service';
import * as bootstrap from 'bootstrap'; // Import Bootstrap

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
  clienteBuscado: Cliente | null = null;
  clientesFiltrados: Cliente[] = [];


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
  deleteCliente(id : number){
    console.log(id);
    this.ClienteService.deleteCliente(id).subscribe(
      ()=>this.listCliente()
    );
  }
  getCliente(id: number){
    this.ClienteService.getCliente(id).subscribe(
      data => {
  console.log(data);
      
      }
    );
  }
  updateCliente(id: number) {
    let cliente = new Cliente(this.id, this.nombreCompleto, this.cedula, this.direccion, this.telefono);
    this.ClienteService.updateCliente(id, cliente).subscribe(
      res => {
        console.log('Cliente actualizado:', res);
        this.listCliente(); // Refresh the list after updating
      },
      error => {
        console.log('Error al actualizar el cliente:', error);
      }
    );
  }
buscarClientePorCedula() {
  if (!this.cedula) {
    this.clientesFiltrados = [];
    return;
  }
  this.ClienteService.buscarClientePorCedula(this.cedula).subscribe(
    data => {
      this.clienteBuscado = data; // Store the found client
      this.clientesFiltrados = [data]; // Display the result in the modal
      const modal = new bootstrap.Modal(document.getElementById('searchResultsModal')!);
      modal.show();
    },
    error => {
      console.log('No se encontró un cliente con la cédula proporcionada.');
      this.clientesFiltrados = [];
    }
  );
}

buscarClientePorNombre() {
  if (!this.nombreCompleto) {
    this.clientesFiltrados = [];
    return;
  }
  this.ClienteService.buscarClientePorNombre(this.nombreCompleto).subscribe(
    data => {
      this.clientesFiltrados = data;
      if (this.clientesFiltrados.length > 0) {
        
        const modal = new bootstrap.Modal(document.getElementById('searchResultsModal')!); // Use imported Bootstrap
        modal.show();
      }
    },
    error => {
      console.log('No se encontraron clientes con el nombre proporcionado.');
      this.clientesFiltrados = [];
    }
  );
}

buscarClientePorId() {
  if (!this.id) {
    console.log('ID no proporcionado.');
    this.clientesFiltrados = [];
    return;
  }
  this.ClienteService.buscarClientePorId(this.id).subscribe(
    data => {
      this.clienteBuscado = data; // Store the found client
      this.clientesFiltrados = [data]; // Display the result in the modal
      const modal = new bootstrap.Modal(document.getElementById('searchResultsModal')!);
      modal.show();
    },
    error => {
      console.log('No se encontró un cliente con el ID proporcionado.');
      this.clientesFiltrados = [];
    }
  );
}

  cargarDatos(cliente: Cliente) {
  this.id = cliente.id;
  this.cedula = cliente.cedula;
  this.nombreCompleto = cliente.nombreCompleto;
  this.direccion = cliente.direccion;
  this.telefono = cliente.telefono;
  this.clientesFiltrados = []; // Clear the dropdown after selecting a client
  }
}
