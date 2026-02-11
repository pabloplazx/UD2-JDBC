package com.dam2.app;

import java.sql.SQLException;

import com.dam.dao.EmpresaDao;
import com.dam2.connect.Conexion;
import com.dam2.daoImplement.EmpresaDaoImpl;
import com.dam2.services.EmpresaService;

public class EmpresaApp {

	public static void main(String[] args) {
		
		EmpresaDao dao = null;
		
		try {
			dao = new EmpresaDaoImpl(Conexion.getPool().getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		EmpresaService service = new EmpresaService(dao);
	}
	
	
	private static void mostrarMenu() {
		System.out.println("1. - Crear departamento");
		System.out.println();
	}

}
