package suppp.project.mock.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import suppp.project.mock.dto.Movies;
import suppp.project.mock.service.Service;

@RestController
public class Controller {
	
	@Autowired
	Service serivce;
	
	//saving a movie
	@PostMapping("/movies")
	public ResponseEntity<Object> saveMovies(@RequestBody Movies movies){
		return serivce.saveMovies(movies);
	}

	//fetching multiple movies
	@GetMapping("/movies")
	public ResponseEntity<Object> fetchAll(){
		return serivce.fetchAll();
	}

	//saving multiple movies
	@PostMapping("/movies/many")
	public ResponseEntity<Object> saveMoviesMany(@RequestBody List<Movies> movies){
		return serivce.saveAllMovies(movies);
	}
	
	//finding a specific movie
	@GetMapping("/movies/{id}")
	public ResponseEntity<Object> findByID(@PathVariable int id){
		return serivce.findingByID(id);
	}
	
	//deleting a specific movie
	@DeleteMapping("/movies/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id){
		return serivce.deleteMovie(id);
	}
	
	//updating an obj completely
	@PutMapping("/movies")
	public ResponseEntity<Object> updateMovies(@RequestBody Movies movies){
		return serivce.updateMovie(movies);
	}
	
	//updating an obj partially
	@PatchMapping("/movies/{id}")
	public ResponseEntity<Object> updateMoviesPartially(@PathVariable int id,@RequestBody Movies movies){
		return serivce.updateMoviesPartially(id, movies);
	}
	
	//getting the number of movies in database
	@GetMapping("/movies/count")
	public ResponseEntity<Object> count(){
		return serivce.countMovies();
	}
	
	//finding a list of movies between a particular rating
	@GetMapping("/movies/{min}/{max}")
	public ResponseEntity<Object> findingBW(@PathVariable int min, @PathVariable int max){
		return serivce.findingBW(min, max);
	}
	
	//finding movies by name
	@GetMapping("/movies/name/{name}")
	public ResponseEntity<Object> findingByName(@PathVariable String name){
		return serivce.findingByName(name);
	}

	//finding movies by year
	@GetMapping("/movies/year/{year}")
	public ResponseEntity<Object> findingByYear(@PathVariable int year){
		return serivce.findingByYear(year);
	}
}
