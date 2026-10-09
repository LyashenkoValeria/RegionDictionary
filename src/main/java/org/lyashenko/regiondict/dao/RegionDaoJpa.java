package org.lyashenko.regiondict.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.lyashenko.regiondict.model.Region;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegionDaoJpa implements RegionDao {
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
    @Transactional
    public void create(Region region) {
        em.persist(region);
    }

    @Override
    @Transactional
    public void delete(Integer code) {
        if (isExists(code)) {
            Region region = Optional.ofNullable(em.find(Region.class, code)).get();
            em.remove(region);
        }
    }

    @Override
    @Transactional
    public void update(Region region) {
        em.merge(region);
    }

    @Override
    public boolean isExists(int code) {
        Long count = em.createQuery(
                        "select count(region) from Region region where region.regionCode = :code", Long.class)
                .setParameter("code", code)
                .getSingleResult();
        return count > 0;
    }

    @Override
    @Transactional
    public void deleteAll() {
        em.createQuery("delete from Region region").executeUpdate();
    }
}
