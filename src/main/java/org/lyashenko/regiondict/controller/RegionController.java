package org.lyashenko.regiondict.controller;

import org.lyashenko.regiondict.model.Region;
import org.lyashenko.regiondict.service.RegionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    //TODO: Сделать возврат Swagger(в виде UI) - html

    @GetMapping("/api/v1/regions")
    public List<Region> findAll(){
        return regionService.findAll();
    }

    @GetMapping("/api/v1/region/{code}")
    public Region findByCode(@PathVariable("code") int code){
        return regionService.findByRegionCode(code);
    }

    @PostMapping("/api/v1/region")
    public void create(@RequestBody Region region){
        regionService.create(region);
    }

    @DeleteMapping("/api/v1/region/{code}")
    public void delete(@PathVariable("code") int code) {
        regionService.delete(code);
    }

    @PutMapping("/api/v1/region")
    public void update(@RequestBody Region region){
        regionService.update(region);
    }
}
