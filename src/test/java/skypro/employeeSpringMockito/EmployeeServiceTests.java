package skypro.employeeSpringMockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.employeeSpringMockito.model.Employee;
import skypro.employeeSpringMockito.record.EmployeeRequest;
import skypro.employeeSpringMockito.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

   @Mock
   private EmployeeRequest employeeRequest;
    @InjectMocks
    private EmployeeService employeeService;


    @Test
    public void checkAddEmployee() {
//   как протестировать метод который ничего не возвращает?
    }

    @Test
    public void checkGetAllEmployees(){

        final List<Employee> actualEmployees = employeeService.getEmployees().values().stream().collect(Collectors.toList());

        final List<Employee> checckedEmployees = employeeService.getAllEmployees();

        assertEquals(checckedEmployees, actualEmployees);
    }

    @Test
    public void checkDeleteEmployee(){
        final int id = 2;
     //   assertEquals(employeeService.deleteEmployee(id), employeeService.getEmployees().remove(id));
        //   как протестировать метод который ничего не возвращает?
    }

    @Test
    public void checkSearchEmployee() {
       final int id = 1;

        final Employee standardEmployee = employeeService.getEmployees().get(id);

        final Employee checkedEmployee = employeeService.searchEmployee(id);

        assertEquals(checkedEmployee, standardEmployee);
    }

}
