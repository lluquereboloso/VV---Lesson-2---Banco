package etsisi.vyv2017.lab2junit_proy1;

import java.time.LocalDate;

public abstract class Tarjeta {
	protected String mNumero, mTitular;
	protected LocalDate mFechaDeCaducidad;
	/**
	 * Comment here
	 * 
	 * @label Tarjeta_CuentaAsociada
	 */
	protected Cuenta mCuentaAsociada;

	public Tarjeta(String numero, String titular, LocalDate fechaCaducidad) {
		mNumero = numero;
		mTitular = titular;
		mFechaDeCaducidad = fechaCaducidad;
	}

	public void setCuenta(Cuenta c) {
		mCuentaAsociada = c;
	}

	public abstract void retirar(double x) throws Exception;

	public abstract void ingresar(double x) throws Exception;

	public abstract void pagoEnEstablecimiento(String datos, double x) throws Exception;

	public abstract double getSaldo();
}