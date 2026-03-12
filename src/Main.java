import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        System.out.println("\n---Secretaria---");
        System.out.println("1 - Alunos");
        System.out.println("2 - Turmas");
        System.out.println("3 - Sair");
        String opcao = Leitura.dados("Digite a opção desejada:");
        switch (opcao) {
            case "1":
                menuAlunos();
                break;
            case "2":
                menuTurmas();
                break;
            case "3":
                System.out.println("Até breve...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção Inválida! Tente novamente");
                menuPrincipal();
        }
    }

    private static void menuTurmas() {
        System.out.println("\n---Turmas---");
        System.out.println("1 - Listar Turmas");
        System.out.println("2 - Cadastrar Turma");
        System.out.println("3 - Atualizar Turma");
        System.out.println("4 - Excluir Turma");
        System.out.println("5 - Voltar ao menu principal");
        String opcao = Leitura.dados("Digite a opção desejada:");
        switch (opcao) {
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
                System.out.println("Opção Inválida! Tente novamente");
                menuTurmas();
        }
    }

    private static void menuAlunos() {
        System.out.println("\n---Alunos---");
        System.out.println("1 - Listar Alunos");
        System.out.println("2 - Cadastrar Aluno");
        System.out.println("3 - Atualizar Aluno");
        System.out.println("4 - Excluir Aluno");
        System.out.println("5 - Voltar ao menu principal");
        String opcao = Leitura.dados("Digite a opção desejada:");
        switch (opcao) {
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
                break;
            case "4":
                excluirAluno();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção Inválida! Tente novamente");
                menuAlunos();
        }
    }

    private static void listarTurmas() {
        if(isVazioTurmas(listaTurmas)){
            System.out.println("Não há turmas cadastradas");
            return;
        }
        for(Turma t: listaTurmas){
            if(t.isAtivo())
                System.out.println(t);
        }
    }

    private static boolean isVazioTurmas(ArrayList<Turma> listaTurmas) {
        if(listaTurmas.isEmpty()) return true;

        for (Turma turma : listaTurmas){
            if(turma.isAtivo()) return false;
        }
        return true;
    }

    private static void cadastrarTurma() {
        Periodo periodo = validarPeriodo();
        String curso = validarCurso();
        String sigla = validarSigla();

        Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
        System.out.println("Turma Adicionada com sucesso!");
    }

    private static Periodo validarPeriodo() {
        String opcaoPeriodo = Leitura.dados("""
                Digite o número do período escolhido:
                1- Matutino
                2- Vespertino
                3- Noturno
                4- Integral""");
        switch (opcaoPeriodo){
            case "1":
                return Periodo.MATUTINO;
            case "2":
                return Periodo.VESPERTINO;
            case "3":
                return Periodo.NOTURNO;
            case "4":
                return Periodo.INTEGRAL;
            default:
                System.out.println("Opção Inválida! Digite novamente\n");
                return validarPeriodo();
        }
    }

    private static String validarCurso () {
        String curso = Leitura.dados("Digite o curso:");
        while(!isCharacter(curso)){
            System.out.println("Nome do curso inválido! Não use números ou caracteres especiais, por favor");
            curso = Leitura.dados("Digite o curso:");
        }
        return curso;
    }

    private static boolean validarSigla(String sigla) {
        if(sigla.isBlank()) return false;

        for (Turma turma: listaTurmas){
            if (turma.getSigla().equals(sigla)) {
                return false;
            }
        }
        return true;
    }

    private static String validarSigla() {
        String sigla = Leitura.dados("Digite a sigla: ");
        while(!validarSigla(sigla)){
            System.out.println("Sigla Inválida! Precisa conter texto e não pode ser repetida");
            sigla = Leitura.dados("Digite a sigla: ");
        }
        return sigla;
    }

    private static void atualizarTurma() {
        if (isVazioTurmas(listaTurmas)) {
            System.out.println("Não há turmas cadastradas");
            return;
        }
        listarTurmasIndiceSigla();
        int idAtualizar = validaIdTurma();

        System.out.printf("O período atual é: %s\n", listaTurmas.get(idAtualizar).getPeriodo());
        atualizaParcial("Período", idAtualizar);
        System.out.printf("O curso atual é: %s\n", listaTurmas.get(idAtualizar).getCurso());
        atualizaParcial("Curso", idAtualizar);
        System.out.printf("A sigla atual é: %s\n", listaTurmas.get(idAtualizar).getSigla());
        atualizaParcial("Sigla", idAtualizar);
    }

    private static void atualizaParcial(String atributo, int idAtualizar){
        boolean rodarNovamente = true;
        while (rodarNovamente) {
            String opcao = Leitura.dados("\nDeseja modificar "+ atributo +" ? (S/N): ").toUpperCase();
            switch (opcao) {
                case "S":
                    switch (atributo){
                        case "Período":
                            Periodo periodo = validarPeriodo();
                            listaTurmas.get(idAtualizar).setPeriodo(periodo);
                            break;
                        case "Curso":
                            String curso = validarCurso();
                            listaTurmas.get(idAtualizar).setCurso(curso);
                            break;
                        case "Sigla":
                            String sigla = validarSigla();
                            listaTurmas.get(idAtualizar).setSigla(sigla);
                            break;
                    }
                    System.out.println(atributo + " atualizado com sucesso!!");
                    rodarNovamente = false;
                    break;
                case "N":
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opção invalida! Escolha S para SIM ou N para NÃO");
            }
        }
    }

    private static void excluirTurma() {
        if(isVazioTurmas(listaTurmas)){
            System.out.println("Não há turmas cadastradas");
            return;
        }
        listarTurmasIndiceSigla();

        int idExcluir = validaIdTurma();

        if(confirmaExclusao()) {
//
            listaTurmas.get(idExcluir).setAtivo(false);
            System.out.println("Turma Excluída com sucesso!");
        } else {
            System.out.println("Operação cancelada!");
        }
    }

    private static boolean confirmaExclusao() {
        while (true) {
            String confirma = Leitura.dados("Você tem certeza? (S/N): ").toUpperCase();
            switch (confirma) {
                case "S":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Opção Inválida, digite S para sim ou N para não");
                    break;
            }
        }
    }

    private static int validarItemLista(String opcao) {
        if(opcao.isBlank()) return -1;

        int opcaoNumero = -1;

        try{
            opcaoNumero = Integer.parseInt(opcao);
        } catch (NumberFormatException e) {
            return -1;
        }

        int indiceLista = opcaoNumero-1;
        return indiceLista >=0 && listaTurmas.size() > indiceLista ? indiceLista: -1;
    }

    private static void listarTurmasIndiceSigla() {
        System.out.println("\nLista das Turmas: ");
        for(int i=0;i<listaTurmas.size();i++){
            if (listaTurmas.get(i).isAtivo())
                System.out.printf("\n%d - %s",i+1, listaTurmas.get(i).getSigla());
        }
    }

    private static int validaIdTurma() {
        String opcao = Leitura.dados("\nDigite o número da turma desejada: ");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while(opcaoValida==-1){
            opcaoUsuario = validarItemLista(opcao);

            if(opcaoUsuario==-1) {
                System.out.println("Opção Inválida!");
                opcao = Leitura.dados("Digite o número da turma desejada: ");
            } else {
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }

    private static boolean isCharacter(String texto) {
        String textoSemNumeros = texto.replaceAll("\\d", "");
        return !texto.isBlank() && texto.equals(textoSemNumeros) && texto.matches("[a-zA-Z\\s]*");
    }

    private static boolean isVazioAlunos(ArrayList<Aluno> listaAlunos) {
        if(listaAlunos.isEmpty()) return true;

        for (Aluno aluno : listaAlunos){
            if(aluno.isAtivo()) return false;
        }
        return true;
    }

    private static int validaIdAluno() {
        String opcao = Leitura.dados("\nDigite o número do aluno desejado: ");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while(opcaoValida==-1){
            opcaoUsuario = validarItemLista(opcao);

            if(opcaoUsuario==-1) {
                System.out.println("Opção Inválida!");
                opcao = Leitura.dados("Digite o número da turma desejada: ");
            } else {
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }

    private static void excluirAluno() {
        if(isVazioAlunos(listaAlunos)){
            System.out.println("Não há alunos cadastrados");
            return;
        }
        listarTurmasIndiceSigla();

        int idExcluir = validaIdTurma();

        if(confirmaExclusao()) {
//
            listaTurmas.get(idExcluir).setAtivo(false);
            System.out.println("Turma Excluída com sucesso!");
        } else {
            System.out.println("Operação cancelada!");
        }
    }


    private static void atualizarAluno() {

    }

    private static void cadastrarAluno() {
        String nome = validarNome();
        String dataNascimento = validarData();
        Turma turma = validarTurma();

        Aluno aluno = new Aluno(nome, dataNascimento, turma);
        listaAlunos.add(aluno);

        System.out.println("Aluno cadastrado com sucesso!!");

    }

    private static Turma validarTurma() {
        listarTurmasIndiceSigla();
        int idTurma = validaIdTurma();

        return listaTurmas.get(idTurma);
    }

    private static String validarData() {
        String dataNascimento = Leitura.dados("Digite a data de nascimento do aluno: (dd/mm/yyyy)");
        while(!isData(dataNascimento)){
            dataNascimento = Leitura.dados("Data inválida!! Digite a data de nascimento do aluno: (dd/mm/yyyy)");
        }
        return dataNascimento;
    }

    private static boolean isData(String dataNascimento) {
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");
            LocalDate nascimento = LocalDate.parse(dataNascimento, formato);

            if (nascimento.isAfter(LocalDate.now())) {
                return false;
            }

            return true;
        }catch(DateTimeParseException e){
            return false;
        }
    }

    private static String validarNome() {
        String nome = Leitura.dados("Digite o nome do aluno:");
        while(!isCharacter(nome)){
            System.out.println("Nome do aluno inválido! Não use números ou caracteres especiais, por favor");
            nome = Leitura.dados("Digite o nome:");
        }
        return nome;
    }

    private static void listarAlunos() {
        if(isVazioAlunos(listaAlunos)){
            System.out.println("Não há alunos cadastrados");
            return;
        }
        for(Aluno a: listaAlunos){
            if(a.isAtivo())
                System.out.println(a);
        }
    }
}