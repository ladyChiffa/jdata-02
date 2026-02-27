package com.example.jdata_02.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    private String queryString;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProductName(String name) {
        queryString = this.read("getproduct.sql");

        String result = "not found";

        SqlRowSet sqlRowSet = namedParameterJdbcTemplate.queryForRowSet(queryString, Map.of("name", name));
        while(sqlRowSet.next()) {
            result = sqlRowSet.getString("product_name");
        }

        return name + " ===> " + result;
    }
}
