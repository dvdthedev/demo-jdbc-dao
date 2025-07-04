package application;

import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Program {
    public static void main(String[] args) {

        Department obj = new Department("Books", 1);

        System.out.println(obj);

        Seller seller = new Seller(1, "Carlos", "bob@gmail.com", new Date(), 3000.0, obj);

        System.out.println(seller);

    }
}