package contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContactTest {

    private Contact contact;

    static class MockContact extends Contact {
        @Override
        public String getAllInfo() {
            return this.getName() + " " + this.getPhoneNumber();
        }

        @Override
        public void getFields() {
            System.out.println("Name: " + getName());
            System.out.println("Phone number: " + getPhoneNumber());
            System.out.println("Time created: " + getTimeCreated());
            System.out.println("Time last edit: " + getTimeModified());
        }

        @Override
        public void editFields(Scanner sc) {
            System.out.print("Enter name: ");
            setName(sc.nextLine());
            System.out.print("Enter phone number: ");
            setPhoneNumber(sc.nextLine());
        }
    }

    @BeforeEach
    void setUp() {
        contact = new MockContact();
    }

    @Test
    void testSetName() {
        contact.setName("John Doe");
        assertEquals("John Doe", contact.getName(), "Name should be set to 'John Doe'");
    }

    @Test
    void testGetPhoneNumber() {
        contact.setPhoneNumber("+123 456 789");
        assertEquals("+123 456 789", contact.getPhoneNumber(), "Phone number should be '+123 456 789'");

        contact.setPhoneNumber("");
        assertEquals("[no number]", contact.getPhoneNumber(), "Phone number should be '[no number]' if it's empty");
    }

    @Test
    void testSetPhoneNumberValid() {
        contact.setPhoneNumber("+123 456 789");
        assertEquals("+123 456 789", contact.getPhoneNumber(), "Phone number should be valid");
    }

    @Test
    void testSetPhoneNumberInvalid() {
        contact.setPhoneNumber("14,.");
        assertEquals("[no number]", contact.getPhoneNumber(), "Phone number should be empty if invalid format");
    }

    @Test
    void testSetTimeCreated() {
        LocalDateTime now = LocalDateTime.now();
        contact.setTimeCreated(now);
        assertEquals(now, contact.getTimeCreated(), "Time created should be set correctly");
    }

    @Test
    void testSetTimeModified() {
        LocalDateTime now = LocalDateTime.now();
        contact.setTimeModified(now);
        assertEquals(now, contact.getTimeModified(), "Time modified should be set correctly");
    }

    @Test
    void testToString() {
        contact.setName("Alice");
        contact.setPhoneNumber("+987 654 321");
        String expected = "Contacts{name='' ,phoneNumber='+987 654 321'}";
        assertEquals(expected, contact.toString(), "toString() should return the correct string representation");
    }

    @Test
    void testGetAllInfo() {
        contact.setName("Bob");
        contact.setPhoneNumber("+555 123 456");
        String allInfo = contact.getAllInfo();
        assertTrue(allInfo.contains("Bob"), "All info should contain the name 'Bob'");
        assertTrue(allInfo.contains("+555 123 456"), "All info should contain the phone number '+555 123 456'");
    }

    @Test
    void testGetFields() {
        contact.setName("Charlie");
        contact.setPhoneNumber("+321 654 987");
        contact.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        contact.setTimeModified(LocalDateTime.now().withSecond(0).withNano(0));

        contact.getFields();
    }

    @Test
    void testEditFields() {
        contact.setName("David");
        contact.setPhoneNumber("+999 888 777");

        // Simulate user input via Scanner
        String simulatedInput = "Eve\n+666 555 444\n";
        Scanner mockScanner = new Scanner(simulatedInput);

        contact.editFields(mockScanner);

        assertEquals("Eve", contact.getName(), "Name should be updated to 'Eve'");
        assertEquals("+666 555 444", contact.getPhoneNumber(), "Phone number should be updated to '+666 555 444'");
    }
}