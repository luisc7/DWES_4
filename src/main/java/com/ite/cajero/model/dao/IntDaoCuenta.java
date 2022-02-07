package com.ite.cajero.model.dao;

import com.ite.cajero.model.beans.Cuenta;

public interface IntDaoCuenta {

	Cuenta findById(int idCuenta);

	int addSaldo(Cuenta cuenta, double cantidadIngresada);
	double consultarSaldo(Cuenta cuenta);
	int decSaldo(Cuenta cuenta, double cantidad);
}
