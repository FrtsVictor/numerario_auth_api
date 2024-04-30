package numerario.auth.api.auth;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

//@SpringBootTest
class AuthApplicationTests {

	@Test
	void contextLoads() {
		var date = LocalDateTime.now().toLocalTime();

		RandomStringUtils.random(5, true, false);
		System.out.printf("date");
	}

}
