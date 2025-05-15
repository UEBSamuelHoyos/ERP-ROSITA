export class Productos {
  id: number;
  nombre: string;
  categoria: string;
  precioCompra: number;
  precioVenta: number;
  stock: number;

  constructor(
    id: number,
    nombre: string,
    categoria: string,
    precioCompra: number,
    precioVenta: number,
    stock: number
  ) {
    this.id = id;
    this.nombre = nombre;
    this.categoria = categoria;
    this.precioCompra = precioCompra;
    this.precioVenta = precioVenta;
    this.stock = stock;
  }
}
