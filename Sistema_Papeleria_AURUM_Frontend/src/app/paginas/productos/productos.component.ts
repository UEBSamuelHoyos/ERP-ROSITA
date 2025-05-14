import { Component, OnInit } from '@angular/core';
import { Productos } from 'src/app/clases/productos';
import { ProductosService } from 'src/app/service/productos.service';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.scss']
})
export class ProductosComponent implements OnInit {

  id: number = 1;
  nombre: string = '';
  categoria: string = '';
  precioCompra: number = 0;
  precioVenta: number = 0;
  stock: number = 0;
  

  productos: Productos[] = [];

  constructor(private productosService: ProductosService) {}

  ngOnInit(): void {
    this.listProductos();
  }

  listProductos() {
    this.productosService.getProductosList().subscribe(
      data => {
        this.productos = data;
        console.log(this.productos);
      }
    );
  }

  addProducto() {
    let producto = new Productos(this.id, this.nombre, this.categoria, this.precioCompra, this.precioVenta, this.stock);
    console.log(producto);
    this.productosService.createProducto(producto).subscribe(
      res => console.log(res)
    );
  }

  deleteProducto(id: number) {
    console.log(id);
    this.productosService.deleteProducto(id).subscribe(
      () => this.listProductos()
    );
  }
}
