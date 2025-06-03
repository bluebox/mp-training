package WarriorsForgeGym.admin;

import WarriorsForgeGym.coreEntities.*;
import WarriorsForgeGym.enums.*;
import WarriorsForgeGym.exceptions.*;
import java.util.*;

public class GymnasiumAdmin 
{
    private List<FitnessMember> membersEnrolled;
    private List<TrainerProfile> availableTrainers;
    private List<TrainingProgramBlueprint> availableTrainingPrograms;

    // we have used this map to put members of the gym and the meals plan assigned to them together.
    private Map<FitnessMember, TrainingDietPlan> membersToDietPlanMap;

    private void initializeDefaultTrainingPrograms()
    {
        if (availableTrainingPrograms.isEmpty()) 
        {
            availableTrainingPrograms.add(new TrainingProgramBlueprint(
                    "Calisthenics Champions",
                    TrainingProgramType.WEIGHT_TRAINING,
                    TrainingMode.BODYWEIGHT_ONLY,
                    null,
                    FitnessGoal.ENDURANCE,
                    Level.NOVICE,
                    8,
                    2500));
            availableTrainingPrograms.add(new TrainingProgramBlueprint(
                    "Lifting Warriors",
                    TrainingProgramType.WEIGHT_TRAINING,
                    TrainingMode.WITH_EQUIPMENT,
                    null,
                    FitnessGoal.MUSCLE_GAIN,
                    Level.INTERMEDIATE,
                    6,
                    3000));
            availableTrainingPrograms.add(new TrainingProgramBlueprint(
                    "Kalari Warriors",
                    TrainingProgramType.MARTIAL_ARTS,
                    null,
                    MartialArtType.KALARIPAYATTU,
                    FitnessGoal.FUNCTIONAL_FITNESS,
                    Level.NOVICE,
                    12,
                    3000));
            availableTrainingPrograms.add(new TrainingProgramBlueprint(
                    "Grappling Kings",
                    TrainingProgramType.MARTIAL_ARTS,
                    null,
                    MartialArtType.WRESTLING,
                    FitnessGoal.FUNCTIONAL_FITNESS,
                    Level.INTERMEDIATE,
                    12,
                    4000));
            availableTrainingPrograms.add(new TrainingProgramBlueprint(
                    "Strike Masters",
                    TrainingProgramType.MARTIAL_ARTS,
                    null,
                    MartialArtType.BOXING,
                    FitnessGoal.FUNCTIONAL_FITNESS,
                    Level.INTERMEDIATE,
                    8,
                    4500));
        }
    }
    
    private void initializeAlreadyAvailableTrainers()
    {
        availableTrainers.add(new TrainerProfile("KALARI301", "Vidyut Jamwal", MartialArtType.KALARIPAYATTU, 15));
        availableTrainers.add(new TrainerProfile("LIFT101", "Vivek Teja", TrainingMode.WITH_EQUIPMENT, 10));
        availableTrainers.add(new TrainerProfile("BOX201", "Bobbili Srikanth Reddy", MartialArtType.BOXING, 12));
        availableTrainers.add(new TrainerProfile("CAL712", "Karan Gupta", TrainingMode.BODYWEIGHT_ONLY, 4));
    }

    public List<TrainingProgramBlueprint> getAllAvailableTrainingPrograms()
    {
        return this.availableTrainingPrograms;
    }
    
    protected List<String> generateMealsForPhysiqueType(FitnessGoal goal, PhysiqueType type) {
        List<String> meals = new ArrayList<>();
    
        switch (goal) {
            case WEIGHT_LOSS:
                switch (type) {
                    case ECTOMORPH:
                        meals.add("Steamed rice cakes with lentil stew");
                        meals.add("Sprouted green gram salad");
                        meals.add("Vegetable semolina porridge with green tea");
                        break;
                    case MESOMORPH:
                        meals.add("Oatmeal with almonds and raisins");
                        meals.add("Grilled cottage cheese with fresh salad");
                        meals.add("Boiled chickpeas with vegetables");
                        break;
                    case ENDOMORPH:
                        meals.add("Finger millet pancakes with mint sauce");
                        meals.add("Bottle gourd curry with multigrain flatbread");
                        meals.add("Mixed vegetable soup with lentil porridge");
                        break;
                }
                break;
    
            case MUSCLE_GAIN:
                switch (type) {
                    case ECTOMORPH:
                        meals.add("Stuffed flatbread with cottage cheese and curd");
                        meals.add("Banana smoothie with soaked almonds");
                        meals.add("Kidney beans with rice cooked in ghee");
                        break;
                    case MESOMORPH:
                        meals.add("Gram flour pancakes with green chutney");
                        meals.add("Rice and lentil mash with curd and pickles");
                        meals.add("Peanut energy bars with warm milk");
                        break;
                    case ENDOMORPH:
                        meals.add("Soy chunks curry with wheat flatbread");
                        meals.add("Steamed rice cakes with lentil stew");
                        meals.add("Millet pancakes with curry leaf chutney");
                        break;
                }
                break;
    
            case ENDURANCE:
                meals.add("Flattened rice with peanuts and lemon");
                meals.add("Lentil rice with curd");
                meals.add("Tender coconut water with fruit salad");
                meals.add("Savory oats with mixed vegetables");
                break;
    
            case FUNCTIONAL_FITNESS:
                meals.add("Vegetable semolina porridge with lentil stew");
                meals.add("Wheat flatbread with spinach and cottage cheese");
                meals.add("Brown rice with spiced lentils and salad");
                meals.add("Chickpea curry with cumin-flavored rice");
                break;
    
            default:
                meals.add("Balanced vegetarian platter: flatbread, lentils, vegetables, rice, and curd");
        }
    
        return meals;
    }
    

