package contactBookTest;

import contact.Contact;
import contact.Person;
import contact.Organization;
import contactBook.ContactBook;
import contactBook.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactBookTest {

    private Scanner sc;
    private List<Contact> contactsList;
    private Contact mockContact;
    private Menu mockMenu;

    @Mock
    private ContactBook contactBook;

    @BeforeEach
    void setUp() {
        sc = mock(Scanner.class); // Mock the Scanner class
        contactsList = new ArrayList<>();
        mockContact = mock(Contact.class); // Mock the Contact class
        mockMenu = mock(Menu.class); // Mock the Menu class

        contactsList.add(mockContact);
        contactBook = new ContactBook(); // The class where editContact method is defined
    }

    @Test
    void testEditContact_validIndexAndEditAction() {
        when(sc.nextLine()).thenReturn("edit");

        // Simulate the user's valid input
        String action = "1";
        contactBook.editContact(sc, contactsList, action);

        // Verify that the editFields method is called
        verify(mockContact).editFields(sc);
    }

    @Test
    void testEditContact_InvalidIndex() {

        // Simulate the user's valid input
        String action = "-1";
        contactBook.editContact(sc, contactsList, action);

    }

    @Test
    void testEditContact_validIndexAndDeleteAction() {
        when(sc.nextLine()).thenReturn("delete");

        // Simulate the user's valid input
        String action = "1";
        contactBook.editContact(sc, contactsList, action);

        // Verify that the editFields method is called
        verify(mockContact).getFields();
    }

    @Test
    void testEditContact_validIndexAndBackAction() {
        try (MockedStatic<Menu> mockedMenu = mockStatic(Menu.class)) {
            when(sc.nextLine()).thenReturn("back");

            // Simulate the user's valid input
            String action = "1";
            contactBook.editContact(sc, contactsList, action);

            // Verify that the editFields method is called
            verify(mockContact).getFields();
        }
    }

    @Test
    public void testSearchMenu_NoResults() {
        // Simulating a search with no results
        String searchInput = "Nonexistent";
        Scanner sc = new Scanner(searchInput);
        contactBook.searchContact(sc);

        // In this test case, it should display "No contacts in the list"
        assertTrue(contactBook.getContactsList().isEmpty());
    }

    @Test
    public void testSearchMenu_Results() {
        try (MockedStatic<Menu> mockedMenu = mockStatic(Menu.class)) {
            // Simulating a search with no results
//            mockAddPersonContact();
            when(sc.nextLine()).thenReturn("person");
            when(sc.nextLine()).thenReturn("John");
            when(sc.nextLine()).thenReturn("Doe");
            when(sc.nextLine()).thenReturn("01/01/1990");
            when(sc.nextLine()).thenReturn("M");
            when(sc.nextLine()).thenReturn("+123456789");

            contactBook.addContact(sc);
            when(sc.nextLine())
                    .thenReturn("1234")
                    .thenReturn("back");

            contactBook.searchContact(sc);
        }
    }

    @Test
    public void testSearchMenu_whenListIsEmpty() {
        List<Contact> contacts = List.of();

        contactBook.searchMenu(sc, contacts);
    }

    @Test
    public void testSearchMenu_whenActionIsAgain() {
        when(sc.nextLine()).thenReturn("again");

        contactBook.searchMenu(sc, contactsList);
    }

    @Test
    public void testSearchMenu_whenActionIsEditContact() {
        when(sc.nextLine()).thenReturn("edit");

        contactBook.searchMenu(sc, contactsList);
    }

    @Test
    public void testEditAction() {
        mockAddPersonContact();
        // Simulate user input: selecting the first contact and choosing "edit"
        when(sc.nextLine()).thenReturn("1");  // User selects "1" for the first contact and chooses "edit"

        // Call the editContact method
        contactBook.editContact(sc, contactBook.getContactsList(), "1");

    }

    @Test
    void testAddPerson() {
        // Simulate the user's input for a person contact
        when(sc.nextLine())
                .thenReturn("person")
                .thenReturn("John")
                .thenReturn("Doe")
                .thenReturn("1990-12-20")
                .thenReturn("M")
                .thenReturn("+123456789"); // phone number

        contactBook.addContact(sc);

        assertEquals(1, contactBook.contactListSize());
        assertTrue(contactBook.getContactsList().get(0) instanceof Person);
    }

    @Test
    void testAddOrganization() {
        // Simulate the user's input for an organization contact
        when(sc.nextLine()).thenReturn("organization"); // type = organization
        when(sc.nextLine()).thenReturn("Tech Corp");   // organization name
        when(sc.nextLine()).thenReturn("123 Tech Road"); // address
        when(sc.nextLine()).thenReturn("+987654321");  // phone number

        contactBook.addContact(sc);

        assertEquals(1, contactBook.contactListSize());
        assertTrue(contactBook.getContactsList().get(0) instanceof Organization);
    }

    @Test
    void testListContacts() {
        // Simulate adding contacts
        when(sc.nextLine()).thenReturn("person");
        when(sc.nextLine()).thenReturn("Alice");
        when(sc.nextLine()).thenReturn("Smith");
        when(sc.nextLine()).thenReturn("02/02/1985");
        when(sc.nextLine()).thenReturn("F");
        when(sc.nextLine()).thenReturn("+1122334455");
        contactBook.addContact(sc);

        when(sc.nextLine()).thenReturn("organization");
        when(sc.nextLine()).thenReturn("Some Org");
        when(sc.nextLine()).thenReturn("456 Organization St");
        when(sc.nextLine()).thenReturn("+9988776655");
        contactBook.addContact(sc);

        assertEquals(2, contactBook.contactListSize());
    }

    @Test
    void testSearchContact() {
        // Add a person first
        when(sc.nextLine()).thenReturn("person");
        when(sc.nextLine()).thenReturn("John");
        when(sc.nextLine()).thenReturn("Doe");
        when(sc.nextLine()).thenReturn("01/01/1990");
        when(sc.nextLine()).thenReturn("M");
        when(sc.nextLine()).thenReturn("+123456789");
        contactBook.addContact(sc);

        // Simulate searching for a contact
        when(sc.nextLine()).thenReturn("John");

        // Since "John" should match the person's info, simulate search
        contactBook.searchContact(sc);

        // The result should be 1 (since only John was added)
        assertEquals(1, contactBook.contactListSize());
    }

    @Test
    void testContactCount() {
        // Add a few contacts
        when(sc.nextLine()).thenReturn("person");
        when(sc.nextLine()).thenReturn("John");
        when(sc.nextLine()).thenReturn("Doe");
        when(sc.nextLine()).thenReturn("01/01/1990");
        when(sc.nextLine()).thenReturn("M");
        when(sc.nextLine()).thenReturn("+123456789");
        contactBook.addContact(sc);

        when(sc.nextLine()).thenReturn("organization");
        when(sc.nextLine()).thenReturn("Tech Corp");
        when(sc.nextLine()).thenReturn("123 Tech Road");
        when(sc.nextLine()).thenReturn("+987654321");
        contactBook.addContact(sc);

        assertEquals(2, contactBook.contactListSize());
    }

    @Test
    void testListContactsWhenEmpty() {

        contactBook.listContacts(sc); // Call the method

        // Verify that no contacts are listed and it exits gracefully
        assertEquals(0, contactBook.contactListSize()); // Ensure no contacts are listed
    }

    @Test
    void testListContactsWhenContactsExist() {
        // Add a contact using a mocked method
        mockAddPersonContact();

        contactBook.listContacts(sc); // Call the method

        // Verify the contact is listed and we navigate back
        assertEquals(1, contactBook.contactListSize()); // Ensure 1 contact exists
    }

    // Test selecting a contact from the list
    @Test
    void testListContactsAndSelect() {
        // Add two contacts using mocked methods
        mockAddPersonContact();   // Adds a Person contact
        mockAddOrganizationContact(); // Adds an Organization contact

        // Simulate user selecting the first contact (index 1)
        when(sc.nextLine()).thenReturn("1");  // Simulate user input "1"

        contactBook.listContacts(sc); // Call the method

        // Verify that the contacts are listed
        assertEquals(2, contactBook.contactListSize()); // Ensure 2 contacts are listed
    }

    // Test selecting "back" action to return to the menu
    @Test
    void testListContactsAndGoBack() {
        try (MockedStatic<Menu> mockedMenu = mockStatic(Menu.class)) {
            // Add a person contact
            mockAddPersonContact();
            when(sc.nextLine()).thenReturn("back");
            contactBook.listContacts(sc); // Call the method

            // Verify that the method exits correctly when "back" is typed
            assertEquals(1, contactBook.contactListSize()); // Ensure one contact is still there
        }
    }

    // Helper method to mock adding a Person contact
    private void mockAddPersonContact() {
        // Simulate the input for adding a Person contact
        when(sc.nextLine()).thenReturn("person");
        when(sc.nextLine()).thenReturn("John");
        when(sc.nextLine()).thenReturn("Doe");
        when(sc.nextLine()).thenReturn("01/01/1990");
        when(sc.nextLine()).thenReturn("M");
        when(sc.nextLine()).thenReturn("+123456789");

        contactBook.addContact(sc); // Add the person contact
    }

    // Helper method to mock adding an Organization contact
    private void mockAddOrganizationContact() {
        // Simulate the input for adding an Organization contact
        when(sc.nextLine()).thenReturn("organization");
        when(sc.nextLine()).thenReturn("Tech Corp");
        when(sc.nextLine()).thenReturn("123 Tech Road");
        when(sc.nextLine()).thenReturn("+987654321");

        contactBook.addContact(sc); // Add the organization contact
    }

}