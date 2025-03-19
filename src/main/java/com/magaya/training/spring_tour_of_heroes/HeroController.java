package com.magaya.training.spring_tour_of_heroes;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeroController {
	
	private final JdbcTemplate jdbcTemplate;
	private final HeroService heroService;
	
	@Autowired
	public HeroController (JdbcTemplate jdbcTemplate, HeroService heroService) {
		this.jdbcTemplate = jdbcTemplate;
		this.heroService = heroService;
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
	
	
	@GetMapping("/hero")
	@ResponseBody
	public List<Hero> getHeroes(){
		return heroService.getHeroes();
	}
	
	@GetMapping("hero/{id}")
	@ResponseBody
	public Hero getHeroById(@PathVariable("id") int id) {
		return heroService.getHero(id);
	}
}
