package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(3);

        System.out.println("TEST #1 : find by Id: ");

        System.out.println(seller);

        System.out.println();

        System.out.println("TEST #2 : find by Department");

        Department department = new Department(null, 2);

        List<Seller> list = sellerDao.findByDepartment(department);

        for (Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("TEST #3 : Seller insert");

        Seller newSeller = new Seller("Greg", "greg@gmail.com", new Date(), 4000.0, department);

        sellerDao.insert(newSeller);

        System.out.println("Inserted! New id: " + newSeller.getId());



    }
}