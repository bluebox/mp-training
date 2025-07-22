import controller.GymController;
import controllerInterface.GymControllerInterface;
public class Main {
    public static void main(String[] args) {
        GymControllerInterface gym=new GymController();
        gym.run();
    }
}
