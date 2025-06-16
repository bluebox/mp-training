package main.com.library.services;

package main.com.library.services;

import main.com.library.dao.MemberDAO;
import main.com.library.domain.Member;
import main.com.library.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberService {
    MemberDAO memberDAO = new MemberDAO();
    public boolean updateMember(Member member) {
        return memberDAO.updateMember(member);
    }
}