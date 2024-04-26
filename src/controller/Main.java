package controller;

import model.Book;
import model.Rent;
import service.BookService;
import service.RentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final Scanner scanNum = new Scanner(System.in);
    public static final Scanner scanStr = new Scanner(System.in);

    public static final BookService bookService = BookService.getInstance();
    public static final RentService rentService = RentService.getInstance();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu(){
        System.out.println("1. Book Menu\n" +
                           "2. Rent Menu");

        int ans = scanNum.nextInt();

        switch (ans){
            case 1 -> BookController.bookMenu();
            case 2 -> RentController.rentMenu();
            default -> {
                System.out.println("Choose correct!");
                mainMenu();
            }
        }
    }

    static {
        bookService.add(new Book("salom", "Said4ik", -22, LocalDate.parse("12/12/2022",DateTimeFormatter.ofPattern("dd/MM/yyyy")), 50D, 1990, 4));
        bookService.add(new Book("assalom", "Oybek", 22, LocalDate.parse("12/01/2022",DateTimeFormatter.ofPattern("dd/MM/yyyy")), 10D, 1990, 7));
        ArrayList<Book> books = bookService.getActives();
        rentService.add(new Rent("Aziz", "sa12", books.get(0).getId(), 100D));
        rentService.add(new Rent("Said", "seafda", books.get(1).getId(), 200D));
        rentService.add(new Rent("Ali", "sasdea", books.get(0).getId(), 300D));
        rentService.add(new Rent("Xon", "svmmva", books.get(1).getId(), 1400D));
        rentService.add(new Rent("Vali", "sddioea", books.get(1).getId(), 1300D));
        rentService.add(new Rent("Aziz", "sddioea", books.get(1).getId(), 1300D));
        rentService.add(new Rent("Ali", "sddioea", books.get(0).getId(), 1300D));
    }
}