package com.soft.rabbitmq;

import com.soft.rabbitmq.component.Sender;
import org.junit.Test;
import org.junit.internal.Classes;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

	@Autowired
	private Sender sender;

	@Test
	public void hello() {
		sender.send();
	}

}
