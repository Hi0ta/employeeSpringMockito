package skypro.employeeSpringMockito.service;

import org.springframework.stereotype.Service;
import skypro.employeeSpringMockito.model.Employee;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getListOfEmployeesByDepartment(int departmentId){
        return employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    public int getSalarySumOfDepartment(int departmentId){
        return getListOfEmployeesByDepartment(departmentId).stream()
                .mapToInt(Employee::getSalary).sum();
    }

    public int getMaxSalaryByDepartment(int departmentId){
        return getListOfEmployeesByDepartment(departmentId).stream()
                .mapToInt(Employee::getSalary).max().orElseThrow();
    }

    public int getMinSalaryByDepartment(int departmentId){
        return getListOfEmployeesByDepartment(departmentId).stream()
                .mapToInt(Employee::getSalary).min().orElseThrow();
    }

    public Set<Integer> getExistingDepartment(){
        return employeeService.getAllEmployees().stream()
                .map(Employee::getDepartment).collect(Collectors.toSet());
    }


    public  Map<Integer, List<Employee>> getListOfEmployeesByAllDepartments(){
        return getExistingDepartment().stream()
                .collect(Collectors.toMap(d -> d, this::getListOfEmployeesByDepartment));
    }


}
