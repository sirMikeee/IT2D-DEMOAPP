package it2d.demoapp.db;

import java.util.Scanner;

public class Orders {
    
    public void oTransaction() {
        Scanner sc = new Scanner(System.in);
        String response;
        
        do {
            System.out.println("1. ADD ORDER");
            System.out.println("2. VIEW ORDERS");
            System.out.println("3. DELETE ORDER");
            System.out.println("4. EXIT");

            System.out.print("Enter action: ");
            int action = sc.nextInt();

            switch (action) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    viewOrders();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
            }

            if (action != 4) {
                System.out.print("Do you want to continue? (yes/no): ");
                response = sc.next();
            } else {
                response = "no";
            }
        } while (response.equalsIgnoreCase("yes"));
    }

    private void addOrder() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        Customer cs = new Customer();
        
        System.out.println("Select a customer:");
        cs.viewCustomer();
        
        System.out.print("Enter Customer ID: ");
        int customerId = sc.nextInt();
         
        while((conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id = ?", customerId)) == 0){
            System.out.println("Selected Customer don't exist!");
            System.out.print("Select Customer ID Again : ");
            customerId = sc.nextInt();
        }

        
        System.out.println("Select a product:");
        Product pr = new Product();
        pr.viewProduct();

        System.out.print("Enter Product ID: ");
        int productId = sc.nextInt();
        
        while((conf.getSingleValue("SELECT p_id FROM tbl_product WHERE p_id = ?", productId)) == 0){
            System.out.println("Selected Product  don't exist!");
            System.out.print("Select Product ID Again : ");
            productId = sc.nextInt();
        }


        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        
        String priceQuery = "SELECT p_price FROM tbl_product WHERE p_id = ?";
        double price = conf.getSingleValue(priceQuery, productId);
        double total = price * quantity;

//        String checkOrderQuery = "SELECT COUNT(*) FROM tbl_orders WHERE c_id = ? AND p_id = ?";
//        int orderCount = conf.getCount(checkOrderQuery, customerId, productId);
//
//        if (orderCount > 0) {
//            System.out.println("An order with this customer and product already exists.");
//            return; 
//        }

        String orderInsertQuery = "INSERT INTO tbl_orders (c_id, p_id, o_quantity, o_due) VALUES (?, ?, ?, ?)";
        conf.addRecord(orderInsertQuery, customerId, productId, quantity, total);

        System.out.println("Order added successfully!");
    }

    private void viewOrders() {
        String orderQuery = "SELECT o.o_id, c.c_fname, c.c_lname, p.p_name, o.o_quantity, o.o_due " +
                            "FROM tbl_orders o " +
                            "JOIN tbl_customer c ON o.c_id = c.c_id " +
                            "JOIN tbl_product p ON o.p_id = p.p_id";
        
        String[] orderHeaders = {"Order ID", "Customer First Name", "Customer Last Name", "Product Name", "Quantity", "Total"};
        String[] orderColumns = {"o_id", "c_fname", "c_lname", "p_name", "o_quantity", "o_due"};

        config conf = new config();
        conf.viewRecords(orderQuery, orderHeaders, orderColumns);
    }

    private void deleteOrder() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the Order ID to delete:");
        int orderId = sc.nextInt();
        
        String deleteQuery = "DELETE FROM tbl_orders WHERE o_id = ?";
        config conf = new config();
        conf.deleteRecord(deleteQuery, orderId);
    }
}
