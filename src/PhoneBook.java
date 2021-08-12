import java.util.*;

public class PhoneBook {

    static int numberOfContacts = 0;

    private static void displayMenu() {
        System.out.println("1. Add or edit contact");
        System.out.println("2. View all contacts");
        System.out.println("3. Find a contact by phone number");
        System.out.println("4. Find contacts by name");
        System.out.println("0. Exit\n");
        System.out.print("Select an option ");
    }

        /*    private static void writeContactsToFile(Map<String, List<String>> contacts) {

        try {
            File fileIn = new File("phonebook.txt");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(contacts);
            oos.flush();
            oos.close();
            fos.close();

        } catch (Exception e) {

        }
        try (ObjectOutput oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("phonebook.txt", false)))) {
            oos.writeObject(contacts);
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
    }*/ //write to file

        /* private static void loadContactsFromFile(Map<String, List<String>> contacts) {

        try {
            File fileIn = new File("phonebook.txt");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Map<String, List<String>> mapInFile = (HashMap<String, List<String>>) ois.readObject();
            ois.close();
            fis.close();

            for (Map.Entry<String, List<String>> listEntry : mapInFile.entrySet()) {
                System.out.println(listEntry.getKey() + " " + listEntry.getValue());
            }
        } catch (Exception e) {

        }
        try (
                ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("phonebook.txt")))) {
            contacts = (Map<String, List<String>>) ois.readObject();
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
    }*/ //read from file

    private static void addContact(Map<String, List<String>> contactToAdd, Scanner contactInput) {

        System.out.print("Enter a phone number ");
        contactInput.nextLine();
        String phoneNumber = contactInput.nextLine();
        List<String> contactDetails = new ArrayList<>();
        String firstName = "";
        String lastName = "";
        String email;
        String address;

        while (true) {
            if (!phoneNumber.matches("^0[\\s\\-?\\d]{1,9}+$") || phoneNumber.length() != 10) {
                System.out.println("Please enter a 10-digit valid number");
                phoneNumber = contactInput.nextLine();
            } else break;
        }

        if (contactToAdd.containsKey(phoneNumber)) {
            System.out.println("This phone number already exists. Editing an existing entry.");

        } else {
            System.out.println("This phone number is new. Adding a new entry to the phone book.");
            numberOfContacts++;
        }

        while (!firstName.matches("[a-zA-Z]+")) {
            System.out.print("First name: ");
            firstName = contactInput.nextLine();
        }
        while (!lastName.matches("[a-zA-Z]+")) {
            System.out.print("Last name: ");
            lastName = contactInput.nextLine();
        }
        System.out.print("Email: ");
        email = contactInput.nextLine();
        System.out.print("Address: ");
        address = contactInput.nextLine();

        contactDetails.add(firstName);
        contactDetails.add(lastName);
        contactDetails.add(email);
        contactDetails.add(address);

        contactToAdd.put(phoneNumber, contactDetails);
        //writeContactsToFile(contactToAdd);

        System.out.println("Phone book was updated successfully");
        System.out.println("There are currently " + numberOfContacts + " contacts in the phone book\n");
        System.out.print("Press enter to continue. ");
        contactInput.nextLine();

    }

    private static void searchByPhoneNumber(Map<String, List<String>> contactToSearch, Scanner contactInput) {

        System.out.print("Enter a phone number ");
        contactInput.nextLine();
        String phoneNumber = contactInput.nextLine();

        while (true) {
            if (!phoneNumber.matches("^0[\\s\\-?\\d]+$") || phoneNumber.length() != 10) {
                System.out.println("Please enter a 10-digit valid number");
                phoneNumber = contactInput.nextLine();
            } else break;
        }
        if (!contactToSearch.containsKey(phoneNumber)) {
            System.out.println("The phone number could not be found in the address book.");
        } else {
            for (Map.Entry<String, List<String>> listEntry : contactToSearch.entrySet()) {
                if (listEntry.getKey().contains(phoneNumber)) {
                    System.out.println("Phone number: " + listEntry.getKey());
                    System.out.println("First name: " + listEntry.getValue().get(0));
                    System.out.println("Last name: " + listEntry.getValue().get(1));
                    System.out.println("Email: " + listEntry.getValue().get(2));
                    System.out.println("Address: " + listEntry.getValue().get(3));
                }
            }
        }
        System.out.print("Press enter to continue.");
        contactInput.nextLine();

    }

