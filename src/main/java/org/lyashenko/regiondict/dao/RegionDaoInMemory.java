package org.lyashenko.regiondict.dao;

import org.lyashenko.regiondict.model.Region;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@ConditionalOnProperty(name = "daoMode", havingValue = "inMemory")
public class RegionDaoInMemory implements RegionDao {
    private final Map<Integer, Region> storage = new LinkedHashMap<>();

    @Override
    public List<Region> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Region> findByRegionCode(Integer code) {
        return Optional.ofNullable(storage.get(code));
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

    @Override
    public boolean isExists(int code) {
        return storage.get(code) != null;
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }
}
