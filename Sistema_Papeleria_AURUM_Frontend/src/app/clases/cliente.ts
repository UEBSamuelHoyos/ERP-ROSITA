export class Cliente {
  id: number;
  nombreCompleto: string;
  cedula: string;
  direccion: string;
  telefono: string;
  afiliado: boolean;

  constructor(
    id: number,
    nombreCompleto: string,
    cedula: string,
    direccion: string,
    telefono: string,
    afiliado: boolean
  ) {
    this.id = id;
    this.nombreCompleto = nombreCompleto;
    this.cedula = cedula;
    this.direccion = direccion;
    this.telefono = telefono;
    this.afiliado = afiliado;
  }
}