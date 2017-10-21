package etsisi.vyv2017.lab2junit_proy1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class CuentaTest extends TestCase {
	Cuenta cuenta;

	public CuentaTest(String sTestName) {
		super(sTestName);
	}

	@Before
	public void setUp() throws Exception {
		cuenta = new Cuenta("0001.0002.12.1234567890", "Fulano de Tal");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIngresar1000() {
		try {
			cuenta.ingresar(1000);
			assertTrue(cuenta.getSaldo() == 1000.0);
		} catch (Exception e) {
			fail("No deberia haber fallado");
		}
	}

	@Test
	public void testRetirar1000() {
		//TODO
		testIngresar1000();
		try {
			cuenta.retirar(1000.0);
			assertTrue(cuenta.getSaldo() == 0.0);
		} catch (Exception e) {
			fail("No deberia haber fallado");
		}
		
	}

	@Test
	public void testIngresoYRetirada() {
		try {
			cuenta.ingresar(1000.0);
			cuenta.retirar(300.0);
			assertTrue(cuenta.getSaldo() == 700.0);
		} catch (Exception e) {
			fail("No deberia haber fallado");
		}

	}

}