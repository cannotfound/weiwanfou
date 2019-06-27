package spittr.repository;

import spittr.pojo.Spitter;

public interface SpitterRepository {

	  void save(Spitter spitter);
	  
	  Spitter findByUsername(String username);
	  
	  Spitter getById(Long id);
	  
	  Spitter findById(Long id);
	  
	  void updateById(Long id);
	  
	  void addAcc(Long id, int num);
	  
	  void subAcc(Long id, int num);
}
