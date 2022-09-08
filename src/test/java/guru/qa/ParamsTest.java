package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$; //статически импортировать нужно только то, что привычно и очевидно всем
import static com.codeborne.selenide.Selenide.$$;

public class ParamsTest {

//    @CsvFileSource(resources = "src/test/resources/test res") //аннотация для получения тестдаты из файла

    @ValueSource(strings = {"JUnit5", "TestNG"}) //аннотация для тестовых данных для параметризованного теста

    @ParameterizedTest(name = "При поиске в яндексе {0} в результатах отображается {0}") //аннотация параметризованного теста, можно без скобок
    //в скобках - обращение к параметру по индеку, в данном случае 0 - это testData, был бы еще один - он 1
    void yaTestCommon(String testData) {
        Selenide.open("https://ya.ru"); //вызвали опен через класс селенид,
        //в таком случае вверху статичный опен не импортится и можно использовать опены из других библиотек
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$(".serp-item").find(text(testData)).shouldBe(visible); //serp-item - найти все карточки с результатами поиска, а в них junit5
    }

    @CsvSource(value = {
            "JUnit5, is the next generation of JUnit",
             "TestNG, is a testing framework inspired from Junit and NUnit"
    }) //аннотация

//    @CsvFileSource(resources = "rc/test/resources/1.csv") //добавляем данные как в предыдущей аннотации, но из файла

    @ParameterizedTest(name = "При поиске в яндексе {0} в результатах отображается {1}") //
    void yaTestComplex(String searchData, String expectedResult) {
        Selenide.open("https://ya.ru"); //
        $("#text").setValue(searchData);
        $("button[type='submit']").click();
        $$(".serp-item").find(text(expectedResult)).shouldBe(visible); //
    }


    static Stream<Arguments> yaTestVeryComplexDataProvider () { //далее базовая конструкция, аргументов может быть больше
        return Stream.of(
                Arguments.of("JUnit 5", List.of("JUnit 5", "framework")),
                Arguments.of("TestNG", List.of("TestNG", "framework"))
        );

    }
    @MethodSource(value = "yaTestVeryComplexDataProvider") //аннотация, для нее нужен статичный метод (выше), value можно не указывать, если метод стрим называется так же как тест

    @ParameterizedTest(name = "При поиске в яндексе {0} в результатах отображается {1}") //тест упадет, т.к. мы задали два варианта с этим текстом, а по факту там больше
    void yaTestVeryComplex(String searchData, List<String> expectedResult) {
        Selenide.open("https://ya.ru");
        $("#text").setValue(searchData);
        $("button[type='submit']").click();
        $$(".serp-item").shouldHave(CollectionCondition.texts(expectedResult));
    }

    @EnumSource(Sex.class) //тест будет запущен столько раз, сколько у нас параметров в классе sex
    @ParameterizedTest
    void enumTest(Sex sex){
        Selenide.open("https://ya.ru");
        $("#text").setValue(sex.desc);
        $("button[type='submit']").click();
        $$(".serp-item").find(text(sex.desc)).shouldBe(visible);
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
