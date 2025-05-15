export interface ProductoVenta {
  idProducto: number;
  cantidad: number;
  precioUnitario?: number;
}

export interface Venta {
  id?: number;
  clienteId: number;
  clienteNombre?: string;
  descuento?: number;
  total?: number;
  fecha?: string;
  productos: ProductoVenta[];
}
