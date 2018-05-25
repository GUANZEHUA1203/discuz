import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bean.Aticle;
import com.google.common.collect.Lists;
import com.schedule.Pashujuxiaohua;
import com.schedule.dataSourceAllAticle;
import com.services.AticleServices;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试  
@ContextConfiguration(locations={"classpath:Spring/applicationContext.xml"}) //加载配置文件
public class TestScheduler  {
	
	@Autowired
	AticleServices aticleServices;
	
	@Autowired
	Pashujuxiaohua xiaohuaData;
	
	@Autowired
	dataSourceAllAticle allAticle;
	@Test
	public void test2() {
		Date date=new Date();
//		AticleServicesImpl.autoAddMovieData();
		for (int n = 0; n < 10;n++) {
			ArrayList<Aticle> newArrayList = Lists.newArrayList();
			for (int i = 0; i <30; i++) {
				Aticle aticle = new Aticle();
	//			atman,attitle,atcontext,atlabel
				aticle.setMan("asdf");
				aticle.setTitle("bbb");
				aticle.setContext("ccc");
				aticle.setLabel("1");
				newArrayList.add(aticle);
			}
			aticleServices.insertBatch(newArrayList);//17455   25416  15701
		}
		System.out.println(new Date().getTime()-date.getTime());//79504  38345
	}
	
	
	@Test
	public void test03(){
//		xiaohuaData.getData();
		allAticle.getData();
	}
	



}
