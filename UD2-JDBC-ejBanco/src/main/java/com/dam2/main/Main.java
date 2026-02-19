package com.dam2.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dam2.connect.Conexion;
import com.dam2.service.BancoService;

public class Main {

	public static void main(String[] args) {
		
		// 1. Arranccamos Spring leyendo la configuración de Conexion.class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(Conexion.class);
		
		// 2. Pedimos el Bean del Servicio
		BancoService servicio = context.getBean(BancoService.class);
		
		// ---------------------------------------------------------
        // CASO A: Transferencia Normal (100€)
        // ---------------------------------------------------------
		System.out.println("\n**************************************************");
		System.out.println("CASO A: Transferencia de 100€ (Debe funcionar)");
		System.out.println("\n**************************************************");
		
		try {
			// Ana (ID 1) envía 100€ a Pepe (ID 2)
			servicio.realizarTransferencia(1, 2, 100.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// ---------------------------------------------------------
        // CASO B: Transferencia Gigante (5000€) -> Provoca Error
        // ---------------------------------------------------------
		System.out.println("\n**************************************************");
		System.out.println("CASO B: Transferencia de 5000€ (Debe fallar y hacer ROLLBACK");
		System.out.println("\n**************************************************");
		
		try {
			// Intentamos sacar 5000€ de Ana.
			// El DAO la hará (SQL update), pero luego el Servicio lanzará la Excepción..
			servicio.realizarTransferencia(1, 2, 5000.0);
		} catch (Exception e) {
			System.out.println("\n>> !EXCEPCIÓN CAPTURADA EN EL MAIN <<");
			System.out.println("Mensaje: "+e.getMessage());
			System.out.println("Spring ha detectado esto y ha ejecutado un ROLLBACK.");
			
		}
		
		context.close();
	}
}
