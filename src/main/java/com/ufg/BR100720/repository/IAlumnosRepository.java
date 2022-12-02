package com.ufg.BR100720.repository;

/* Usando CrudRepository */
import org.springframework.data.repository.CrudRepository;

import com.ufg.BR100720.model.Alumno;

public interface IAlumnosRepository extends CrudRepository<Alumno,Integer>{

}
