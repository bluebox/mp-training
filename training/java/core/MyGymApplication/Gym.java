package MyGymApplication;

interface Gym{

    void gymMemberDetails();

    Member getMemberById(String id);

    Trainer getTrainerById(String id);

    void gymMembershipPlans();

    void gymTrainerDetails();

}