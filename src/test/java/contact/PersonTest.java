package contact;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person();

    }

    @Test
    void getSurName() {
        person.setSurName("Doe");
        assertEquals("Doe", person.getSurName(), "Surname should be 'Doe'");
    }

    @Test
    void getBirthDate() {
        person.setBirthDate("01-01-2000");
        assertEquals("01-01-2000", person.getBirthDate(), "Birth date should be '01-01-2000'");
    }

    @Test
    void getGender() {
        person.setGender("M");
        assertEquals("M", person.getGender(), "Gender should be 'M'");

        person.setGender("F");
        assertEquals("F", person.getGender(), "Gender should be 'F'");

        person.setGender("X");  // Invalid gender
        assertEquals("[no data]", person.getGender(), "Gender should be '[no data]' for invalid input");
    }

    @Test
    void setSurName() {
        person.setSurName("Smith");
        assertEquals("Smith", person.getSurName(), "Surname should be set correctly");
    }

    @Test
    void setBirthDate() {
        person.setBirthDate("25-12-1995");
        assertEquals("25-12-1995", person.getBirthDate(), "Birth date should be set correctly");

        person.setBirthDate("");  // Invalid birth date
        assertEquals("[no data]", person.getBirthDate(), "Birth date should be '[no data]' for empty input");
    }

    @Test
    void setGender() {
        person.setGender("M");
        assertEquals("M", person.getGender(), "Gender should be set correctly");

        person.setGender("Invalid");  // Invalid gender
        assertEquals("[no data]", person.getGender(), "Gender should be '[no data]' for invalid input");
    }

    @Test
    void add() {

        String simulatedInput = "John\nDoe\n01-01-2000\nM\n123456789\n";

        Scanner mockScanner = new Scanner(simulatedInput);

        person = (Person) Person.add(mockScanner);

        assertNotNull(person, "Person object should not be null after adding");

        assertEquals("John", person.getName(), "Name should be 'John'");
        assertEquals("Doe", person.getSurName(), "Surname should be 'Doe'");
        assertEquals("01-01-2000", person.getBirthDate(), "Birth date should be '01-01-2000'");
        assertEquals("M", person.getGender(), "Gender should be 'M'");
        assertEquals("123456789", person.getPhoneNumber(), "Phone number should be '123456789'");
    }


    @Test
    void getAllInfo() {
        person.setName("John");
        person.setSurName("Doe");
        person.setPhoneNumber("123456789");

        String allInfo = person.getAllInfo();
        assertTrue(allInfo.contains("John"), "All info should contain the name");
        assertTrue(allInfo.contains("Doe"), "All info should contain the surname");
        assertTrue(allInfo.contains("123456789"), "All info should contain the phone number");
    }

    @Test
    void getFields() {
        person.setName("Jane");
        person.setSurName("Smith");
        person.setBirthDate("15-03-1990");
        person.setGender("F");
        person.setPhoneNumber("987654321");

        person.getFields();
    }

    @Test
    void editFields() {
        person.setName("Mike");
        person.setSurName("Jordan");
        person.setPhoneNumber("123123123");
        person.setBirthDate("15-03-1990");
        person.setGender("F");

        String newName = "Michael";
        String newSurname = "Jordan";
        String newNumber = "321321321";
        String newBirth = "2012-12-12";
        String newGender = "M";

        person.editFields(new Scanner("name\n" + newName + "\n"));
        person.editFields(new Scanner("surname\n" + newSurname + "\n"));
        person.editFields(new Scanner("number\n" + newNumber + "\n"));
        person.editFields(new Scanner("birth\n" + newBirth + "\n"));
        person.editFields(new Scanner("gender\n" + newGender + "\n"));
        assertEquals(newName, person.getName(), "The name should be updated to 'Michael'");
        assertEquals(newSurname, person.getSurName(), "The surname should be updated to 'Jordan'");
        assertEquals(newNumber, person.getPhoneNumber(), "The phone number should be updated to '321321321'");
        assertEquals(newBirth, person.getBirthDate(), "The surname should be updated to 'Jordan'");
        assertEquals(newGender, person.getGender(), "The phone number should be updated to '321321321'");
    }

    @Test
    void editFieldsInvalid() {
        person.editFields(new Scanner("nam\n" + "John" + "\n"));
    }
}