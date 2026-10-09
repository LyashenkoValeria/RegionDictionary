package org.lyashenko.regiondict.repository;

import org.lyashenko.regiondict.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {

}
