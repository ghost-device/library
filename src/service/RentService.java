package service;

import model.Rent;
import model.Status;
import repository.RentRepo;

import java.time.LocalDate;
import java.util.*;

public class RentService extends BaseService<Rent, RentRepo> {
    public static final RentService RENT_SERVICE = new RentService();
    public static RentService getInstance() {
        return RENT_SERVICE;
    }
    public RentService() {
        super(RentRepo.getInstance());
    }

    private final BookService bookService = BookService.getInstance();

    public ArrayList<Rent> getRentsByPhone(String phone){
        return repo.getByPhone(phone);
    }

    public String closeRent(UUID rentId){
        LocalDate now = LocalDate.now();
        Rent rent = findById(rentId);
        if (!rent.getDueDate().isBefore(now)){
            return "Fine: " + getDaysCount(rent.getDueDate(), now) *
                    bookService.findById(rent.getBookId()).getAmount();
        }

        repo.close(rentId);
        return "Successfully!";
    }

    public int getDaysCount(LocalDate date1, LocalDate date2){
        int i = 0;
        while (date1.isBefore(date2)){
            i++;
            date1 = date1.plusDays(1);
        }

        return i;
    }

    public ArrayList<Rent> getRentsByStatus(Status status){
        return repo.getByStatus(status);
    }

    public ArrayList<String> getTop3UsersOfAllTime(){
        HashMap<String, Integer> map = new HashMap<>();

        for (Rent rent : getActives()) {
            map.put(rent.getPhoneNum(), map.getOrDefault(rent.getPhoneNum(), 0) + 1);
        }

        List<Map.Entry<String, Integer>> list1 = new ArrayList<>(map.entrySet());

        list1.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        return new ArrayList<>(List.of(list1.getFirst().getKey(), list1.get(1).getKey(), list1.get(2).getKey()));
    }

    public ArrayList<String> getTop3UsersOfMonth(){
        Map<String, Integer> map = new HashMap<>();

        for (Rent rent : getActives()) {
            if (rent.getCreatedAt().getMonth() == LocalDate.now().getMonth()){
                map.put(rent.getPhoneNum(), map.getOrDefault(rent.getPhoneNum(), 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        return new ArrayList<>(List.of(list.get(0).getKey(), list.get(1).getKey(), list.get(2).getKey()));
    }

    public ArrayList<String> getTop3UsersOfLast30Day(){
        Map<String, Integer> map = new HashMap<>();

        for (Rent rent : getActives()) {
            if (rent.getCreatedAt().minusDays(30).isAfter(LocalDate.now())){
                map.put(rent.getPhoneNum(), map.getOrDefault(rent.getPhoneNum(), 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        return new ArrayList<>(List.of(list.get(0).getKey(), list.get(1).getKey(), list.get(2).getKey()));
    }
}
