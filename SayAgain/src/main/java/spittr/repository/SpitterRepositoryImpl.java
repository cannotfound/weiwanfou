package spittr.repository;

import java.util.Random;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import spittr.pojo.Spitter;

@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

	//private JdbcOperations jdbcOperations;
	/*
	@Autowired
	public SpitterRepositoryImpl(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}*/
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(Spitter spitter) {
		
		getSession().save(spitter);
		
	}
	
	@Override
	public Spitter getById(Long id) {
		
		Spitter cell = (Spitter) this.getSession().get(Spitter.class, id);
		System.out.printf("id=%s, username=%s", id, cell.getUsername());
		
		return cell;
	}
	
	@Override
	public Spitter findById(Long id) {
		
		Spitter cell = (Spitter) this.getSession().get(Spitter.class, id);
		return cell;
	}
	
	@Override
	public void updateById(Long id) {
		
		int temp = (new Random()).nextInt(20000);
		
		Spitter cell = this.findById(id);
		
		cell.setUsername(cell.getUsername() + "_" + temp);
		
		this.getSession().update(cell);
		
	}
	

	@Override
	public Spitter findByUsername(String username) {
		
		
		
		String sql = "select title from hao_user where id=?";
		
		//String title = jdbcOperations.queryForObject(sql, String.class, 1);
		
		Spitter spitter = new Spitter("yty8", "1234", "JB", "Jack", "119@qq.com");
		
		
		//jdbcOperations.execute("insert into hao_user values('5687', 'nm', 'jc', '110@qq.com')");
		
		
		
		return spitter;
	}

	@Override
	public void addAcc(Long id, int num) {
		
		Spitter spitter = this.findById(id);
		spitter.setAcc(spitter.getAcc() + num);
		
		this.save(spitter);
		
	}

	@Override
	public void subAcc(Long id, int num) {
		
		
		Spitter spitter = this.findById(id);
		spitter.setAcc(spitter.getAcc() - num);
		
		this.save(spitter);
		
	}

}
