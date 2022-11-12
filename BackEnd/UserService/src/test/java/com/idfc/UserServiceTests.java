package com.idfc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import com.idfc.dao.UserRepository;
import com.idfc.model.User;
import com.idfc.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserServiceTests {
	
	@MockBean
	private UserRepository repo;
	
	@Autowired
	private UserService underTest;
	
	User user;
	User admin;

	@BeforeEach
	void setUp() throws Exception {
//		underTest = new UserServiceImpl(repo);
		user = new User(
				"rahul21", 
				"rahul21@gmail.com", 
				"1111", 
				"patient"
		);
		user.setId(1l);
	}

	@Test
	void testCanAddUser() {
//		When
		underTest.addUser(user);
		
//		Then
		ArgumentCaptor<User> argCapture = ArgumentCaptor.forClass(User.class);
//		capturing the values from the save method to test
		verify(repo).save(argCapture.capture());
		User capturedUser = argCapture.getValue();
		assertThat(capturedUser).isEqualTo(user);
		
	}

	@Test
	void testGetUsers() {
//		When
		underTest.getUsers();
//		Then
		verify(repo).findAll();
	}

	@Test
	void testGetUserById() {
//		Given
		given(repo.findById(1l)).willReturn(Optional.of(user));
//		When
		User res = underTest.getUserById(user.getId()).get();
//		System.out.println(res);
//		Then
		assertThat(res).isNotNull();
	}

	@Test
	void testGetUserByEmail() {
//		Given
		given(repo.findByEmail("rahul21@gmail.com")).willReturn(user);
//		When
		User resUser = underTest.getUserByEmail(user.getEmail());
//		Then
		assertThat(resUser).isNotNull();
	}

	@Test
	void testUpdatePasswordWithMathchingOldPassword() {
//		Given
		given(repo.findByEmail("rahul21@gmail.com")).willReturn(user);
//		When
		underTest.updatePassword("rahul21@gmail.com", "1111", "4444");
//		Then
		ArgumentCaptor<User> argCapture = ArgumentCaptor.forClass(User.class);
		verify(repo).save(argCapture.capture());
		User capturedUser = argCapture.getValue();
		assertThat(capturedUser.getPassword()).isEqualTo("4444");
	}
	
	@Test
	void testUpdatePasswordWithNotMathchingOldPassword() {
//		Given
		given(repo.findByEmail("rahul21@gmail.com")).willReturn(user);
//		When
		User res = underTest.updatePassword("rahul21@gmail.com", "2222", "4444");
//		Then
		assertThat(res).isNull();
	}

	@Test
	void testUpdateUserTypeIfItIsAdmin() {
//		Given
		admin = new User(
				"admin21", 
				"admin21@gmail.com", 
				"2222", 
				"admin"
		);
		admin.setId(2l);
		given(repo.findById(2l)).willReturn(Optional.of(admin));
		given(repo.findById(1l)).willReturn(Optional.of(user));
//		When
		underTest.updateUserType(2l, 1l);
//		Then
		ArgumentCaptor<User> argCapture = ArgumentCaptor.forClass(User.class);
		verify(repo).save(argCapture.capture());
		User capturedUser = argCapture.getValue();
		assertThat(capturedUser.getRole()).isEqualTo("doctor");
	}

	@Test
	void testUpdateUserTypeIfItIsNotAdmin() {
//		Given
		admin = new User(
				"admin21", 
				"admin21@gmail.com", 
				"2222", 
				"doctor"
		);
		admin.setId(2l);
		given(repo.findById(2l)).willReturn(Optional.of(admin));
//		When
		User res = underTest.updateUserType(2l, 1l);
//		Then
		assertThat(res).isNull();
	}


	@Test
	void testDeleteUser() {
//		Given
		willDoNothing().given(repo).deleteById(1l);
		given(repo.findById(1l)).willReturn(Optional.of(user));
//		When
		underTest.deleteUser(1l);
//		Then
		verify(repo, times(1)).deleteById(1l);
	}

}
