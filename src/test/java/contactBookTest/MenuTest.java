package contactBookTest;

import contactBook.ContactBook;
import contactBook.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;


class MenuTest {

    @Mock
    private ContactBook contactBook;
    @Mock
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        contactBook = mock(ContactBook.class); // Mock the ContactBook class
        scanner = mock(Scanner.class); // Mock the Scanner class
    }

    @Test
    public void testAddContact() {
        // Simulate user input: user chooses to add a contact and then exits
        String input = "add\nexit";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);


//        contactBook.when(()-> contactBook.addContact(scanner)).thenAnswer(invocation->null);
        doNothing().when(contactBook).addContact(scanner);


        Menu.start(contactBook);
    }

    @Test
    public void testListContacts() {
        // Simulate user input: user chooses to list contacts and then exits
        String input = "list\nexit";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);


//        contactBook.when(()-> contactBook.addContact(scanner)).thenAnswer(invocation->null);
        doNothing().when(contactBook).listContacts(scanner);


        Menu.start(contactBook);
    }

    @Test
    public void testSearchContacts() {
        // Simulate user input: user chooses to search contacts and then exits
        String input = "search\nexit";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);


//        contactBook.when(()-> contactBook.addContact(scanner)).thenAnswer(invocation->null);
        doNothing().when(contactBook).searchContact(scanner);


        Menu.start(contactBook);
    }

    @Test
    public void testCountContacts() {
        // Simulate user input: user chooses to count the contacts and then exits
        String input = "count\nexit";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Menu.start(contactBook);
    }

}