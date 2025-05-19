package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

<<<<<<< Updated upstream
=======
import java.util.List;

>>>>>>> Stashed changes
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.CuentasPagar;

@Repository
public interface CuentasPagarRepository extends JpaRepository<CuentasPagar, Long> {
<<<<<<< Updated upstream
}
=======

    // Buscar todas las cuentas asociadas a un proveedor
    List<CuentasPagar> findByProveedorId(Long proveedorId);
}
>>>>>>> Stashed changes
