package etsisi.vyv2017.lab2junit_proy1;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;


public class DebitoTest extends TestCase {
	Debito debito;
	Cuenta cuenta;

	public DebitoTest(String sTestName) {
		super(sTestName);
	}

	@Before
	public void setUp() throws Exception {
		cuenta = new Cuenta("0001.0002.12.1234567890", "Fulano de Tal");
		cuenta.ingresar(1000.0);
		Date hoy = new Date();
		LocalDate fechacaducidad = hoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		fechacaducidad.plusYears(4); // Caduca en 4 años
		debito = new Debito("1234567890123456", "Fulano de Tal", fechacaducidad);
		debito.setCuenta(cuenta);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetirar1000() {
		//TODO
		try {
			double saldoAnteriorCuenta = cuenta.getSaldo();
			double saldoAnteriorDebito = debito.getSaldo();
			
			debito.retirar(1000.0);

			double saldoActualCuenta = cuenta.getSaldo();
			double saldoActualDebito = debito.getSaldo();

			assertTrue(saldoActualCuenta == saldoAnteriorCuenta - 1000.0);
			assertTrue(saldoActualDebito == saldoAnteriorDebito - 1000.0);
		} catch (Exception e) {
			fail("Salta excepcion - No deberia haber fallado");
		}
	}
}