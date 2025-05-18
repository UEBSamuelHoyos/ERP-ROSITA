package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:4200")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/ingresos-egresos/pdf")
    public ResponseEntity<byte[]> descargarReporteIngresosEgresos() {
        byte[] pdf = reporteService.generarReporteIngresosEgresos().readAllBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "reporte_ingresos_egresos.pdf");
        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
