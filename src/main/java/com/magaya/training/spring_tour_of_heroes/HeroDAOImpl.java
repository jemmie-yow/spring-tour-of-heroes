package com.magaya.training.spring_tour_of_heroes;

import java.util.List;

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
		List<Hero> heroes = jdbc.query(sql, (rs, rowNum) -> {
			Hero hero = new Hero();
			hero.setId(rs.getInt("id"));
			hero.setName(rs.getString("name"));
			hero.setUser_visits(rs.getInt("user_visits"));
			return hero;
		});
		return heroes;
	}

	@Override
	public Hero getHero(int id) {
		return null;
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
