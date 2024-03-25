import Repository.DoctorsRepository;
import Repository.PatientsRepository;
import Repository.Pr;
import entity.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        PatientsRepository patientsRepo = new PatientsRepository();
        DoctorsRepository doctorsRepo = new DoctorsRepository();

        List<Patients> patients = patientsRepo.getList();
        List<Doctors> doctors = doctorsRepo.getList();

        for (Patients patient : patients) {
            Pr.printPatientsDoctors(patient);

        }
    }
}
