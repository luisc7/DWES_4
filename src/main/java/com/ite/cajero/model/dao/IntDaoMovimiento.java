package com.ite.cajero.model.dao;

import java.util.List;

import com.ite.cajero.model.beans.Cuenta;
import com.ite.cajero.model.beans.Movimiento;

public interface IntDaoMovimiento {

	int ingreso(Cuenta cuenta, double cantidadIngresada);
	int extraccion(Cuenta cuenta, double cantidad);
	int transferenciaEmitida(Cuenta cuenta, double cantidad);
	int transferenciaRecibida(Cuenta cuentaDestino, double cantidad);
	List<Movimiento> movimientosCuenta(Cuenta cuenta);
}
