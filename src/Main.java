import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.Period;

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
                System.out.println("Opção Inválida! Tente novamente");
                menuAlunos();
        }
    }

    private static void listarTurmas() {
        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas");
            return;
        }
        for (Turma t : listaTurmas) {
            if (t.isAtivo())
                System.out.println(t);
        }
    }

    private static void listarTurmasIndiceSigla() {
        System.out.println("\nLista das Turmas: ");
        for (int i = 0; i < listaTurmas.size(); i++) {
            if (listaTurmas.get(i).isAtivo())
                System.out.printf("\n%d - %s", i + 1, listaTurmas.get(i).getSigla());
        }
    }

    private static void listarAlunos() {

        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas");
            return;
        }

        listarTurmasIndiceSigla();
        int id = validaId(listaTurmas);

        Turma turmaSelecionada = listaTurmas.get(id);

        System.out.println("\nAlunos da turma: " + turmaSelecionada.getSigla());

        boolean encontrou = false;

        for (Aluno a : listaAlunos) {
            if (a.isAtivo() &&
                    a.getTurma().getSigla().equals(turmaSelecionada.getSigla())) {

                System.out.println(a);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum aluno encontrado nessa turma.");
        }
    }

    private static void listarAlunosIndiceNome() {
        System.out.println("\nLista dos Alunos: ");
        for (int i = 0; i < listaAlunos.size(); i++) {
            if (listaAlunos.get(i).isAtivo())
                System.out.printf("\n%d - %s", i + 1, listaAlunos.get(i).getNome());
        }
    }

    private static void cadastrarTurma() {
        Periodo periodo = validarPeriodo();
        String curso = validarCurso();
        String sigla = validarSigla();

        Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
        System.out.println("Turma cadastrada com sucesso!");
    }

    private static Periodo validarPeriodo() {
        String opcaoPeriodo = Leitura.dados("""
                Digite o número do período escolhido:
                1- Matutino
                2- Vespertino
                3- Noturno
                4- Integral""");
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
                System.out.println("Opção Inválida! Digite novamente\n");
                return validarPeriodo();
        }
    }

    private static String validarCurso() {
        String curso = Leitura.dados("Digite o curso: ");
        while (!isCharacter(curso)) {
            System.out.println("Nome do curso inválido! Não use números ou caracteres especiais, por favor");
            curso = Leitura.dados("Digite o curso: ");
        }
        return curso;
    }

    private static boolean validarSigla(String sigla) {
        if (sigla.isBlank()) return false;

        for (Turma turma : listaTurmas) {
            if (turma.getSigla().equals(sigla)) {
                return false;
            }
        }
        return true;
    }

    private static String validarSigla() {
        String sigla = Leitura.dados("Digite a sigla: ");
        while (!validarSigla(sigla)) {
            System.out.println("Sigla Inválida! Precisa conter texto e não pode ser repetida");
            sigla = Leitura.dados("Digite a sigla: ");
        }
        return sigla;
    }

    private static void cadastrarAluno() {
        if(isVazio(listaTurmas)){
            System.out.println("\nNão é possível cadastrar um aluno sem turma!");
            menuPrincipal();
        }
        String nome = validarNome();
        String dataNascimento = validarData();
        Turma turma = validarTurma();

        Aluno aluno = new Aluno(nome, dataNascimento, turma);
        listaAlunos.add(aluno);

        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static String validarNome() {
        String nomeAluno = Leitura.dados("Digite o nome do aluno: ");
        while (!isCharacter(nomeAluno)) {
            System.out.println("Nome do aluno inválido! Não use números ou caracteres especiais, por favor");
            nomeAluno = Leitura.dados("Digite o nome do aluno:");
        }
        return nomeAluno;
    }

    private static String validarData() {
        String dataNascimento = Leitura.dados("Digite a data de nascimento do aluno (dd/mm/yyyy) : ");
        while (!isData(dataNascimento)) {
            System.out.println("Data inválida!");
            dataNascimento = Leitura.dados("Digite a data de nascimento do aluno (dd/mm/yyyy) : ");
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

            Period idade = Period.between(nascimento, LocalDate.now());
            int anos = idade.getYears();

            if (anos < 14 || anos > 130) {
                return false;
            }

            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static Turma validarTurma() {
        listarTurmasIndiceSigla();

        int idTurma = validaId(listaTurmas);

        return listaTurmas.get(idTurma);
    }

    private static void atualizarParcial(String atributo, Runnable acaoAtualizar) {
        boolean rodarNovamente = true;
        while (rodarNovamente) {
            String opcao = Leitura.dados("\nDeseja modificar " + atributo + " ? (S/N): ").toUpperCase();
            switch (opcao) {
                case "S":
                    acaoAtualizar.run();
                    System.out.println(atributo + " atualizado com sucesso!");
                    rodarNovamente = false;
                    break;

                case "N":
                    rodarNovamente = false;
                    break;

                default:
                    System.out.println("Opção invalida!");
            }
        }
    }

    private static void atualizarTurma() {
        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas");
            return;
        }
        listarTurmasIndiceSigla();
        int idAtualizar = validaId(listaTurmas);

        System.out.printf("O periodo atual é %s:\n", listaTurmas.get(idAtualizar).getPeriodo());
        atualizarParcial("Periodo", () ->
                listaTurmas.get(idAtualizar).setPeriodo(validarPeriodo())
        );

        System.out.printf("O curso atual é %s:\n", listaTurmas.get(idAtualizar).getCurso());
        atualizarParcial("Curso", () ->
                listaTurmas.get(idAtualizar).setCurso(validarCurso())
        );

        System.out.printf("A sigla atual é %s:\n", listaTurmas.get(idAtualizar).getSigla());
        atualizarParcial("Sigla", () ->
                listaTurmas.get(idAtualizar).setSigla(validarSigla())
        );
    }

    private static void atualizarAluno() {
        if (isVazio(listaAlunos)) {
            System.out.println("Não há alunos cadastrados");
            return;
        }
        listarAlunosIndiceNome();
        int idAtualizar = validaId(listaAlunos);

        System.out.printf("O nome atual é %s:\n", listaAlunos.get(idAtualizar).getNome());
        atualizarParcial("Nome", () ->
                listaAlunos.get(idAtualizar).setNome(validarNome())
        );

        System.out.printf("A data de nascimento atual é %s:\n", listaAlunos.get(idAtualizar).getDataNascimento());
        atualizarParcial("Data de Nascimento", () ->
                listaAlunos.get(idAtualizar).setDataNascimento(validarData())
        );
        System.out.printf("A turma atual é %s:\n", listaAlunos.get(idAtualizar).getTurma().getSigla());
        atualizarParcial("Turma", () ->
                listaAlunos.get(idAtualizar).setTurma(validarTurma())
        );
    }

    private static void excluirTurma() {
        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas");
            return;
        }

        listarTurmasIndiceSigla();
        int idExcluir = validaId(listaTurmas);

        Turma turmaSelecionada = listaTurmas.get(idExcluir);

        //  verifica se tem aluno ativo na turma
        boolean temAlunoAtivo = listaAlunos.stream()
                .anyMatch(aluno ->
                        aluno.isAtivo() &&
                                aluno.getTurma().getSigla().equals(turmaSelecionada.getSigla())
                );

        //  se NÃO tiver aluno segue fluxo normal
        if (!temAlunoAtivo) {

            if (confirmaExclusao()) {
                turmaSelecionada.setAtivo(false);
                System.out.println("Turma excluída com sucesso!");
            } else {
                System.out.println("Operação cancelada!");
            }

            return;
        }

        //  se tiver aluno ativo
        System.out.println("Essa turma possui alunos ativos!");

        String opcao = Leitura.dados("Deseja excluir a turma realmente? Os alunos também serão excluídos! (S/N): ").toUpperCase();

        switch (opcao) {
            case "S":


                listaAlunos.forEach(aluno -> {
                    if (aluno.isAtivo() &&
                            aluno.getTurma().getSigla().equals(turmaSelecionada.getSigla())) {

                        aluno.setAtivo(false);
                    }
                });

                if (confirmaExclusao()) {
                    turmaSelecionada.setAtivo(false);
                    System.out.println("Alunos e turma excluídos com sucesso!");
                } else {
                    System.out.println("Operação cancelada!");
                }

                break;

            case "N":
                System.out.println("Operação cancelada!");
                break;

            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void excluirAluno() {
        if (isVazio(listaAlunos)) {
            System.out.println("Não há alunos cadastradas");
            return;
        }
        listarAlunosIndiceNome();

        int idExcluir = validaId(listaAlunos);

        if (confirmaExclusao()) {
//
            listaAlunos.get(idExcluir).setAtivo(false);
            System.out.println("Aluno excluído com sucesso!");
        } else {
            System.out.println("Operação cancelada!");
        }

    }
    private static boolean isCharacter(String texto) {
        String textoSemNumeros = texto.replaceAll("\\d", "");
        return !texto.isBlank() && texto.equals(textoSemNumeros) && texto.matches("[a-zA-Z\\s]*");
    }

    private static boolean isVazio(ArrayList<? extends Ativavel> lista) {
        if (lista.isEmpty()) {
            return true;
        }

        for (Ativavel item : lista) {
            if (item.isAtivo()) {
                return false;
            }
        }

        return true;
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

    private static int validarItemLista(String opcao, ArrayList<?> lista) {
        if (opcao.isBlank()) return -1;

        int opcaoNumero = -1;

        try {
            opcaoNumero = Integer.parseInt(opcao);
        } catch (NumberFormatException e) {
            return -1;
        }

        int indiceLista = opcaoNumero - 1;
        return indiceLista >= 0 && lista.size() > indiceLista ? indiceLista : -1;
    }

    private static int validaId(ArrayList<?> lista) {
        String opcao = Leitura.dados("\nDigite o número desejado: ");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while (opcaoValida == -1) {
            opcaoUsuario = validarItemLista(opcao, lista);

            if (opcaoUsuario == -1) {
                System.out.println("Opção Inválida! Digite novamente: ");
                opcao = Leitura.dados("Digite o número desejado: ");
            } else {
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }



}
