package main.java.ru.autobase.dao;

import java.util.List;

public interface GeneralDAO<Entity, Key> {
    void create(Entity newInstance);
    Entity getById(Key id);
    List<Entity> getAll();
    void update(Entity instance);
    void delete(Key key);
}
