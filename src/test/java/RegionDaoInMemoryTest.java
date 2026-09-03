import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.lyashenko.regiondict.dao.RegionDao;
import org.lyashenko.regiondict.dao.RegionDaoInMemory;
import org.lyashenko.regiondict.model.Region;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegionDaoInMemoryTest {
    private final RegionDao regionDao = new RegionDaoInMemory();

    @AfterEach
    void cleanUp(){
        regionDao.deleteAll();
    }

    @Test
    void testFindAll(){
        Region region1 = new Region(21, "Чувашская Республика");
        Region region2 = new Region(77, "Москва");
        regionDao.create(region1);
        regionDao.create(region2);


        List<Region> resultRegions = regionDao.findAll();

        Assertions.assertEquals(2, resultRegions.size());
        Assertions.assertEquals(region1, resultRegions.get(0));
        Assertions.assertEquals(region2, resultRegions.get(1));
    }

    @Test
    void testCreateAndFind(){
        Region region1 = new Region(21, "Чувашская Республика");

        regionDao.create(region1);

        Assertions.assertEquals(region1, regionDao.findByRegionCode(region1.getRegionCode()).get());
    }

    @Test
    void testDeleteAndIsExists(){
        Region region1 = new Region(21, "Чувашская Республика");
        regionDao.create(region1);
        Assertions.assertTrue(regionDao.isExists(region1.getRegionCode()));

        regionDao.delete(region1.getRegionCode());

        Assertions.assertFalse(regionDao.isExists(region1.getRegionCode()));
    }

    @Test
    void testUpdate(){
        Region region1 = new Region(21, "Чувашская Республика");
        Region regionForUpdate = new Region(21, "Чувашия");
        regionDao.create(region1);

        regionDao.update(regionForUpdate);

        Assertions.assertEquals(regionForUpdate, regionDao.findByRegionCode(regionForUpdate.getRegionCode()).get());
    }
}
