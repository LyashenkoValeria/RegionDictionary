package org.lyashenko.regiondict.service;

import org.lyashenko.regiondict.model.Region;

import java.util.List;

public interface RegionService {
    List<Region> findAll();

    Region findByRegionCode(Integer code);

    void create(Region region);

    void delete(Integer code);

    void update(Region region);
}
