package com.example.proyecto_spring_v2;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Proyecto{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProy;
	
	@Column(name="nombre")
	private String nombre;
	
	@JsonIgnoreProperties({"proyectos"})
	@ManyToMany(fetch = FetchType.EAGER,mappedBy="proyectos")
	private List<Empleados> empleados;
	
	
	public Proyecto(){
	}
	public Proyecto(int idProy,String nombre) {
		this.idProy=idProy;
		this.nombre=nombre;
	}
	public int getidProy(){
		return idProy;
	}
	public void setidProy(int idProy){
		this.idProy=idProy;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Empleados> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleado(List<Empleados> empleados) {
		this.empleados = empleados;
	}
}