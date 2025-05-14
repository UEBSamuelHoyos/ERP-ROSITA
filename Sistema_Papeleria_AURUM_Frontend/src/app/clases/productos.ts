export class Productos {
    constructor(
        public id: number,
        public nombre: String,
        public categoria: String,
        public precioCompra: number,
        public precioVenta: number,
        public stock: number
    ) {}
}