    public GymnasiumAdmin()
    {
        membersEnrolled = new ArrayList<FitnessMember>();
        availableTrainers = new ArrayList<TrainerProfile>();
        availableTrainingPrograms = new ArrayList<TrainingProgramBlueprint>();
        membersToDietPlanMap = new HashMap<FitnessMember, TrainingDietPlan>();
        if (this.availableTrainingPrograms.isEmpty()) 
        {
            initializeDefaultTrainingPrograms();
        }
        if (this.availableTrainers.isEmpty())
        {
            initializeAlreadyAvailableTrainers();
        }
    }
    public void addNewMember(FitnessMember member) 
    {
        membersEnrolled.add(member);
        System.out.println("\nNew member enrolled in 'Warrior's Forge' : " + member.getName());
    }

    private FitnessMember getMemberByID(String memberID) throws MemberNotFoundException
    {
        for (int i = 0; i < membersEnrolled.size(); i++) {
            if (membersEnrolled.get(i).getMemberID().equalsIgnoreCase(memberID)) {
                return membersEnrolled.get(i);
            }
        }
        throw new MemberNotFoundException("Member with ID: " + memberID + " does not exist.");
    }
    
    public void registerNewTrainer(String trainerID, String name, TrainingMode mode, int experience) 
    {
        TrainerProfile trainer = new TrainerProfile(trainerID, name, mode, experience);
        availableTrainers.add(trainer);
        System.out.println("\nNew Weight Training Trainer Registered: " + name + " [Mode: " + mode + ", Exp: "
                + experience + " yrs]");
    }
    
    public void registerNewTrainer(String trainerID, String name, MartialArtType martialArtType, int experience) 
    {
        TrainerProfile trainer = new TrainerProfile(trainerID, name, martialArtType, experience);
        availableTrainers.add(trainer);
        System.out.println("\nNew Martial Arts Trainer Registered: " + name + " [Art: " + martialArtType + ", Exp: "
                + experience + " yrs]");
    }
    
    public void assignTrainingProgramToTrainer(String trainerID, String programName)
            throws TrainingPlanNotFoundException 
    {
        TrainerProfile targetTrainer = null;
        for (int i = 0 ; i<availableTrainers.size(); i++) 
        {
            TrainerProfile trainer = availableTrainers.get(i);
            if (trainer.getTrainerID().equalsIgnoreCase(trainerID)) 
            {
                targetTrainer = trainer;
                break;
            }
        }
    
        if (targetTrainer == null) 
        {
            System.out.println("Trainer with ID " + trainerID + " has not been registered.");
            return;
        }
        TrainingProgramBlueprint selectedProgram = null;
        for (int i = 0; i < availableTrainingPrograms.size(); i++) 
        {
            TrainingProgramBlueprint program = availableTrainingPrograms.get(i);
            if (program.getProgramTitle().equalsIgnoreCase(programName)) 
            {
                selectedProgram = program;
                break;
            }
        }
    
        if (selectedProgram == null) 
        {
            throw new TrainingPlanNotFoundException("\nTraining program \"" + programName + "\" does not exist.");
        }
        targetTrainer.enrollInTrainingProgram(selectedProgram.getProgramType());
    }
    

