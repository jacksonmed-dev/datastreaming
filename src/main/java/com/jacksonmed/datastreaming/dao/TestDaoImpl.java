package com.jacksonmed.datastreaming.dao;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.AsyncResultSet;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.jacksonmed.datastreaming.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionStage;

@Component("TestDaoImpl")
public class TestDaoImpl implements TestDao{
    private static final String INSERT_TEST = "insert into test (name) values (?)";
    private static final String SELECT_TEST_BY_NAME = "select * from test where name = ?";
    @Autowired
    CqlSession cqlSession;

    @Override
    public void insertTestData(Test testData) {
        CompletionStage<CqlSession> sessionStage = CqlSession.builder().buildAsync();

        PreparedStatement ps = cqlSession.prepare(SELECT_TEST_BY_NAME);
        BoundStatement bs = ps.bind()
                .setString(0, testData.name)
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);

        // Chain one async operation after another:
        CompletionStage<AsyncResultSet> responseStage =
                sessionStage.thenCompose(
                        session -> session.executeAsync(bs));

        // Apply a synchronous computation:
        CompletionStage<String> resultStage =
                responseStage.thenApply(resultSet -> resultSet.one().getString("test"));


        // Perform an action once a stage is complete:
        resultStage.whenComplete(
                (version, error) -> {
                    if (error != null) {
                        System.out.printf("Failed to retrieve the version: %s%n", error.getMessage());
                    } else {
                        System.out.printf("Server version: %s%n", version);
                    }
                    sessionStage.thenAccept(CqlSession::closeAsync);
                });
    }

    @Override
    public Test retrieveTestData(String name) {
        PreparedStatement ps = cqlSession.prepare(SELECT_TEST_BY_NAME);
        BoundStatement bs = ps.bind()
                .setString(0, name)
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        cqlSession.execute(bs);
        return new Test();
    }
}
