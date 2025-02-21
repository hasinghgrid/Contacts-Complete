import contactBook.ContactBook;
import contactBook.Menu;

public class Main {
    public static void main(String[] args) {
        ContactBook contactBook = new ContactBook();
        Menu.start(contactBook);
    }
}
