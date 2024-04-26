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

    public ArrayList<Rent> getRentsByPhoneAndStatus(String phone, Status status){
        return repo.getByPhoneAndStatus(phone, status);
    }

    public String closeRent(UUID rentId){
        LocalDate now = LocalDate.now();
        Rent rent = findById(rentId);
        if (!rent.getDueDate().isBefore(now.plusDays(1))){
            repo.close(rentId);
            return "Fine: " + getDaysCount(rent.getDueDate(), now) *
                    bookService.findById(rent.getBookId()).getDailyRentCharge();
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
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        ArrayList<String> topUsers = new ArrayList<>();
        for (int i = 0; i < Math.min(3, list1.size()); i++) {
            topUsers.add(list1.get(i).getKey());
        }

        return topUsers;
    }

    public ArrayList<String> getTop3UsersOfMonth(){
        Map<String, Integer> map = new HashMap<>();

        for (Rent rent : getActives()) {
            if (rent.getCreatedAt().getMonth() == LocalDate.now().getMonth()){
                map.put(rent.getPhoneNum(), map.getOrDefault(rent.getPhoneNum(), 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list1 = new ArrayList<>(map.entrySet());

        list1.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        ArrayList<String> topUsers = new ArrayList<>();
        for (int i = 0; i < Math.min(3, list1.size()); i++) {
            topUsers.add(list1.get(i).getKey());
        }

        return topUsers;
    }

    public ArrayList<String> getTop3UsersOfLast30Day(){
        Map<String, Integer> map = new HashMap<>();

        for (Rent rent : getActives()) {
            if (rent.getCreatedAt().minusDays(30).isAfter(LocalDate.now())){
                map.put(rent.getPhoneNum(), map.getOrDefault(rent.getPhoneNum(), 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list1 = new ArrayList<>(map.entrySet());

        list1.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        ArrayList<String> topUsers = new ArrayList<>();
        for (int i = 0; i < Math.min(3, list1.size()); i++) {
            topUsers.add(list1.get(i).getKey());
        }

        return topUsers;        }
}
