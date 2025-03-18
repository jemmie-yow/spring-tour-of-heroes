package com.magaya.training.spring_tour_of_heroes;

import java.nio.file.Watchable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public HelloWorld (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@ResponseBody
	@GetMapping("/hello")
	public List<String> helloWorld() {
		List<String> tablesResult =  jdbcTemplate.execute(
				(Connection conn) -> {
					List<String> tables = new ArrayList<>();
					DatabaseMetaData metaData = conn.getMetaData();
					ResultSet rs = metaData.getTables(null, null, null, new String[] {"TABLE"});
					while (rs.next()) {
						tables.add(rs.getString("TABLE_NAME"));
					}
					return tables;
				}
			);
		return tablesResult;
//		return tablesResult.toString();
//		return "Hello World " +jdbcTemplate.getDataSource();
	}
}
