package dao;

import java.util.List;

import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;
import jakarta.persistence.TypedQuery;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void saveEmployee(Employee employee) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(employee);
            tx.commit();
            System.out.println("Employee saved successfully.");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(employee);
            tx.commit();
            System.out.println("Employee Updated Successfully");

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();

        }
    }
    @Override
    public void deleteEmployee(int id) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            Employee employee = em.find(Employee.class, id);

            if (employee != null) {

                tx.begin();
                em.remove(employee);
                tx.commit();

                System.out.println("Employee Deleted Successfully");

            } else {

                System.out.println("Employee Not Found");

            }

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();

        }

    }
    @Override
    public Employee getEmployeeById(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        Employee employee = null;

        try {

            employee = em.find(Employee.class, id);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            TypedQuery<Employee> query =
                    em.createQuery("SELECT e FROM Employee e", Employee.class);

            return query.getResultList();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            em.close();

        }
    }
}