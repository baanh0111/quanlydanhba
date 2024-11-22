package repository;

import entity.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private final String FILE_PATH = "data/contacts.csv";
    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveToFile();
    }

    // Trong ContactRepository
    public void removeContact(String name) {
        // Xóa theo tên
        contacts.removeIf(contact -> contact.getName().equals(name));
        saveToFile();
    }


    public Contact findContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    // Đọc danh bạ từ file CSV
    public void loadFromFile() {
        contacts.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    Contact contact = new Contact(data[0], data[1], data[2], data[3], data[4]);
                    contacts.add(contact);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File danh bạ chưa tồn tại.");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file");
        }
    }

    // Lưu danh bạ vào file CSV
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," +
                        contact.getPhoneNumber() + "," +
                        contact.getAddress() + "," +
                        contact.getEmail() + "," +
                        contact.getFacebook());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file.");
        }
    }
}
