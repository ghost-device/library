package controller;
import model.Book;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static controller.Main.*;

public class BookController {
    public static void bookMenu() {
        System.out.println("""
                1. Search by title
                2. Search by publish date
                3. Search by written year
                4. Create
                5. Read
                6. Update
                7. Delete
                0. Back""");

        int ans = scanNum.nextInt();

        switch (ans){
            case 1 -> searchByTitle();
            case 2 -> searchByPublishDate();
            case 3 -> searchByWrittenDay();
            case 4 -> create();
            case 5 -> {read(); bookMenu();}
            case 6 -> update();
            case 7 -> delete();
            case 0 -> mainMenu();
            default -> {
                System.out.println("Choose correct!");
                bookMenu();
            }
        }
    }

    private static void create(){
        bookService.add(bookCreate());

        System.out.println("Successfully!");

        bookMenu();
    }

    public static ArrayList<Book> read(){
        int i = 1;
        for (Book book : bookService.readBook()) {
            System.out.println(i++ + ". " + book);
        }

        return bookService.getActives();
    }

    private static void update(){
        ArrayList<Book> list = read();

        System.out.print("Choose -> ");
        int choice = scanNum.nextInt() - 1;

        Book book = bookCreate();

        bookService.update(list.get(choice).getId(), book);

        bookMenu();
    }

    private static void delete(){
        ArrayList<Book> list = read();

        System.out.print("Choose -> ");
        int choice = scanNum.nextInt() - 1;

        bookService.delete(list.get(choice).getId());

        bookMenu();
    }

    private static Book bookCreate() {
        System.out.print("Enter title -> ");
        String title = scanStr.nextLine();
        System.out.print("Enter author -> ");
        String author = scanStr.nextLine();
        System.out.print("Enter num of pages -> ");
        int pages = scanNum.nextInt();
        System.out.print("Enter publish date(dd/MM/yyyy) -> ");
        String publishDate = scanStr.nextLine();
        System.out.print("Enter daily Rent Charge -> ");
        Double dailyRentCharge = scanNum.nextDouble();
        System.out.print("Enter written year -> ");
        int writtenYear = scanNum.nextInt();
        System.out.print("Enter amount -> ");
        int amount = scanNum.nextInt();

        LocalDate lcd = LocalDate.parse(publishDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return new Book(title, author, pages, lcd, dailyRentCharge,writtenYear, amount);
    }

    private static void searchByWrittenDay() {
        System.out.print("Enter written day -> ");
        Integer day = scanNum.nextInt();

        for (Book book : bookService.searchBooksByWrittenYear(day)) {
            System.out.println(book);
        }

        bookMenu();
    }

    private static void searchByPublishDate() {
        System.out.print("Enter publish date (YYYY or YYYY/MM) -> ");
        String date = scanStr.nextLine();

        for (Book book : bookService.searchBooksByPublishDate(date)) {
            System.out.println(book);
        }

        bookMenu();
    }

    private static void searchByTitle() {
        System.out.print("Enter title -> ");
        String title = scanStr.nextLine();

        for (Book book : bookService.searchBooksByTitle(title)) {
            System.out.println(book);
        }

        bookMenu();
    }
}
