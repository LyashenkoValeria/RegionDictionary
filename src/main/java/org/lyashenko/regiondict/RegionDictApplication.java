package org.lyashenko.regiondict;

import org.lyashenko.regiondict.dao.RegionDaoInMemory;
import org.lyashenko.regiondict.model.Region;

public class RegionDictApplication {
    public static void main(String[] args) {
        RegionDaoInMemory memoryDao = new RegionDaoInMemory();
        memoryDao.create(new Region(1, "Адыгея"));
        memoryDao.create(new Region(77, "Москва"));
        memoryDao.create(new Region(47, "Ленинградская область"));
        memoryDao.create(new Region(78, "Санкт-Петербург"));
        memoryDao.create(new Region(97, "Москва"));
        System.out.println(memoryDao.findAll());

        memoryDao.delete(97);
        memoryDao.update(new Region(1, "Республика Адыгея"));
        System.out.println(memoryDao.findByRegionCode(1).toString());
        System.out.println(memoryDao.findAll());
    }
}
