package entity;
//import javax.persistence.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Doctors")
@NamedQuery(name = "Doctors.getAll", query = "SELECT s from Doctors s")
public class Doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "surname", length = 255)
    private String surname;

    @Column(name = "ot", length = 255)
    private String ot;

    public Doctors(Integer id, String name,String surname,String ot) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.ot = ot;
    }
    public Doctors() {}
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }
}