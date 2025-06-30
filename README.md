# 🏋️ Gym Membership Management System
**Case Study 1 - Object Oriented Programming (OOP) Project**

## 📜 Description
A Java-based **Gym Membership Management System** designed to demonstrate core **OOP concepts** while providing practical functionalities like managing members, assigning membership plans, deleting members, and persisting data using file storage.

## 🎯 **OOP Concepts Implemented**

- ### 🔥 **Polymorphism**  
   → The `Member` class **overrides** the abstract `showDetails()` method from the `Person` class to provide customized member details.

- ### 🔒 **Abstraction**  
   → The `Person` class is declared as an **abstract class**, hiding common details and forcing subclasses to implement specific behaviors like `showDetails()`.

- ### 🛡️ **Encapsulation**  
   → The `Member` class properties are **private**, and are accessed or modified strictly via **getters and setters**. Only the `Gym` class interacts with it securely.

- ### 🧬 **Inheritance**  
   → The `Member` class **inherits** from the `Person` abstract class, gaining all common attributes like `name`, `age`, `height`, and `weight`.

## ⚙️ **Functionalities**

- ✅ **Add New Member**  
   → Automatically generates a unique **ID** for each member (auto-incremented).

- ✅ **Assign Membership Plan**  
   → Select from available plans like **Basic, Premium, or Gold**.

- ✅ **View All Members**  
   → Displays all members in a **neatly formatted table**, including their membership plan.

- ✅ **Delete Member**  
   → Delete any member by selecting their ID.

- ✅ **Data Persistence**  
   → Member data and membership plans are saved in a file (`members.txt`) and automatically loaded when the program starts.

## 🌟 **Additional Features**

- 🔥 **Graceful Input Error Handling**  
   → Includes input validation (e.g., valid age, weight, and height ranges) and protects against invalid inputs.

- 🔥 **Persistent Data Storage**  
   → Data remains intact between different runs of the application due to the implementation of **file-based storage**.

## 🚀 **How to Run**

1. Clone the repository:  
   ```
   git clone <your-repository-url>
   ```
2. Open in **Eclipse** or any Java IDE.

3. Run the `Main` class for the console app or `GymUI` class for the JavaFX GUI (if configured).

4. Data will be saved in `members.txt` in the project directory.

## 📂 **File Structure Example**
```
/src
 └── com.gym.classes
 └── com.gym.driver
members.txt  <-- Auto-generated for storing member data
```

## 👨‍💻 **Developed For**
Case Study 1 - Object Oriented Programming Concepts Practice.
