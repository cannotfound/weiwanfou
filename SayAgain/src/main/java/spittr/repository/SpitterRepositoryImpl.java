package spittr.repository;

import java.util.Random;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import spittr.pojo.Spitter;

@Repository
@Transactional
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
	
	/**
	 * Spitter 属性acc小于0的，不会被缓存
	 * id>=5才会被缓存，
	 * unless属性只有在缓存方法有返回值时才开始发挥作用。而condition
	 * 肩负着在方法上禁用缓存的任务，因此它不能等到方法返回时再确定是否该关闭缓存。这意
	 * 味着它的表达式必须要在进入方法时进行计算，所以我们不能通过#result引用返回值
	 */
	@Override
	@Cacheable(value="haolihai", key="'spitter_'+#id", unless="#result.acc<0", condition="#id>=5")
	public Spitter findById(Long id) {
		
		
		System.out.println("--------   来数据库查了-----  id=" + id);
		
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
	@Cacheable(value="haolihai", key="'cache_'+#username")
	public Spitter findByUsername(String username) {
		
		System.out.println("--------   来数据库查了---  ");
		
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
