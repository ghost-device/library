package controller;

import model.Book;
import model.Rent;
import model.Status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static controller.Main.*;

public class RentController {
    public static void rentMenu() {
        System.out.println("""
                1. Open
                2. Close
                3. The Reader of The Month (top 3)
                4. Top 3 Readers of past 30 days
                5. Top 3 Readers of all time
                6. Show open rents
                7. Show closed rents
                0. Back""");

        int ans = scanNum.nextInt();

        switch (ans){
            case 1 -> open();
            case 2 -> close();
            case 3 -> top3UsersOfMonth();
            case 4 -> top3UsersOf30Days();
            case 5 -> top3UsersOfAllTime();
            case 6 -> showOpen();
            case 7 -> showClose();
            case 0 -> mainMenu();
            default -> {
                System.out.println("Choose correct!");
                rentMenu();
            }
        }
    }

    private static void close() {
        System.out.print("Enter phone -> ");
        String phone = scanStr.nextLine();

        ArrayList<Rent> rentsByPhoneAndStatus = rentService.getRentsByPhoneAndStatus(phone, Status.OPEN);

        if (rentsByPhoneAndStatus.isEmpty()) {
            System.out.println("Not found!");
            rentMenu();
        }

        int i = 1;
        for (Rent rent : rentsByPhoneAndStatus) {
            System.out.println(i++ + ". " + rent);
        }

        System.out.print("Choose -> ");
        int ans = scanNum.nextInt() - 1;

        Rent rent = rentService.getRentsByPhoneAndStatus(phone, Status.OPEN).get(ans);
        System.out.println(rentService.closeRent(rent.getId()));

        bookService.setAmountOfBook(rent.getBookId(), -1);

        rentMenu();
    }

    private static void open() {
        System.out.print("Enter book code -> ");
        int code = scanNum.nextInt();

        Book book = bookService.getByCode(code);

        if (Objects.isNull(book)) {
            System.out.println("Not Found!");
            open();
        }

        Rent rent = rentCreate(book.getId());
        bookService.setAmountOfBook(book.getId(), 1);

        rentMenu();
    }

    private static Rent rentCreate(UUID bookId) {
        System.out.print("Enter phone -> ");
        String phone = scanStr.nextLine();
        System.out.print("Enter name -> ");
        String name = scanStr.nextLine();
        System.out.print("Enter due date(dd/MM/yyyy) -> ");
        String dueDate = scanStr.nextLine();

        LocalDate lcd = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        double amount = rentService.getDaysCount(LocalDate.now(), lcd) * bookService.findById(bookId).getDailyRentCharge();

        System.out.println("Amount: " + amount + "\n" +
                "1. Next\n" +
                "0. Back");

        int ans = scanNum.nextInt();

        if (ans == 0) rentMenu();

        return new Rent(phone, name, bookId, amount);
    }

    private static void top3UsersOfAllTime() {
        for (String s : rentService.getTop3UsersOfAllTime()) {
            System.out.println(s);
        }

        rentMenu();
    }

    private static void top3UsersOf30Days() {
        for (String s : rentService.getTop3UsersOfLast30Day()) {
            System.out.println(s);
        }

        rentMenu();
    }

    private static void top3UsersOfMonth() {
        for (String s : rentService.getTop3UsersOfMonth()) {
            System.out.println(s);
        }

        rentMenu();
    }

    private static void showClose() {
        for (Rent rent : rentService.getRentsByStatus(Status.CLOSED)) {
            System.out.println(rent);
        }

        rentMenu();
    }

    private static void showOpen() {
        for (Rent rent : rentService.getRentsByStatus(Status.OPEN)) {
            System.out.println(rent);
        }

        rentMenu();
    }
}
