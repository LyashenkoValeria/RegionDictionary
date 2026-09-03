package org.lyashenko.regiondict.model;

public class Region {
    private Integer regionCode;
    private String regionName;

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
