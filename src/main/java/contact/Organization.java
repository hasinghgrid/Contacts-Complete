package contact;


import java.time.LocalDateTime;
import java.util.Scanner;


public class Organization extends Contact {

    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String getAllInfo() {
        return this.getName() + " " + this.getAddress() + " " + this.getPhoneNumber();
    }

    @Override
    public void editFields(Scanner sc) {
        System.out.print("Select a field (name, address, number): ");
        String action = sc.nextLine();

        switch (action) {
            case "name" :
                System.out.print("Enter name: ");
                setName(sc.nextLine());
                break;

            case "address" :
                System.out.print("Enter address: ");
                setAddress(sc.nextLine());
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


    @Override
    public void getFields() {
        System.out.println("Organization name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + getTimeCreated());
        System.out.println("Time last edit: " + getTimeModified());
    }


    public static Contact add(Scanner sc) {
        Organization newOrganization = new Organization();

        System.out.print("Enter the organization name: ");
        String organizationName = sc.nextLine();
        newOrganization.setName(organizationName);

        System.out.print("Enter the company address: ");
        String companyAddress = sc.nextLine();
        newOrganization.setAddress(companyAddress);

        System.out.print("Enter the number: ");
        String number = sc.nextLine();
        newOrganization.setPhoneNumber(number);

        newOrganization.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        newOrganization.setTimeModified(LocalDateTime.now().withSecond(0).withNano(0));

        System.out.println("The record added.");
        return newOrganization;
    }

}

