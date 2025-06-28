public class smartk {
    private coffeMaker brewMaster;
    private dishwasher dishWasher;
    private refrigerator iceBox;

    public smartk() {
        brewMaster = new coffeMaker();
        dishWasher = new dishwasher();
        iceBox = new refrigerator();
    }

    public void addWater() {
        brewMaster.sethasworktodo(true);
    }

    public void pourMilk() {
        iceBox.sethasworktodo(true);
    }

    public void loadDishwasher() {
        dishWasher.sethasworktodo(true);
    }

    public void setKitchenState(boolean coffeet, boolean fridget, boolean dishWashert) {
        brewMaster.sethasworktodo(coffeet);
        iceBox.sethasworktodo(fridget);
        dishWasher.sethasworktodo(dishWashert);
    }

    public void doKitchenWork() {
        brewMaster.brewcofee();
        iceBox.orderfood();
        dishWasher.dodishes();
    }
}