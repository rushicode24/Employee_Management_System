package com.Rushikesh.Employee_System;


import com.Rushikesh.Employee_System.Entity.Employee;
import com.Rushikesh.Employee_System.Service.EmpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private EmpService empService;

    @GetMapping("/")
    public String index(Model m) {
        List<Employee> list = empService.getAllEmp();
        m.addAttribute("empList", list);   // here error was i was imported model class or library from different
        // so it was ot compatible after adding right library method addattribute became available
        return "index";
    }

    @GetMapping("/loadEmpSave")
    public String loadEmpSave() {
        return "emp_save";
    }

    @GetMapping("/EditEmp/{id}")
    public String EditEmp(@PathVariable int id, Model m) {
        Employee emp = empService.getEmpById(id);
        m.addAttribute("emp", emp);
        return "edit_emp";
    }

    @PostMapping("/saveEmp")
    public String saveEmp(@ModelAttribute Employee emp, HttpSession session) {
        Employee newEmp = empService.saveEmp(emp);
        //  System.out.println(emp);
        if (newEmp != null) {
            //  System.out.println("Save success");
            session.setAttribute("msg", "save Successfully");
        } else {
            // System.out.println(" something wrong on server");
            session.setAttribute("msg", "something wrong on server");

        }
        return "redirect:/loadEmpSave";      // error may cause- space before the redirect can cause the template error
        // or unbale to load or display the empsave mapping page.
    }

    @PostMapping("/updateEmpDtls")
    public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
        Employee updateEmp = empService.saveEmp(emp);
        //  System.out.println(emp);
        if (updateEmp != null) {
            //  System.out.println("Save success");
            session.setAttribute("msg", "update Successfully");
        } else {
            // System.out.println(" something wrong on server");
            session.setAttribute("msg", "something wrong on server");

        }
        return "redirect:/";      // error may cause- space before the redirect can cause the template error
        // or unbale to load or display the empsave mapping page.
    }

    @GetMapping("/deleteEmp/{id}")
    public String loadEmpSave(@PathVariable int id, HttpSession session) {
        boolean f = empService.deleteEmp(id);
        if (f) {
            session.setAttribute("msg", "delete Successfully");
        } else {
            session.setAttribute("msg", "something wrong on server");
        }
        return "redirect:/";
    }
}
