import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void addShouldSumTwoNumbers() {
        assertEquals(4, App.add(2, 2));
    }
}

