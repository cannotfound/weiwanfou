package spittr.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import spittr.pojo.Spitter;

@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

	private JdbcOperations jdbcOperations;
	
	@Autowired
	public SpitterRepositoryImpl(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}
	
	@Override
	public Spitter save(Spitter spitter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spitter findByUsername(String username) {
		
		
		
		String sql = "select title from hao_user where id=?";
		
		String title = jdbcOperations.queryForObject(sql, String.class, 1);
		
		Spitter spitter = new Spitter(title, "1234", "JB", "Jack", "119@qq.com");
		
		return spitter;
	}

}
