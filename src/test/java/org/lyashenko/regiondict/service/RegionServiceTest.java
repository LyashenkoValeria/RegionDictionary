package org.lyashenko.regiondict.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lyashenko.regiondict.exception.RegionAlreadyExists;
import org.lyashenko.regiondict.exception.RegionNotFoundException;
import org.lyashenko.regiondict.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@Testcontainers
class RegionServiceTest {
    private final RegionService regionService;

    @Autowired
    public RegionServiceTest(RegionService regionService) {
        this.regionService = regionService;
    }

    @AfterEach
    void cleanUp() {
        //regionService.deleteAll();
    }

    @Test
    void testCreatAndFind() {
        Region region1 = new Region(21, "Чувашская Республика");

        regionService.create(region1);

        Assertions.assertEquals(region1, regionService.findByRegionCode(region1.getRegionCode()));
    }

    @Test
    void testFindUnexistedRegion() {
        Assertions.assertThrows(RegionNotFoundException.class, () -> regionService.findByRegionCode(100000000));
    }

    @Test
    void testFindAll() {
        Region region1 = new Region(21, "Чувашская Республика");
        Region region2 = new Region(77, "Москва");
        regionService.create(region1);
        regionService.create(region2);


        List<Region> resultRegions = regionService.findAll();

        Assertions.assertEquals(2, resultRegions.size());
        Assertions.assertEquals(region1, resultRegions.get(0));
        Assertions.assertEquals(region2, resultRegions.get(1));
    }

    @Test
    void testDeleteAndIsExists() {
        Region region1 = new Region(21, "Чувашская Республика");
        regionService.create(region1);
        Assertions.assertEquals(region1, regionService.findByRegionCode(region1.getRegionCode()));

        regionService.delete(region1.getRegionCode());

        Assertions.assertThrows(RegionNotFoundException.class, () -> regionService.findByRegionCode(region1.getRegionCode()));
    }

    @Test
    void testUpdate() {
        Region region1 = new Region(21, "Чувашская Республика");
        Region regionForUpdate = new Region(21, "Чувашия");
        regionService.create(region1);

        regionService.update(regionForUpdate);

        Assertions.assertEquals(regionForUpdate, regionService.findByRegionCode(regionForUpdate.getRegionCode()));
    }

    @Test
    void testAlreadyExists() {
        Region region1 = new Region(21, "Чувашская Республика");
        regionService.create(region1);
        Assertions.assertThrows(RegionAlreadyExists.class, () -> regionService.create(region1));
    }
}