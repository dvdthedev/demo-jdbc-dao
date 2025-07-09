package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(3);

        System.out.println("TEST #1 : find by Id: ");

        System.out.println(seller);

        System.out.println();

        System.out.println("TEST #2 : find by Department");

        Department department = new Department();

        department.setId(2);

        List<Seller> list = sellerDao.findByDepartment(department);

        for (Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("TEST #3 : Seller insert");

        Seller newSeller = new Seller("Deivid", "dvd@gmail.com", new Date(1992 ,5,2), 3000.0, department);

        sellerDao.insert(newSeller);

        System.out.println("Inserted! New id: " + newSeller.getId());

        System.out.println("TEST #4 : Seller update");

        seller = sellerDao.findById(1);

        seller.setName("Carlos Wayne");

        sellerDao.update(seller);

        System.out.println("Update completed!");

        System.out.println("TEST #6 : Seller delete");

        System.out.print("Enter seller id for delete test: ");

        int sellerId = sc.nextInt();

        sellerDao.deleteById(sellerId);

        System.out.println("Delete completed");

        List<Seller> allSellersList = sellerDao.findAll();

        System.out.println(allSellersList);

        sc.close();

    }
}