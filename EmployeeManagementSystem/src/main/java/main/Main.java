package main;

import java.util.List;
import java.util.Scanner;

import entity.Employee;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import util.JPAUtil;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeServiceImpl();

        int choice;

        do {

            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Find Employee By ID");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    Employee emp = new Employee();

                    System.out.print("Enter Employee ID: ");
                    emp.setId(sc.nextInt());

                    sc.nextLine();

                    System.out.print("Enter Employee Name: ");
                    emp.setName(sc.nextLine());

                    System.out.print("Enter Employee Email: ");
                    emp.setEmail(sc.nextLine());

                    System.out.print("Enter Employee Salary: ");
                    emp.setSalary(sc.nextDouble());

                    service.saveEmployee(emp);
                    break;

                case 2:
                    Employee updateEmp = new Employee();

                    System.out.print("Enter Employee ID: ");
                    updateEmp.setId(sc.nextInt());

                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    updateEmp.setName(sc.nextLine());

                    System.out.print("Enter New Email: ");
                    updateEmp.setEmail(sc.nextLine());

                    System.out.print("Enter New Salary: ");
                    updateEmp.setSalary(sc.nextDouble());

                    service.updateEmployee(updateEmp);
                    break;

                case 3:
                    System.out.print("Enter Employee ID to Delete: ");
                    int deleteId = sc.nextInt();

                    service.deleteEmployee(deleteId);
                    break;

                case 4:
                    System.out.print("Enter Employee ID: ");
                    int searchId = sc.nextInt();

                    Employee employee = service.getEmployeeById(searchId);

                    if (employee != null) {
                        System.out.println("\nEmployee Found");
                        System.out.println("ID : " + employee.getId());
                        System.out.println("Name : " + employee.getName());
                        System.out.println("Email : " + employee.getEmail());
                        System.out.println("Salary : " + employee.getSalary());
                    } else {
                        System.out.println("Employee Not Found");
                    }

                    break;

                case 5:
                    List<Employee> employees = service.getAllEmployees();

                    if (employees.isEmpty()) {
                        System.out.println("No Employees Found");
                    } else {
                        for (Employee e : employees) {
                            System.out.println("--------------------------------");
                            System.out.println("ID : " + e.getId());
                            System.out.println("Name : " + e.getName());
                            System.out.println("Email : " + e.getEmail());
                            System.out.println("Salary : " + e.getSalary());
                        }
                    }

                    break;

                case 6:
                    JPAUtil.close();
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 6);

        sc.close();
    }
}