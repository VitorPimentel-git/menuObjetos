import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {
        menuPrincipal();
    }
    public static void menuPrincipal(){
        System.out.println("\n==== Menu Principal ====");
        System.out.println("1 - Alunos");
        System.out.println("2 - Turmas");
        System.out.println("3 - Sair");
        String opcao = Leitura.dados("Digite a opção desejada: ");
        switch (opcao){
            case "1":
                menuAlunos();
                break;
            case "2":
                menuTurmas();
                break;
            case "3":
                System.out.println("Saindo...");
            default:
                System.out.println("Opção Inválida. Tente novamente: ");
                menuPrincipal();
        }

    }

    private static void menuTurmas() {
        System.out.println("\n==== Menu de Turmas ====");
        System.out.println("1 - Listar Turmas");
        System.out.println("2 - Cadastrar Turma");
        System.out.println("3 - Atualizar Turma");
        System.out.println("4 - Excluir Turma");
        System.out.println("5 - Voltar ao Menu Principal");
        String opcao = Leitura.dados("Digite a opção desejada: ");
        switch(opcao){
            case "1":
                listarTurmas();
                menuTurmas();
                break;
            case "2":
                cadastrarTurma();
                menuTurmas();
                break;
            case "3":
                atualizarTurma();
                menuTurmas();
                break;
            case "4":
                excluirTurma();
                menuTurmas();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção Inválida. Tente novamente: ");
                menuTurmas();
        }
    }

    private static void menuAlunos() {
        System.out.println("\n==== Menu de Alunos ====");
        System.out.println("1 - Listar Alunos");
        System.out.println("2 - Cadastrar Aluno");
        System.out.println("3 - Atualizar Aluno");
        System.out.println("4 - Excluir Aluno");
        System.out.println("5 - Voltar ao Menu Principal");
        String opcao = Leitura.dados("Digite a opção desejada: ");
        switch(opcao){
            case "1":
                listarAlunos();
                menuAlunos();
                break;
            case "2":
                cadastrarAluno();
                menuAlunos();
                break;
            case "3":
                atualizarAluno();
                menuAlunos();
                break;
            case "4":
                excluirAluno();
                menuAlunos();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção Inválida. Tente novamente: ");
                menuAlunos();
        }
    }

    private static void excluirTurma() {
       listarTurmasIndiceSigla();
       String opcao = Leitura.dados("Digite o número da turma que deseja excluir: ");
       int opcaoValida = -1;
       while (validarOpcaoExcluir(opcao)==-1){
           System.out.println("Opção inválida! Digite novamente: ");
           opcao = Leitura.dados("Digite o número da turma que deseja excluir: ");
       }
    }

    private static int validarOpcaoExcluir(String opcao) {
        if (opcao.isBlank()) return -1;

        int opcaoNumero = -1;

        try{
            opcaoNumero = Integer.parseInt(opcao);
        }catch(NumberFormatException e){
            return -1;
        }

        int indiceLista = opcaoNumero;
        return indiceLista >= 0 && listaTurmas.size() > indiceLista ? indiceLista : -1;
    }

    private static void listarTurmasIndiceSigla() {
        System.out.println("\nLista das Turmas");
        for (int i = 0;i<listaTurmas.size();i++){
            System.out.printf("\n%d - %s", i+1, listaTurmas.get(i).getSigla());

        }
    }

    private static void atualizarTurma() {

    }

    private static void cadastrarTurma() {
        Periodo periodo = validarPeriodo();

        String curso = Leitura.dados("Digite o curso: ");
        while(!isCharacter(curso)) {
            System.out.println("Nome de curso inválido! Favor não utilizar números ou caracter especiais");
            curso = Leitura.dados("Digite o curso: ");
        }
        String sigla = Leitura.dados("Digite a sigla: ");
        while(!validarSigla(sigla)){
            System.out.println("Sigla inválida! É necessário conter texto e não é permitido ser repetida");
            sigla = Leitura.dados("Digite a sigla: ");
        }

        Turma turma = new Turma(curso, sigla, periodo);

        listaTurmas.add(turma);
    }

    private static boolean validarSigla(String sigla) {
        if(sigla.isBlank()) return false;

        for(Turma turma : listaTurmas){
            if(turma.getSigla().equals(sigla)){
                return false;
            }
        }
        return true;
    }

    private static boolean isCharacter(String texto) {
        String textoSemNumeros = texto.replaceAll("\\d", "");
        return !texto.isBlank() && texto.equals(textoSemNumeros);

    }

    private static Periodo validarPeriodo() {
        String opcaoPeriodo = Leitura.dados("""
                Digite o número do periodo:
                1 - Matutino
                2 - Vespertino
                3 - Noturno
                4 - Integral
                """);
        switch (opcaoPeriodo) {
            case "1":
                return Periodo.MATUTINO;
            case "2":
                return Periodo.VESPERTINO;
            case "3":
                return Periodo.NOTURNO;
            case "4":
                return Periodo.INTEGRAL;
            default:
                System.out.println("Opcão inválida. Digite novamente: ");
                return validarPeriodo();
        }
    }

    private static void listarTurmas() {
        if(listaTurmas.isEmpty()){
            System.out.println("Não há turmas cadastradas");
            return;
        }
        for(Turma t : listaTurmas) {
            System.out.println(t);
        }
    }

    private static void excluirAluno() {
    }

    private static void atualizarAluno() {

    }

    private static void cadastrarAluno() {

    }

    private static void listarAlunos() {
        if(listaAlunos.isEmpty()){
            System.out.println("Não há alunos cadastrados");
            return;
        }
        for(Aluno a : listaAlunos) {
            System.out.println(a);
        }
    }
}
