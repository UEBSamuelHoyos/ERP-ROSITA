import { Component, OnInit } from '@angular/core';
import { Proveedores } from 'src/app/clases/proveedores';
import { ProveedoresService, ProductoCompraDTO, CompraProveedorRequest } from 'src/app/service/proveedores.service';

@Component({
  selector: 'app-proveedores',
  templateUrl: './proveedores.component.html',
  styleUrls: ['./proveedores.component.scss']
})
export class ProveedoresComponent implements OnInit {

  id: number = 1;
  nombre: string = '';
  contacto: string = '';
  direccion: string = '';
  telefono: string = '';

  proveedores: Proveedores[] = [];

  // Para la compra dinámica
  selectedProveedorId: number | null = null;
  productoId: number | null = null;
  productoNombre: string = '';
  cantidad: number = 1;
  precioUnitario: number = 0;
  montoTotal: number = 0;
  productosDisponibles: { id: number, nombre: string }[] = [];

  constructor(private proveedoresService: ProveedoresService) {}

  ngOnInit(): void {
    this.listProveedores();
    // Si tienes un servicio de productos, aquí puedes cargar productosDisponibles
    // this.productosService.getProductos().subscribe(data => this.productosDisponibles = data);
  }

  listProveedores() {
    this.proveedoresService.getProveedoresList().subscribe(
      data => {
        this.proveedores = data;
        console.log(this.proveedores);
      }
    );
  }

  addProveedor() {
    let proveedor = new Proveedores(this.id, this.nombre, this.contacto, this.direccion, this.telefono);
    this.proveedoresService.createProveedor(proveedor).subscribe(
      res => {
        this.listProveedores();
      }
    );
  }

  deleteProveedor(id: number) {
    this.proveedoresService.deleteProveedor(id).subscribe(
      () => this.listProveedores()
    );
  }

  seleccionarProveedor(proveedorId: number) {
    this.selectedProveedorId = proveedorId;
    // Si quieres filtrar productos por proveedor, hazlo aquí
    // this.productosDisponibles = ...;
  }

  comprarProductos() {
    if (!this.selectedProveedorId || this.cantidad <= 0 || this.precioUnitario <= 0 || (!this.productoId && !this.productoNombre)) {
      alert('Selecciona proveedor, producto (o nombre nuevo), cantidad y precio unitario válidos');
      return;
    }
    const compra: CompraProveedorRequest = {
      productos: [
        {
          productoId: this.productoId ? this.productoId : undefined,
          nombre: !this.productoId ? this.productoNombre : undefined,
          cantidad: this.cantidad,
          precioUnitario: this.precioUnitario,
          proveedorId: this.selectedProveedorId
        }
      ],
      montoTotal: this.cantidad * this.precioUnitario
    };
    this.proveedoresService.comprarProductos(this.selectedProveedorId, compra).subscribe(
      res => {
        alert('Compra realizada y stock actualizado');
        this.productoId = null;
        this.productoNombre = '';
        this.cantidad = 1;
        this.precioUnitario = 0;
        this.montoTotal = 0;
      }
    );
  }
}
