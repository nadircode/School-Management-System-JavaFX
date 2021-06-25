package BasicClass;

public class Students {
    private String firstname;
    private String lastname;
    private String emailS;
    private String id;
    private String datebirth;
    private String wilaya;
    private String level;
    private String sexe;
    public Students(String RN , String fname , String lname , String levelS){
        this.id = RN;
        this.firstname = fname;
        this.lastname = lname;
        this.level = levelS;
    }
    public Students(String id){
        this.id = id;
    }
    public Students(String ID ,String fname , String lname , String datebirth , String wilaya , String level , String emailS , String sexe ){
        this.id = ID;
        this.firstname = fname;
        this.lastname = lname;
        this.level = level;
        this.wilaya = wilaya;
        this.level = level ;
        this.emailS = emailS ;
        this.sexe = sexe;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLevel() {
        return level;
    }

    public String getDatebirth() {
        return datebirth;
    }

    public String getWilaya() {
        return wilaya;
    }

    public String getEmailS() {
        return emailS;
    }

    public String getSexe() {
        return sexe;
    }

    public void setDatebirth(String datebirth) {
        this.datebirth = datebirth;
    }

    public void setEmailS(String emailS) {
        this.emailS = emailS;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
