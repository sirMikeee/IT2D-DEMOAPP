
package it2d.demoapp.db;

import java.util.Scanner;


public class Product {
    
    public void pTransaction(){
        
        Scanner sc = new Scanner(System.in);
        String response;
        do {
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.print("Enter action: ");
            int action = sc.nextInt();
            Product prs = new Product();
            switch (action) {
                case 1:
                    prs.addProduct();
                    break;
                case 2:
                    prs.viewProduct();
                    break;
                case 3:
                   
                    break;
                case 4:
                    
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
            }
            System.out.print("Do you want to continue? (yes/no): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("yes"));
        
    }
    
    public void addProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: ");
        String pname = sc.next();
        System.out.print("Price: ");
        String pprice = sc.next();
        System.out.print("Stocks: ");
        String pstocks = sc.next();
        System.out.print("Status: ");
        String pstatus = sc.next();

        String sampleqry = "INSERT INTO tbl_product (p_name, p_price, p_stocks, p_status) VALUES (?, ?, ?, ?)";
        config conf = new config();
        conf.addRecord(sampleqry, pname, pprice, pstocks, pstatus);
    }
    
    public void viewProduct() {
        String qry = "SELECT * FROM tbl_product";
        String[] hdrs = {"Product ID", "Name", "Price", "Stocks", "Status"};
        String[] clmns = {"p_id", "p_name", "p_price", "p_stocks", "p_status"};

        config conf = new config();
        conf.viewRecords(qry, hdrs, clmns);
    }
    
}
