package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {
    @Test
    void shouldSubmitForm() {
        open("http://localhost:9999");
        $(".input_type_text input").setValue("Мария Склодовская-Кюри");
        $(".input_type_tel input").setValue("+79220450812");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldFailNameValidationLatinLetters() {
        open("http://localhost:9999");
        $(".input_type_text input").setValue("Maria Sklodovskaya-Kuri");
        $(".input_type_tel input").setValue("+79220450812");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.".trim()));
    }

    @Test
    void shouldFailNameValidationSpecialSymbols() {
        open("http://localhost:9999");
        $(".input_type_text input").setValue("Мария Де'Кюри");
        $(".input_type_tel input").setValue("+79220450812");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.".trim()));
    }

    @Test
    void shouldFailPhoneValidationBrackets() {
        open("http://localhost:9999");
        $(".input_type_text input").setValue("Мария Склодовская-Кюри");
        $(".input_type_tel input").setValue("+7(922)0450812");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.".trim()));
    }

    @Test
    void shouldFailPhoneValidationHyphen() {
        open("http://localhost:9999");
        $(".input_type_text input").setValue("Мария Склодовская-Кюри");
        $(".input_type_tel input").setValue("+7-922-045-08-12");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.".trim()));
    }

    @Test
    void shouldFailPhoneValidationSpace() {
        open("http://localhost:9999");
        $(".input_type_text input").setValue("Мария Склодовская-Кюри");
        $(".input_type_tel input").setValue("+7 922 0450812");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.".trim()));
    }

    @Test
    void shouldFailUnpressedCheckbox() {
        open("http://localhost:9999");
        $(".input_type_text input").setValue("Мария Склодовская-Кюри");
        $(".input_type_tel input").setValue("+79220450812");
        $(".button").click();
        $(".input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй".trim()));
    }
}
