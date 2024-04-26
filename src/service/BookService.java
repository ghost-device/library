package service;

import model.Book;
import repository.BookRepo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

public class BookService extends BaseService<Book, BookRepo> {
    private static final BookService projectService = new BookService();
    public static BookService getInstance() {
        return projectService;
    }
    public BookService() {
        super(BookRepo.getInstance());
    }

    public ArrayList<Book> searchBooksByTitle(String title){
        ArrayList<Book> list = repo.searchByTitle(title);

        list.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getPublishDate().compareTo(o2.getPublishDate());
            }
        });

        return list;
    }

    public ArrayList<Book> searchBooksByPublishDate(String date){
        return repo.searchByPublishDate(date);
    }

    public ArrayList<Book> searchBooksByWrittenYear(Integer writtenYear){
        return repo.searchByWrittenYear(writtenYear);
    }

    public void setAmountOfBook(UUID bookId, int i){
        repo.setAmount(bookId, i);
    }

    public ArrayList<Book> readBook(){
        ArrayList<Book> list = new ArrayList<>();
        for (Book book : getActives()) {
            if (book.getAmount() > 0) {
                list.add(book);
            }
        }

        return list;
    }

    public Book getByCode(int code) {
        return repo.getByCode(code).get();
    }
}
