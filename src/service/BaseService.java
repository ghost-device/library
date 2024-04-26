package service;

import model.BaseModel;
import repository.BaseRepo;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseService<T extends BaseModel, R extends BaseRepo<T>> {
    protected R repo;

    public BaseService(R repo) {
        this.repo = repo;
    }

    public void add(T t){
        repo.add(t);
    }

    public void delete(UUID id){
        repo.delete(id);
    }

    public void update(UUID id, T t){
        repo.update(id, t);
    }

    public T findById(UUID id) {
        return repo.findById(id).get();
    }

    public ArrayList<T> getActives(){
        return repo.getActives();
    }
}
