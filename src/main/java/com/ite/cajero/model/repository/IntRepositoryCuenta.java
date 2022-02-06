package com.ite.cajero.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ite.cajero.model.beans.Cuenta;

public interface IntRepositoryCuenta extends JpaRepository<Cuenta, Integer>{

}
