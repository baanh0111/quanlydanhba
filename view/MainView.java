package view;

import service.ContactService;
import java.util.Scanner;

public class MainView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ContactService contactService = new ContactService();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n----- MENU QUẢN LÝ DANH BẠ -----");
            System.out.println("1. Hiển thị danh bạ");
            System.out.println("2. Thêm danh bạ");
            System.out.println("3. Sửa danh bạ");
            System.out.println("4. Xóa danh bạ");
            System.out.println("5. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");


            while (!scanner.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
                System.out.print("Nhập lại lựa chọn của bạn: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    contactService.displayContacts();
                    break;
                case 2:
                    contactService.addContact();
                    break;
                case 3:
                    contactService.editContact();
                    break;
                case 4:
                    contactService.deleteContact();
                    break;
                case 5:
                    System.out.println("Đã thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
            }
        } while (choice != 5);
    }
}
