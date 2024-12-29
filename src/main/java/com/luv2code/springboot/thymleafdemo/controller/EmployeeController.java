package com.luv2code.springboot.thymleafdemo.controller;

import com.luv2code.springboot.thymleafdemo.entity.Employee;
import com.luv2code.springboot.thymleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model themodel){
        Employee emp = new Employee();
        themodel.addAttribute("employee",emp);
        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String update(@RequestParam(name = "employeeId") int id,Model themodel){
        Employee emp = employeeService.findById(id);
        themodel.addAttribute("employee",emp);
        return "employees/employee-form";
    }

    @GetMapping("/deleteEmployee")
    public String delete(@RequestParam(name = "employeeId") int id,Model themodel){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee")Employee emp){
        employeeService.save(emp);
        return"redirect:/employees/list";
    }


}
