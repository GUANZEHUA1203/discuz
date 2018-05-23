package com.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Aticle;
import com.dao.AticleDao;
import com.schedule.dataSourceAticleWeek;
import com.schedule.dataSourceMovie;
import com.services.AticleServices;
import com.util.SqlMapper;

import net.coderbee.mybatis.batch.BatchParameter;

@Service("aticleServices")
public class AticleServicesImpl implements AticleServices {
	@Autowired
	private AticleDao aticledao;
	@Autowired
	private SqlSessionFactory sqlsessionfactory;

	public void fabiao(Aticle aticle) {
		this.aticledao.addAticle(aticle);
	}

	public List<Aticle> showaticle(int pn, int px) {
		List<Aticle> aticles = this.aticledao.paginationAticle(pn, px);
		return aticles;
	}

	public List<Aticle> findAll() {
		List<Aticle> count = this.aticledao.findAticles();
		return count;
	}

	public List<Aticle> selectAticle(Map<String, Object> map) {
		List<Aticle> aticles = this.aticledao.findAticle(map);
		return aticles;
	}

	public void deleteAticle(Integer id) {
		this.aticledao.deleteAticle(id.intValue());
	}

	public void updateAticle(Map<String, Object> map) {
		this.aticledao.updateAticle(map);
	}

	public void autoAddMovieData() {
		List<String> resultSql = new ArrayList();
		try {
			System.out.println("******************************start scheduled");
			List<String> list = dataSourceMovie.addDateMain();
			// List<String> data = dataSourceAticleWeek.getData();
			resultSql.addAll(list);
			// resultSql.addAll(data);
			SqlSession openSession = this.sqlsessionfactory.openSession();
			for (String string : resultSql) {
				System.err.println(string);
				new SqlMapper(openSession).insert(string);
			}
			openSession.close();
			System.out.println("end scheduled");
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public int findCountAticle(Map<String, Object> map) {
		int findCountAticle = this.aticledao.findCountAticle(map);
		return findCountAticle;
	}

	public void addDatabySql(List<String> list) {
		SqlSession openSession = this.sqlsessionfactory.openSession();
		for (String string : list) {
			System.err.println(string);
			new SqlMapper(openSession).insert(string);
		}
		openSession.close();
	}

	public void autoAddData() {

	}

	public void insertBatch(List<Aticle> aticles) {
//		this.aticledao.batchInsert(BatchParameter.wrap(aticles));
		this.aticledao.batchInsertByUnion(aticles);
	}
}
