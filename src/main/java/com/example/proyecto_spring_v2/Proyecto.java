package com.example.proyecto_spring_v2;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
//@NamedQuery(name="Proyecto.findOne", query="SELECT p FROM proyecto p WHERE p.id=?1")
public class Proyecto{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idProy")
	private int id;
	@Column(name="nombre")
	private String nombre;
	
	@JsonIgnore
	@ManyToMany(mappedBy="proyectos")
	private List<Empleados> empleados;
	
	public Proyecto(){
	}
	public Proyecto(int id,String nombre) {
		this.id=id;
		this.nombre=nombre;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
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