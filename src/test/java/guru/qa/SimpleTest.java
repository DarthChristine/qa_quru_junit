package guru.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


// @Disabled //если нужно задизейблить все тесты в данном классе (здесь симпо тестс)
//@DisplayName("Учебные тесты") //название всему классу
public class SimpleTest {

    @DisplayName("Проверка, что 3>2") //аннотация, при которой при запуске теста будет отображаться название из скобок
//    @Disabled("TICKET123") //аннотация, с которой тест не будет запускать, с указанием номера бага в джире

    @Test
    void simpleTest() {
        Assertions.assertTrue(3>2);
    }
}
