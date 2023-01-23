package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @Test
    void sendOnlyEnglish() {

        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceImpl.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location(null, Country.USA, null, 0));

        LocalizationServiceImpl localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceImpl.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoServiceImpl, localizationServiceImpl);
        Map<String, String> header = new HashMap<String, String>();
        header.put("x-real-ip", "96.44.183.149");
        Assertions.assertEquals(messageSender.send(header), "Welcome");
    }
    @Test
    void sendOnlyRussian() {

        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceImpl.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationServiceImpl localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceImpl.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoServiceImpl, localizationServiceImpl);
        Map<String, String> header = new HashMap<String, String>();
        header.put("x-real-ip", "172.0.32.11");
        Assertions.assertEquals(messageSender.send(header), "Добро пожаловать");


    }


}