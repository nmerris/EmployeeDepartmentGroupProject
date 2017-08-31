package com.example.demo.controllers;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.models.Team;
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



        Department fullDeptObject = departmentRepository.findOne(employee.getDepartment().getId());
        employee.setDepartment(fullDeptObject);



        // check to see what the value is for 'no dept' from drop down
         System.out.println("###################################### employee depthead id was: " + employee.getDepartment().getId());





        // check to see if employee that was just entered in the form was entered as the head of any department
        if (employee.getHeadOfThisDepartment().getId() != -1)
        {
            // this employee is the head of some department

            Department headdepartment = departmentRepository.findOne(employee.getHeadOfThisDepartment().getId());

//            employee.setHeadOfThisDepartment(employee.getHeadOfThisDepartment());
            employee.setHeadOfThisDepartment(headdepartment);
            fullDeptObject.setDeptHeadEmployee(employee);


//            Department departmentwithhead = employee.getHeadOfThisDepartment();
//
//            departmentwithhead.setDeptHeadEmployee(employee);

            System.out.println("###################################### about to save to department repo.......");


//            departmentRepository.save(departmentwithhead);
        }

        else {
            employee.setHeadOfThisDepartment(null);
        }

//        Employee employeetest = employeeRepository.findOne(employee.getId());



        employeeRepository.save(employee);



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

    // show all employees
    @RequestMapping("/show/{id}")
    public String showallemployees(@PathVariable("id") long id, Model model){

        Department onedepartment = departmentRepository.findOne(id);
        model.addAttribute("onedepartment", onedepartment);

//        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + onedepartment.g);

        return "employeelist";
    }

    // allow user to update a department
    @RequestMapping("/update/{id}")
    //get mapping
    public String updatedepartment(@PathVariable("id") long id, Model model) {


        Department onedepart = departmentRepository.findOne(id);
        model.addAttribute("department", onedepart);

        return "departmentform";
    }


    // allow user to update one employee
    @RequestMapping("/updateemployee/{id}")
    //get mapping
    public String updateemployee(@PathVariable("id") long id, Model model) {


        Employee oneemployee = employeeRepository.findOne(id);
        model.addAttribute("employee", oneemployee);

        return "employeeform";
    }


    // allow user to delete one employee
    @RequestMapping("/deleteemployee/{id}")
    //get mapping
    public String deleteemployee(@PathVariable("id") long id, Model model) {

        Employee oneemployee = employeeRepository.findOne(id);


        // you MUST first remove the employee from the set of employees for their department, then you can delete the employee
        employeeRepository.findOne(id).getDepartment().removeEmployee(employeeRepository.findOne(id));
        employeeRepository.delete(id);





        long deptToGoTo = oneemployee.getDepartment().getId();


        return "redirect:/show/" + deptToGoTo;
    }

    @GetMapping("/team")
    public String addteam(Model model)
    {

        Team team= new Team();
        model.addAttribute("team", team);

        return "teamform";
    }



}
