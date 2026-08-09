package org.lyashenko.regiondict;

import org.lyashenko.regiondict.dao.RegionDaoJdbc;
import org.lyashenko.regiondict.model.Region;
import org.lyashenko.regiondict.service.RegionService;
import org.lyashenko.regiondict.service.RegionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RegionDictApplication {
    public static void main(String[] args){
        //RegionService service = new RegionServiceImpl(new RegionDaoJdbc());
        ConfigurableApplicationContext context = SpringApplication.run(RegionDictApplication.class);
        RegionService service = context.getBean(RegionService.class);
        service.create(new Region(8, "Калмыкия"));
        System.out.println(service.findByRegionCode(8));
        service.update(new Region(8, "Республика Калмыкия"));
        System.out.println(service.findByRegionCode(8));
        service.delete(8);
        System.out.println(service.findByRegionCode(8));
    }
}
