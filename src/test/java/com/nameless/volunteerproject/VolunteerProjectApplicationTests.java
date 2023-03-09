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
import java.util.UUID;
import static org.mockito.Mockito.when;


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
		Fundraising fundraising1 = new Fundraising();
		fundraising1.setFundraisingName("Fundraising 1");
		fundraising1.setType(FundraisingType.TRANSPORT);
		fundraising1.setActive(true);

		Fundraising fundraising2 = new Fundraising();
		fundraising2.setFundraisingName("Fundraising 2");
		fundraising2.setType(FundraisingType.TRANSPORT);
		fundraising2.setActive(true);

		Fundraising fundraising3 = new Fundraising();
		fundraising3.setFundraisingName("Fundraising 3");
		fundraising3.setType(FundraisingType.TRANSPORT);
		fundraising3.setActive(true);

		Fundraising fundraising4 = new Fundraising();
		fundraising4.setFundraisingName("Fundraising 4");
		fundraising4.setType(FundraisingType.TRANSPORT);
		fundraising4.setActive(false);

		Fundraising fundraising5 = new Fundraising();
		fundraising5.setFundraisingName("Fundraising 5");
		fundraising5.setType(FundraisingType.TECHNIQUE);
		fundraising5.setActive(true);

		Fundraising fundraising6 = new Fundraising();
		fundraising6.setFundraisingName("Fundraising 6");
		fundraising6.setType(FundraisingType.TECHNIQUE);
		fundraising6.setActive(true);

		Fundraising fundraising7 = new Fundraising();
		fundraising7.setFundraisingName("Fundraising 7");
		fundraising7.setType(FundraisingType.TECHNIQUE);
		fundraising7.setActive(false);

		Fundraising fundraising8 = new Fundraising();
		fundraising8.setFundraisingName("Fundraising 8");
		fundraising8.setType(FundraisingType.TECHNIQUE);
		fundraising8.setActive(false);

		given(fundraisingService.getActiveFundraisingsByType(FundraisingType.TRANSPORT))
				.willReturn(Arrays.asList(fundraising1, fundraising2, fundraising3));
		given(fundraisingService.getActiveFundraisingsByType(FundraisingType.TECHNIQUE))
				.willReturn(Arrays.asList(fundraising5, fundraising6));

		List<Fundraising> activeFundraisings = fundraisingService.getActiveFundraisingsByType(FundraisingType.TRANSPORT);
		List<Fundraising> activeFundraisings2 = fundraisingService.getActiveFundraisingsByType(FundraisingType.TECHNIQUE);

		assertThat(activeFundraisings.size()).isEqualTo(3);
		assertThat(activeFundraisings).isEqualTo(Arrays.asList(fundraising1, fundraising2, fundraising3));
		assertThat(activeFundraisings2.size()).isEqualTo(2);
		assertThat(activeFundraisings2).isEqualTo(Arrays.asList(fundraising5, fundraising6));
	}
	@Test
	public void  givenFundraisingType_whengetCompletedFundraisings_thenReturnCompletedFundraisings() {
		UUID userId = UUID.randomUUID();
		List<Fundraising> fundraisings = Arrays.asList(
				Fundraising.builder().id(UUID.randomUUID()).userId(userId).isActive(false).type(FundraisingType.TRANSPORT).build(),
				Fundraising.builder().id(UUID.randomUUID()).userId(userId).isActive(true).type(FundraisingType.TECHNIQUE).build(),
				Fundraising.builder().id(UUID.randomUUID()).userId(UUID.randomUUID()).isActive(false).type(FundraisingType.DEMINING_EQUIPMENT).build()
		);

		when(fundraisingRepository.findByUserIdAndIsActiveFalse(userId)).thenReturn(fundraisings.subList(0, 1));

		List<Fundraising> completedFundraisings = fundraisingService.getCompletedFundraisings(userId);

		assertThat(completedFundraisings).hasSize(1);
		assertThat(completedFundraisings.get(0).getId()).isEqualTo(fundraisings.get(0).getId());
	}




}
