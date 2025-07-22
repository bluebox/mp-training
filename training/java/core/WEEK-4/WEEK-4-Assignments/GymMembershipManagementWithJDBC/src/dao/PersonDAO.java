package dao;

public interface PersonDAO {
	void addPerson(String phone,String name, int age);
	
	void updateName(String phone,String name);
	
	void updateAge(String phone, int age);
	
	void deleteUser(String phone);
}
