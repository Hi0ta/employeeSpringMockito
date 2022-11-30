package skypro.employeeSpringMockito.service;

import org.springframework.stereotype.Service;
import skypro.employeeSpringMockito.model.Employee;
import skypro.employeeSpringMockito.record.EmployeeRequest;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    Map<Integer, Employee> employees = new HashMap<>();

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee name should be set");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());

        this.employees.put(employee.getId(), employee);
    }

    public List<Employee> getAllEmployees() {
        return employees.values().stream().collect(Collectors.toList());
    }

    public void deleteEmployee(int id){
       this.employees.remove(id);
    }
    // String employee = String.valueOf(employees.get(id));
    //      return employee + " удален";

    public Employee searchEmployee(int id){
        return this.employees.get(id);
    }

}