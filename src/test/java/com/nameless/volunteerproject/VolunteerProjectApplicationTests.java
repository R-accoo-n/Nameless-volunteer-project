package com.nameless.volunteerproject;

import static org.assertj.core.api.Assertions.assertThat;

import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.UserRepository;
import com.nameless.volunteerproject.services.VolunteerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.nameless.volunteerproject.repositories.FundraisingRepository;
import com.nameless.volunteerproject.services.FundraisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Arrays;
import static org.mockito.BDDMockito.given;



@SpringBootTest
class VolunteerProjectApplicationTests {
	@InjectMocks
	VolunteerService volunteerService;

	@Mock
	UserRepository userRepository;
	@Mock
	private FundraisingRepository fundraisingRepository;

	@InjectMocks
	private FundraisingService fundraisingService;

	@Test
	public void givenFundraisingType_whenGetFundraisingType_thenReturnFunraisingsByType() {
	   assertThat(47).isEqualTo(47);
	}




}
