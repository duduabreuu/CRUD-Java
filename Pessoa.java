import java.time.Year;

public class Pessoa {

    private int id;
    private String nome;
    private int anoNascimento;
    private String hobby;

    public Pessoa(int id, String nome, int anoNascimento, String hobby) {
        this.id = id;
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public String getHobby() {
        return hobby;
    }

    public int getIdade() {
        return Year.now().getValue() - anoNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

     public String toCSV() {
        return id + ";" + nome + ";" + anoNascimento + ";" + hobby;
    }

    @Override
    public String toString() {
        return "\nID: " + id +
               "\nNome: " + nome +
               "\nIdade: " + getIdade() +
               "\nHobby: " + hobby +
               "\n---------------------";
    }
}
