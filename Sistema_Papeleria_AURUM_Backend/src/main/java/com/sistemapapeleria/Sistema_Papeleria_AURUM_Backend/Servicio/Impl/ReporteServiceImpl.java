package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ReporteService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.VentasRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.CuentasPagarRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Ventas;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.CuentasPagar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private VentasRepository ventasRepository;

    @Autowired
    private CuentasPagarRepository cuentasPagarRepository;

    @Override
    public ByteArrayInputStream generarReporteIngresosEgresos() {
        double totalVentas = ventasRepository.findAll().stream()
            .mapToDouble(v -> v.getTotal() != null ? v.getTotal() : 0.0)
            .sum();

        double totalCompras = cuentasPagarRepository.findAll().stream()
            .mapToDouble(c -> c.getMonto() != null ? c.getMonto() : 0.0)
            .sum();

        double ingresos = totalVentas - totalCompras;

        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Reporte de Ingresos y Egresos", font);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Total Ventas (Ingresos): $" + totalVentas));
            document.add(new Paragraph("Total Compras a Proveedores (Egresos): $" + totalCompras));
            document.add(new Paragraph("Ingresos Netos: $" + ingresos));

            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF: " + e.getMessage());
        }
    }
}
