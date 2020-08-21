package com.botti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Birthday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String idUsuario;
	private String fechaCumple;
		
		
	public Birthday() {
		super();
	}


	public Birthday(String idUsuario, String fechaCumple) {
		super();
		this.idUsuario = idUsuario;
		this.fechaCumple = fechaCumple;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getFechaCumple() {
		return fechaCumple;
	}


	public void setFechaCumple(String fechaCumple) {
		this.fechaCumple = fechaCumple;
	}
	
			
	


}
