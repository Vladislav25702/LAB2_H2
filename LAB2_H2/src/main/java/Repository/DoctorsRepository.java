package Repository;

import entity.*;
import jakarta.persistence.*;

//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorsRepository implements DoctorRepository {
    public EntityManager em =
            Persistence.createEntityManagerFactory("TEST").createEntityManager();
@Override
public Integer Insert(Doctors doctors) throws SQLException {
        String str = String.format("INSERT INTO doctors (surname, name, ot) VALUES (%s, '%s', '%s')",
        doctors.getSurname(),
        doctors.getName(),
        doctors.getOt());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
        try (ResultSet rs = this.getStatement(this.connectToDB()).executeQuery("SELECT MAX(id) FROM Stylists")) {
        while (rs.next()) {
        return rs.getInt(1);
        }
        return -1;
        }
        }

@Override
public void Delete(Doctors doctors) throws SQLException {
        String str = String.format("DELETE FROM doctors where id = %s" , doctors.getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
        }
@Override
public void Update(Doctors doctors, int id) throws SQLException {
        String str = String.format("UPDATE doctors SET id = %s, surname = '%s', name = '%s', ot = '%s' WHERE id = %s" ,
        doctors.getId(),
        doctors.getSurname(),
        doctors.getName(),
        doctors.getOt(),
        doctors.getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();

        }

        @Override
        public List<Doctors> getList() {
                TypedQuery<Doctors> namedQuery = em.createNamedQuery("Doctors.getAll", Doctors.class);
                return namedQuery.getResultList();
        }
@Override
public Connection connectToDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/mem:mydb", "sa", "");
        if (conn==null) {
        System.out.println("Error with connection with DataBase!");
        System.exit(0);
        }
        return conn;
        }
@Override
public Statement getStatement(Connection conn) throws SQLException {
        return conn.createStatement();
        }
public void closeConnection(Statement stmt) throws SQLException {
        stmt.close();
        }
        }
