package skypro.employeeSpringMockito.controller;

import org.springframework.web.bind.annotation.*;
import skypro.employeeSpringMockito.model.Employee;
import skypro.employeeSpringMockito.record.EmployeeRequest;
import skypro.employeeSpringMockito.service.EmployeeService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public List<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/add")
    public void createEmployee(@RequestBody EmployeeRequest employeeRequest){
       this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable("id") int id){
        return this.employeeService.deleteEmployee(id);
    }

    @GetMapping("/{id}/search")
    public Employee searchEmployee(@PathVariable("id") int id){
        return this.employeeService.searchEmployee(id);
    }


}
