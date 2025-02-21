package contact;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Person extends Contact{
    private String surName;
    private String birthDate;
    private String gender;

    public String getSurName() {
        return this.surName;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public String getGender() {
        return this.gender;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setBirthDate(String birthDate) {
        if(birthDate.isEmpty()){
            System.out.println("Bad birth date!");
            this.birthDate = "[no data]";
        } else{
            this.birthDate = birthDate;
        }

    }

    public void setGender(String gender) {
        if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")){
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        } else {
            this.gender = gender;
        }
    }

    public static Contact add(Scanner sc) {
        Person newPerson = new Person();

        System.out.print("Enter the name: ");
        String name = sc.nextLine();
        newPerson.setName(name);

        System.out.print("Enter the surname: ");
        String surname = sc.nextLine();
        newPerson.setSurName(surname);

        System.out.print("Enter the birth of date: ");
        String birth = sc.nextLine();
        newPerson.setBirthDate(birth);

        System.out.print("Enter the gender (M,F): ");
        String gender = sc.nextLine();
        newPerson.setGender(gender);

        System.out.print("Enter the number: ");
        String number = sc.nextLine();
        newPerson.setPhoneNumber(number);

        newPerson.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        newPerson.setTimeModified(LocalDateTime.now().withSecond(0).withNano(0));

        System.out.println("The record added.");
        return newPerson;
    }

    @Override
    public String getAllInfo() {
        return this.getName() + " " + this.getSurName() + " " + this.getPhoneNumber();
    }

    @Override
    public void getFields() {
        System.out.println("Name: " + getName());
        System.out.println("Surname: " + getSurName());
        System.out.println("Birth date: " + getBirthDate());
        System.out.println("Gender: " + getGender());
        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + getTimeCreated());
        System.out.println("Time last edit: " + getTimeModified());
    }

    @Override
    public void editFields(Scanner sc) {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = sc.nextLine();

        switch (field) {
            case "name" :
                System.out.print("Enter name: ");
                setName(sc.nextLine());
                break;

            case "surname" :
                System.out.print("Enter surname: ");
                setSurName(sc.nextLine());
                break;

            case "birth" :
                System.out.print("Enter birth date: ");
                setBirthDate(sc.nextLine());
                break;

            case "gender" :
                System.out.print("Enter gender: ");
                setGender(sc.nextLine());
                break;

            case "number" :
                System.out.print("Enter number: ");
                setPhoneNumber(sc.nextLine());
                break;

            default : System.out.println("Invalid Command!");
        }
        System.out.println("Saved");
        this.setTimeModified(LocalDateTime.now().withSecond(0).withNano(0));
    }

}