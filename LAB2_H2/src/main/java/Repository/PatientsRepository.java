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

public class PatientsRepository implements IPatientRepository {
    public EntityManager em =
            Persistence.createEntityManagerFactory("TEST").createEntityManager();
    @Override
    public void Insert(Patients patients) throws SQLException {
        String str = String.format("INSERT INTO patients (surname, name, ot, id_doctor) VALUES (?, ?, ?,?)");
        try (Connection conn = connectToDB();
             PreparedStatement statement = conn.prepareStatement(str, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, patients.getName());
            statement.setString(2, patients.getSurname());
            statement.setString(3, patients.getOt());
            statement.setInt(4, patients.getIdDoctors().getId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    patients.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to get generated id for client.");
                }
            }
        }
    }

    @Override
    public void Delete(Patients client) throws SQLException {
        String str = String.format("DELETE FROM patients where id = %s" , client.getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public void Update(Patients client, int id) throws SQLException {
        String str = String.format("UPDATE patients SET id = %s, surname = '%s', name = '%s', ot = '%s', idStylists = %s WHERE id = %s" ,
                client.getId(),
                client.getSurname(),
                client.getName(),
                client.getOt(),
                client.getIdDoctors().getId(),
                id);
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public List<Patients> getList() {
        TypedQuery<Patients> namedQuery = em.createNamedQuery("Patients.getAll", Patients.class);
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