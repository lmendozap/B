package com.model;

public class Medida {
	private String pais;
	private String anio;
	private String mes;
	private int temperatura;
	private int precipitacion;
	
	public Medida(){		
	}
	
	public Medida(String pais,String anio,String mes,int temperatura,int precipitacion){
		this.pais=pais;
		this.anio=anio;
		this.mes=mes;
		this.temperatura=temperatura;
		this.precipitacion=precipitacion;
	}
	
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public int getPrecipitacion() {
		return precipitacion;
	}

	public void setPrecipitacion(int precipitacion) {
		this.precipitacion = precipitacion;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	
	
	
}
