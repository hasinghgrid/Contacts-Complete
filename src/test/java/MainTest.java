
import contactBook.Menu;
import contactBook.ContactBook;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class MainTest {

    @Test
    void testStartApplication() {
        ContactBook mockContactBook = mock(ContactBook.class, Mockito.withSettings().useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS));

        try (MockedStatic<Menu> mockedMenu = Mockito.mockStatic(Menu.class)) {

            mockedMenu.when(() -> Menu.start(mockContactBook)).thenAnswer(invocation -> null);
            Main.main(new String[]{});
            mockedMenu.verify(() -> Menu.start(any(ContactBook.class)), times(1));

        }
    }
}