package next.dao;

import next.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface GetValues {
    void getValues(List<User> users, ResultSet rs) throws SQLException;
}
