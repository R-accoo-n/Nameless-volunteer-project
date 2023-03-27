package com.nameless.volunteerproject;

import static org.assertj.core.api.Assertions.assertThat;

import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.models.SupportTicket;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.SupportTicketRepository;
import com.nameless.volunteerproject.repositories.UserRepository;
import com.nameless.volunteerproject.services.SupportTicketService;
import com.nameless.volunteerproject.services.UserService;
import com.nameless.volunteerproject.services.VolunteerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.nameless.volunteerproject.repositories.FundraisingRepository;
import com.nameless.volunteerproject.services.FundraisingService;
import java.util.List;
import java.util.Arrays;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.when;
import com.nameless.volunteerproject.enums.UserRole;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



@SpringBootTest
class VolunteerProjectApplicationTests {

	@InjectMocks
	VolunteerService volunteerService;
	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;
	@Mock
	private FundraisingRepository fundraisingRepository;

	@InjectMocks
	private FundraisingService fundraisingService;
	@Mock
	private SupportTicketRepository supportTicketRepository;

	@InjectMocks
	private SupportTicketService supportTicketService;



//	@Test
//	public void givenFundraisingType_whenGetFundraisingType_thenReturnFunraisingsByType() {
//		Fundraising fundraising1 = new Fundraising();
//		fundraising1.setFundraisingName("Fundraising 1");
//		fundraising1.setSocialType(FundraisingType.АВТО);
//		fundraising1.setActive(true);
//
//		Fundraising fundraising2 = new Fundraising();
//		fundraising2.setFundraisingName("Fundraising 2");
//		fundraising2.setSocialType(FundraisingType.АВТО);
//		fundraising2.setActive(true);
//
//		Fundraising fundraising3 = new Fundraising();
//		fundraising3.setFundraisingName("Fundraising 3");
//		fundraising3.setSocialType(FundraisingType.АВТО);
//		fundraising3.setActive(true);
//
//		Fundraising fundraising4 = new Fundraising();
//		fundraising4.setFundraisingName("Fundraising 4");
//		fundraising4.setSocialType(FundraisingType.АВТО);
//		fundraising4.setActive(false);
//
//		Fundraising fundraising5 = new Fundraising();
//		fundraising5.setFundraisingName("Fundraising 5");
//		fundraising5.setSocialType(FundraisingType.АВТО);
//		fundraising5.setActive(true);
//
//		Fundraising fundraising6 = new Fundraising();
//		fundraising6.setFundraisingName("Fundraising 6");
//		fundraising6.setSocialType(FundraisingType.АВТО);
//		fundraising6.setActive(true);
//
//		Fundraising fundraising7 = new Fundraising();
//		fundraising7.setFundraisingName("Fundraising 7");
//		fundraising7.setSocialType(FundraisingType.АВТО);
//		fundraising7.setActive(false);
//
//		Fundraising fundraising8 = new Fundraising();
//		fundraising8.setFundraisingName("Fundraising 8");
//		fundraising8.setSocialType(FundraisingType.АВТО);
//		fundraising8.setActive(false);
//
//		given(fundraisingService.getActiveFundraisingsByType(FundraisingType.АВТО))
//				.willReturn(Arrays.asList(fundraising1, fundraising2, fundraising3));
//		given(fundraisingService.getActiveFundraisingsByType(FundraisingType.АВТО))
//				.willReturn(Arrays.asList(fundraising5, fundraising6));
//
//		List<Fundraising> activeFundraisings = fundraisingService.getActiveFundraisingsByType(FundraisingType.АВТО);
//		List<Fundraising> activeFundraisings2 = fundraisingService.getActiveFundraisingsByType(FundraisingType.АВТО);
//
//		assertThat(activeFundraisings.size()).isEqualTo(3);
//		assertThat(activeFundraisings).isEqualTo(Arrays.asList(fundraising1, fundraising2, fundraising3));
//		assertThat(activeFundraisings2.size()).isEqualTo(2);
//		assertThat(activeFundraisings2).isEqualTo(Arrays.asList(fundraising5, fundraising6));
//	}

//	@Test
//	public void givenFundraisingType_whengetCompletedFundraisings_thenReturnCompletedFundraisings() {
//		UUID userId = UUID.randomUUID();
//		List<Fundraising> fundraisings = Arrays.asList(
//				Fundraising.builder().id(UUID.randomUUID()).userId(userId).isActive(false).socialType(FundraisingType.АВТО).build(),
//				Fundraising.builder().id(UUID.randomUUID()).userId(userId).isActive(true).socialType(FundraisingType.АВТО).build(),
//				Fundraising.builder().id(UUID.randomUUID()).userId(UUID.randomUUID()).isActive(false).socialType(FundraisingType.АВТО).build()
//		);
//
//		when(fundraisingRepository.findByUserIdAndIsActiveFalse(userId)).thenReturn(fundraisings.subList(0, 1));
//
//		List<Fundraising> completedFundraisings = fundraisingService.getCompletedFundraisings(userId);
//
//		assertThat(completedFundraisings).hasSize(1);
//		assertThat(completedFundraisings.get(0).getId()).isEqualTo(fundraisings.get(0).getId());
//	}


//		@Test
//		public void givenExistingUser_whenVerifyUser_thenMakeUserVerified() {
//			UUID userId = UUID.randomUUID();
//			UserRole role = UserRole.MILITARY;
//			User user = User.builder()
//					.id(userId)
//					.name("John")
//					.surname("Doe")
//					.role(UserRole.VOLUNTEER)
//					.build();
//			when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//			when(userRepository.save(any(User.class))).thenReturn(user);
//			boolean result = userService.verifyUser(userId, role);
//			assertTrue(result);
//			assertEquals(role, user.getRole());
//			verify(userRepository).findById(userId);
//			verify(userRepository).save(user);
//		}

//		@Test
//		public void givenNotExisistingUser_whenVerifyUser_thenReturnFalse() {
//			UUID userId = UUID.randomUUID();
//			UserRole role = UserRole.MILITARY;
//			when(userRepository.findById(userId)).thenReturn(Optional.empty());
//			boolean result = userService.verifyUser(userId, role);
//			assertFalse(result);
//			verify(userRepository).findById(userId);
//			verifyNoMoreInteractions(userRepository);
//		}
//	@Test
//	public void givenSupportTicket_whenCreateSupportTicket_thenSaveTicket() {
//		SupportTicket ticket = SupportTicket.builder()
//				.id(UUID.randomUUID())
//				.description("Test ticket")
//				.problemType("Test type")
//				.problemSubtype("Test subtype")
//				.build();
//
//		when(supportTicketRepository.save(ticket)).thenReturn(ticket);
//
//		SupportTicket savedTicket = supportTicketService.createSupportTicket(ticket);
//
//		verify(supportTicketRepository, times(1)).save(ticket);
//		assertEquals(ticket, savedTicket);
//	}
//	@Test
//	public void givenSupportTicket_whenFindById_thenReturnTicket() {
//		UUID id = UUID.randomUUID();
//		SupportTicket ticket = SupportTicket.builder()
//				.id(id)
//				.description("Test ticket")
//				.problemType("Test type")
//				.problemSubtype("Test subtype")
//				.build();
//
//		when(supportTicketRepository.findById(id)).thenReturn(Optional.of(ticket));
//
//		Optional<SupportTicket> foundTicket = supportTicketService.getSupportTicketById(id);
//
//		verify(supportTicketRepository, times(1)).findById(id);
//		assertTrue(foundTicket.isPresent());
//		assertEquals(ticket, foundTicket.get());
//	}
//	@Test
//	public void givenSupportTicket_whenfindAll_thenReturnListOfSupportTickets() {
//		List<SupportTicket> tickets = Arrays.asList(
//				SupportTicket.builder()
//						.id(UUID.randomUUID())
//						.description("Ticket 1")
//						.problemType("Type 1")
//						.problemSubtype("Subtype 1")
//						.build(),
//				SupportTicket.builder()
//						.id(UUID.randomUUID())
//						.description("Ticket 2")
//						.problemType("Type 2")
//						.problemSubtype("Subtype 2")
//						.build(),
//				SupportTicket.builder()
//						.id(UUID.randomUUID())
//						.description("Ticket 3")
//						.problemType("Type 3")
//						.problemSubtype("Subtype 3")
//						.build()
//		);
//
//		when(supportTicketRepository.findAll()).thenReturn(tickets);
//
//		List<SupportTicket> allTickets = supportTicketService.getAllSupportTickets();
//
//		verify(supportTicketRepository, times(1)).findAll();
//		assertEquals(tickets, allTickets);
//	}



	}

