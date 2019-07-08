package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.modal.EmployeeModal;



@RestController

@RequestMapping("/main")
public class EmployeeController {

	@Autowired
	private EmployeeDao empDao;
	@RequestMapping(value="/createEmployee", method = RequestMethod.POST)
	public String creaateEmp(@RequestBody EmployeeModal empData) {
		System.out.println(empData.getName());
		empDao.save(empData);
		return "successfully created";
		
	}
	@RequestMapping("/getDetails/{id}")
	public Optional<EmployeeModal> getEmpDetails(@PathVariable("id") Integer id ){
		return  empDao.findById(id);
	}
	
	@RequestMapping(value="/deleteEmp/{id}", method = RequestMethod.DELETE)
	public boolean deleteEmployee(@PathVariable("id") Integer id) {
		Optional<EmployeeModal> emp=empDao.findById(id);
		if(emp.isPresent()) {
			empDao.deleteById(id);	
			return true;
		}
		else {
			return false;	
		}
		
	}
	
	@RequestMapping(value="/updateEmployee/{id}", method=RequestMethod.PATCH)
	public String updateEmployee(@PathVariable Integer id, @RequestBody EmployeeModal empData) {
		Optional<EmployeeModal> emp= empDao.findById(id);
		System.out.println(emp.get().getName());
		if(emp.isPresent()) {
			
			empDao.save(empData);
			return "successfull";
		}else {
			return "failed";
		}
		
	}
}
