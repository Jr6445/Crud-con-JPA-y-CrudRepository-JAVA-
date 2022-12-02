package com.ufg.BR100720;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ufg.BR100720.model.Alumno;
import com.ufg.BR100720.repository.IAlumnosJpa;
import com.ufg.BR100720.repository.IAlumnosRepository;

@SpringBootApplication
public class Br100720Application implements CommandLineRunner{
	@Autowired
	private IAlumnosRepository repositorio;
	
	@Autowired
	private IAlumnosJpa jpa;

	public static void main(String[] args) {
		SpringApplication.run(Br100720Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//test();
		//guardar();
		//guardarYVerificar();
		//modificar();
		//BuscarTodosJPa();
		//buscarTodosOrdenados();
		//buscarTodosYCompaginar();
		//verificarYEliminar();
	}
	
	private void test() {
		System.out.println(repositorio);
		System.out.println("Conexion establecida");
	}
	
	private void guardar() {
		Alumno alumno = new Alumno();
		alumno.setApellidos("angel");
		alumno.setCarnet("21f3");
		alumno.setCarrera("Computacion");
		alumno.setNombres("guzan");
		
		Alumno alumno1 = new Alumno();
		alumno1.setNombres("andres");
		alumno1.setCarnet("j212");
		alumno1.setApellidos("lio");
		alumno1.setCarrera("Videojuegos");
		
		Alumno alumno2 = new Alumno();
		alumno2.setNombres("garby");
		alumno2.setCarnet("s723");
		alumno2.setApellidos("nose");
		alumno2.setCarrera("programador");
		
		Alumno alumno3 = new Alumno();
		alumno3.setNombres("neli");
		alumno3.setCarnet("2ds2");
		alumno3.setApellidos("Mas");
		alumno3.setCarrera("Diseño");
		
		Alumno alumno4 = new Alumno();
		alumno4.setNombres("Ronald");
		alumno4.setCarnet("w3s1");
		alumno4.setApellidos("junior");
		alumno4.setCarrera("Idiomas");
		
		repositorio.save(alumno);
		
		repositorio.save(alumno1);
		repositorio.save(alumno2);
		repositorio.save(alumno3);
		repositorio.save(alumno4);
	}
	
	
	private void guardarYVerificar() {
		Alumno alumno = new Alumno();
		alumno.setApellidos("Casio");
		alumno.setCarnet("2sd22");
		alumno.setCarrera("Computacion");
		alumno.setNombres("Marco");
		
		
		List<Alumno> list = BuscarCarnet(alumno.getCarnet());
		if(list.isEmpty()) {
			jpa.save(alumno);		
		}else {
			System.out.println("Ese carnet ya existe");
		}
		
	}
	
	public List<Alumno>BuscarCarnet(String carnet){
		return (List<Alumno>)jpa.getByCarnet(carnet);
	}
	
	
	
	private void verificarYEliminar() {
		String carnet = "2sd22";
		
		List<Alumno> list = BuscarCarnet("2sd22");
		for(Alumno alu : list) {
			Integer id = alu.getId();
			jpa.deleteById(id);
		}
	}
	
	
	
	private void modificar() {
		Optional<Alumno> optional = repositorio.findById(11);
		if(optional.isPresent()) {
			Alumno aluTemp = new Alumno();
			aluTemp = optional.get();
			aluTemp.setNombres("miguel");
			aluTemp.setApellidos("Gonzales");
			repositorio.save(aluTemp);
			System.out.println(optional.get());
		}else
			System.out.println("Alumno no encontrado");
	}
	
	private void BuscarTodosJPa() {
		List<Alumno> lista = jpa.findAll();
		for(Alumno alu : lista)
			System.out.println(alu.getId() + " "+alu.getNombres()+" "+alu.getApellidos()+" "+alu.getCarnet()+" "+alu.getCarrera());
		
	}
	
	private void buscarTodosOrdenados() {
		Scanner sc = new Scanner(System.in);
		int op = 0;
		
		System.out.println("Como desea organizar los datos?");
		System.out.println("1- por nombre");
		System.out.println("2- por apellido");
		System.out.println("3- por carnet");
		System.out.println("4- por carrera");
		System.out.println("4- por id");
		op=sc.nextInt();
		
		switch(op) {
		case 1:
			List<Alumno> lista = jpa.findAll(Sort.by("nombres"));
			for (Alumno alu : lista)
				System.out.println(alu.getId() + " "+alu.getNombres()+" "+alu.getApellidos()+" "+alu.getCarnet()+" "+alu.getCarrera() );
			break;
		case 2:
			List<Alumno> lista2 = jpa.findAll(Sort.by("apellidos"));
			for (Alumno alu : lista2)
				System.out.println(alu.getId() + " "+alu.getNombres()+" "+alu.getApellidos()+" "+alu.getCarnet()+" "+alu.getCarrera() );
			break;
		case 3:
			List<Alumno> lista3 = jpa.findAll(Sort.by("carnet"));
			for (Alumno alu : lista3)
				System.out.println(alu.getId() + " "+alu.getNombres()+" "+alu.getApellidos()+" "+alu.getCarnet()+" "+alu.getCarrera() );
			break;
		case 4:
			List<Alumno> lista4 = jpa.findAll(Sort.by("carrera"));
			for (Alumno alu : lista4)
				System.out.println(alu.getId() + " "+alu.getNombres()+" "+alu.getApellidos()+" "+alu.getCarnet()+" "+alu.getCarrera() );
			break;
		case 5:
			List<Alumno> lista5 = jpa.findAll(Sort.by("id"));
			for (Alumno alu : lista5)
				System.out.println(alu.getId() + " "+alu.getNombres()+" "+alu.getApellidos()+" "+alu.getCarnet()+" "+alu.getCarrera() );
			
		}
		
	}
	
	private void buscarTodosYCompaginar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Cuantas alumnos desea ver por pagina?");
		int pag = sc.nextInt();
		
		Page<Alumno> page = jpa.findAll(PageRequest.of(0, pag));
		System.out.println("Total Alumnos: "+ page.getTotalElements());
		System.out.println("Total paginas: "+page.getTotalPages());
		
		for (Alumno alu : page)
			System.out.println(alu.getId() + " "+alu.getNombres()+" "+alu.getApellidos()+" "+alu.getCarnet()+" "+alu.getCarrera());
	}
	
	
	
	

}
