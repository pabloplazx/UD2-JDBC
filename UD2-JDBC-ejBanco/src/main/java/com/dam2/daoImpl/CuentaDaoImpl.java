package com.dam2.daoImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dam2.dao.CuentaDao;

// @Repository: Le dice a Spring "Esta clase maneja datos, trátala con cariño"
// y traduce las excepciones raras de SQL a excepciones de Spring.
@Repository
public class CuentaDaoImpl implements CuentaDao {
	
	// Esta es nuestra herramienta principal
	private final JdbcTemplate jdbcTemplate;

	// INYECCIÓN POR CONSTRUCTOR
	// Spring ve esto y dice: "Necesito un JdbcTemplate, toma, aquí tiene uno configurado".
	public CuentaDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void retirarDinero(int id, double cantidad) {
		// Cuidado con el orden de los interrogantes (?)
		// 1er ? = cantidad
		// 2do ? = id
		String sql = "UPDATE cuentas SET saldo = saldo - ? WHERE id = ?";
		
		jdbcTemplate.update(sql, cantidad, id);
		
		System.out.println("DAO: Retirados " + cantidad +" del ID: " + id);
		
	}

	@Override
	public void ingresarDinero(int id, double cantidad) {
		String sql = "UPDATE cuentas SET saldo = saldo + ? WHERE id = ?";
		
		jdbcTemplate.update(sql, cantidad, id);
		
		System.out.println("DAO: Ingresados "+cantidad+" al ID: "+id);
		
	}
}
