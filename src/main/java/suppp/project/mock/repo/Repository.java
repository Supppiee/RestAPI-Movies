package suppp.project.mock.repo;

import suppp.project.mock.dto.Movies;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Movies, Integer>{

	List<Movies> findByRatingBetween(int min, int max);

	Movies findByName(String name);

	List<Movies> findByYear(int year);
}
