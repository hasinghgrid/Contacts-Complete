package contactBook;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Menu {

    public static void start(ContactBook contactBook) {
        Scanner sc = new Scanner(System.in , StandardCharsets.UTF_8);
        while (true) {
            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
            String action = sc.nextLine();

            switch (action) {
                case "add" : contactBook.addContact(sc);
                             break;
                case "list" : contactBook.listContacts(sc);
                              break;
                case "search" : contactBook.searchContact(sc);
                               break;
                case "count" : System.out.println("The Phone book has " + contactBook.contactListSize() + " records");
                                break;
                case "exit" :
                    sc.close();
                    return;
            }
        }
    }
}