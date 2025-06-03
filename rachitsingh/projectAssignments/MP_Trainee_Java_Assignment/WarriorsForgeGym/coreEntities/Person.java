package WarriorsForgeGym.coreEntities;
import WarriorsForgeGym.enums.Gender;

public abstract class Person 
{

    private String name;
    private int age;
    private Gender gender;

    public Person(String name, int age, Gender gender) 
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        if (name != null && name.isEmpty()) 
        {
            this.name = name;
        }
    }

    public int getAge() 
    {
        return age;
    }

    public void setAge(int age) 
    {
        if (age > 0 && age <= 120) 
        {
            this.age = age;
        }
    }

    public Gender getGender() 
    {
        return gender;
    }

    public void setGender(Gender gender) 
    {
        if (gender != null) 
        {
            this.gender = gender;
        }
    }

    public abstract void displayPersonDetails();
    @Override
    public String toString() 
    {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender;
    }
}
