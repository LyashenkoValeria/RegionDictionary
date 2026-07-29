package org.lyashenko.regiondict.service;

import org.lyashenko.regiondict.dao.RegionDao;
import org.lyashenko.regiondict.dao.RegionDaoJdbc;
import org.lyashenko.regiondict.exception.RegionAlreadyExists;
import org.lyashenko.regiondict.exception.RegionNotFoundException;
import org.lyashenko.regiondict.model.Region;

import java.util.List;
import java.util.Optional;

public class RegionServiceImpl implements RegionService {
    private final RegionDao regionDao;

    public RegionServiceImpl(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    @Override
    public List<Region> findAll() {
        return regionDao.findAll();
    }

    @Override
    public Region findByRegionCode(Integer code) {
        return regionDao.findByRegionCode(code).orElseThrow(() ->
                new RegionNotFoundException(code));
    }

    @Override
    public void create(Region region) {
        if (regionDao.isExists(region.getRegionCode())) {
            throw new RegionAlreadyExists(region.getRegionCode());
        } else {
            regionDao.create(region);
        }

    }

    @Override
    public void delete(Integer code) {
        regionDao.delete(code);
    }

    @Override
    public void update(Region region) {
        int code = region.getRegionCode();
        if (regionDao.isExists(code)) {
            regionDao.update(region);
        } else {
            throw new RegionNotFoundException(code);
        }
    }
}
