import java.time.LocalDate;

public class Aluno implements Ativavel {
    private String nome;
    private String dataNascimento;
    private Turma turma;
    private boolean ativo;

    public Aluno(String nome, String dataNascimento, Turma turma) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.turma = turma;
        this.ativo = true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", turma=" + turma +
                '}';
    }


}
