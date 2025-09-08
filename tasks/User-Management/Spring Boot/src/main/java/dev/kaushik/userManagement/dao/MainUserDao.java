package dev.kaushik.userManagement.dao;

import java.util.List;

import dev.kaushik.userManagement.model.MainUser;

public interface MainUserDao {
	boolean addMainuser(MainUser mainUser);

	boolean updateMainUser(MainUser mainUser);

	boolean updateMainUserWithPassword(MainUser mainUser);
	
	List<MainUser> getAllMainUsers();
}
