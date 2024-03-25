package entity;

import jakarta.persistence.*;

//import javax.persistence.*;

@Entity
@Table(name = "Patients")
@NamedQuery(name = "Patients.getAll", query = "SELECT c from Patients c")
public class Patients {

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
    @ManyToOne
    @JoinColumn(name = "id_Doctor")
    private Doctors idDoctors;


    public Patients( String name, String surname, String ot, Doctors idDoctors) {
        this.name = name;
        this.surname = surname;
        this.ot = ot;
        this.idDoctors = idDoctors;
    }
    public Patients() {}
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

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Doctors getIdDoctors() {
        return idDoctors;
    }

    public void setIdDoctors(Doctors idDoctors) {
        this.idDoctors = idDoctors;
    }
}