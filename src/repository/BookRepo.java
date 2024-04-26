package repository;

import model.Book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class BookRepo extends BaseRepo<Book>{
    public static final BookRepo bookRepo = new BookRepo();
    public static BookRepo getInstance() {
        return bookRepo;
    }
    private BookRepo() {
    }

    public ArrayList<Book> searchByTitle(String title){
        ArrayList<Book> list = new ArrayList<>();
        for (Book book : getActives()) {
            if (book.getTitle().contains(title)) {
                list.add(book);
            }
        }

        return list;
    }

    public ArrayList<Book> searchByPublishDate(String date){
        if (date.length() == 4) {
            return getByDate(date);
        }

        return getByYear(date);
    }

    private ArrayList<Book> getByYear(String date) {
        ArrayList<Book> list = new ArrayList<>();

        for (Book book : getActives()) {
            int year = Integer.parseInt(date);

            if (book.getPublishDate().getYear() == year) {
                list.add(book);
            }
        }

        return list;
    }

    private ArrayList<Book> getByDate(String date) {
        ArrayList<Book> list = new ArrayList<>();

        for (Book book : getActives()) {
            if (book.getPublishDate().isEqual(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM")))) {
                list.add(book);
            }
        }

        return list;
    }

    public ArrayList<Book> searchByWrittenYear(Integer writtenYear){
        ArrayList<Book> list = new ArrayList<>();

        for (Book book : getActives()) {
            if (book.getWrittenYear().equals(writtenYear)){
                list.add(book);
            }
        }

        return list;
    }

    public void setAmount(UUID bookId, int i) {
        for (Book book : data) {
            if (book.getId().equals(bookId)) {
                book.setAmount(book.getAmount() - i);
                return;
            }
        }
    }

    public Optional<Book> getByCode(int code) {
        for (Book book : getActives()) {
            if (book.getCode().equals(code)){
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }
}
