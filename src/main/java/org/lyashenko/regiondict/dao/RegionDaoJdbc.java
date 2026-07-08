package org.lyashenko.regiondict.dao;

import org.lyashenko.regiondict.model.Region;

import java.util.List;

public class RegionDaoJdbc implements RegionDao{
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

    }

    @Override
    public void delete(Integer code) {

    }

    @Override
    public void update(Region region) {

    }
}
