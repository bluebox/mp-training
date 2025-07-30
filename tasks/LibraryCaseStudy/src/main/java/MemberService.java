import java.util.List;
import java.util.stream.Collectors;

public class MemberService {
    private MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public boolean registerMember(Member member) throws IllegalArgumentException {
        validateMember(member);
        return memberDAO.registerMember(member);
    }

    public boolean updateMemberDetails(Member member) throws IllegalArgumentException {
        if (member == null || member.getMemberId() <= 0) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        validateMember(member);
        return memberDAO.updateMemberDetails(member);
    }

    private void validateMember(Member member) throws IllegalArgumentException {
        if (member == null ||
                member.getName() == null || member.getName().trim().isEmpty() ||
                member.getEmail() == null || member.getEmail().trim().isEmpty() ||
                member.getMobile().length() <= 0 ||
                (member.getGender() != 'M' && member.getGender() != 'F') ||
                member.getAddress() == null || member.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid member details");
        }
    }

    public Member getMemberById(int memberId) throws IllegalArgumentException {
        if (memberId <= 0) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        return (Member) memberDAO.getMemberById(memberId);
    }

    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    public List<Member> getMembersByGender(char gender) {
        return memberDAO.getAllMembers().stream()
                .filter(member -> member.getGender() == gender)
                .collect(Collectors.toList());
    }
}