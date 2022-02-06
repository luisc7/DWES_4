package com.ite.cajero.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ite.cajero.model.beans.Movimiento;

public interface IntRepositoryMovimiento extends JpaRepository<Movimiento, Integer>{

}
