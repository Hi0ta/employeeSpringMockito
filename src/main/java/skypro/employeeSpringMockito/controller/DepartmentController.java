package skypro.employeeSpringMockito.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skypro.employeeSpringMockito.model.Employee;
import skypro.employeeSpringMockito.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/{id}/employees")
    public List<Employee> getListOfEmployeesByDepartment(@PathVariable("id") int departmentId){
    return this.departmentService.getListOfEmployeesByDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSalarySumOfDepartment(@PathVariable("id") int departmentId){
        return this.departmentService.getSalarySumOfDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public int getMaxSalaryByDepartment(@PathVariable("id") int departmentId){
        return this.departmentService.getMaxSalaryByDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public int getMinSalaryByDepartment(@PathVariable("id") int departmentId){
        return this.departmentService.getMinSalaryByDepartment(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getListOfEmployeesByAllDepartments(){
        return this.departmentService.getListOfEmployeesByAllDepartments();
    }
}
