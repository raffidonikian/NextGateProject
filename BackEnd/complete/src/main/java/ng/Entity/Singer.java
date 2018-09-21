package ng.Entity;

public class Singer {

    private int id;
    private String name;
    private String dob;
    private String sex;

    public Singer(int id, String name, String dob, String sex) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.sex = sex;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
