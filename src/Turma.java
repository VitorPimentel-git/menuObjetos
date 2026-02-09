public class Turma {
    private String curso;
    private String sigla;
    private Periodo periodo;

    public Turma(String curso, String sigla, Periodo periodo) {
        this.curso = curso;
        this.sigla = sigla;
        this.periodo = periodo;
    }

    public Turma() {
        this.curso = "";
        this.sigla = "";
        this.periodo = Periodo.MATUTINO;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
