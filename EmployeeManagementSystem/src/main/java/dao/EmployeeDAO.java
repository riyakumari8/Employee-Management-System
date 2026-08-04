package dao;

import java.util.List;
import entity.Employee;

public interface EmployeeDAO {

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(int id);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();
}