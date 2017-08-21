package pms_test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.service.UserService;
import cn.teacheredu.service.batch.ProjectSummariesBatch;
import cn.teacheredu.utils.Md5Encrypt;

public class UserServiceTest extends BaseSpringJunit {

	private Integer TESTID = 1;

	@Autowired
	UserService userService;

	@Before
	public void init() {

	}

	@Rollback(false)
	public void insertUserEntity() {
		UserEntity userEntity = new UserEntity();
		userEntity.setLoginName("admin");
		userEntity.setPassword(Md5Encrypt.md5("123123"));
		userEntity.setRealname("张老三");
		userEntity.setDmId(0);
		userEntity.setRoleId(0);
		userEntity.setEmail("pms@teacheredu.cn");
		userEntity.setMobile("18141906787");
		userEntity.setIdcard("130132198903036754");
		userEntity.setGenTime(new Date());
		userEntity.setSex("0");
		int TESTID = 0;
		try {
			TESTID = userService.insertUserEntity(userEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("成功插入一条用户，id号：" + TESTID);
	}

	public void testGetUserEntityById() throws Exception {
		UserEntity userEntity = userService.getUserEntityById(TESTID);
		System.out.println(userEntity.toString());
		Assert.assertNotNull(userEntity);
	}

	@Autowired
	private ProjectSummariesBatch projectSummariesBatch;
	@Test
	@Rollback(false)
	public void testSummaryBatch() {
		try {
			Date dNow = new Date();   //当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); //得到日历
			calendar.setTime(dNow);//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -365);  //设置为前一天
			dBefore = calendar.getTime();   //得到前一天的时间

			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
			String defaultStartDate = sdf.format(dBefore);   
			Date csDate = sdf.parse(defaultStartDate);
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse("2017-01-01");*/
			Date date = null;
			projectSummariesBatch.summariesBatch(csDate);
			System.out.println("哈哈");
		} catch (Exception e) {
			System.out.println("尴尬了");
		}
	}
}
