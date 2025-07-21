package service;

import dao.MemberDAO;
import model.Member;
import model.MembershipType;
import model.Gender;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;



public class MemberService {
    private final MemberDAO dao = new MemberDAO();
    private final Scanner scanner = new Scanner(System.in);
     public void addMember() {
            System.out.println(" Member Registration Started");

            
                try {
                    String name = inputName();
                    int age = inputAge();
                    String email = inputEmail();
                    Gender gender = inputGender();

                    Member member = new Member(name, age, email, gender, LocalDate.now(), LocalDate.now());
                    MemberDAO memberDAO = new MemberDAO();  
                    memberDAO.addMember(member);          

                    System.out.println(" Member registered successfully.");
                } catch (Exception e) {
                    System.out.println(" Error during registration: " + e.getMessage());
                }

        }


        private String inputName() {
            while (true) {
                System.out.print("Enter Name: ");
                String name = scanner.nextLine().trim();
                if (isValidName(name)) return name;
                System.out.println(" Invalid name. Use letters and spaces (2–30 characters).");
            }
        }

        private int inputAge() {
            while (true) {
                System.out.print("Enter Age: ");
                String input = scanner.nextLine().trim();
                if (isValidAge(input)) return Integer.parseInt(input);
                System.out.println(" Invalid age. Enter a number between 10 and 100.");
            }
        }

        private String inputEmail() {
            while (true) {
                System.out.print("Enter Email: ");
                String email = scanner.nextLine().trim();
                if (isValidEmail(email)) return email;
                System.out.println(" Invalid email format.");
            }
        }

        private Gender inputGender() {
            while (true) {
                System.out.print("Enter Gender (male/female/other): ");
                String input = scanner.nextLine().trim().toLowerCase();
                Gender gender = parseGender(input);
                if (gender != null) return gender;
                System.out.println(" Invalid gender. Please enter male/female/other.");
            }
        }

        // Validations 

        private boolean isValidName(String name) {
            return name.matches("^[A-Za-z ]{2,30}$");
        }

