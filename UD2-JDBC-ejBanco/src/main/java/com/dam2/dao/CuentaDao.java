package com.dam2.dao;

public interface CuentaDao {
	
	void retirarDinero(int id, double cantidad);
	void ingresarDinero(int id, double cantidad);

}
