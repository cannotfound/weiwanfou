package spittr.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import spittr.pojo.Spittle;

@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		
		
		//----------临时直接构造返回的
	    List<Spittle> spittles = new ArrayList<Spittle>();
	    for (int i=0; i < count; i++) {
	      spittles.add(new Spittle((long) i, "Spittle " + i, new Date()));
	    }
	    return spittles;
		
	}

	@Override
	public Spittle findOne(long id) {
		
		
		return new Spittle(id, "i am " + id + " spittle.", new Date());
	}

}
