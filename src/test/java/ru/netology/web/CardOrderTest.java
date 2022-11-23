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
}
