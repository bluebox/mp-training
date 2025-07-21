package service;

import dao.GymDao;
import model.Member;
import java.sql.SQLException;
import java.util.List;

public class GymService {
    private static GymDao dao = new GymDao();

   

    public static void registerMember(String name, int age, String phone, String plan) throws SQLException {
        Member member = new Member(name, age, phone, plan);
       
        dao.addMember(member);
    }

    public List<Member> showAllMembers() throws SQLException {
        return dao.getAllMembers();
        
    }

    public void removeMember(int id) throws SQLException {
        dao.deleteMember(id);
    }

    public void modifyMember(int id, String name, int age, String phone, String plan) throws SQLException {
        Member member = new Member(id, name, age, phone, plan);
        dao.updateMember(member);
    }
}