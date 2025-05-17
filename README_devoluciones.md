# Cómo probar devoluciones de productos

1. **Asegúrate de tener una venta y una factura existente**
   - Realiza una venta desde el frontend o usando Postman.
   - Obtén el `id` de la factura generada y el `id` del producto vendido.

2. **Haz una devolución**
   - Endpoint: `POST http://localhost:8080/api/devoluciones`
   - Body de ejemplo:
     ```json
     {
       "facturaId": 1,
       "productoId": 2,
       "cantidad": 3
     }
     ```
     Cambia los valores por los IDs reales y la cantidad a devolver.

3. **Verifica la respuesta**
   - Debes recibir un objeto con los datos de la devolución y la fecha.

4. **Verifica el stock**
   - Haz un GET a `/api/productos/{id}` o revisa en la base de datos que el stock del producto haya aumentado en la cantidad devuelta.

5. **Verifica el inventario**
   - Haz un GET a `/api/inventario` y revisa que la cantidad del producto también se haya actualizado.

6. **Consulta las devoluciones**
   - GET a `http://localhost:8080/api/devoluciones` para ver todas las devoluciones registradas.

---

**Resumen:**  
- Realiza una venta.
- Haz un POST a `/api/devoluciones` con el id de la factura, id del producto y cantidad.
- Verifica que el stock y el inventario se actualizan correctamente.
- Consulta las devoluciones para ver el historial.

**Si ocurre un error, revisa el mensaje de respuesta y los logs del backend para depurar.**
