export class Devoluciones {
    constructor(
        public id: number,
        public facturaId: number,
        public productoId: number,
        public cantidad: number,
        public motivo: String
    ) {}
}
