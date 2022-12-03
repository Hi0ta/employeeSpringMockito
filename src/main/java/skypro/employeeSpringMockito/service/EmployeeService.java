package skypro.employeeSpringMockito.service;

import org.springframework.stereotype.Service;
import skypro.employeeSpringMockito.exception.FieldsShouldNotBeEmptyException;
import skypro.employeeSpringMockito.model.Employee;
import skypro.employeeSpringMockito.record.EmployeeRequest;

import java.util.*;

@Service
public class EmployeeService {
    Map<Integer, Employee> employees = new HashMap<>();

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new FieldsShouldNotBeEmptyException("поля Имя и Фамилия должны быть заполнены");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());

        if (employees.containsValue(employee)){
            throw new IllegalArgumentException("такой сотрудник уже существует");
        }
        this.employees.put(employee.getId(), employee);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public String deleteEmployee(int id) {
        String employee = String.valueOf(employees.get(id));
        this.employees.remove(id);
        return employee + " удален";
    }

    public Employee searchEmployee(int id){
        return this.employees.get(id);
    }

}