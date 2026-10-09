package org.lyashenko.regiondict.service;

import org.lyashenko.regiondict.exception.RegionAlreadyExists;
import org.lyashenko.regiondict.exception.RegionNotFoundException;
import org.lyashenko.regiondict.model.Region;
import org.lyashenko.regiondict.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository repository;

    public RegionServiceImpl(RegionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Region> findAll() {
        return repository.findAll();
    }

    @Override
    public Region findByRegionCode(Integer code) {
        return repository.findById(code).orElseThrow(() ->
                new RegionNotFoundException(code));
    }

    @Override
    public void create(Region region) {
        if (repository.existsById(region.getRegionCode())) {
            throw new RegionAlreadyExists(region.getRegionCode());
        } else {
            repository.save(region);
        }

    }

    @Override
    public void delete(Integer code) {
        repository.deleteById(code);
    }

    @Override
    public void update(Region region) {
        int code = region.getRegionCode();
        if (repository.existsById(code)) {
            repository.save(region);
        } else {
            throw new RegionNotFoundException(code);
        }
    }
}
