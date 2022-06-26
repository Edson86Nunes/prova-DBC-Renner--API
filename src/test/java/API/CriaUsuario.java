package API;

public class CriaUsuario {

    private String name;
    private String job;

    public CriaUsuario(){}

    public CriaUsuario(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
