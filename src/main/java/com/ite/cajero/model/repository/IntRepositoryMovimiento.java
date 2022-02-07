package com.ite.cajero.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.cajero.model.beans.Movimiento;

public interface IntRepositoryMovimiento extends JpaRepository<Movimiento, Integer>{

	@Query("select m from Movimiento m where m.cuenta.idCuenta = ?1")
	public List<Movimiento> findByCuenta(int idCuenta);

}
