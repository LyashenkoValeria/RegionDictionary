package org.lyashenko.regiondict.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @Column(name = "region_code")
    private Integer regionCode;
    @Column(name = "region_name")
    private String regionName;

    public Region() {}

    public Region(Integer regionCode, String regionName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionCode=" + regionCode +
                ", regionName='" + regionName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Region other = (Region) obj;
        return regionCode.equals(other.regionCode) && regionName.equals(other.regionName);
    }
}
