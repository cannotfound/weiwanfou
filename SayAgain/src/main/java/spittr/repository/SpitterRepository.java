package spittr.repository;

import spittr.pojo.Spitter;

public interface SpitterRepository {

	  Spitter save(Spitter spitter);
	  
	  Spitter findByUsername(String username);
}
