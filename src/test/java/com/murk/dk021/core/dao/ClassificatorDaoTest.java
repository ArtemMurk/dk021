package com.murk.dk021.core.dao;

import com.murk.dk021.core.exception.EmptyClassificatorException;
import com.murk.dk021.core.model.Classificator;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import static mocks.ClassificatorInfo.CLASSIFICATOR_NOT_FOUND_ID;
import static mocks.ClassificatorInfo.CLASSIFICATOR_NOT_FOUND_NUM;
import static mocks.model.MockModels.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ContextConfiguration(locations = "file:src/test/resources/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ClassificatorDaoTest {


    @Autowired
    private ClassificatorDao dao;

    @Autowired
    private BasicDataSource ds;

    @Before
    public void initDb()
    {
        try(Connection connection = ds.getConnection())
        {
            InputStream createTableStream  = new FileInputStream("dk_021.sql");
            DBHelper.importSQL(connection,createTableStream);

            InputStream insertMocksStream  = new FileInputStream("src/test/java/mocks/insert_mocks.sql");
            DBHelper.importSQL(connection,insertMocksStream);


        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSuccess()
    {
        Classificator classificator = dao.get(CLASSIFICATOR_MODEL_1.getId(),CLASSIFICATOR_MODEL_1.getNum());
        assertThat(CLASSIFICATOR_MODEL_1).isEqualToComparingFieldByField(classificator);
    }

    @Test
    public void getNotFound()
    {
        Classificator classificator = dao.get(CLASSIFICATOR_NOT_FOUND_ID,CLASSIFICATOR_NOT_FOUND_NUM);
        assertThat(classificator).isNull();
    }

    @Test
    public void getNodesSuccess()
    {
        Set<Classificator> nodes = dao.getNodes(CLASSIFICATOR_MODEL_1.getId(),CLASSIFICATOR_MODEL_1.getNum());
        assertThat(NODES_FOR_CLASSIFICATOR_MODEL_1).isEqualTo(nodes);
    }

    @Test
    public void getNodesNotFound()
    {
        Set<Classificator> nodes = dao.getNodes(CLASSIFICATOR_NOT_FOUND_ID,CLASSIFICATOR_NOT_FOUND_NUM);
        assertThat(nodes.size()).isZero();
    }

    @Test
    public void getRootNodesSuccess()
    {
        Set<Classificator> rootNodes = dao.getRootNodes();
        assertThat(ROOT_NODES).isEqualTo(rootNodes);
    }

    @Test
    public void updateSuccess()
    {
        dao.update(ALL_CLASSIFICATORS_MODELS);

        ALL_CLASSIFICATORS_MODELS.forEach((id,classififcator)->
        {
            Classificator classificatorFromDao = dao.get(id,classififcator.getNum());
            assertThat(classififcator).isEqualToComparingFieldByField(classificatorFromDao);
        });
    }


    @Test(expected = EmptyClassificatorException.class)
    public void updateFailEmpty()
    {
        dao.update(new HashMap<>());
    }


    @After
    public void dropDB()
    {
        try(Connection connection = ds.getConnection())
        {

            InputStream insertMocksStream  = new FileInputStream("src/test/java/mocks/drop_db.sql");
            DBHelper.importSQL(connection,insertMocksStream);

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}