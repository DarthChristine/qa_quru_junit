package guru.qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$; //статически импортировать нужно только то, что привычно и очевидно всем
import static com.codeborne.selenide.Selenide.$$;

public class ParamsTest {

//    @CsvFileSource(resources = "src/test/resources/test res") //аннотация для получения тестдаты из файла

    @ValueSource(strings = {"JUnit5", "TestNG"}) //аннотация для тестовых данных для параметризованного теста

    @ParameterizedTest(name = "Проверка поиска для {0}") //аннотация параметризованного теста, можно без скобок
    //в скобках - обращение к параметру по индеку, в данном случае 0 - это testData, был бы еще один - он 1
    void yaTestCommon(String testData) {
        Selenide.open("https://ya.ru"); //вызвали опен через класс селенид,
        //в таком случае вверху статичный опен не импортится и можно использовать опены из других библиотек
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$(".serp-item").find(text(testData)).shouldBe(visible); //serp-item - найти все карточки с результатами поиска, а в них junit5
    }


//    @Test
//    void yaTestJUnit() {
//        Selenide.open("https://ya.ru"); //вызвали опен через класс селенид,
//        //в таком случае вверху статичный опен не импортится и можно использовать опены из других библиотек
//        $("#text").setValue("JUnit5");
//        $("button[type='submit']").click();
//        $$(".serp-item").find(text("JUnit5")).shouldBe(visible); //serp-item - найти все карточки с результатами поиска, а в них junit5
//    }

//    @Test
//    void yaTestTestNG() {
//        Selenide.open("https://ya.ru"); //вызвали опен через класс селенид,
//        //в таком случае вверху статичный опен не импортится и можно использовать опены из других библиотек
//        $("#text").setValue("TestNG");
//        $("button[type='submit']").click();
//        $$(".serp-item").find(text("TestNG")).shouldBe(visible); //serp-item - найти все карточки с результатами поиска, а в них junit5
//    }
}
