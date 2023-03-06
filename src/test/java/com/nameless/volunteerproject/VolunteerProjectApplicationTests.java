package com.nameless.volunteerproject;

import static org.assertj.core.api.Assertions.assertThat;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.UserRepository;
import com.nameless.volunteerproject.services.VolunteerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VolunteerProjectApplicationTests {
	@InjectMocks
	VolunteerService volunteerService;

	@Mock
	UserRepository userRepository;

	@Test
	void testScenario(){
		User u = new User();
		assertThat(u != null);
	}

}
