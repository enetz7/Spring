package com.example.proyecto_spring_v2;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EmpleadoRepository extends CrudRepository<Empleados, Integer> {

	@Query(value="SELECT * FROM Empleados WHERE dept_no = ?1", nativeQuery = true)
	List<Empleados> findEmpleado(int dept_no);
}