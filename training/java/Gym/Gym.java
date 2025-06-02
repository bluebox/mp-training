package Assignments.GymCaseStudy;
import java.util.ArrayList;
public class Gym{
    public static ArrayList<Member> membersRecord = new ArrayList<>();

    public void add(Member member1){
        for(Member member: membersRecord){
            if(member.getName().equals(member1.getName())){
                System.out.println("Member already exists");
                return;
            }
        }
        membersRecord.add(member1);
    }

    public void deleteMember(String memId){
        for(Member member: membersRecord){
            if(member.getMemberId().equals(memId)){
               membersRecord.remove(member);
                return;
            }
        }
        System.out.println("No Member with that ID");
    }

    public void deleteRecordData(){
        membersRecord.clear();
    }
    public void update(String memId,String age){
        for(Member member: membersRecord){
            if(member.getMemberId().equals(memId)){
                member.setAge(age);
                return;
            }
        }
        System.out.println("No Member with that ID");
    }

    public void getMember(String memId){
        for(Member member: membersRecord){
            if(member.getMemberId().equals(memId)){
                System.out.println(member.getMember());
                return;
            }
        }
        System.out.println("No Member with that ID");
    }
    public void DisplayMembersRecord(){
        if(membersRecord.size() >0){
            for(Member member : membersRecord){
                System.out.println(member.showMembersDetails());
            }
        }else{
            System.out.println("No Members in the Record");
        }
    }
}
