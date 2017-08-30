package com.example.demo.controllers;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;



    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("*************************** default route RequestMapping");



        model.addAttribute("departments", departmentRepository.findAll());



        return "index";

    }

    @GetMapping("/adddepartment")
    public String adddepartment(Model model)
    {
        Department department= new Department();
        model.addAttribute("department", department);

        return "departmentform";

    }

    @PostMapping("/adddepartment")
    public String postDep(@Valid @ModelAttribute("department") Department department, BindingResult bindingResult, Model model)

    {
        if(bindingResult.hasErrors()) {

            return "departmentform";
        }

        if (employeeRepository.findOne(department.getDepartmentHeadEmployeeId())==null)
        {
            return "errorpage";
        }

        Employee head = employeeRepository.findOne(department.getDepartmentHeadEmployeeId());





//        head.setDepartment(department);

        department.setDepartmentHeadFullName(head.getFirstName() + " "+head.getLastName());
//        model.addAttribute("head", head);
        model.addAttribute("department", department);

        return "confirmation";



    }






}
