/**
 * @autho zehua
 *  下午5:32:40
 */
package com.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import com.bean.Picture;
import com.services.PictureServices;

/**下午5:32:40
 * @author 2017*****下午5:32:40
 *
 */
@Service("pictureServicess")
public class PictureServicesImpl implements PictureServices {
	@Resource
	private SqlSessionFactory sqlSessionFactory;
	
	public List<Picture> getRandomPic() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Picture> selectOne = session.selectList("com.picture.dao.getRandom");
		session.close();
		return selectOne;
	}

}
