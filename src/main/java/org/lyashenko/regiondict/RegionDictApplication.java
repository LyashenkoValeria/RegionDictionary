package org.lyashenko.regiondict;

import org.lyashenko.regiondict.dao.RegionDaoJdbc;
import org.lyashenko.regiondict.model.Region;
import org.lyashenko.regiondict.service.RegionService;
import org.lyashenko.regiondict.service.RegionServiceImpl;

public class RegionDictApplication {
    public static void main(String[] args){
        RegionService service = new RegionServiceImpl(new RegionDaoJdbc());

        service.create(new Region(8, "Калмыкия"));
        System.out.println(service.findByRegionCode(8));
        service.update(new Region(8, "Республика Калмыкия"));
        System.out.println(service.findByRegionCode(8));
        service.delete(8);
        System.out.println(service.findByRegionCode(8));
    }
}
