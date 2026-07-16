package org.lyashenko.regiondict.dao;

import org.lyashenko.regiondict.model.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionDaoInMemory implements RegionDao{
    private Map<Integer, Region> storage = new HashMap<>();

    @Override
    public List<Region> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Region findByRegionCode(Integer code) {
        return storage.get(code);
    }

    @Override
    public void create(Region region) {
        storage.put(region.getRegionCode(), region);
    }

    @Override
    public void delete(Integer code) {
        storage.remove(code);
    }

    @Override
    public void update(Region region) {
        Integer code = region.getRegionCode();
        storage.put(code, region);
    }
}
