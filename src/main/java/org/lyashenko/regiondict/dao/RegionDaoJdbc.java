package org.lyashenko.regiondict.dao;

import org.lyashenko.regiondict.exception.SqlProcessingException;
import org.lyashenko.regiondict.model.Region;
import org.lyashenko.regiondict.util.ConnectionUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RegionDaoJdbc implements RegionDao {
    private static final String FIND_ALL = "select * from region";
    private static final String FIND_BY_CODE = "select * from region where region_code = ?";
    private static final String CREATE_REGION = "insert into region (region_code, region_name) values (?, ?)";
    private static final String DELETE_REGION = "delete from region where region_code = ?";
    private static final String UPDATE_REGION = "update region set region_name = ? where region_code = ?";
    private static final String REGION_EXISTS = "select exists (select 1 from region where region_code = ?) as found";


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
    public Optional<Region> findByRegionCode(Integer code) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_CODE)) {
            preparedStatement.setInt(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(buildRegion(resultSet));
            }
            return Optional.empty();
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

    @Override
    public boolean isExists(int code) {
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REGION_EXISTS)){
            preparedStatement.setInt(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean("found");

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
