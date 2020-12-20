package com.app.meli.appmeli;

import static org.assertj.core.api.Assertions.assertThat;

import com.app.meli.appmeli.controllers.CouponController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppmeliApplicationTests {

	@Autowired
	CouponController couponController;

	@Test
	void contextLoads() {
		assertThat(couponController).isNotNull();
	}

}
