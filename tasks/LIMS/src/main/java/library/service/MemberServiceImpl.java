package library.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import library.dao.interfaceimpl.MemberDAOImpl;
import library.dao.interfaces.MemberDAO;
import library.exception.LibraryException;
import library.model.Member;
import library.model.enums.Gender;
import library.service.interfaces.MemberService;
import library.validation.MemberValidator;

public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO;

    public MemberServiceImpl() {
        this.memberDAO = new MemberDAOImpl();
    }

    @Override
    public String addMember(Member member) {
        MemberValidator.validateMember(member);

        Member emailCriteria = new Member();
        emailCriteria.setEmail(member.getEmail());
        if (!memberDAO.findMembers(emailCriteria).isEmpty()) {
            throw new LibraryException("Member with email " + member.getEmail() + " already exists.");
        }

        Member phoneCriteria = new Member();
        phoneCriteria.setPhoneNumber(member.getPhoneNumber());
        if (!memberDAO.findMembers(phoneCriteria).isEmpty()) {
            throw new LibraryException("Member with phone number " + member.getPhoneNumber() + " already exists.");
        }

        return memberDAO.addMember(member);
    }

    @Override
    public Member getMemberById(int id) {
        MemberValidator.validateMemberId(id);

        Member criteria = new Member();
        criteria.setMemberID(id);
        List<Member> members = memberDAO.findMembers(criteria);
        return members.isEmpty() ? null : members.get(0);
    }

    @Override
    public Member getMemberByEmail(String email) {
        MemberValidator.validateMemberEmail(email);

        Member criteria = new Member();
        criteria.setEmail(email);
        List<Member> members = memberDAO.findMembers(criteria);
        return members.isEmpty() ? null : members.get(0);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberDAO.findMembers(new Member());
    }

    @Override
    public List<Member> findMembers(Map<String, Object> criteriaMap) {
        Member criteria = new Member();
        if (criteriaMap.containsKey("memberID") && Objects.nonNull(criteriaMap.get("memberID"))) {
            criteria.setMemberID((Integer) criteriaMap.get("memberID"));
        }
        if (criteriaMap.containsKey("name") && Objects.nonNull(criteriaMap.get("name"))) {
            criteria.setName((String) criteriaMap.get("name"));
        }
        if (criteriaMap.containsKey("email") && Objects.nonNull(criteriaMap.get("email"))) {
            criteria.setEmail((String) criteriaMap.get("email"));
        }
        if (criteriaMap.containsKey("phoneNumber") && Objects.nonNull(criteriaMap.get("phoneNumber"))) {
            criteria.setPhoneNumber((Long) criteriaMap.get("phoneNumber"));
        }
        if (criteriaMap.containsKey("gender") && Objects.nonNull(criteriaMap.get("gender"))) {
            if (criteriaMap.get("gender") instanceof String) {
                criteria.setGender(Gender.fromCodeString((String) criteriaMap.get("gender")));
            } else if (criteriaMap.get("gender") instanceof Character) {
                criteria.setGender(Gender.fromCode((Character) criteriaMap.get("gender")));
            } else if (criteriaMap.get("gender") instanceof Gender) {
                 criteria.setGender((Gender) criteriaMap.get("gender"));
            }
        }
        if (criteriaMap.containsKey("address") && Objects.nonNull(criteriaMap.get("address"))) {
            criteria.setAddress((String) criteriaMap.get("address"));
        }
        return memberDAO.findMembers(criteria);
    }

    @Override
    public void updateMember(Member member) {
        MemberValidator.validateMemberForUpdate(member);

        Member existingMember = getMemberById(member.getMemberID());
        if (existingMember == null) {
            throw new LibraryException("Member with ID " + member.getMemberID() + " not found.");
        }

        if (!existingMember.getEmail().equalsIgnoreCase(member.getEmail())) {
             Member emailCriteria = new Member();
             emailCriteria.setEmail(member.getEmail());
             if (!memberDAO.findMembers(emailCriteria).isEmpty()) {
                throw new LibraryException("Another member with email " + member.getEmail() + " already exists.");
            }
        }

        if (existingMember.getPhoneNumber() != member.getPhoneNumber()) {
            Member phoneCriteria = new Member();
            phoneCriteria.setPhoneNumber(member.getPhoneNumber());
            if (!memberDAO.findMembers(phoneCriteria).isEmpty()) {
                throw new LibraryException("Another member with phone number " + member.getPhoneNumber() + " already exists.");
            }
        }

        memberDAO.updateMember(member);
    }

    @Override
    public void deleteMember(Member member) {
        if (member == null || member.getMemberID() <= 0) { 
            throw new LibraryException("Invalid member data for deletion.");
        }
        
        Member existingMember = getMemberById(member.getMemberID());
        if (existingMember == null) {
            throw new LibraryException("Member with ID " + member.getMemberID() + " not found.");
        }
        memberDAO.deleteMember(member);
    }

    @Override
    public void deleteMembers(List<Integer> memberIds) {
        if (memberIds == null || memberIds.isEmpty()) {
            throw new LibraryException("No member IDs provided for batch deletion.");
        }

        memberDAO.deleteMembersInBatch(memberIds);
    }
}