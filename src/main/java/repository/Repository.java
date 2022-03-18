package repository;

import model.Entity;

public interface Repository<E extends Entity> {

    void save(E e);

}
