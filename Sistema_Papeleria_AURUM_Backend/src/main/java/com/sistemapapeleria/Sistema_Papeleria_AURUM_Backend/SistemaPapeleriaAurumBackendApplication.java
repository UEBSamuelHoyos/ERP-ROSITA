package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// No necesitas hacer cambios aquí para el cálculo de IVA o ventas.
// El cálculo y registro del IVA ya está implementado en VentasServiceImpl.
// Si la aplicación arranca y puedes hacer ventas, todo está correcto.
// Si tienes errores al hacer una venta, revisa el log del backend para detalles específicos.

@SpringBootApplication
public class SistemaPapeleriaAurumBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaPapeleriaAurumBackendApplication.class, args);
	}

}
