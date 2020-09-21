package backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    @Test
    void changeThemeToClassic() {
        Controller controller1 = new Controller();
        assertEquals(024,024);
    }

    @Test
    void changeThemeToForrest() {
        Controller controller1 = new Controller();
        assertEquals(68,68);
    }

    @Test
    void changeThemeToHighContrast() {
        Controller controller2 = new Controller();
        assertEquals(70, 70);
    }
}