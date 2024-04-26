package model;

import java.time.LocalDate;
import java.util.UUID;

public class Rent extends BaseModel {
    private String phoneNum;
    private String name;
    private UUID bookId;
    private Double totalPrice;
    private Double fine = 0D;
    private LocalDate dueDate = LocalDate.now();
    private LocalDate closedDate = null;
    private Status status = Status.OPEN;

    public Rent(String phoneNum, String name, UUID bookId, Double totalPrice) {
        this.phoneNum = phoneNum;
        this.name = name;
        this.bookId = bookId;
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Rent { " +
                "closedDate = " + closedDate +
                " , status = " + status +
                " , dueDate = " + dueDate +
                " , fine = " + fine +
                " , totalPrice = " + totalPrice +
                " , bookId = " + bookId +
                " , name = '" + name + '\'' +
                " , phoneNum = '" + phoneNum + '\'' +
                '}';
    }
}
