package etsisi.vyv2017.lab2junit_proy1;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class CreditoTest extends TestCase {
	Cuenta cuenta;
	Credito tarjeta;

	public CreditoTest(String sTestName) {
		super(sTestName);
	}

	@Before
	public void setUp() throws Exception {
		cuenta = new Cuenta("0001.0002.12.1234567890", "Fulano de Tal");
		cuenta.ingresar(1000.0);
		Date hoy = new Date();
		LocalDate fechacaducidad = hoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		fechacaducidad.plusYears(4); // Caduca en 4 años
		tarjeta = new Credito("1234567890123456", "Fulano de Tal", fechacaducidad, 1000.0); // 1000€ de crédito
		tarjeta.setCuenta(cuenta);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIngresar500() {
		try {
			double saldoAnteriorCuenta = cuenta.getSaldo();
			double saldoAnteriorTarjeta = tarjeta.getSaldo();
			double creditoAnterior = tarjeta.getCreditoDisponible();

			tarjeta.ingresar(500.0);

			double saldoActualCuenta = cuenta.getSaldo();
			double saldoActualTarjeta = tarjeta.getSaldo();
			double creditoActual = tarjeta.getCreditoDisponible();

			assertTrue(saldoActualCuenta == saldoAnteriorCuenta + 500);
			assertTrue(saldoActualTarjeta == saldoAnteriorTarjeta);
			assertTrue(creditoActual == creditoAnterior);
		} catch (Exception e) {
			fail("Salta excepcion - No deberia haber fallado");
		}
	}

	@Test
	public void testRetirar300() {
		// TODO
	}

	@Test
	public void testLiquidar() {
		try {
			double saldoAnteriorCuenta = cuenta.getSaldo();
			tarjeta.pagoEnEstablecimiento("Zara", 120.0);
			tarjeta.pagoEnEstablecimiento("El Corte Inglés", 230.0);
			tarjeta.liquidar(10, 2017);
			assertTrue(saldoAnteriorCuenta == cuenta.getSaldo() - 350.0);
		} catch (Exception e) {
			fail("Salta excepcion - No deberia haber fallado");
		}
	}

	public static void main(String args[]) {
		Result result = JUnitCore.runClasses(CreditoTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

	}
}