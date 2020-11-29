package com.example.proyecto_spring_v2;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * The persistent class for the departamentos database table.
 *
 */
@Entity
@Table(name="departamentos")
@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dept_no")
	private int deptNo;

	private String dnombre;

	private String loc;
	
	@JsonIgnoreProperties({"departamento"})
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "departamento")
	private List<Empleados> empleados;
	
	


	public Departamento() {
	}

	public int getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDnombre() {
		return this.dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	public List<Empleados> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleado(List<Empleados> empleados) {
		this.empleados = empleados;
	}

}