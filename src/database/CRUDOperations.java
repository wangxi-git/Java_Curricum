package database;

public interface CRUDOperations {
    void create(String sql);

    void read(String sql);

    void update(String sql);

    void delete(String sql);
}
