package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasPagarDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.CuentasPagarService;

@RestController
@RequestMapping("/api/cuenta")
@CrossOrigin(origins = "*")
public class CuentasPagarController {

    private static final Logger logger = LoggerFactory.getLogger(CuentasPagarController.class);

    private final CuentasPagarService cuentasPagarService;

    @Autowired
    public CuentasPagarController(CuentasPagarService cuentasPagarService) {
        this.cuentasPagarService = cuentasPagarService;
    }

    @PostMapping
    public ResponseEntity<CuentasPagarDTO> crear(@RequestBody CuentasPagarDTO cuentaDTO) {
        return ResponseEntity.ok(cuentasPagarService.saveCuentaPagar(cuentaDTO));
    }

    @GetMapping
    public ResponseEntity<List<CuentasPagarDTO>> listarTodos() {
        return ResponseEntity.ok(cuentasPagarService.getAllCuentasPagar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentasPagarDTO> buscarPorId(@PathVariable Long id) {
        CuentasPagarDTO dto = cuentasPagarService.getCuentaPagarById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentasPagarService.deleteCuentaPagar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentasPagarDTO> actualizar(@PathVariable Long id, @RequestBody CuentasPagarDTO dto) {
        CuentasPagarDTO actualizada = cuentasPagarService.updateCuentaPagar(id, dto);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<CuentasPagarDTO> marcarComoPagada(@PathVariable Long id) {
        CuentasPagarDTO actualizada = cuentasPagarService.marcarComoPagada(id);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<CuentasPagarDTO>> obtenerPorProveedor(@PathVariable Long proveedorId) {
        List<CuentasPagarDTO> cuentas = cuentasPagarService.getCuentasByProveedorId(proveedorId);
        return ResponseEntity.ok(cuentas);
    }

    @PostMapping("/comprar-credito")
    public ResponseEntity<?> comprarACredito(@RequestBody CuentasPagarDTO cuentaDTO) {
        try {
            // Validación básica de campos obligatorios
            if (cuentaDTO.getProveedorId() == null || cuentaDTO.getMonto() == null) {
                return ResponseEntity.badRequest().body("proveedorId y monto son obligatorios.");
            }
            if (cuentaDTO.getMonto() <= 0) {
                return ResponseEntity.badRequest().body("El monto debe ser mayor a 0.");
            }

            // Conversión robusta de fechaVencimiento desde String a java.sql.Date
            if (cuentaDTO.getFechaVencimiento() == null) {
                cuentaDTO.setFechaVencimiento(new java.sql.Date(System.currentTimeMillis()));
            } else if (cuentaDTO.getFechaVencimiento() instanceof String) {
                try {
                    cuentaDTO.setFechaVencimiento(java.sql.Date.valueOf((String) cuentaDTO.getFechaVencimiento()));
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body("Formato de fechaVencimiento inválido. Usa 'YYYY-MM-DD'.");
                }
            } else if (cuentaDTO.getFechaVencimiento() instanceof java.util.Date && !(cuentaDTO.getFechaVencimiento() instanceof java.sql.Date)) {
                cuentaDTO.setFechaVencimiento(new java.sql.Date(((java.util.Date) cuentaDTO.getFechaVencimiento()).getTime()));
            }

            if (cuentaDTO.getDescripcion() == null) {
                cuentaDTO.setDescripcion("Compra a crédito");
            }

            cuentaDTO.setEstado("PENDIENTE");
            CuentasPagarDTO creada = cuentasPagarService.saveCuentaPagar(cuentaDTO);
            return ResponseEntity.ok(creada);
        } catch (IllegalArgumentException ex) {
            logger.warn("Validación fallida al registrar compra a crédito: {}", ex.getMessage());
            return ResponseEntity.badRequest().body("Error de validación: " + ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error al registrar compra a crédito: {}", ex.getMessage(), ex);
            // Devuelve el mensaje y stacktrace para depuración
            StringBuilder sb = new StringBuilder();
            sb.append("Error interno al registrar compra a crédito: ").append(ex.getMessage()).append("\n");
            for (StackTraceElement ste : ex.getStackTrace()) {
                sb.append(ste.toString()).append("\n");
            }
            return ResponseEntity.status(500).body(sb.toString());
        }
    }

    // Utilidad para mostrar el stacktrace en la respuesta (solo para depuración)
    private String getStackTraceAsString(Exception ex) {
        java.io.StringWriter sw = new java.io.StringWriter();
        java.io.PrintWriter pw = new java.io.PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
        return "Error de validación en los datos enviados: " + ex.getMessage();
    }
}