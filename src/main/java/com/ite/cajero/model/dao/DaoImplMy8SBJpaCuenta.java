package com.ite.cajero.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.cajero.model.beans.Cuenta;
import com.ite.cajero.model.repository.IntRepositoryCuenta;

@Service
public class DaoImplMy8SBJpaCuenta implements IntDaoCuenta {
	
	@Autowired
	private IntRepositoryCuenta crepo;

	@Override
	public Cuenta findById(int idCuenta) {
		return crepo.findById(idCuenta).orElse(null);
	}
	
	@Override
	public double consultarSaldo(Cuenta cuenta) {
		Cuenta cuentaConsulta = crepo.getById(cuenta.getIdCuenta());
		return cuentaConsulta.getSaldo();
	}

	@Override
	public int addSaldo(Cuenta cuenta, double cantidadIngresada) {
		
		Cuenta cuentaAddSaldo = crepo.getById(cuenta.getIdCuenta());		
		double saldoPrevio = cuentaAddSaldo.getSaldo();
		cuentaAddSaldo.setSaldo(saldoPrevio+cantidadIngresada);
		crepo.save(cuentaAddSaldo);
		
		return 1;
	}

	@Override
	public int decSaldo(Cuenta cuenta, double cantidad) {
		
		Cuenta cuentaDecSaldo = crepo.getById(cuenta.getIdCuenta());		
		double saldoPrevio = cuentaDecSaldo.getSaldo();
		cuentaDecSaldo.setSaldo(saldoPrevio-cantidad);
		crepo.save(cuentaDecSaldo);
		
		return 1;
	}
	

}