    public void assignTrainingProgramToMember(String memberID, String trainingPlanName) throws TrainingPlanNotFoundException, MemberNotFoundException
    {
        FitnessMember member = getMemberByID(memberID);
        TrainingProgramBlueprint selectedTrainingPlan = null;

        for (int i = 0; i < availableTrainingPrograms.size(); i++) 
        {
            TrainingProgramBlueprint trainingPlan = availableTrainingPrograms.get(i);
            if (trainingPlan.getProgramTitle().equalsIgnoreCase(trainingPlanName)) 
            {
                selectedTrainingPlan = trainingPlan;
                break;
            }
        }
        if (selectedTrainingPlan == null) 
        {
            throw new TrainingPlanNotFoundException("Requested training Plan not found: " + trainingPlanName);
        }

        member.setMembershipPlan(selectedTrainingPlan);
        System.out.println("Training program successfully assigned to member " + member.getName() + ": "
                + selectedTrainingPlan.getProgramTitle());
    }
    
    public void assigningTrainerToMember(String memberID) throws MemberNotFoundException 
    {
        FitnessMember member = getMemberByID(memberID);
        TrainingProgramBlueprint trainingPlan = member.getMembershipPlan();

        if (trainingPlan == null) 
        {
            System.out.println("\nNo training program assigned. Please assign a program first.");
            return;
        }

        TrainerProfile trainer = null;

        if (trainingPlan.getMartialArtType() != null) 
        {
            MartialArtType type = trainingPlan.getMartialArtType();
            for (TrainerProfile t : availableTrainers) 
            {
                if (type.equals(t.getMartialArtType())) 
                {
                    trainer = t;
                    break;
                }
            }
        } 
        else if (trainingPlan.getTrainingMode() != null) 
        {
            TrainingMode mode = trainingPlan.getTrainingMode();
            for (TrainerProfile t : availableTrainers) 
            {
                if (mode.equals(t.getTrainingMode())) 
                {
                    trainer = t;
                    break;
                }
            }
        }

        if (trainer == null) 
        {
            System.out.println("\nCurrently no trainers are available for this training program.");
            return;
        }

        member.setAssignedTrainer(trainer);
        System.out.println("Trainer " + trainer.getName() + " has been assigned to member " + member.getName());
    }
    
    public void generatingDietPlanForMember(String memberID, PhysiqueType physiqueType) throws MemberNotFoundException 
    {
        FitnessMember member = getMemberByID(memberID);
        TrainingProgramBlueprint trainingPlan = member.getMembershipPlan();

        if (trainingPlan == null) 
        {
            System.out.println("\nCannot generate diet plan without an enrolled training program.");
            return;
        }
        List<String> meals = generateMealsForPhysiqueType(member.getFitnessGoal(), physiqueType);
        TrainingDietPlan dietPlanForMember = new TrainingDietPlan(member.getFitnessGoal(), physiqueType,
                meals);
        membersToDietPlanMap.put(member, dietPlanForMember);
        System.out.println("\nComplete Diet plan has been generated for " + member.getName() + ":");
        dietPlanForMember.displayTrainingMealPlan();
    }
    
    public void displayAllGymMembers() 
    {
        if (membersEnrolled.isEmpty()) 
        {
            System.out.println("\nNo members have registered yet to any training program.");
            return;
        }
        System.out.println("\n----------- All Enrolled Warrior's Forge Members -----------");
        for (FitnessMember member : membersEnrolled) 
        {
            System.out.println("----------------------------------------------------------");
            System.out.println("Member ID: " + member.getMemberID());
            System.out.println("Name: " + member.getName());
            System.out.println("Age: " + member.getAge());

            TrainingProgramBlueprint trainingPlan = member.getMembershipPlan();
            System.out.println("Plan: " + (trainingPlan != null ? trainingPlan.getProgramTitle() : "Not assigned"));

            TrainerProfile trainer = member.getTrainerAssigned();
            System.out.println("Trainer: " + (trainer != null ? trainer.getName() : "Not assigned"));

        }
    }
    
    public void displayAllAvailableTrainingPrograms()
    {
        if (availableTrainingPrograms.isEmpty())
        {
            System.out.println("No training programs have been designed as of now.");
            return;
        }
        System.out.println("------------ Available Training Programs at Warrior's Forge --------------");
        for (int i = 0; i < availableTrainingPrograms.size(); i++) 
        {
            System.out.println(
                    availableTrainingPrograms.get(i).getProgramTitle() + " [ "
                            + availableTrainingPrograms.get(i).getProgramType()
                            + " ] - " + availableTrainingPrograms.get(i).getDurationInMonths() + " months - Rs."
                            + availableTrainingPrograms.get(i).getMonthlyCharges());
        }
    }
    
    public void displayAllTrainers()
    {
        if (this.availableTrainers.isEmpty())
        {
            System.out.println("No trainers have registered to our Gymnasium as of now.");
            return;
        }
        System.out.println("------------- Currently Available Trainers at Warrior's Forge----------------");
        for (int i = 0; i < availableTrainers.size(); i++) 
        {
            availableTrainers.get(i).displayTrainersAbout();
            System.out.println();
        }
    }

}
