export class Inventario {
    constructor(
        public categoria: string,
        public fechaIngreso: Date,
        public nombreProducto: string,
        public idProducto: number,
        public cantidadProducto: number
    ) {}
}
