package com.chao.bysj;

import com.chao.bysj.po.User;
import com.chao.bysj.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderonlineApplicationTests {
	@Autowired
	UserRepository dao;
	@Test
	@Transactional
	public void contextLoads() {
		List<User> list = dao.findAll();
		User user = dao.getOne(3);
		System.out.println(list);
		System.out.println(user.getAddressId());
	}

}