        private boolean isValidAge(String ageStr) {
            try {
                int age = Integer.parseInt(ageStr);
                return age >= 10 && age <= 100;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private boolean isValidEmail(String email) {
            return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        }

        private Gender parseGender(String genderStr) {
            switch (genderStr.toLowerCase()) {
                case "male": return Gender.MALE;
                case "female": return Gender.FEMALE;
                case "other":
                case "others": return Gender.OTHER;
                default: return null;
            }
        }
    
   
            
            public void assignPlanToMember() {
                try {
                    int memberId = getValidMemberId(); 
                    MembershipType plan = getValidPlan(); 
                    LocalDate today = LocalDate.now();

                    dao.assignPlan(memberId, plan, today); 
                    System.out.println("MemberId "+memberId+" plan is upgraded to "+plan+" sucessfully..");
              

               
                } catch (Exception e) {
                    System.out.println(" Error: " + e.getMessage());
                }
            }

         
            private int getValidMemberId() {
                int id;
                while (true) {
                    System.out.print("Enter Member ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.print(" Invalid input. Enter a number for Member ID: ");
                        scanner.next(); 
                    }
                    id = scanner.nextInt();
                    if (dao.doesMemberExist(id)) {
                        return id;
                    } else {
                        System.out.println(" Member ID " + id + " does not exist. Try again.");
                    }
                }
            }

            
            private MembershipType getValidPlan() {
                System.out.println("Select Plan: 1. BASIC  2. PREMIUM  3. GOLD");
                int choice;
                while (true) {
                    while (!scanner.hasNextInt()) {
                        System.out.print(" Invalid input. Enter 1, 2, or 3: ");
                        scanner.next();
                    }
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= MembershipType.values().length) {
                        return MembershipType.values()[choice - 1];
                    } else {
                        System.out.print(" Invalid choice. Please enter 1, 2, or 3: ");
                    }
                }
            }
        

 
  
   
            public void viewAllMembers() {
                try {
                    List<Member> members = dao.getAllMembers();

                    if (members == null || members.isEmpty()) {
                        System.out.println(" No members found in the gym.");
                        return;
                    }

                    System.out.println("\n All Gym Members Overview\n");

                    String format = "| %-4s | %-15s | %-3s | %-25s | %-10s | %-10s | %-12s | %-12s |\n";
                    System.out.println("=".repeat(114));
                    System.out.printf(format, "ID", "Name", "Age", "Email", "Gender", "Plan", "JoinedDate", "ModifiedDate");
                    System.out.println("=".repeat(114));

                    for (Member m : members) {
                        System.out.printf(format,
                                m.getMemberId(),
                                m.getName(),
                                m.getAge(),
                                m.getEmail(),
                                m.getGender(),
                                (m.getMembershipType() != null ? m.getMembershipType() : "Not Assigned"),
                                m.getJoinedDate(),
                                m.getModifiedDate()
                        );
                    }

                    System.out.println("=".repeat(114));

                } catch (SQLException e) {
                    System.out.println(" Error fetching member data: " + e.getMessage());
                }
            }

            public void updateMember() {
                try {
                    System.out.print("Enter Member ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (!dao.doesMemberExist(id)) {
                        System.out.println(" Member ID not found.");
                        return;
                    }

                    String name = null;
                    Integer age = null;
                    String email = null;
                    Gender gender = null;

                    System.out.print("Do you want to update Name? (yes/no): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                        name = inputName(); 
                    }

                    System.out.print("Do you want to update Age? (yes/no): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                        age = inputAge(); 
                    }

                    System.out.print("Do you want to update Email? (yes/no): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                        email = inputEmail(); 
                    }

                    System.out.print("Do you want to update Gender? (yes/no): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                        gender = inputGender(); 
                    }

                    if (name == null && age == null && email == null && gender == null) {
                        System.out.println(" No fields selected for update.");
                        return;
                    }

                    
                    dao.updateMemberFields(id, name, age, email, gender);
                    System.out.println(" Member details updated successfully.");
                } catch (Exception e) {
                    System.out.println(" Error during update: " + e.getMessage());
                }
            }


            public void removeMember() {
                try {
                    System.out.print("Enter Member ID to remove: ");
                    int id = scanner.nextInt();

                    
                    Member member = dao.getMemberById(id);
                    if (member == null) {
                        System.out.println(" Member with ID " + id + " does not exist. Please try again.");
                        return;
                    }

                 
                    System.out.println("\n  Confirm Deletion of Member:");
                    viewAllMembers();
                    System.out.print("Are you sure you want to delete this member? (yes/no): ");
                    scanner.nextLine();
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("yes")) {
                        dao.deleteMember(id);
                        System.out.println(" Member removed successfully.");
                    } else {
                        System.out.println(" Operation cancelled.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println(" Invalid input. Please enter a numeric Member ID.");
                    scanner.nextLine(); 
                } catch (SQLException e) {
                    System.out.println(" Error: " + e.getMessage());
                }
            }

    public void exportToCSV() {
        try {
            dao.exportToCSV("members_export.csv");
            System.out.println(" Exported to members_export.csv");
        } catch (Exception e) {
            System.out.println(" Export failed: " + e.getMessage());
        }
    }
    
    
    public void importFromCSV() {
        String fileName = "members_data.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty() || line.toLowerCase().startsWith("memberid")) {
                    continue; 
                }

                String[] values = line.split(",");
                if (values.length < 8) {
                    System.out.println(" Line " + lineNumber + " skipped: Not enough fields.");
                    continue;
                }

                try {
                    int memberId = Integer.parseInt(values[0].trim());
                    String name = values[1].trim();
                    String ageStr = values[2].trim();
                    String email = values[3].trim();
                    String genderStr = values[4].trim();
                    String planStr = values[5].trim();
                    String joinedDateStr = values[6].trim();
                    String modifiedDateStr = values[7].trim(); 

                   
                    if (!isValidName(name)) {
                        System.out.println(" Line " + lineNumber + " skipped: Invalid name.");
                        continue;
                    }
                    if (!isValidAge(ageStr)) {
                        System.out.println(" Line " + lineNumber + " skipped: Invalid age.");
                        continue;
                    }
                    int age = Integer.parseInt(ageStr);

                    if (!isValidEmail(email)) {
                        System.out.println(" Line " + lineNumber + " skipped: Invalid email.");
                        continue;
                    }

                    Gender gender = parseGender(genderStr);
                    if (gender == null) {
                        System.out.println(" Line " + lineNumber + " skipped: Invalid gender.");
                        continue;
                    }

                    MembershipType membershipType = MembershipType.valueOf(planStr.toUpperCase());
                    LocalDate joinedDate = LocalDate.parse(joinedDateStr);
                    LocalDate modifiedDate = LocalDate.parse(modifiedDateStr);

                    Member member = new Member(name, age, email, gender, joinedDate, modifiedDate);
                    member.setMemberId(memberId);
                    member.setMembershipType(membershipType);

                    dao.addMember(member); 

                } catch (Exception ex) {
                    System.out.println(" Line " + lineNumber + " skipped: " + ex.getMessage());
                }
            }

            System.out.println(" Import completed.");
        } catch (IOException e) {
            System.out.println(" Import failed: " + e.getMessage());
        }
    }


 }
