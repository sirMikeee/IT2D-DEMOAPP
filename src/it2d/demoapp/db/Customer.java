/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it2d.demoapp.db;

import java.util.Scanner;

/**
 *
 * @author SerMike
 */
public class Customer {
    
    public void cTransaction(){
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
            Customer customer = new Customer();
            switch (action) {
                case 1:
                    customer.addCustomer();
                    break;
                case 2:
                    customer.viewCustomer();
                    break;
                case 3:
                    customer.viewCustomer();
                    customer.updateCustomer();
                    customer.viewCustomer();
                    break;
                case 4:
                    customer.viewCustomer();
                    customer.deleteCustomer();
                    customer.viewCustomer();
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
    
    public void addCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("First Name: ");
        String fname = sc.next();
        System.out.print("Last Name: ");
        String lname = sc.next();
        System.out.print("Email: ");
        String email = sc.next();
        System.out.print("Status: ");
        String status = sc.next();

        String sql = "INSERT INTO tbl_customer (c_fname, c_lname, c_email, c_status) VALUES (?, ?, ?, ?)";
        config conf = new config();
        conf.addRecord(sql, fname, lname, email, status);
    }
    
    public void viewCustomer() {
        String qry = "SELECT * FROM tbl_customer";
        String[] hdrs = {"Customer ID", "FName", "LastName", "Email", "Status"};
        String[] clmns = {"c_id", "c_fname", "c_lname", "c_email", "c_status"};

        config conf = new config();
        conf.viewRecords(qry, hdrs, clmns);
    }
    
    public void updateCustomer(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the id to update: ");
        int id = sc.nextInt();
        
        System.out.print("New First Name: ");
        String nfname = sc.next();
        System.out.print("New Last Name: ");
        String nlname = sc.next();
        System.out.print("New Email: ");
        String nemail = sc.next();
        System.out.print("New Status: ");
        String nstatus = sc.next();
        
        String qry = "UPDATE tbl_customer SET c_fname = ?, c_lname = ?, c_email = ?, c_status = ? WHERE c_id = ?";
        
        config conf = new config();
        conf.updateRecord(qry, nfname, nlname, nemail, nstatus, id);
    }
    
    
    public void deleteCustomer(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the id to delete: ");
        int id = sc.nextInt();
        
        String qry = "DELETE FROM tbl_customer WHERE c_id = ?";
        
        config conf = new config();
        conf.deleteRecord(qry, id);
    
    }
    
    
}
