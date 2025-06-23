def show_menu():
    print("\nSimple Contact Book")
    print("1. View Contacts")
    print("2. Add Contact")
    print("3. Delete Contact")
    print("4. Exit")

def view_contacts(contacts):
    if not contacts:
        print("No contacts found.")
    else:
        print("\nContacts:")
        for name, phone in contacts.items():
            print(f"{name}: {phone}")

def add_contact(contacts):
    name = input("Enter contact name: ").strip()
    phone = input("Enter phone number: ").strip()
    if name in contacts:
        print(f"Contact '{name}' already exists")
    else:
        contacts[name] = phone
        print(f"Contact '{name}' added")

def delete_contact(contacts):
    view_contacts(contacts)
    name = input("Enter contact name to delete: ").strip()
    if name in contacts:
        del contacts[name]
        print(f"Contact '{name}' deleted")
    else:
        print(f"Contact '{name}' not found")

def main():
    contacts = {}

    while True:
        show_menu()
        choice = input("Choose an option (1-4): ").strip()

        if choice == "1":
            view_contacts(contacts)
        elif choice == "2":
            add_contact(contacts)
        elif choice == "3":
            delete_contact(contacts)
        elif choice == "4":
            print("Exiting Contact Book")
            break
        else:
            print("Invalid choice. Please try again")

if __name__ == "__main__":
    main()
