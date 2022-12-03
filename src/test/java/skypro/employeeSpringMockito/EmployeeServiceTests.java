package skypro.employeeSpringMockito;


import org.junit.jupiter.api.Test;
import skypro.employeeSpringMockito.exception.FieldsShouldNotBeEmptyException;
import skypro.employeeSpringMockito.model.Employee;
import skypro.employeeSpringMockito.record.EmployeeRequest;
import skypro.employeeSpringMockito.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTests {
    private EmployeeService employeeService = new EmployeeService();
    private final EmployeeRequest employeeRequest = new EmployeeRequest("Ivan", "Ivanov", 1, 1000);

    private final EmployeeRequest incorrectEmployeeRequest1 = new EmployeeRequest(null, "Ivanov", 1, 0);
    private final EmployeeRequest incorrectEmployeeRequest2 = new EmployeeRequest("Ivan", null, 1, 0);

    @Test
    public void checkAddEmployee() {
        int employeeId = 2;
        employeeService.addEmployee(employeeRequest);
        assertNotNull(employeeService.getEmployees());
        assertNull(employeeService.getEmployees().get(employeeId));
    }
    @Test
    public void checkAddTwiceEmployee() {
        employeeService.addEmployee(employeeRequest);
        assertThrows(IllegalArgumentException.class, () -> employeeService.addEmployee(employeeRequest));
    }
    @Test
    public void checkExceptionWhenAddingFirstNameFieldEmployee() {
        assertThrows(FieldsShouldNotBeEmptyException.class, () -> employeeService.addEmployee(incorrectEmployeeRequest1));
    }
    @Test
    public void checkExceptionWhenAddingLastNameFieldEmployee() {
        assertThrows(FieldsShouldNotBeEmptyException.class, () -> employeeService.addEmployee(incorrectEmployeeRequest2));
    }

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
        String standard = employeeService.getEmployees().get(employeeId) + " удален";
        String checked =employeeService.deleteEmployee(employeeId);
        assertEquals(checked, standard);
    }
}





