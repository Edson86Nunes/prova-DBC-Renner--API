package API;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)  // ignora propriedades não mapeadas que vem quando extrai um Json

public class Usuario {

    @JsonAlias("first_name")  // assim que vem da API
    private String name;
    @JsonAlias("last_name")
    private String lastname;
    private String job;

    public Usuario(){}

    public Usuario(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getLastname() {
        return lastname;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getJob() {
        return job;
    }
}
