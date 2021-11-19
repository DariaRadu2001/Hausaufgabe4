package KonsoleView;

import Controller.KursController;
import Controller.LehrerController;
import Controller.StudentController;
import Modele.Kurs;
import Modele.Lehrer;
import Modele.Student;
import Exception.DasElementExistiertException;
import java.io.IOException;
import java.util.Scanner;
import Exception.ListIsEmptyException;

public class KonsoleView {

    private KursController kursController;
    private LehrerController lehrerController;
    private StudentController studentController;

    public KonsoleView(KursController kursController, LehrerController lehrerController, StudentController studentController) {
        this.kursController = kursController;
        this.lehrerController = lehrerController;
        this.studentController = studentController;
    }


    public KursController getKursController() {
        return kursController;
    }

    public void setKursController(KursController kursController) {
        this.kursController = kursController;
    }

    public LehrerController getLehrerController() {
        return lehrerController;
    }

    public void setLehrerController(LehrerController lehrerController) {
        this.lehrerController = lehrerController;
    }

    public StudentController getStudentController() {
        return studentController;
    }

    public void setStudentController(StudentController studentController) {
        this.studentController = studentController;
    }

    public void getMenu()
    {
        System.out.println("""
                1.Filter Kurse\s
                2.Sort Kurse\s
                3.Filter Studenten\s
                4.Sort Studenten\s
                5.Register\s
                6.Kurse mit freien Platzen\s
                7.Studenten angemeldet bei einem Kurs\s
                8.Get Kurse\s
                9.Loschen Kurs\s
                10.Andern ects\s
                11.Add Kurs\s
                12.Add Lehrer\s
                13.Add Student\s
                14.Tschuss\s
                """);
    }

    public void start() throws IOException, DasElementExistiertException, DasElementExistiertException, ListIsEmptyException, ListIsEmptyException {
        while(true)
        {
            getMenu();
            Scanner keyboard = new Scanner( System.in );
            int key;
            do {
                System.out.print("Wahlen sie bitte eine Option: ");
                key = keyboard.nextInt();
            }
            while(key<1 && key >14);

            Scanner scan= new Scanner(System.in);
            long id;
            long idKurs;
            switch (key) {
                case 1:
                    System.out.println(kursController.filter());
                    break;

                case 2:
                    kursController.sort();
                    System.out.println(kursController.getAll());
                    break;

                case 3:
                    System.out.println(studentController.filter());
                    break;

                case 4:
                    System.out.println();studentController.sort();
                    break;

                case 5:

                    //kursController.register();
                    break;

                case 6:
                    kursController.getKurseFreiePlatzen();
                    break;

                case 7:
                    System.out.println("ID:");
                    id= scan.nextLong();
                    if(kursController.containsID(id))
                    {
                        studentController.getStudentenAngemeldetBestimmtenKurs(id);
                    }
                    else
                        System.out.println("Das gegebene Kurs existiert nicht.\n");
                    break;

                case 8:
                    kursController.getAll();
                    break;

                case 9:
                    System.out.println("LehrerId:");
                    id= scan.nextLong();
                    System.out.println("KursId:");
                    idKurs = scan.nextLong();
                    if(lehrerController.containsID(id))
                    {

                        Lehrer lehrer = lehrerController.findOne(id);
                        if(lehrerController.containsKurs(lehrer, idKurs))
                        {
                            Kurs kurs = kursController.findOne(idKurs);
                            lehrerController.loschenKurs(lehrer,kurs);
                            lehrerController.deleteKursFromAll(kurs);
                        }
                        else
                            System.out.println("Der Lehrer kann das Kurs nicht löschen.\n");
                    }

                    else
                        System.out.println("Der Lehrer existiert nicht.\n");
                    break;

                case 10:

                    System.out.println("KursId:");
                    idKurs = scan.nextLong();
                    System.out.println("ECTS:");
                    int ects = scan.nextInt();
                    if(kursController.containsID(idKurs))
                    {
                        kursController.andernECTS(ects,idKurs);
                    }
                    break;

                case 11:
                    Kurs kurs = this.createKurs();
                    kursController.create(kurs);
                    break;

                case 12:
                    Lehrer lehrer = this.createLehrer();
                    lehrerController.create(lehrer);
                    break;

                case 13:
                    Student student = this.createStudent();
                    studentController.create(student);
                    break;


                case 14:
                {
                    System.out.println("TSCHUSS!!!");
                    System.exit(0);
                }
            }
        }

    }

    public Student getStudent(Long id)
    {
        return studentController.findOne(id);
    }

    public Kurs getKurs(Long id) throws IOException {
        return kursController.findOne(id);
    }

    public Lehrer getLehrer(Long id) throws IOException {
        return lehrerController.findOne(id);
    }

    public Student createStudent()
    {
        Scanner scan= new Scanner(System.in);
        System.out.println("Vorname:");
        String vorname= scan.nextLine();
        System.out.println("Nachname:");
        String nachname= scan.nextLine();
        Long id = -1L;
        do{
            System.out.println("ID:");
            id= scan.nextLong();
        }while(studentController.containsID(id));

        return new Student(vorname, nachname, id);

    }

    public Lehrer createLehrer()
    {
        Scanner scan= new Scanner(System.in);
        System.out.println("Vorname:");
        String vorname= scan.nextLine();
        System.out.println("Nachname:");
        String nachname= scan.nextLine();
        Long id = -1L;
        do{
            System.out.println("ID:");
            id= scan.nextLong();
        }while(lehrerController.containsID(id));

        return new Lehrer(vorname, nachname, id);

    }


    public Kurs createKurs()
    {
        Scanner scan= new Scanner(System.in);
        System.out.println("Name:");
        String name= scan.nextLine();

        long id = -1L;
        do{
            System.out.println("ID:");
            id= scan.nextLong();
        }while(kursController.containsID(id));

        Long idLehrer = -1L;
        do{
            System.out.println("Lehrer:");
            idLehrer= scan.nextLong();
        }while(!lehrerController.containsID(idLehrer));

        int maximaleAnzahlStudenten = -1;
        do{
            System.out.println("Maximale Anzahl von Studenten:");
            maximaleAnzahlStudenten= scan.nextInt();
        }while(maximaleAnzahlStudenten <= 0);

        int ects = -1;
        do{
            System.out.println("ECTS:");
            ects= scan.nextInt();
        }while(ects <= 0);

        return new Kurs(id, name, idLehrer, maximaleAnzahlStudenten, ects);

    }
}
