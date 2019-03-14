package spittr.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import spittr.pojo.Spittle;

@Repository
public interface SpittleRepository {

	List<Spittle> findSpittles(long max, int count);
	
	Spittle findOne(long id);
}
