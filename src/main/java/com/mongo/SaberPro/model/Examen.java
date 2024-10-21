package com.mongo.SaberPro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "examenes")
public class Examen {

	@Id
	private String nRegistro;
	
	private int comunicacion;
	
	private int razonamiento;
	
	private int lectura;
	
	private int cCiudadanas;
	
	private int ingles;
	
	private int fProyectos;
	
	private int pCientifico;
	
	private int dSoftware;
	
	private String nIngles;
	
	private boolean anulado;

	public String getnRegistro() {
		return nRegistro;
	}

	public void setnRegistro(String nRegistro) {
		this.nRegistro = nRegistro;
	}

	public int getComunicacion() {
		return comunicacion;
	}

	public void setComunicacion(int comunicacion) {
		this.comunicacion = comunicacion;
	}

	public int getRazonamiento() {
		return razonamiento;
	}

	public void setRazonamiento(int razonamiento) {
		this.razonamiento = razonamiento;
	}

	public int getLectura() {
		return lectura;
	}

	public void setLectura(int lectura) {
		this.lectura = lectura;
	}

	public int getcCiudadanas() {
		return cCiudadanas;
	}

	public void setcCiudadanas(int cCiudadanas) {
		this.cCiudadanas = cCiudadanas;
	}

	public int getIngles() {
		return ingles;
	}

	public void setIngles(int ingles) {
		this.ingles = ingles;
	}

	public int getfProyectos() {
		return fProyectos;
	}

	public void setfProyectos(int fProyectos) {
		this.fProyectos = fProyectos;
	}

	public int getpCientifico() {
		return pCientifico;
	}

	public void setpCientifico(int pCientifico) {
		this.pCientifico = pCientifico;
	}

	public int getdSoftware() {
		return dSoftware;
	}

	public void setdSoftware(int dSoftware) {
		this.dSoftware = dSoftware;
	}

	public String getnIngles() {
		return nIngles;
	}

	public void setnIngles(String nIngles) {
		this.nIngles = nIngles;
	}
	
	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public int getTotal() {
	    double promedio = (comunicacion + razonamiento + lectura + cCiudadanas + ingles) / 5.0; 
	    
	    if (promedio % 1 >= 0.5) {
	        return (int) Math.ceil(promedio); 
	    } else {
	        return (int) Math.floor(promedio); 
	    }
	}


}
