package service;

import entity.Contact;
import repository.ContactRepository;

import java.util.Scanner;

public class ContactService implements IContactService {
    private ContactRepository repository = new ContactRepository();
    private Scanner scanner = new Scanner(System.in);

    public ContactService() {
        repository.loadFromFile();
    }

    @Override
    public void addContact() {
        String name = inputName();
        String phone = inputPhone();
        String address = inputAddress();
        String email = inputEmail();
        String facebook = inputFacebook();

        Contact contact = new Contact(name, phone, address, email, facebook);
        repository.addContact(contact);
        System.out.println("Đã thêm danh bạ thành công!");
    }

    @Override
    public void editContact() {
        System.out.print("Nhập tên danh bạ cần sửa: ");
        String name = scanner.nextLine();
        Contact contact = repository.findContact(name);

        if (contact != null) {
            System.out.print("Số điện thoại mới: ");
            String phone = scanner.nextLine();
            if (!phone.isBlank()) contact.setPhoneNumber(phone);

            System.out.print("Địa chỉ mới: ");
            String address = scanner.nextLine();
            if (!address.isBlank()) contact.setAddress(address);

            System.out.print("Email mới: ");
            String email = scanner.nextLine();
            if (!email.isBlank()) contact.setEmail(email);

            System.out.print("Facebook mới : ");
            String facebook = scanner.nextLine();
            if (!facebook.isBlank()) contact.setFacebook(facebook);

            System.out.println("Cập nhật danh bạ thành công!");
            repository.saveToFile();
        } else {
            System.out.println("Không tìm thấy danh bạ với tên đã nhập.");
        }
    }

    @Override
    public void deleteContact() {
        displayContacts();
        System.out.print("Nhập tên danh bạ cần xóa: ");

        String name = scanner.nextLine();
        Contact contactToRemove = repository.findContact(name);

        if (contactToRemove != null) {

            repository.removeContact(name);
            System.out.println("Đã xóa danh bạ thành công!");
        } else {
            System.out.println("Không tìm thấy danh bạ với tên đã nhập.");
        }
    }


    @Override
    public void displayContacts() {
        if (repository.getAllContacts().isEmpty()) {
            System.out.println("Không có danh bạ nào để hiển thị.");
        } else {
            System.out.println("----- DANH SÁCH DANH BẠ -----");
            int count = 1;  // Khởi tạo số thứ tự bắt đầu từ 1
            for (Contact contact : repository.getAllContacts()) {
                System.out.println(count + ". " + contact);
                count++;
            }
        }
    }


    private String inputName() {
        String name;
        while (true) {
            System.out.print("Nhập tên: ");
            name = scanner.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                break; // Chấp nhận tên hợp lệ
            } else {
                System.out.println("Tên không hợp lệ! Vui lòng chỉ nhập chữ cái.");
            }
        }
        return name;
    }


    private String inputPhone() {
        String phone;
        while (true) {
            System.out.print("Nhập số điện thoại: ");
            phone = scanner.nextLine();
            if (phone.matches("[0-9]+")) {
                break;
            } else {
                System.out.println("Số điện thoại không hợp lệ! Vui lòng chỉ nhập số.");
            }
        }
        return phone;
    }


    private String inputAddress() {
        System.out.print("Nhập địa chỉ: ");
        return scanner.nextLine();
    }


    private String inputEmail() {
        System.out.print("Nhập email: ");
        return scanner.nextLine(); // Không kiểm tra định dạng email
    }


    private String inputFacebook() {
        System.out.print("Nhập Facebook: ");
        return scanner.nextLine();
    }
}
