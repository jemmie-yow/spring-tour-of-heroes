package com.magaya.training.spring_tour_of_heroes;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HeroDAOImpl implements HeroDAO{

	JdbcTemplate jdbc;
	
	public HeroDAOImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public List<Hero> getHeroes() {
		String sql = "SELECT * FROM heroes";
		List<Hero> heroes = jdbc.query(sql, new BeanPropertyRowMapper<Hero>(Hero.class));
		return heroes;
	}

	@Override
	public Hero getHero(int id) {
		String sql = "SELECT * FROM heroes WHERE id = ?";
		Hero hero = jdbc.queryForObject(sql, new BeanPropertyRowMapper<Hero>(Hero.class), new Object[]{id});
		return hero;
	}

	@Override
	public Hero insertHero(Hero hero) {
		return null;
	}

	@Override
	public Hero updateHero(Hero hero) {
		return null;
	}

	@Override
	public void deleteHero(int id) {

	}

}
