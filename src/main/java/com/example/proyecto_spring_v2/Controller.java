package com.example.proyecto_spring_v2;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
public class Controller extends WebMvcConfigurationSupport {

	// Permite acceder a los archivos css, js etc..
	// Ya que por defecto spring capa este tipo de archivos
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
		super.addResourceHandlers(registry);
	}

	@Autowired
	private Repository Repository;
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private DemoDAO demoDao;

	@RequestMapping("/")
	public ModelAndView firstPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home.html");
		return mav;
	}

	@RequestMapping("/home")
	public ModelAndView logitec() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home.html");
		return modelAndView;
	}

	
	///////////////////EMPLEADO
	
	@RequestMapping("/home/empleado")
	public ModelAndView empleado() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Empleado_home.html");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/home/empleado/insertar")
	public ModelAndView insertar() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("insertar_empleado.html");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/home/empleado/insertar") // Map ONLY POST Requests
	public @ResponseBody String addNewUser(@RequestParam String nombre, @RequestParam long dinero,@RequestParam int dept_no) {
		Empleados n = new Empleados();
		n.setApellido(nombre);
		n.setSalario(dinero);
		Departamento departamento = Repository.findById(dept_no).get();
		n.setDepartamento(departamento);
		empleadoRepository.save(n);
		return "Saved";
	}

	@RequestMapping("/home/empleado/actualizar")
	public ModelAndView actualizar() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("actualizar_empleado.html");
		return mav;
	}

	@PostMapping("/home/empleado/update") // Map ONLY POST Requests
	public @ResponseBody String updateUser(@RequestParam int id, @RequestParam String nombre,
			@RequestParam int dinero,@RequestParam int dept_no) {
		Empleados n = empleadoRepository.findById(id).get();
		n.setApellido(nombre);
		n.setSalario(dinero);
		Departamento departamento = Repository.findById(dept_no).get();
		n.setDepartamento(departamento);
		empleadoRepository.save(n);
		return "Updated";
	}

	@RequestMapping("/home/empleado/eliminar")
	public ModelAndView eliminar() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("eliminar_empleado.html");
		return mav;
	}

	@PostMapping("/home/empleado/delete") // Map ONLY POST Requests
	public @ResponseBody String eliminarUser(@RequestParam int id) {
		Empleados n = empleadoRepository.findById(id).get();
		empleadoRepository.delete(n);
		return "Deleted";
	}

	@GetMapping("/home/empleado/all")
	public @ResponseBody Iterable<Empleados> getAllEmpleados() {
		return empleadoRepository.findAll();

	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/home/empleado/empleadoall")
	public ModelAndView indexView8() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("verEmpleados.html");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/verEmpleado")
	public @ResponseBody Optional<Empleados> indexView9(@RequestParam int id) {
		return empleadoRepository.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/EmpleadoAll")
	public @ResponseBody Iterable<Empleados> empleadoAll() {
		return empleadoRepository.findAll();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/home/empleado/deparEmpleado")
	public ModelAndView indexView11() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("verDeparEmpleados.html");
		return mav;
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/home/departamentos/departEmpleado")
	public @ResponseBody List<Empleados> indexView10(@RequestParam int dept_no) {
		List<Empleados> empleados = empleadoRepository.findEmpleado(dept_no);
		return empleados;
	}
	
	
	/////////////////////////DEPARTAMENTO
	

	@RequestMapping("/home/departamento")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("departamentos_home");

		return modelAndView;

	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/home/departamentos/insertar")
	public ModelAndView insertarDepartamento() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("insertar_departamento.html");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/home/departamentos/insertar") // Map ONLY POST Requests
	public @ResponseBody String addNewDepartamento(@RequestParam int dept_no,@RequestParam String dnombre, @RequestParam String loc ){
		Departamento n = new Departamento();
		n.setDeptNo(dept_no);
		n.setDnombre(dnombre);
		n.setLoc(loc);
		Repository.save(n);
		return "Saved";
	}

	@RequestMapping("/home/departamentos/actualizar")
	public ModelAndView actualizardepartamento() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("actualizar_departamento.html");
		return mav;
	}

	@PostMapping("/home/departamentos/update") // Map ONLY POST Requests
	public @ResponseBody String updateDepartamento(@RequestParam int dept_no, @RequestParam String nombre,
			 @RequestParam String loc) {
		Departamento n = Repository.findById(dept_no).get();
		n.setDnombre(nombre);
		n.setLoc(loc);
		Repository.save(n);
		return "Updated";
	}

	@RequestMapping("/home/departamentos/eliminar")
	public ModelAndView eliminardepartamento() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("eliminar_departamento.html");
		return mav;
	}

	@PostMapping("/home/departamentos/delete") // Map ONLY POST Requests
	public @ResponseBody String eliminarDepartamento(@RequestParam int dept_no) {
		Departamento n = Repository.findById(dept_no).get();
		Repository.delete(n);
		return "Deleted";
	}

	@GetMapping("/home/departamentos/all")
	public @ResponseBody Iterable<Departamento> getAllDepartamento() {
		return Repository.findAll();

	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/home/departamentos/departamentoall")
	public ModelAndView indexView6() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("verDepartamentos.html");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/verDepartamento")
	public @ResponseBody Optional<Departamento> indexView7(@RequestParam int dept_no) {

		return Repository.findById(dept_no);
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/departamentoAll")
	public @ResponseBody Iterable<Departamento> departamentoAll() {
		return Repository.findAll();
	}
	
	
	////////////////////////PROYECTOS
	

	@RequestMapping(method = RequestMethod.GET, value = "/home/proyectos")
	public ModelAndView indexView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Proyecto_home.html");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/home/proyectos/insertar")
	public ModelAndView indexView2() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("insertarProyecto.html");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public @ResponseBody String insertar(@RequestParam String nombre) {
		Proyecto proyecto = new Proyecto();
		proyecto.setNombre(nombre);
		demoDao.save(proyecto);
		return "Saved";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/home/proyectos/proyectosall")
	public ModelAndView indexView3() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("verProyectos.html");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/verProyecto")
	public @ResponseBody Optional<Proyecto> indexView5(@RequestParam int id) {

		return demoDao.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/proyectoAll")
	public @ResponseBody Iterable<Proyecto> proyectoAll() {
		return demoDao.findAll();
	}

	@RequestMapping("/home/proyectos/actualizar")
	public ModelAndView actualizarproyecto() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("actualizar_proyecto.html");
		return mav;
	}

	@PostMapping("/home/proyectos/update") // Map ONLY POST Requests
	public @ResponseBody String updateProyecto(@RequestParam int id, @RequestParam String nombre) {
		Proyecto n = demoDao.findById(id).get();
		n.setNombre(nombre);
		demoDao.save(n);
		return "Updated";
	}

	@RequestMapping("/home/proyectos/eliminar")
	public ModelAndView eliminarproyeto() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("eliminar_proyecto.html");
		return mav;
	}

	@PostMapping("/home/proyectos/delete") // Map ONLY POST Requests
	public @ResponseBody String eliminarProyecto(@RequestParam int id) {
		Proyecto n = demoDao.findById(id).get();
		demoDao.delete(n);
		return "Deleted";
	}

}