package repository;

import model.BaseModel;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseRepo<T extends BaseModel> {
    protected final ArrayList<T> data = new ArrayList<>();

    public void add(T t){
        data.add(t);
    }

    public void delete(UUID id){
        for (T t : data) {
            if (t.getId().equals(id)) {
                t.setActive(false);
                return;
            }
        }
    }

    public void update(UUID id, T t){
        int i = 0;
        for (T datum : data) {
            if (datum.getId().equals(id)){
                t.setId(id);
                data.set(i, t);
                return;
            }
            i++;
        }
    }

    public Optional<T> findById(UUID id) {
        for (T t : data) {
            if (t.getId().equals(id)){
                return Optional.of(t);
            }
        }

        return Optional.empty();
    }

    public ArrayList<T> getActives(){
        ArrayList<T> list = new ArrayList<>();
        for (T t : data) {
            if (t.isActive()){
                list.add(t);
            }
        }

        return list;
    }
}
