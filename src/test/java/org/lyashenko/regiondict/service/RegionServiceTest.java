package org.lyashenko.regiondict.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lyashenko.regiondict.exception.RegionNotFoundException;
import org.lyashenko.regiondict.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
class RegionServiceTest {
    private final RegionService regionService;

    @Autowired
    public RegionServiceTest(RegionService regionService) {
        this.regionService = regionService;
    }

    @Test
    void creatAndFindTest() {
        Region region1 = new Region(21, "Чувашская Республика");

        regionService.create(region1);

        Assertions.assertEquals(region1, regionService.findByRegionCode(region1.getRegionCode()));
    }

    @Test
    void findUnexistedRegionTest() {
        Assertions.assertThrows(RegionNotFoundException.class, () -> regionService.findByRegionCode(100000000));
    }

    //TODO: Написать остальные тесты, учесть тесты с ошибками || AlreadyExist
}