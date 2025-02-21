package contactBook;

import contact.Contact;
import contact.Organization;
import contact.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactBook {
    private final List<Contact> contactsList = new ArrayList<>();

    // Method to add a new contact to the list
    public void addContact(Scanner sc) {
        System.out.print("Enter the type (person, organization): ");
        String type = sc.nextLine();

        if (type.equals("person")) {
            contactsList.add(Person.add(sc));  // Add a person contact
        } else {
            contactsList.add(Organization.add(sc));  // Add an organization contact
        }
    }

    // Method to search contacts by query
    public void searchContact(Scanner sc) {
        System.out.print("Enter search query: ");
        String type = sc.nextLine();
        List<Contact> searchList = new ArrayList<>();

        Pattern pattern = Pattern.compile(type, Pattern.CASE_INSENSITIVE);

        for (Contact contact : contactsList) {
            Matcher matcher = pattern.matcher(contact.getAllInfo());
            if (matcher.find()) {
                searchList.add(contact);
            }
        }

        System.out.println("Found " + listSize(searchList) + " results: \n");
        for (int i = 0; i < searchList.size(); i++) {
            System.out.println(i + 1 + ". " + searchList.get(i).getAllInfo());
        }

        searchMenu(sc, searchList);
    }

    // Method to list all contacts
    public void listContacts(Scanner sc) {
        if (listSize(contactsList) == 0) {
            System.out.println("No records to list");
        }

        for (int i = 0; i < contactsList.size(); i++) {
            String info = contactsList.get(i).getAllInfo();
            System.out.println(i + 1 + ". " + info);
        }

        System.out.println("\n[list] Enter action ([number], back):");
        String action = sc.nextLine();

        if ("back".equals(action)) {
            Menu.start(this);  // Pass 'this' directly
        } else {
            editContact(sc, contactsList, action);
        }
    }

    // Helper method to handle search menu
    public void searchMenu(Scanner sc, List<Contact> searchList) {
        if (listSize(searchList) == 0) {
            System.out.println("No contacts in the list");
            return;
        }

        System.out.println("\n[search] Enter Action ([number], back, again): ");
        String action = sc.nextLine();

        switch (action) {
            case "back" : Menu.start(this);
                            break;
            case "again" : searchContact(sc);
                            break;
            default : editContact(sc, contactsList, action);
        }
    }

    // Method to edit a contact
    public void editContact(Scanner sc, List<Contact> contactsList, String action) {
        try {
            int index = Integer.parseInt(action) - 1;  // Parse the input as an integer
            if (index >= 0 && index < contactsList.size()) {
                Contact selectedContact = contactsList.get(index);
                selectedContact.getFields();
                System.out.println("\n[record] Enter action (edit, delete, menu): ");

                String option = sc.nextLine();
                switch (option) {
                    case "edit":
                        selectedContact.editFields(sc);
                        break;
                    case "delete":
                        contactsList.remove(selectedContact);
                        break;
                    case "back":
                        Menu.start(this);  // Pass 'this' directly
                        break;
                    default:
                        System.out.println("Incorrect Inputs");
                        break;
                }
            } else {
                System.out.println("Invalid index. Please enter a valid number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    // Method to get the size of a list (for search results)
    public int listSize(List<Contact> searchList) {
        return searchList.size();
    }

    // Method to get the size of the contacts list
    public int contactListSize() {
        return contactsList.size();
    }

    // Get the list of contacts
    public List<Contact> getContactsList() {
        return Collections.unmodifiableList(contactsList);  // Return an unmodifiable list
    }
}