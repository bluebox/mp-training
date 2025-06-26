from Gym import Member, Gym, Membership


def main():
    bfc = Gym()
    while True:
        name = input("Enter name: ")
        age = int(input("Enter age: "))
        plan = input("Enter plan (Base/Gold/Premium): ")
        time = int(input("Enter time in months: "))

        a = Member(name, age)
        b = Membership(plan, time)
        bfc.addMember(a, b)

        val = input("Enter Q to exit or any other key to continue: ")
        if val.strip().upper() == "Q":
            break

    print("\n--- Member Details ---")
    bfc.display()


if __name__ == "__main__":
    main()
