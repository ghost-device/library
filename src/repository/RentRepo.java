package repository;

import model.Rent;
import model.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class RentRepo extends BaseRepo<Rent> {
    public static final RentRepo rentRepo = new RentRepo();
    public static RentRepo getInstance() {
        return rentRepo;
    }
    public RentRepo() {
    }

    public ArrayList<Rent> getByPhoneAndStatus(String phone, Status status){
        ArrayList<Rent> list = new ArrayList<>();
        for (Rent rent : getActives()) {
            if (rent.getPhoneNum().equals(phone) && rent.getStatus() == status) {
                list.add(rent);
            }
        }

        return list;
    }

    public void close(UUID id){
        for (Rent rent : getActives()) {
            if (rent.getId().equals(id)) {
                rent.setStatus(Status.CLOSED);
                rent.setClosedDate(LocalDate.now());
                return;
            }
        }
    }

    public ArrayList<Rent> getByStatus(Status status){
        ArrayList<Rent> list = new ArrayList<>();
        for (Rent rent : getActives()) {
            if (rent.getStatus() == status) {
                list.add(rent);
            }
        }

        return list;
    }
}
