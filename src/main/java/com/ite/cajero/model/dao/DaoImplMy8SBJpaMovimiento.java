package com.ite.cajero.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.cajero.model.beans.Cuenta;
import com.ite.cajero.model.beans.Movimiento;
import com.ite.cajero.model.repository.IntRepositoryMovimiento;

@Service
public class DaoImplMy8SBJpaMovimiento implements IntDaoMovimiento {
	
	@Autowired IntRepositoryMovimiento mrepo;
	
	@Override
	public int ingreso(Cuenta cuenta, double cantidadIngresada) {
		Movimiento movimientoIngreso = new Movimiento(cantidadIngresada, new Date(), "Ingreso", cuenta);
		mrepo.save(movimientoIngreso);
		return 1;
	}

	@Override
	public int extraccion(Cuenta cuenta, double cantidadExtraida) {
		Movimiento movimientoExtraccion = new Movimiento(cantidadExtraida, new Date(), "Retirada", cuenta);
		mrepo.save(movimientoExtraccion);
		return 1;
	}

	@Override
	public int transferenciaEmitida(Cuenta cuenta, double cantidadTransferida) {
		Movimiento movimientoTransferenciaEmitida = new Movimiento(cantidadTransferida, new Date(), "Transferencia Emitida", cuenta);
		mrepo.save(movimientoTransferenciaEmitida);
		return 1;
	}

	@Override
	public int transferenciaRecibida(Cuenta cuentaDestino, double cantidadRecibida) {
		Movimiento movimientoTransferenciaRecibida = new Movimiento(cantidadRecibida, new Date(), "Transferencia Recibida", cuentaDestino);
		mrepo.save(movimientoTransferenciaRecibida);
		return 1;
	}

	@Override
	public List<Movimiento> movimientosCuenta(Cuenta cuenta) {
		
		List<Movimiento> misMovimientos = new ArrayList<Movimiento>();
		
		List<Movimiento> todosMovimientos = mrepo.findAll();
		for (Movimiento mov : todosMovimientos) {
			if (mov.getCuenta().equals(cuenta)) {
				misMovimientos.add(mov);
			}
		}	
		
		return misMovimientos;
	}

}
