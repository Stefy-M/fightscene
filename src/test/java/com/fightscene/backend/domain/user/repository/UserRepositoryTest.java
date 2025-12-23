package com.fightscene.backend.domain.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.fightscene.backend.domain.user.User;
import com.fightscene.backend.domain.user.UserRole;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testExistsByEmail () {
		String email = "mail@gmail.com";
		String passwordHash = "123435fghj12345";
		UUID USER_ID = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
		
		
		User user = new User(USER_ID,email,passwordHash,UserRole.USER,Instant.now(),Instant.now());
		
		user = userRepository.save(user);
		
		boolean exist = userRepository.existsByEmail(user.getEmail());
		
		assertThat(exist).isTrue();
	}
}
