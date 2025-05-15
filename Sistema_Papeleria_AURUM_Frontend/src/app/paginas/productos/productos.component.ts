import { Component, OnInit } from '@angular/core';
import { Productos } from 'src/app/clases/productos';
import { ProductosService } from 'src/app/service/productos.service';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.scss']
})
export class ProductosComponent implements OnInit {
  productos: Productos[] = [];
  productoSeleccionado: Productos = new Productos(0, '', '', 0, 0, 0);

  constructor(private productosService: ProductosService) {}

  ngOnInit(): void {
    this.listarProductos();
  }

  listarProductos(): void {
    this.productosService.getProductos().subscribe(
      data => {
        this.productos = data;
      },
      error => {
        console.error('Error al obtener los productos:', error);
      }
    );
  }

  seleccionarProducto(producto: Productos): void {
    this.productoSeleccionado = { ...producto }; // Clonar el producto seleccionado
  }

  actualizarProducto(): void {
    if (this.productoSeleccionado.id) {
      this.productosService.updateProducto(this.productoSeleccionado.id, this.productoSeleccionado).subscribe(
        data => {
          console.log('Producto actualizado:', data);
          this.listarProductos(); // Actualizar la lista de productos
          this.productoSeleccionado = new Productos(0, '', '', 0, 0, 0); // Limpiar el formulario
        },
        error => {
          console.error('Error al actualizar el producto:', error);
        }
      );
    }
  }
}
