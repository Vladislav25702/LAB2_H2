package Repository;
import entity.*;
import java.sql.*;
import java.util.List;

public interface DoctorRepository {
    Integer Insert(Doctors doctor) throws SQLException;
    void Delete(Doctors doctor) throws SQLException;
    void Update(Doctors doctor, int id) throws SQLException;

    List<Doctors> getList() throws  SQLException;
    Connection connectToDB() throws SQLException;
    Statement getStatement(Connection conn) throws SQLException;
}