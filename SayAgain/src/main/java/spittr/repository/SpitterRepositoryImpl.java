package spittr.repository;

import org.springframework.stereotype.Repository;

import spittr.pojo.Spitter;

@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

	@Override
	public Spitter save(Spitter spitter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spitter findByUsername(String username) {
		
		
		Spitter spitter = new Spitter("root", "1234", "JB", "Jack", "119@qq.com");
		
		return spitter;
	}

}
