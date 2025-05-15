export class Inventario {
  categoria: string;
  fechaIngreso: Date;
  nombreProducto: string;
  idProducto: number;
  cantidadProducto: number;

  constructor(
    categoria: string,
    fechaIngreso: Date,
    nombreProducto: string,
    idProducto: number,
    cantidadProducto: number
  ) {
    this.categoria = categoria;
    this.fechaIngreso = fechaIngreso;
    this.nombreProducto = nombreProducto;
    this.idProducto = idProducto;
    this.cantidadProducto = cantidadProducto;
  }
}
