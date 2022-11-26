package poofinal.entities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Student extends Pessoa implements Serializable {
    private String password;
    private String matriculation;
    private LocalDate joined;
    private LocalDate graduationForecast;
    private String email;
    private List<Activities> listActivities = new ArrayList<>();
    private Course course;

    public Student(String password, String matriculation, LocalDate joined, LocalDate graduationForecast, String name, String email, Course course) {
        super(name);
        this.password = password;
        this.matriculation = matriculation;
        this.joined = joined;
        this.graduationForecast = graduationForecast;
        this.email = email;
        this.course = course;
    }

    public String getMatriculation() {
        return matriculation;
    }

    public String getPassword() {
        return password;
    }

    public String getJoined() {
        return joined.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }

    public String getGraduationForecast() {
        return graduationForecast.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }

    public String getEmail() {
        return email;
    }

    public Course getCourse() {
        return course;
    }

    public void setActivities(Activities activitie) throws IOException {
        listActivities.add(activitie);
        var path = Path.of("src\\main\\resources\\files\\studentActivities\\" + getName() + "\\");
        if(!Files.exists(path)) {
            Files.createDirectories(path);
        }
            var file = new File(path + "\\" + activitie.getDescription() + checkExistsActivitie(activitie) + ".dat");
            var outStream = new ObjectOutputStream(new FileOutputStream(file));
            outStream.writeObject(activitie);
            outStream.close();
    }

    public void loadFilesActivities() throws IOException, ClassNotFoundException {
        var path = new File("src\\main\\resources\\files\\studentActivities\\" + getName() + "\\");
        if (path.exists() && path.isDirectory()) {
            File listFiles[] = path.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                var inStream = new ObjectInputStream(new FileInputStream("src\\main\\resources\\files\\studentActivities\\" + getName() + "\\" + listFiles[i].getName()));
                Activities activities = (Activities) inStream.readObject();
                inStream.close();
                listActivities.add(activities);
            }
        }
    }

    public List<Activities> getListActivities(){
        return listActivities;
    }

    public String checkExistsActivitie(Activities activitie){
        var path = new File("src\\main\\resources\\files\\studentActivities\\" + getName() + "\\");
        if (path.exists() && path.isDirectory()) {
            File listFiles[] = path.listFiles();
            int j = 1;
            String[] split = new String[2];
            for (int i = 0; i < listFiles.length; i++) {
                if(listFiles[i].getName().contains(activitie.getDescription())) {
                    split = listFiles[i].getName().split("\\.");
                    j = Integer.parseInt(String.valueOf(split[0].charAt(split[0].length() - 1)));
                    j++;
                }
            }
            return String.valueOf(j);
        }
        return "";
    }
}
