package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Program2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        Department department = departmentDao.findById(1);

        System.out.println("TEST #1 : Find by Id: ");

        System.out.println(department);

        System.out.println("TEST #2 : Department insertion: ");

        Department newDepartment = new Department("Hardware");

        departmentDao.insert(newDepartment);

        System.out.println("Inserted! department id: " + newDepartment.getId());

        System.out.println("TEST #3 : Department update: ");

        Department departmentForFind = departmentDao.findById(9);

        departmentForFind.setName("Tools");

        departmentDao.update(departmentForFind);

        System.out.println("TEST #4 : Deparment delete");

        System.out.print("Enter department id for delete test: ");

        int departmentId = sc.nextInt();

        departmentDao.deleteById(departmentId);

        System.out.println("Delete completed");

        System.out.println("TEST #5 : Deparment list");

        List<Department> allDepartments = departmentDao.findAll();
        System.out.println(allDepartments);


        sc.close();

    }
}