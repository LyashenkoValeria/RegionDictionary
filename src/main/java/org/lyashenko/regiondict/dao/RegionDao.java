package org.lyashenko.regiondict.dao;

import org.lyashenko.regiondict.model.Region;

import java.util.List;

public interface RegionDao {

    List<Region> findAll();

    Region findByRegionCode(Integer code);

    void create(Region region);

    void delete(Integer code);

    void update(Region region);
}
