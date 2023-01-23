package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

class LocalizationServiceImplTest {

    @Test
    void locale() {
        LocalizationServiceImpl localizationServiceImpl = Mockito.spy(LocalizationServiceImpl.class);
        Assertions.assertEquals("Добро пожаловать", localizationServiceImpl.locale(RUSSIA));
        Assertions.assertEquals("Welcome", localizationServiceImpl.locale(USA));
    }
}