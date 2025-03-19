package com.magaya.training.spring_tour_of_heroes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/hero")
public class HeroController {
	
	private final JdbcTemplate jdbcTemplate;
	private final HeroService heroService;
	
	@Autowired
	public HeroController (JdbcTemplate jdbcTemplate, HeroService heroService) {
		this.jdbcTemplate = jdbcTemplate;
		this.heroService = heroService;
	}

	
	@GetMapping
	@ResponseBody
	public List<Hero> getHeroes(@RequestParam(
			name = "keyword",
			defaultValue = "",
			required = false) String keyword){
		return heroService.getHeroes(keyword);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Hero getHeroById(@PathVariable("id") int id) {
		return heroService.getHero(id);
	}
	
	@PostMapping
	@ResponseBody
	public Hero insertHero(@RequestBody Hero hero) {
		return heroService.insertHero(hero);
	}
	
	@PutMapping
	@ResponseBody
	public Hero updateHero(@RequestBody Hero hero) {
		return heroService.updateHero(hero);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public void deleteHero(@PathVariable("id") int id) {
		heroService.deleteHero(id);
	}
	
}
