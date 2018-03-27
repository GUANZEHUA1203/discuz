import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.schedule.dataSourceAllAticle;
import com.services.impl.AticleServicesImpl;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试  
@ContextConfiguration(locations={"classpath:Spring/applicationContext.xml"}) //加载配置文件
public class TestScheduler  {
	@Autowired
	dataSourceAllAticle autoAddDataAllaticle;
	@Autowired
	AticleServicesImpl autoAddData;
	@Test
	public void test() {
		autoAddDataAllaticle.getData();
	}
	@Test
	public void test2() {
		AticleServicesImpl.autoAddMovieData();
	}

}
