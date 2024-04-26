package controller;

import service.BookService;
import service.RentService;

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
}