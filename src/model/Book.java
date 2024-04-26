package model;

import java.time.LocalDate;

public class Book extends BaseModel {
    private static Integer codeGenerator = 10101;

    private String title;
    private String author;
    private Integer numOfPages;
    private LocalDate publishDate;
    private Double dailyRentCharge;
    private Integer writtenYear;
    private Integer amount;
    private Integer code = codeGenerator++;

    public Book(String title, String author, Integer numOfPages, LocalDate publishDate, Double dailyRentCharge, Integer writtenYear, Integer amount) {
        this.title = title;
        this.author = author;
        this.numOfPages = numOfPages;
        this.publishDate = publishDate;
        this.dailyRentCharge = dailyRentCharge;
        this.writtenYear = writtenYear;
        this.amount = amount;
    }

    public static Integer getCodeGenerator() {
        return codeGenerator;
    }

    public static void setCodeGenerator(Integer codeGenerator) {
        Book.codeGenerator = codeGenerator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(Integer numOfPages) {
        this.numOfPages = numOfPages;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getWrittenYear() {
        return writtenYear;
    }

    public void setWrittenYear(Integer writtenYear) {
        this.writtenYear = writtenYear;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getDailyRentCharge() {
        return dailyRentCharge;
    }

    public void setDailyRentCharge(Double dailyRentCharge) {
        this.dailyRentCharge = dailyRentCharge;
    }

    @Override
    public String toString() {
        return "Book {" +
                " title = '" + title + '\'' +
                " , author = '" + author + '\'' +
                " , numOfPages = " + numOfPages +
                " , publishDate = " + publishDate +
                " , writtenYear = " + writtenYear +
                " , amount = " + amount +
                " , code = " + code +
                " }";
    }
}
