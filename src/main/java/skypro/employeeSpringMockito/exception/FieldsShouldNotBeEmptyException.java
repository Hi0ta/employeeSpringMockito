package skypro.employeeSpringMockito.exception;

public class FieldsShouldNotBeEmptyException extends RuntimeException {

    public FieldsShouldNotBeEmptyException(String message) {
        super(message);
    }
}
