package org.lyashenko.regiondict.dao;

import org.lyashenko.regiondict.exception.SqlProcessingException;
import org.lyashenko.regiondict.model.Region;
import org.lyashenko.regiondict.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionDaoJdbc implements RegionDao {
    private static final String FIND_ALL = "select * from region";
    private static final String FIND_BY_CODE = "select * from region where region_code = ?";
    private static final String CREATE_REGION = "insert into region (region_code, region_name) values (?, ?)";
    private static final String DELETE_REGION = "delete from region where region_code = ?";
    private static final String UPDATE_REGION = "update region set region_name = ? where region_code = ?";


    @Override
    public List<Region> findAll() {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            List<Region> regions = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                regions.add(buildRegion(resultSet));
            }
            return regions;
        } catch (SQLException e) {
            throw new SqlProcessingException(e);
        }
    }

    @Override
    public Region findByRegionCode(Integer code) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_CODE)) {
            preparedStatement.setInt(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return buildRegion(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new SqlProcessingException(e);
        }
    }

    @Override
    public void create(Region region) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_REGION)) {
            preparedStatement.setInt(1, region.getRegionCode());
            preparedStatement.setString(2, region.getRegionName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlProcessingException(e);
        }
    }

    @Override
    public void delete(Integer code) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REGION)) {
            preparedStatement.setInt(1, code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlProcessingException(e);
        }
    }

    @Override
    public void update(Region region) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REGION)) {
            preparedStatement.setString(1, region.getRegionName());
            preparedStatement.setInt(2, region.getRegionCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlProcessingException(e);
        }
    }

    private Region buildRegion(ResultSet resultSet) throws SQLException {
        int regionCode = resultSet.getInt("region_code");
        String regionName = resultSet.getString("region_name");
        return new Region(regionCode, regionName);
    }
}
