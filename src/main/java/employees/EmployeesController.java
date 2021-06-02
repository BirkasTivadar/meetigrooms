package employees;

public class EmployeesController {

    private EmployeeService employeeService = new EmployeeService(new MySqlEmployeesRepository());

    public static void main(String[] args) {
        new EmployeesController().start();

    }

    public void start(){
        employeeService.save("Joe");
        System.out.println(employeeService.listEmployees());
    }
}
