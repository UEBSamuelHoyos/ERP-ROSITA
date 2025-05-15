export interface VentaFactura {
  id: number;
  clienteId: number;
  clienteNombre: string;
  descuento: number;
  total: number;
  fecha: string;
}

export interface Factura {
  id: number;
  clienteId: number;
  fecha: string;
  total: number;
  estado: string;
  ventas: VentaFactura[];
}

