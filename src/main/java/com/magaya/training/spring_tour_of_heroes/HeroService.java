package com.magaya.training.spring_tour_of_heroes;

import java.util.List;

public interface HeroService {
	List<Hero> getHeroes();
	Hero getHero(int id); 
	Hero insertHero(Hero hero); 
	Hero updateHero(Hero hero); 
	void deleteHero(int id);
}
