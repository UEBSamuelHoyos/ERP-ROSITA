export class Cliente {
  id: number;
  cedula: string;
  nombreCompleto: string;
  direccion: string;
  telefono: string;

  constructor(id: number, nombreCompleto: string, cedula: string, direccion: string, telefono: string) {
    this.id = id;
    this.cedula = cedula;
    this.nombreCompleto = nombreCompleto;
    this.direccion = direccion;
    this.telefono = telefono;
  }
}