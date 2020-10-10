package com.daily.sale.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String telefono;
	
	public Persona() {
		
	}
	
	public Persona(int id, String name, String telefono) {
		super();
		this.id = id;
		this.name = name;
		this.telefono = telefono;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
