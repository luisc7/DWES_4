package com.ite.cajero.model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Movimiento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idMovimiento;
	private Cuenta cuenta;
	private Date fecha;
	private BigDecimal cantidad;
	private String operacion;
	
	public Movimiento() {
		super();
	}

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	@Override
	public String toString() {
		return "Movimiento [idMovimiento=" + idMovimiento + ", cuenta=" + cuenta + ", fecha=" + fecha + ", cantidad="
				+ cantidad + ", operacion=" + operacion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMovimiento;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Movimiento))
			return false;
		Movimiento other = (Movimiento) obj;
		if (idMovimiento != other.idMovimiento)
			return false;
		return true;
	}
	
	
}
