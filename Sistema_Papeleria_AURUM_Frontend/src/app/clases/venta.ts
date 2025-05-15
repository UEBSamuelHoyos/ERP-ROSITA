import { Cliente } from './cliente';
import { Productos } from './productos';

export class Venta {
  id: number;
  cliente: Cliente;
  producto: Productos;
  cantidad: number;
  precioUnitario: number;
  descuento: number;
  total: number;
  fecha: Date;

  constructor(
    id: number,
    cliente: Cliente,
    producto: Productos,
    cantidad: number,
    precioUnitario: number,
    descuento: number,
    total: number,
    fecha: Date
  ) {
    this.id = id;
    this.cliente = cliente;
    this.producto = producto;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.descuento = descuento;
    this.total = total;
    this.fecha = fecha;
  }
}
