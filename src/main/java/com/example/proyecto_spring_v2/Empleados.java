package com.example.proyecto_spring_v2;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





/**
 * The persistent class for the equipo database table.
 *
 */
@Entity
public class Empleados{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int emp_no;

	private int salario;

	private String apellido;

	@JsonIgnoreProperties({"empleados"})
	@ManyToOne
    @JoinColumn(name = "dept_no")
	private Departamento departamento;
	
	@JsonIgnoreProperties({"empleados"})
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="asignacionproyecto",
		joinColumns={
				@JoinColumn(name="emp_no")
		},
		inverseJoinColumns= {
				@JoinColumn(name="idProy")
		}
	)
    private List<Proyecto> proyectos;

	public Empleados() {
	}

	public Empleados(int emp_no,String apellido,int salario) {
		this.emp_no=emp_no;
		this.apellido=apellido;
		this.salario=salario;
	}

	public int getEmp_no() {
		return this.emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	
	public int getSalario() {
		return this.salario;
	}

	public void setSalario(long dinero) {
		this.salario = (int) dinero;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public List<Proyecto> getProyectos() {
		return this.proyectos;
	}

	public void setProyecto(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	
	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}
