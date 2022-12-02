package com.ufg.BR100720.repository;

import java.util.List;
/* Usando JPA */
import org.springframework.data.jpa.repository.JpaRepository;

import com.ufg.BR100720.model.Alumno;

public interface IAlumnosJpa extends JpaRepository<Alumno,Integer>{

	public List<Alumno> getByCarnet(String carnet);
	
}
