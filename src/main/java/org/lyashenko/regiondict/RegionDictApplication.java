package org.lyashenko.regiondict;

import org.lyashenko.regiondict.dao.RegionDao;
import org.lyashenko.regiondict.dao.RegionDaoJdbc;
import org.lyashenko.regiondict.model.Region;

public class RegionDictApplication {
    public static void main(String[] args){
        RegionDao regionDaoJdbc = new RegionDaoJdbc();
        regionDaoJdbc.create(new Region(8, "Калмыкия"));
        System.out.println(regionDaoJdbc.findByRegionCode(8));
        regionDaoJdbc.update(new Region(8, "Республика Калмыкия"));
        System.out.println(regionDaoJdbc.findByRegionCode(8));
        regionDaoJdbc.delete(8);
        System.out.println(regionDaoJdbc.findByRegionCode(8));
    }
}
