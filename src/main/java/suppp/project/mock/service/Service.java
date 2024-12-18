package suppp.project.mock.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.helpers.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import suppp.project.mock.dto.Movies;
import suppp.project.mock.repo.Repository;


@org.springframework.stereotype.Service
public class Service {
	
	@Autowired
	Repository repository;

	public ResponseEntity<Object> saveMovies(Movies movies) {
		Movies list=repository.save(movies);
		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Successsfully saved");
		map.put("Movies", list);
		return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<Object> fetchAll() {
	   List<Movies> movies = repository.findAll();
	   Map<String, Object> map = new HashMap<>();
	   map.put("Message", "All movies in the Database");
	   map.put("Movies", movies);
	   return new ResponseEntity<Object>(map, HttpStatus.FOUND);
	}

	public ResponseEntity<Object> saveAllMovies(List<Movies> movies) {
	    List<Movies> moviess = repository.saveAll(movies);
	    Map<String, Object> map = new HashMap<>();
	    map.put("Message", "Successsfully saved");
	    map.put("Movies", moviess);
	    return new ResponseEntity<Object>(map, HttpStatus.OK);
		
	}

	public ResponseEntity<Object> findingByID(int id) {
		 if(!repository.existsById(id)) {
			 Map<String, Object> map = new HashMap<>();
			 map.put("Message", "No movie found!");
			 return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		 }else {
			Optional<Movies> movie = repository.findById(id);
			Map<String, Object> map = new HashMap<>();
			map.put("Message", "Movie found!");
			map.put("Movie", movie);
			return new ResponseEntity<Object>(map, HttpStatus.FOUND);
		 }
	}

	public ResponseEntity<Object> deleteMovie(int id) {
		if(!repository.existsById(id)) {
			Map<String, Object> map = new HashMap<>();
			map.put("Message", "No such movie!");
			return new ResponseEntity<Object>(map, HttpStatus.FOUND);
		}else {
			repository.deleteById(id);
			Map<String, Object> map = new HashMap<>();
			map.put("Message", "Movie deleted successfully!");
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> updateMovie(Movies movies) {
		Movies movie = repository.save(movies);
		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Successsfully saved");
		map.put("Movie", movie);
		return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<Object> updateMoviesPartially(int id, Movies movies) {
		Optional<Movies> optional = repository.findById(id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<>();
			map.put("Message", "No such movie to update!");
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			Movies ExistingMovie = optional.get();
			if(movies.getName() != null) {
				ExistingMovie.setName(movies.getName());
			}else if(movies.getGenre()!=null) {
				ExistingMovie.setGenre(movies.getGenre());
			}else if(movies.getRating()!=0) {
				ExistingMovie.setRating(movies.getRating());
			}else if(movies.getYear()!=0) {
				ExistingMovie.setYear(movies.getYear());
			}
			repository.save(ExistingMovie);
			map.put("Message", "Updated successfully!");
			map.put("Movies", ExistingMovie);
			return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
		}
	}

	public ResponseEntity<Object> countMovies() {
		long count = repository.count();
		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Total Movies");
		map.put("Count", count);
		return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
	}

	
	public ResponseEntity<Object> findingBW(int min, int max) {
	    List<Movies> movies = repository.findByRatingBetween(min, max);
	    if(movies.isEmpty()) {
	    	Map<String, Object> map = new HashMap<>();
	    	map.put("Message", "No such movies that lie between the range");
	    	return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
	    }else {
	    	Map<String, Object> map = new HashMap<>();
	    	map.put("Message", "Enjoy");
	    	map.put("Movies", movies);
	    	return new ResponseEntity<Object>(map, HttpStatus.FOUND);
	    }
	}

	public ResponseEntity<Object> findingByName(String name) {
		Optional<Movies> optional = Optional.ofNullable(repository.findByName(name));
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<>();
			map.put("Messgae", "No movie found with such name!");
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
	    	Map<String, Object> map = new HashMap<>();
	    	map.put("Message", "Enjoy");
	    	map.put("Movies", optional.get());
	    	return new ResponseEntity<Object>(map, HttpStatus.FOUND);
	    }
		
	}

	public ResponseEntity<Object> findingByYear(int year) {
		List<Movies> movies = repository.findByYear(year);
	    if(movies.isEmpty()) {
	    	Map<String, Object> map = new HashMap<>();
	    	map.put("Message", "No such movies that lie between the range");
	    	return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
	    }else {
	    	Map<String, Object> map = new HashMap<>();
	    	map.put("Message", "Enjoy");
	    	map.put("Movies", movies);
	    	return new ResponseEntity<Object>(map, HttpStatus.FOUND);
	    }
	}
	
}
