package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

<<<<<<< Updated upstream
import java.util.List;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasPagarDTO;

public interface CuentasPagarService {
    CuentasPagarDTO saveCuentaPagar(CuentasPagarDTO cuentaDTO);
    List<CuentasPagarDTO> getAllCuentasPagar();
    CuentasPagarDTO getCuentaPagarById(Long id);
    void deleteCuentaPagar(Long id);
<<<<<<< Updated upstream
}
=======
    CuentasPagarDTO updateCuentaPagar(Long id, CuentasPagarDTO dto);
    List<CuentasPagarDTO> getCuentasByProveedorId(Long proveedorId);
    CuentasPagarDTO marcarComoPagada(Long id);
}
>>>>>>> Stashed changes
=======
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.CuentasPagar;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.CuentasPagarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuentasPagarService {

    @Autowired
    private CuentasPagarRepository repository;

    public CuentasPagar guardarCuenta(CuentasPagar cuenta) {
        return repository.save(cuenta);
    }

    public Optional<CuentasPagar> editarCuenta(Long id, CuentasPagar datosActualizados) {
        return repository.findById(id).map(cuenta -> {
            cuenta.setProveedorId(datosActualizados.getProveedorId());
            cuenta.setMonto(datosActualizados.getMonto());
            cuenta.setEstado(datosActualizados.getEstado());
            return repository.save(cuenta);
        });
    }
}
>>>>>>> Stashed changes
