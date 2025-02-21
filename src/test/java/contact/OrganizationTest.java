package contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationTest {

    private Organization organization;

    @BeforeEach
    void setUp() {

        organization = new Organization() {};
    }

    @Test
    void getAddress() {
        organization.setAddress("123 Business Ave");
        assertEquals("123 Business Ave", organization.getAddress(), "Address should be '123 Business Ave'");
    }

    @Test
    void setAddress() {
        organization.setAddress("456 Corporate Rd");
        assertEquals("456 Corporate Rd", organization.getAddress(), "Address should be '456 Corporate Rd'");
    }

    @Test
    void getAllInfo() {
        organization.setName("TechCorp");
        organization.setAddress("789 Innovation Blvd");
        organization.setPhoneNumber("987654321");

        String allInfo = organization.getAllInfo();
        assertTrue(allInfo.contains("TechCorp"), "All info should contain the name 'TechCorp'");
        assertTrue(allInfo.contains("789 Innovation Blvd"), "All info should contain the address '789 Innovation Blvd'");
        assertTrue(allInfo.contains("987654321"), "All info should contain the phone number '987654321'");
    }

    @Test
    void getFields() {
        organization.setName("Innovate Ltd.");
        organization.setAddress("456 Tech Park");
        organization.setPhoneNumber("123123123");

        organization.getFields();

        assertEquals("Innovate Ltd.\n456 Tech Park\n123123123",
                organization.getName() + "\n" + organization.getAddress() + "\n" + organization.getPhoneNumber());
    }

    @Test
    void editFields_name() {
        organization.setName("Business Group");
        organization.setAddress("101 Business St.");
        organization.setPhoneNumber("1122334455");

        String simulatedInput = "name\nNew Business Group\n";
        Scanner mockScanner = new Scanner(simulatedInput);

        organization.editFields(mockScanner);

        assertEquals("New Business Group", organization.getName(), "Name should be updated to 'New Business Group'");
    }

    @Test
    void editFields_address() {
        organization.setName("Business Group");
        organization.setAddress("101 Business St.");
        organization.setPhoneNumber("1122334455");

        String simulatedInput = "address\n456 Business Rd\n";
        Scanner mockScanner = new Scanner(simulatedInput);

        organization.editFields(mockScanner);

        assertEquals("456 Business Rd", organization.getAddress(), "Address should be updated to '456 Business Rd'");
    }

    @Test
    void editFields_number() {
        organization.setName("Business Group");
        organization.setAddress("101 Business St.");
        organization.setPhoneNumber("1122334455");

        String simulatedInput = "number\n9988776655\n";
        Scanner mockScanner = new Scanner(simulatedInput);

        organization.editFields(mockScanner);

        assertEquals("9988776655", organization.getPhoneNumber(), "Phone number should be updated to '9988776655'");
    }

    @Test
    void editFields_invalid() {
        organization.setName("Business Group");
        organization.setAddress("101 Business St.");
        organization.setPhoneNumber("1122334455");

        String simulatedInput = "email\ninvalidInput\n"; // Invalid case
        Scanner mockScanner = new Scanner(simulatedInput);

        organization.editFields(mockScanner);

        // Assert that there was no change because of the invalid input
        assertEquals("Business Group", organization.getName(), "Name should remain the same.");
        assertEquals("101 Business St.", organization.getAddress(), "Address should remain the same.");
        assertEquals("1122334455", organization.getPhoneNumber(), "Phone number should remain the same.");
    }

    @Test
    void add() {
        String simulatedInput = "Tech Inc.\n100 Innovation Park\n555123456\n";

        Scanner mockScanner = new Scanner(simulatedInput);

        organization = (Organization) Organization.add(mockScanner);

        assertNotNull(organization, "Organization object should not be null after adding");

        assertEquals("Tech Inc.", organization.getName(), "Organization name should be 'Tech Inc.'");
        assertEquals("100 Innovation Park", organization.getAddress(), "Address should be '100 Innovation Park'");
        assertEquals("555123456", organization.getPhoneNumber(), "Phone number should be '555123456'");
    }
}