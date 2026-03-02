import java.util.ArrayList;

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
                System.exit(0);
                break;
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
        if(isVazio(listaTurmas)){
            System.out.println("Não há turmas cadastradas");
            return;
        }
       listarTurmasIndiceSigla();

        int idExcluir = validaIdTurma();

       if (confirmaExclusao()){
//           listaTurmas.remove(opcaoUsuario);
           listaTurmas.get(idExcluir).setAtivo(false);
           System.out.println("Turma Excluída com sucesso!!");
       }
       else{
           System.out.println("Operação Cancelada!!");
       }


    }

    private static boolean isVazio(ArrayList<Turma> listaTurmas) {
        if(listaTurmas.isEmpty()) return true;

        for (Turma turma : listaTurmas){
            if(turma.isAtivo()) return false;

        }

        return true;
    }

    private static boolean confirmaExclusao() {
        while (true) {
            String confirma = Leitura.dados("Você tem certeza que deseja excluir? (S/N): ").toUpperCase();
            switch (confirma) {
                case "S":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Opção Inválida. Favor digitar S para excluir ou N para não!");
                    break;
            }
        }
    }

    private static int validarItemLista(String opcao) {
        if (opcao.isBlank()) return -1;

        int opcaoNumero = -1;

        try{
            opcaoNumero = Integer.parseInt(opcao);
        }catch(NumberFormatException e){
            return -1;
        }

        int indiceLista = opcaoNumero-1;
        return indiceLista >= 0 && listaTurmas.size() > indiceLista ? indiceLista : -1;
    }

    private static void listarTurmasIndiceSigla() {
        System.out.println("\nLista das Turmas");
        for (int i = 0;i<listaTurmas.size();i++){
            if(listaTurmas.get(i).isAtivo())
            System.out.printf("\n%d - %s", i+1, listaTurmas.get(i).getSigla());

        }
    }

    private static void atualizarTurma() {
        if(isVazio(listaTurmas)){
            System.out.println("Não há turmas cadastradas");
            return;
        }
        listarTurmasIndiceSigla();

        int idAtualizar = validaIdTurma();

        System.out.printf("O período atual é: %s\n", listaTurmas.get(idAtualizar).getPeriodo());

        atualizarPeriodo(idAtualizar);
    }

    private static void atualizarPeriodo(int idAtualizar) {
        boolean rodarNovamente = true;
        while(rodarNovamente){
            String editarPeriodo = Leitura.dados("\nVocê deseja alterar o Periodo? (S/N): ").toUpperCase();
            switch (editarPeriodo){
                case "S":
                    listaTurmas.get(idAtualizar).setPeriodo(validarPeriodo());
                    System.out.printf("O periodo foi atualizado para: %s\n", listaTurmas.get(idAtualizar).getPeriodo());
                    rodarNovamente = false;
                    break;
                case "N":
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("\nOpção Inválida. Favor digitar S para excluir ou N para não!");
            }

        }
    }

    private static int validaIdTurma() {
        String opcao = Leitura.dados("\nDigite o número da turma que desejada: ");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while (opcaoValida==-1) {
            opcaoUsuario = validarItemLista(opcao);

            if (opcaoUsuario == -1) {
                System.out.println("Opção inválida! Digite novamente: ");
                opcao = Leitura.dados("Digite o número da turma que desejada: ");
            } else {
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
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
        System.out.println("Turma cadastrada com sucesso!!");
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
        if(isVazio(listaTurmas)){
            System.out.println("Não há turmas cadastradas");
            return;
        }
        for(Turma t : listaTurmas) {
            if(t.isAtivo())
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
