package com.magaya.training.spring_tour_of_heroes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
		try {
			String sql = "INSERT INTO heroes (name) VALUES (?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbc.update((Connection con) -> {
						PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						ps.setString(1, hero.getName());
						return ps;
					}
					, keyHolder);
			Hero newHero = new Hero();
			newHero.setId((int) keyHolder.getKeys().get("id"));
			newHero.setName(keyHolder.getKeys().get("name").toString());
			newHero.setUserVisits((int) keyHolder.getKeys().get("user_visits"));
			return newHero;
		} catch (Exception e) {
			throw e;
		} 	
	}
	

	@Override
	public Hero updateHero(Hero hero) {
		String sql = "UPDATE heroes SET name = ?, user_visits = ? WHERE id = ?";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update((Connection con) -> {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, hero.getName());
			ps.setInt(2, hero.getUserVisits());
			ps.setInt(3, hero.getId());
			return ps;
		}, keyHolder);
		Hero updatedHero = new Hero();
		updatedHero.setId((int) keyHolder.getKeys().get("id"));
		updatedHero.setName(keyHolder.getKeys().get("name").toString());
		updatedHero.setUserVisits((int) keyHolder.getKeys().get("user_visits"));
		return updatedHero;
	}

	@Override
	public void deleteHero(int id) {

	}

}
