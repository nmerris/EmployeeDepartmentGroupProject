package com.example.demo.controllers;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

             System.out.println(bindingResult.toString());

            return "departmentform";
        }

//        if (employeeRepository.findOne(department.getDeptHeadEmployee().getId()) == null)
//        {
//            // user entered a dept head employee id that was not in the employee table, so show an error message
//            return "errorpage";
//        }

//        Employee head = employeeRepository.findOne(department.getDeptHeadEmployee().getId());
//
//        department.setDeptHeadEmployee(head);

//        head.setDepartment(department);

        departmentRepository.save(department);

//
//        department.setDepartmentHeadFullName(head.getFirstName() + " "+head.getLastName());
////        model.addAttribute("head", head);
        model.addAttribute("department", department);

        return "confirmation";



    }


    @GetMapping("/addemployee")
    public String addemployee(Model model)
    {
        Employee employee= new Employee();
        model.addAttribute("employee", employee);

        Iterable<Department> departments = departmentRepository.findAll();
        model.addAttribute("alldepartments", departments);


        return "employeeform";

    }

    @PostMapping("/addemployee")
    public String postDep(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model)

    {
        Iterable<Department> departments = departmentRepository.findAll();

        if(bindingResult.hasErrors()) {
//            Iterable<Department> departments = departmentRepository.findAll();
            model.addAttribute("alldepartments", departments);
            return "employeeform";
        }


        employee.setDepartment(departmentRepository.findOne(employee.getDepartment().getId()));

        employeeRepository.save(employee);



//        if (employee.getHeadOfThisDepartment()!=null)
//        {
////            employee.getHeadOfThisDepartment().getDeptHeadEmployee().setId(employee.getId());
//
//            Department departmentwithhead = employee.getHeadOfThisDepartment();
//
//            departmentwithhead.setDeptHeadEmployee(employee);
//
//            departmentRepository.save(departmentwithhead);
//        }
//Employee employeetest = employeeRepository.findOne(employee.getId());

        model.addAttribute("employee", employee);

        System.out.println("********************************************************" + employee.getDepartment().getName());

        return "employeeconfirmation";

    }

    @GetMapping("/listall")
    public String displayall(Model model)
    {

        Iterable<Department> alldepartments = departmentRepository.findAll();

        model.addAttribute("alldepartments", alldepartments);

        return "departmentlist";

    }

    @RequestMapping("/show/{id}")
    public String showallmov(@PathVariable("id") long id, Model model){

        Department onedepartment = departmentRepository.findOne(id);
        model.addAttribute("onedepartment", onedepartment);

//        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + onedepartment.g);

        return "employeelist";
    }

    // allow user to add a movie to a director
    @RequestMapping("/update/{id}")
    //get mapping
    public String updatemoivetodir(@PathVariable("id") long id, Model model) {


        Department onedepart = departmentRepository.findOne(id);
        model.addAttribute("onedepart", onedepart);

        return "departmentform";
    }


}
