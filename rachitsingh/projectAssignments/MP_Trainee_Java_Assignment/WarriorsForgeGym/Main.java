package WarriorsForgeGym;
import WarriorsForgeGym.enums.*;
import WarriorsForgeGym.exceptions.InvalidEnumValueException;
import WarriorsForgeGym.exceptions.InvalidInputException;
import WarriorsForgeGym.admin.GymnasiumAdmin;
import WarriorsForgeGym.coreEntities.FitnessMember;
import WarriorsForgeGym.coreEntities.TrainingProgramBlueprint;
import WarriorsForgeGym.comparator.TrainingProgramComparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input= new Scanner(System.in);

        GymnasiumAdmin admin = new GymnasiumAdmin();
        boolean exitStatus = false;
        try
        {
            while (!exitStatus)
            {
                System.out.println("||------------------------------------------------------------------------||");
                System.out.println("||              Welcome to Warrior's Forge Gymnasium                      ||");
                System.out.println("||                    Forge The Warrior Within                            ||");
                System.out.println("||------------------------------------------------------------------------||");
                System.out.println("||                                                                        ||");
                System.out.println("||   1. View All Available Training Programs                              ||");
                System.out.println("||   2. Enroll a New Member into Gymnasium                                ||");
                System.out.println("||   3. Register a new Trainer                                            ||");
                System.out.println("||   4. Assign a Training Program to a Trainer                            ||");
                System.out.println("||   5. Assign a Training Program to a Member                             ||");
                System.out.println("||   6. Assign a Trainer to a Member                                      ||");
                System.out.println("||   7. Generate a Training Diet Plan for an Enrolled Member              ||");
                System.out.println("||   8. View All Enrolled Gymnasium Members                               ||");
                System.out.println("||   9. View All Available Trainers                                       ||");
                System.out.println("||   10. Order and View Training Programs                                 ||");
                System.out.println("||   0. Exit                                                              ||");
                System.out.println("||                                                                        ||");
                System.out.println("||------------------------------------------------------------------------||");
                System.out.print("\nEnter your choice : ");
                int userInput = input.nextInt();
                input.nextLine();

                switch (userInput)
                {
                    case 1:
                        admin.displayAllAvailableTrainingPrograms();
                        break;
                    case 2:
                        System.out.print("Enter the Trainee Member's ID: ");
                        String id = input.nextLine();

                        System.out.print("Enter the Name: ");
                        String name = input.nextLine();

                        System.out.print("Enter age: ");
                        int age = input.nextInt();
                        input.nextLine();

                        System.out.print("Enter Gender (MALE / FEMALE) : ");
                        Gender gender = Gender.valueOf(input.nextLine().toUpperCase());

                        System.out.print("Enter Physique Type (ECTOMORPH / MESOMORPH / ENDOMORPH) : ");
                        PhysiqueType physiqueType = PhysiqueType.valueOf(input.nextLine().toUpperCase());

                        System.out
                                .print("Enter Fitness Goal (WEIGHT_LOSS, MUSCLE_GAIN, ENDURANCE, FUNCTIONAL_FITNESS) : ");
                        FitnessGoal goal = FitnessGoal.valueOf(input.nextLine().trim().toUpperCase().replace(" ","_"));

                        FitnessMember member = new FitnessMember(id, name, age, gender, goal, physiqueType);
                        admin.addNewMember(member);
                        break;
                    case 3:
                        System.out.print("Enter new Trainer's ID: ");
                        String trainerID = input.nextLine();
                    
                        System.out.print("Enter Trainer Name: ");
                        String trainerName = input.nextLine();
                    
                        System.out.print("Enter Specialization (WEIGHT_TRAINING / MARTIAL_ARTS): ");
                        TrainingProgramType specialization = TrainingProgramType.valueOf(input.nextLine().trim().toUpperCase().replace(" ","_"));
                    
                        System.out.print("Enter Experience in Years: ");
                        int experience = input.nextInt();
                        input.nextLine();
                    
                        if (specialization == TrainingProgramType.WEIGHT_TRAINING) 
                        {
                            System.out.print("Enter Training Mode (BODYWEIGHT_ONLY / WITH_EQUIPMENT): ");
                            TrainingMode mode = TrainingMode.valueOf(input.nextLine().trim().toUpperCase().replace(" ","_"));
                            admin.registerNewTrainer(trainerID, trainerName, mode, experience);
                        } 
                        else if (specialization == TrainingProgramType.MARTIAL_ARTS) 
                        {
                            System.out.print("Enter Martial Art Type (KALARIPAYATTU / BOXING / WRESTLING): ");
                            MartialArtType martialArt = MartialArtType.valueOf(input.nextLine().toUpperCase());
                            admin.registerNewTrainer(trainerID, trainerName, martialArt, experience);
                        } 
                        else 
                        {
                            System.out.println("Invalid specialization entered.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter the Trainer ID to assign a training program: ");
                        String targetTrainerID = input.nextLine();
                    
                        System.out.print("Enter Training Program Name (Calisthenics Champions / Lifting Warriors / Kalari Warriors / Strike Masters / Grappling Kings): ");
                        String assignedProgramName = input.nextLine();
                    
                        admin.assignTrainingProgramToTrainer(targetTrainerID, assignedProgramName);
                        break;
                    case 5:
                        System.out.print("Enter Member ID to enrol in a training program : ");
                        String memberID = input.nextLine();

                        System.out.print("Enter Training Program Name (Calisthenics Champions / Lifting Warriors / Kalari Warriors / Strike Masters / Grappling Kings): ");
                        String programName = input.nextLine();

                        admin.assignTrainingProgramToMember(memberID, programName);
                        break;
                    case 6:
                        System.out.print("Enter the Member ID to whom trainer has to be assigned : ");
                        String trainerMemberID = input.nextLine();
                        admin.assigningTrainerToMember(trainerMemberID);
                        break;
                    case 7:
                        System.out.print("Enter Member ID to generate an accurate diet plan : ");
                        String dietMemberID = input.nextLine();

                        System.out.print("Enter Physique Type (ECTOMORPH / MESOMORPH / ENDOMORPH) : ");
                        PhysiqueType physique = PhysiqueType
                                .valueOf(input.nextLine().toUpperCase());

                        admin.generatingDietPlanForMember(dietMemberID, physique);
                        break;
                    case 8:
                        admin.displayAllGymMembers();
                        break;
                    case 9:
                        admin.displayAllTrainers();
                        break;
                    case 10:
                        List<TrainingProgramBlueprint> orderedTrainingPrograms = admin.getAllAvailableTrainingPrograms();
                        orderedTrainingPrograms.sort(new TrainingProgramComparator());
                        System.out.println(
                                "-------------------------------------------------- Training Programs have been ordered on the basis of length of duration ------------------------------------------------------");
                        for (int i = 0; i < orderedTrainingPrograms.size(); i++) 
                        {
                            System.out.println(orderedTrainingPrograms.get(i));
                        }
                        break;
                    case 0:
                        exitStatus = true;
                        System.out.println("Exiting out of the Warrior's Forge. Keep the warrior alive !");
                        break;
                    default:
                        throw new InvalidInputException(
                                "Invalid user input. Please enter an integer between 0 and 10 only.");
                }
                if(! exitStatus)
                {
                    System.out.println("\n Press Enter to Continue.....");
                    if(!input.hasNextLine())
                    {
                        System.out.println("Input stream has been closed. Exiting...");
                        exitStatus = true;
                    }
                    else
                    {
                        input.nextLine();
                    }
                }
            }
        }
        catch(InvalidInputException exception)
        {
            System.out.println("Error occured: " + exception.getMessage());
        }
        catch(InvalidEnumValueException exception)
        {
            System.out.println("Error occured: " + exception.getMessage());
        }
        catch( NoSuchElementException exception)
        {
            System.out.println("\n Program is exiting due to end of input or invalid expression encountered.");
            exitStatus = true;
        }
        catch( Exception exception)
        {
            System.out.println("An unexpected error has occurred: " + exception.getMessage());
        }
        finally
        {
            input.close();
        }
    }
}