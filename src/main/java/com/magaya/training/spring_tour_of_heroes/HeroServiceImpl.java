package com.magaya.training.spring_tour_of_heroes;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class HeroServiceImpl implements HeroService {
	
	private final HeroDAO heroDAO;
	
	public HeroServiceImpl(HeroDAO heroDAO) {
		this.heroDAO = heroDAO;
	}
	
	@Override
	public List<Hero> getHeroes() {
		return heroDAO.getHeroes();
	}
	
	@Override
	public Hero getHero(int id) {
		return heroDAO.getHero(id);
	}
	
	@Override
	public Hero insertHero(Hero hero) {
		return heroDAO.insertHero(hero);
	}
	
	@Override
	public Hero updateHero(Hero hero) {
		return heroDAO.updateHero(hero);
	}
	
	@Override
	public void deleteHero(int id) {
		heroDAO.deleteHero(id);
	}
	
}
