package skypro.employeeSpringMockito;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.employeeSpringMockito.model.Employee;
import skypro.employeeSpringMockito.service.DepartmentService;
import skypro.employeeSpringMockito.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTests {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    private List<Employee> actualEmployees;

    @BeforeEach
    public void setUp(){
        Employee employee1 = new Employee("Ivan", "Ivanov", 1, 1_000);
        Employee employee2 = new Employee("Petr", "Petrov", 2, 3_000);
        Employee employee3 = new Employee("Sergey", "Sergeev", 1, 4_000);
        Employee employee4 = new Employee("Lara", "Leonteva", 1, 1_500);
        Employee employee5 = new Employee("Lena", "Larina", 2, 5_000);

        actualEmployees = new ArrayList<>(List.of(employee1, employee2, employee3, employee4, employee5));

        when(employeeService.getAllEmployees()).thenReturn(actualEmployees);
    }

    @Test
    public void checkGetListOfEmployeesByDepartment(){
        final int departmentId = 1;

        final List<Employee> standard = actualEmployees.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
        final List<Employee> checked = departmentService.getListOfEmployeesByDepartment(departmentId);
            assertEquals(checked, standard);
    }

    @Test
    public void checkGetListOfEmployeesByDepartmentWhenDepartmentDoesNotExist(){
        final int departmentId = 5;

        final List<Employee> standard = actualEmployees.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
        final List<Employee> checked = departmentService.getListOfEmployeesByDepartment(departmentId);
        assertEquals(checked, standard);
    } // в чем смысл такого теста? если отдела не существует в обоих случаях будет пусто...

    @Test
    public void checkGetSalarySumOfDepartment(){
        final int departmentId = 2;
        final int standard = actualEmployees.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary).sum();
        final int checked = departmentService.getSalarySumOfDepartment(departmentId);
        assertEquals(checked, standard);
    }

    @Test
    public void checkGetSalarySumOfDepartmentWhenDepartmentDoesNotExist(){
        final int departmentId = 5;
        final int checked = departmentService.getSalarySumOfDepartment(departmentId);
        assertEquals(checked, 0);
    }

    @Test
    public void checkGetMaxSalaryByDepartment(){
        final int departmentId = 1;
        final int standard = actualEmployees.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary).max().orElseThrow();
        final int checked = departmentService.getMaxSalaryByDepartment(departmentId);
        assertEquals(checked, standard);
    }

    @Test
    public void checkGetMinSalaryByDepartment(){
        final int departmentId = 1;
        final int standard = actualEmployees.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary).min().orElseThrow();
        final int checked = departmentService.getMinSalaryByDepartment(departmentId);
        assertEquals(checked, standard);
    }

    @Test
    public void checkGetExistingDepartment(){
    final Set<Integer> standard = actualEmployees.stream()
            .map(Employee::getDepartment).collect(Collectors.toSet());
    final Set<Integer> checked = departmentService.getExistingDepartment();
        assertEquals(checked, standard);
    }

    @Test
    public void checkGetListOfEmployeesByAllDepartments(){
        final Map<Integer, List<Employee>> standard = actualEmployees.stream()
                .map(Employee::getDepartment).collect(Collectors.toSet()).stream()
                .collect(Collectors.toMap(d -> d, d -> actualEmployees.stream().filter(employee -> employee.getDepartment() == d)
                        .collect(Collectors.toList())));
        final Map<Integer, List<Employee>> checked = departmentService.getListOfEmployeesByAllDepartments();
        assertEquals(checked, standard);
    }
}
