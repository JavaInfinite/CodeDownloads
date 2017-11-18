/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javainfinite.Application.Controller;

import com.javainfinite.DAO.EmployeeDAO;
import com.javainfinite.model.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vikra
 */
@RestController
public class EmployeeController{
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    @RequestMapping(value="/getAllEmployees", method=RequestMethod.GET)
    
    public List<Employee> getAllEmployees(){
        List<Employee> empList = (List<Employee>) employeeDAO.findAll();
        System.out.println(empList.size());
        return empList;
    }
    
    @RequestMapping(value="/saveEmployee", method=RequestMethod.POST)
    public Employee saveEmployee(@RequestBody Employee employee){
        Employee emp = employeeDAO.save(employee);
        return emp;
    }
    
    @RequestMapping(value="/getById/{id}", method=RequestMethod.GET)
    public Employee getByIdEmployee( @PathVariable("id") long id){
        Employee emp = employeeDAO.findOne(id);
        return emp;
    }
    
    @RequestMapping(value="/updateEmployee/{id}", method=RequestMethod.PUT)
    public Employee updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee){
        Employee emp = employeeDAO.findOne(id);
        emp.setEmpName(employee.getEmpName());
        emp.setEmpDept(employee.getEmpDept());
        Employee savedEmp = employeeDAO.save(emp);
        return savedEmp;
    }
    
    @RequestMapping(value="/deleteEmployee/{id}", method=RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable("id") long id){
         employeeDAO.delete(employeeDAO.findOne(id));
    }
}
