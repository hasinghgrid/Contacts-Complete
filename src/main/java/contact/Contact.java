package contact;

import java.time.LocalDateTime;
import java.util.Scanner;

public abstract class Contact {
    private String name;
    private LocalDateTime timeCreated;
    private LocalDateTime timeEdited;
    private String phoneNumber;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber.isEmpty()? "[no number]" : this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        String regex = "^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*";

        if (phoneNumber.matches(regex)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            this.phoneNumber = "";
        }
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeModified() {
        return timeEdited;
    }

    public void setTimeModified(LocalDateTime timeModified) {
        this.timeEdited = timeModified;
    }

    public String toString(){
        return "Contacts{" +
                "name='" + '\'' +
                " ,phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


    public abstract String getAllInfo() ;

    public abstract void getFields() ;

    public abstract void editFields(Scanner sc);

}
