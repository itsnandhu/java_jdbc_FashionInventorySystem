package com.main_l;

import com.list.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        inventory inv = new inventory();
        dress dr = new dress();
        int opt = 0, con = 0;
        login l = new login();
        String u_name, pwd;
        User u;

        do {
            System.out.println("Enter User Name: ");
            u_name = sc.next();
            System.out.println("Enter Password: ");
            pwd = sc.next();
            u = new User(u_name, pwd);

            if (l.checkUser(u)) {
                do {
                    System.out.println("1. Add 2. Update 3. Search 4. Remove 5. Show -1 to Exit");

                    // Validate input for option selection
                    while (true) {
                        try {
                            opt = sc.nextInt();  // Input integer for option
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.next();  // Consume the invalid input
                        }
                    }

                    switch (opt) {
                        case 1:
                            dr = new dress();
                            System.out.println("Enter category:");
                            dr.setCategory(sc.next());
                            System.out.println("Enter color:");
                            dr.setColour(sc.next());
                            System.out.println("Enter Price:");
                            while (!sc.hasNextFloat()) {
                                System.out.println("Invalid input. Please enter a valid price.");
                                sc.next();
                            }
                            dr.setPrice(sc.nextFloat());
                            System.out.println("Enter Model:");
                            dr.setModel(sc.next());
                            inv.add(dr);  // Auto-generated p_No will be handled by DB
                            break;

                        case 2:
                            System.out.println("List of items");
                            inv.show();
                            System.out.println("Enter product no:");
                            while (!sc.hasNextInt()) {
                                System.out.println("Invalid input. Please enter a valid product number.");
                                sc.next();
                            }
                            int p_No = sc.nextInt();
                            dr = inv.search(p_No);
                            if (dr == null) {
                                System.out.println("No item found with this product number.");
                                break;
                            }
                            System.out.println("Enter \n1.category\n2.colour\n3.price\n4.model\n to update product based on:");
                            int o = sc.nextInt();
                            switch (o) {
                                case 1:
                                    System.out.println("Enter new category:");
                                    dr.setCategory(sc.next());
                                    break;
                                case 2:
                                    System.out.println("Enter new color:");
                                    dr.setColour(sc.next());
                                    break;
                                case 3:
                                    System.out.println("Enter new price:");
                                    while (!sc.hasNextFloat()) {
                                        System.out.println("Invalid input. Please enter a valid price.");
                                        sc.next();
                                    }
                                    dr.setPrice(sc.nextFloat());
                                    break;
                                case 4:
                                    System.out.println("Enter new model:");
                                    dr.setModel(sc.next());
                                    break;
                                default:
                                    System.out.println("Invalid option");
                                    continue;
                            }
                            inv.update(dr, o);
                            break;

                        case 3:
                            System.out.println("Enter id to be searched:");
                            while (!sc.hasNextInt()) {
                                System.out.println("Invalid input. Please enter a valid ID.");
                                sc.next();
                            }
                            int id = sc.nextInt();
                            dr = inv.search(id);
                            if (dr == null) {
                                System.out.println("No item found with this ID.");
                            } else {
                                System.out.println(dr);
                            }
                            break;

                        case 4:
                            System.out.println("Enter id to be deleted:");
                            while (!sc.hasNextInt()) {
                                System.out.println("Invalid input. Please enter a valid ID.");
                                sc.next();
                            }
                            int deleteId = sc.nextInt();
                            inv.remove(deleteId);
                            break;

                        case 5:
                            inv.show();
                            break;

                        case -1:
                            System.out.println("Exiting...");
                            break;

                        default:
                            System.out.println("Invalid option, try again.");
                    }
                } while (opt != -1);
            } else {
                inv.show();
            }

            // Validate input for continuation
            System.out.println("Enter -1 to exit or any number to continue:");
            while (true) {
                try {
                    con = sc.nextInt();  // Input integer for continuation
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next();  // Consume the invalid input
                }
            }

        } while (con != -1);

        sc.close();
    }
}
