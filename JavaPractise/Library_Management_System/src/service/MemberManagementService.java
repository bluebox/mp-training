package service;

import dao.MemberManagementDao;
import domain.Member;
import util.ValidationUtil;

import java.util.List;

public class MemberManagementService implements MemberManagementServiceInterface {
    private final MemberManagementDao memberDao;

    public MemberManagementService() {
        this.memberDao = new MemberManagementDao();
    }
    public boolean registerMember(Member member) throws Exception {
        ValidationUtil.validateNotEmpty("Name", member.getName());
        ValidationUtil.validateEmail(member.getEmail());
        ValidationUtil.validatePhone(member.getMobile());
        ValidationUtil.validateNotEmpty("Address", member.getAddress());
        if (member.getAddedBy() == null || member.getAddedBy().trim().isEmpty()) {
        	member.setAddedBy("ADMIN");
        }
        if (memberDao.isEmailOrMobileDuplicate(member.getEmail(), member.getMobile(),member.getMemberId())) {
            return false; 
        }
        memberDao.registerNewMember(member);
        return true;
    }
    public boolean updateMember(Member member) throws Exception {
        if (memberDao.isEmailOrMobileDuplicate(member.getEmail(), member.getMobile(), member.getMemberId())) {
            return false;
        }
        ValidationUtil.validateNotEmpty("Name", member.getName());
        ValidationUtil.validateEmail(member.getEmail());
        ValidationUtil.validatePhone(member.getMobile());
        ValidationUtil.validateNotEmpty("Address", member.getAddress());
        return memberDao.updaterMemberDetails(member);
    }
    public List<Member> getAllMembers() throws Exception {
        return memberDao.viewAllMembers();
    }
}