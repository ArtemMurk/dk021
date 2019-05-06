package com.murk.dk021.core.dao;

import com.murk.dk021.core.model.Classificator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.murk.dk021.core.dao.SQLHelper.*;

@Repository
@Slf4j

public class ClassificatorDaoImpl implements ClassificatorDao {

    private BasicDataSource ds;

    @Autowired
    public ClassificatorDaoImpl(BasicDataSource ds) {
        this.ds = ds;
    }

    @Override
    public Classificator get(int id, short num) {
        Classificator classificator = null;
        try (Connection connection = ds.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(SELECT_CLASSIFICATOR);)
            {
            statement.setInt(1,id);
            statement.setShort(2,num);
                try (ResultSet resultSet = statement.executeQuery())
                {
                    if (resultSet.next())
                    {
                    classificator = getClassificatorFromResultSet(resultSet);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classificator;
    }


    @Override
    public Set<Classificator> getNodes(int id, short num) {
        Set<Classificator> classificators = ConcurrentHashMap.newKeySet();

        try (Connection connection = ds.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(SELECT_CLASSIFICATOR_NODES))
            {
                statement.setInt(1,id);
                statement.setShort(2,num);
                try (ResultSet resultSet = statement.executeQuery())
                {
                    while (resultSet.next())
                    {
                        classificators.add(getClassificatorFromResultSet(resultSet));
                    }
                }
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return classificators;
    }

    @Override
    public Set<Classificator> getRootNodes() {
        Set<Classificator> classificators = ConcurrentHashMap.newKeySet();

        try (Connection connection = ds.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ROOT_NODES))
            {
                try (ResultSet resultSet = statement.executeQuery())
                {
                    while (resultSet.next())
                    {
                        classificators.add( getClassificatorFromResultSet(resultSet));
                    }
                }
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return classificators;
    }

    private Classificator getClassificatorFromResultSet(ResultSet resultSet) throws SQLException {
        Classificator classificator;

        int idC = resultSet.getInt("id");
        short  numC= resultSet.getShort("num");
        Integer parentidC = resultSet.getInt("parentid");
        String nameC = resultSet.getString("name");

        classificator = new Classificator(idC,numC,parentidC,nameC);
        return classificator;
    }

    @Override
    public void update(Map<Integer, Classificator> classificators) {
        try (Connection connection = ds.getConnection()){
            connection.setAutoCommit(false);

            createTempTable(connection);

            log.warn("Fill info in temp table");
            insertInTempTable(connection,classificators);

            log.warn("Start synchronize calssificators");
            truncateMainTable(connection);
            synchronizeTables(connection);
            dropTempTable(connection);

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }



    private void createTempTable(Connection connection) throws SQLException {
        try(PreparedStatement createTemptableStatement = connection.prepareStatement(CREATE_TEMP_TABLE))
        {
            createTemptableStatement.execute();
        }
    }

    private void insertInTempTable(Connection connection, Map<Integer, Classificator> classificators) throws SQLException {
        for (Map.Entry<Integer, Classificator> entry : classificators.entrySet()) {
            Integer id = entry.getKey();
            Classificator classificator = entry.getValue();

            insertInTempTable(connection,classificator);
        }
    }

    private void insertInTempTable(Connection connection, Classificator classificator) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_CLASSIFICATOR);)
        {
            statement.setInt(1,classificator.getId());
            statement.setShort(2,classificator.getNum());

            if (classificator.getParentId()== null)
            {
                statement.setNull(3, Types.SMALLINT);
            } else
            {
                statement.setInt(3,classificator.getParentId());
            }

            statement.setString(4,classificator.getName());

            statement.execute();
        }
    }

    private void truncateMainTable(Connection connection) throws SQLException {
        try(PreparedStatement createTemptableStatement = connection.prepareStatement(TRUNCATE_MAIN_TABLE))
        {
            createTemptableStatement.execute();
        }
    }
    private void synchronizeTables(Connection connection) throws SQLException {
        try(PreparedStatement synchronizeStatement = connection.prepareStatement(TRANSFER_CLASSIFICATORS))
        {
            synchronizeStatement.execute();
        }
    }

    private void dropTempTable(Connection connection) throws SQLException {
        try(PreparedStatement dropTempTableStatement = connection.prepareStatement(DROP_TEMP_TABLE))
        {
            dropTempTableStatement.execute();
        }
    }
}
