package org.lyashenko.regiondict.dao;

import jakarta.persistence.EntityManager;
import org.lyashenko.regiondict.model.Region;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegionDaoJpa implements RegionDao{
    private final EntityManager em;

    public RegionDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Region> findAll() {
        return em.createQuery("select region from Region region", Region.class).getResultList();
    }

    @Override
    public Optional<Region> findByRegionCode(Integer code) {
        return Optional.ofNullable(em.find(Region.class, code));
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

    @Override
    public boolean isExists(int code) {
        return false;
    }

    @Override
    public void deleteAll() {

    }
}
