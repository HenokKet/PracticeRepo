package learn.memories.data;

import learn.memories.models.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserRepository {
    List<User> findAll() throws DataAccessException;
    User findById(int userId) throws DataAccessException;
    User findByUsername(String username) throws DataAccessException;
    User add(User user) throws DataAccessException;
    boolean update(User user) throws DataAccessException;
    boolean deleteById(int userId) throws DataAccessException;
}
