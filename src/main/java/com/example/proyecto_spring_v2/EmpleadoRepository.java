package com.example.proyecto_spring_v2;

import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EmpleadoRepository extends CrudRepository<Empleados, Integer> {

}