    private static void searchByName(Map<String, List<String>> contactToSearch, Scanner contactInput) {

        System.out.print("Enter a name (first name, last name or both): ");
        contactInput.nextLine();
        boolean found = false;
        String nameToSearch = contactInput.nextLine().toLowerCase(Locale.ROOT);
        for (Map.Entry<String, List<String>> listEntry : contactToSearch.entrySet()) {
            String fullName = listEntry.getValue().get(0) + " " + listEntry.getValue().get(1);
            String reverseName = listEntry.getValue().get(1) + " " + listEntry.getValue().get(0);
            if (listEntry.getValue().get(0).toLowerCase(Locale.ROOT).contains(nameToSearch)
                    || listEntry.getValue().get(1).toLowerCase(Locale.ROOT).contains(nameToSearch)
                    || fullName.toLowerCase(Locale.ROOT).contains(nameToSearch)
                    || reverseName.toLowerCase(Locale.ROOT).contains(nameToSearch)) {
                found = true; //if name is found in phone book set found to true to check later
                break;
            }
        }
        if (found) {
            for (Map.Entry<String, List<String>> listEntry : contactToSearch.entrySet()) {
                String fullName = listEntry.getValue().get(0) + " " + listEntry.getValue().get(1);
                String reverseName = listEntry.getValue().get(1) + " " + listEntry.getValue().get(0);
                if (listEntry.getValue().get(0).toLowerCase(Locale.ROOT).contains(nameToSearch)
                        || listEntry.getValue().get(1).toLowerCase(Locale.ROOT).contains(nameToSearch)
                        || fullName.toLowerCase(Locale.ROOT).contains(nameToSearch)
                        || reverseName.toLowerCase(Locale.ROOT).contains(nameToSearch)) {
                    System.out.print(listEntry.getKey() + "   ");
                    System.out.println(listEntry.getValue().get(0) + " " +
                            listEntry.getValue().get(1) + "   " +
                            listEntry.getValue().get(2) + "   " +
                            listEntry.getValue().get(3));
                    /*for (String details : listEntry.getValue()) {
                        System.out.print(details + " ");
                    }
                    System.out.println();*/
                }
            }
        } else //if typed name is not found display message
            System.out.println("No results.");

        System.out.print("Press enter to continue");
        contactInput.nextLine();
    }

    private static void viewContacts(Map<String, List<String>> contactList) {

        for (Map.Entry<String, List<String>> listEntry : contactList.entrySet()) {
            System.out.print(listEntry.getKey() + "   ");
            System.out.println(listEntry.getValue().get(0) + " " +
                    listEntry.getValue().get(1) + "   " +
                    listEntry.getValue().get(2) + "   " +
                    listEntry.getValue().get(3));
            /*for (String details : listEntry.getValue()) {
                System.out.print(details + " ");
            }
            System.out.println();*/
        }
        System.out.print("\nPress enter to continue.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void main(String[] args) {

        System.out.println("Phone Book");
        System.out.println("There are currently " + numberOfContacts + " contacts in the phone book\n");
        Map<String, List<String>> contact = new HashMap<>();
        //loadContactsFromFile(contact);
        Scanner scanner = new Scanner(System.in);
        displayMenu();
        int key;
        do {
            try {
                key = scanner.nextInt();
                switch (key) {
                    case 1:
                        System.out.println("Add/edit contact\n");
                        addContact(contact, scanner);
                        displayMenu();
                        break;
                    case 2:
                        System.out.println("View all contacts\n");
                        //call view contacts
                        viewContacts(contact);
                        displayMenu();
                        break;
                    case 3:
                        System.out.println("Find a contact by phone number\n");
                        //call search
                        searchByPhoneNumber(contact, scanner);
                        displayMenu();
                        break;
                    case 4:
                        System.out.println("Find contacts by name\n");
                        //call search
                        searchByName(contact, scanner);
                        displayMenu();
                        break;
                    case 0:
                        System.out.println("Exit");
                        break;
                }
            } catch (Exception e) {
                break;
            }
        } while (key > 0 && key < 5);
    }
}
