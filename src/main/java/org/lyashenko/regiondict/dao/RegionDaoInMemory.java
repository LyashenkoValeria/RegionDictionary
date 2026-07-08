package org.lyashenko.regiondict.dao;

import org.lyashenko.regiondict.model.Region;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionDaoInMemory implements RegionDao{
    private Map<Integer, Region> storage = new HashMap<>();

    @Override
    public List<Region> findAll() {
        return List.of();
    }

    @Override
    public Region findByRegionCode(Integer code) {
        return null;
    }

    @Override
    public void create(Region region) {
        storage.put(region.getRegionCode(), region);
    }

    @Override
    public void delete(Integer code) {

    }

    @Override
    public void update(Region region) {

    }
}
