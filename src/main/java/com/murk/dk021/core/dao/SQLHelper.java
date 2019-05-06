package com.murk.dk021.core.dao;

public class SQLHelper {
    public static final String SELECT_CLASSIFICATOR =       "SELECT * FROM dk_021 WHERE id=? AND num=?";

    public static final String SELECT_ROOT_NODES =          "SELECT * FROM dk_021 WHERE parentid IS NULL";

    public static final String SELECT_CLASSIFICATOR_NODES = "SELECT nodesDK.id, nodesDK.num, nodesDK.parentid, nodesDK.name " +
                                                            "FROM dk_021 as parentDK " +
                                                            "INNER JOIN dk_021 as nodesDK ON parentDK.id = nodesDK.parentId " +
                                                            "WHERE parentDK.id = ? AND parentDK.num =? ";

    public static final String CREATE_TEMP_TABLE =          "CREATE TEMP TABLE IF NOT EXISTS temp_DK_021 " +
                                                            "(" +
                                                            "id INTEGER NOT NULL, " +
                                                            "num SMALLINT NOT NULL, " +
                                                            "parentId INTEGER , " +
                                                            "name VARCHAR(300) NOT NULL," +
                                                            "constraint code_temp PRIMARY KEY(id,num), " +
                                                            "constraint uniqueId_temp UNIQUE(id) " +
                                                            ")";

    public static final String INSERT_CLASSIFICATOR =       "INSERT INTO temp_DK_021 VALUES (?,?,?,?);";

    public static final String TRUNCATE_MAIN_TABLE =        "TRUNCATE TABLE DK_021;";

    public static final String TRANSFER_CLASSIFICATORS =    "INSERT INTO dk_021 SELECT * FROM temp_DK_021;";

    public static final String DROP_TEMP_TABLE =            "DROP TABLE IF EXISTS  temp_DK_021; ";


}
