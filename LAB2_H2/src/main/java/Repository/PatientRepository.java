package Repository;
import entity.*;
import java.sql.*;
import java.util.List;

public interface PatientRepository {
    Integer Insert(Patients doctor) throws SQLException;
    void Delete(Patients doctor) throws SQLException;
    void Update(Patients doctor, int id) throws SQLException;

    List<Patients> getList() throws  SQLException;
    Connection connectToDB() throws SQLException;
    Statement getStatement(Connection conn) throws SQLException;
}