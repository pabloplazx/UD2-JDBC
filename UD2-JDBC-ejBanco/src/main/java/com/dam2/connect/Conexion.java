package com.dam2.connect;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration // 1. "Soy una clase de configuración"
@ComponentScan(basePackages = "com.dam2") // 2. "Busca componentes (DAOs, Services) aquí"
@EnableTransactionManagement // 3. "¡Activa la magia de las transacciones!"
public class Conexion {
	
	// BEAN 1: La fuente de datos (La conexión física)
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // CORREGIDO: banco_db
		dataSource.setUrl("jdbc:mysql://localhost:3306/banco_db?useSSL=false&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		
		return dataSource;
	}
	
	// BEAN 2: La herramienta para ejecutar SQL
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	// BEAN 3: El Árbitro de las transacciones
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}