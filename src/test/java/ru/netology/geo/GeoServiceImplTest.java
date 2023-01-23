package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.geo.GeoServiceImpl.*;

class GeoServiceImplTest {

    @Test
    void byIp() {
        GeoServiceImpl geoServiceImpl = Mockito.spy(GeoServiceImpl.class);
        Assertions.assertEquals(null, geoServiceImpl.byIp("95."));
        Assertions.assertEquals(new Location("New York", Country.USA, null, 0).getCity(), geoServiceImpl.byIp("96.").getCity());
        Assertions.assertEquals(new Location("New York", Country.USA, null, 0).getCountry(), geoServiceImpl.byIp("96.").getCountry());
        Assertions.assertEquals(new Location("Moscow", Country.RUSSIA, null, 0).getCountry(), geoServiceImpl.byIp("172.").getCountry());
        Assertions.assertEquals(new Location("Moscow", Country.RUSSIA, null, 0).getCity(), geoServiceImpl.byIp("172.").getCity());
        Assertions.assertEquals(new Location("New York", Country.USA, " 10th Avenue", 32).getCity(), geoServiceImpl.byIp(NEW_YORK_IP).getCity());
        Assertions.assertEquals(new Location("New York", Country.USA, " 10th Avenue", 32).getStreet(), geoServiceImpl.byIp(NEW_YORK_IP).getStreet());
        Assertions.assertEquals(new Location("Moscow", Country.RUSSIA, "Lenina", 15).getCity(), geoServiceImpl.byIp(MOSCOW_IP).getCity());
        Assertions.assertEquals(new Location("Moscow", Country.RUSSIA, "Lenina", 15).getBuiling(), geoServiceImpl.byIp(MOSCOW_IP).getBuiling());
        Assertions.assertEquals(new Location(null, null, null, 0).getStreet(), geoServiceImpl.byIp(LOCALHOST).getStreet());


    }

//    @Test
//    void byIp() {
//        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
//        Mockito.when(geoServiceImpl.byIp(Mockito.startsWith("96.")))
//                .thenReturn(new Location(null, Country.USA, null, 0));
//    }
}