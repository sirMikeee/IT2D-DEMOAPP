package it2d.demoapp.db;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IT2DDEMOAPPDB {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        
        do{
            System.out.println("1. CUSTOMER");
            System.out.println("2. PRODUCT");
            System.out.println("3. ORDER");
            System.out.println("4. EXIT");

            System.out.print("Enter action: ");
            int act = sc.nextInt();

            switch(act){
                case 1:
                    Customer cs = new Customer();
                    cs.cTransaction();
                break;
                case 2:
                    Product pr = new Product();
                    pr.pTransaction();
                break;
                case 3:
                    Orders or = new Orders();
                    or.oTransaction();
                break;
                case 4:
                    System.out.print("Exiting ... U sure? (yes/no): ");
                    String resp = sc.next();
                        if(resp.equalsIgnoreCase("yes")){
                            exit = false;
                        }
                break;
            }
        }while(exit);
        System.out.println("See you soon!");
    }
    
    
    
}
