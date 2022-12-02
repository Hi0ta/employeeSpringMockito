package skypro.employeeSpringMockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.employeeSpringMockito.model.Employee;
import skypro.employeeSpringMockito.record.EmployeeRequest;
import skypro.employeeSpringMockito.service.EmployeeService;
//import skypro.employeeSpringMockito.exception.FieldsShouldNotBeEmptyException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRequest employeeRequest;
    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        when(employeeRequest.getFirstName()).thenReturn("Ivan");
        when(employeeRequest.getLastName()).thenReturn("Ivanov");
        when(employeeRequest.getDepartment()).thenReturn(1);
        when(employeeRequest.getSalary()).thenReturn(1000);
    }

    @Test
    public void checkAddEmployee() {
        employeeService.addEmployee(employeeRequest);
        int employeeId = 2;
        assertNotNull(employeeService.getEmployees());
        assertThrows(IllegalArgumentException.class, () -> employeeService.addEmployee(employeeRequest));
        assertNull(employeeService.getEmployees().get(employeeId));
    }//если выброс исключения тестить отдельно от создания+добавления сотрудника то возникает конфликт между тестами при общем запуске
     //          почему? и как этого избежать?

//    @Test
//    public void checkExceptionWhenAddingFirstNameFieldEmployee() {
//        when(employeeRequest.getFirstName()).thenReturn(null);
//        assertThrows(FieldsShouldNotBeEmptyException.class, () -> employeeService.addEmployee(employeeRequest));
//    } // этот тест запускается и работает только отдельно, если его запустить вместе со всеми -то он не работает.
//       //почему происходит этот конфликт между тестами и как его избежать?
//
//    @Test
//    public void checkExceptionWhenAddingLastNameFieldEmployee() {
//        when(employeeRequest.getFirstName()).thenReturn("Ivan");
//        when(employeeRequest.getLastName()).thenReturn(null);
//        assertThrows(FieldsShouldNotBeEmptyException.class, () -> employeeService.addEmployee(employeeRequest));
//    }// этот тест запускается и работает только отдельно, если его запустить вместе со всеми -то он не работает.
//       //почему происходит этот конфликт между тестами и как его избежать?

    @Test
    public void checkGetAllEmployees() {
        employeeService.addEmployee(employeeRequest);
        final List<Employee> actualEmployees = new ArrayList<>(employeeService.getEmployees().values());
        final List<Employee> checckedEmployees = employeeService.getAllEmployees();
        assertEquals(checckedEmployees, actualEmployees);
    }

    @Test
    public void checkSearchEmployee() {
        employeeService.addEmployee(employeeRequest);
        final int id = 1;
        final Employee standardEmployee = employeeService.getEmployees().get(id);
        final Employee checkedEmployee = employeeService.searchEmployee(id);
        assertEquals(checkedEmployee, standardEmployee);
    }

    @Test
    public void checkDeleteEmployee() {
        employeeService.addEmployee(employeeRequest);
        int employeeId = 1;
        String standard = employeeService.getEmployees().get(employeeId) + " удален";
        String checked = employeeService.deleteEmployee(employeeId);
        assertEquals(checked, standard);
    }
    @Test
    public void checkDeleteEmployeeWhenItDoesNotExist() {
        employeeService.addEmployee(employeeRequest);
        int employeeId = 5;
        String standard = String.valueOf(employeeService.getEmployees().get(employeeId)) + " удален";
        String checked =employeeService.deleteEmployee(employeeId);
        assertEquals(checked, standard);
    } //в чем смысл такого теста? если сотрудника не существует ничего не произойдет и что мы при этом проверяем?
}



//    @Test
//    public void checkAddTwiceEmployee() {
//        when(employeeRequest.getFirstName()).thenReturn("Petr");
//        when(employeeRequest.getLastName()).thenReturn("Petrov");
//        when(employeeRequest.getDepartment()).thenReturn(2);
//        when(employeeRequest.getSalary()).thenReturn(2000);
//
//        employeeService.addEmployee(employeeRequest);
//
//        assertThrows(IllegalArgumentException.class, () -> employeeService.addEmployee(employeeRequest));
//    } //если выброс исключения тестить отдельно от создания+добавления сотрудника то возникает конфликт между тестами при общем запуске
//          почему? и как этого избежать?
