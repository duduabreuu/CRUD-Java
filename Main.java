import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PessoaCRUD crud = new PessoaCRUD();

        int opcao = -1;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Editar");
            System.out.println("4 - Remover");
            System.out.println("5 - Apagar Tudo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                continue;
            }
 
            switch (opcao) {
 
                case 1:
                    try {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());
 
                        System.out.print("Nome: ");
                        String nome = sc.nextLine().trim();
 
                        System.out.print("Ano de nascimento: ");
                        int ano = Integer.parseInt(sc.nextLine().trim());
 
                        System.out.print("Hobby: ");
                        String hobby = sc.nextLine().trim();
 
                        crud.criar(id, nome, ano, hobby);
 
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: ID e ano devem ser números.");
                    }
                    break;
 
                case 2:
                    crud.listar();
                    break;
 
                case 3:
                    try {
                        System.out.print("ID para buscar: ");
                        int idBusca = Integer.parseInt(sc.nextLine().trim());
                        var pessoa = crud.buscarPorId(idBusca);
                        if (pessoa != null) {
                            System.out.println(pessoa);
                        } else {
                            System.out.println("Pessoa não encontrada.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: ID deve ser um número.");
                    }
                    break;
 
                case 4:
                    try {
                        System.out.print("ID para editar: ");
                        int idEditar = Integer.parseInt(sc.nextLine().trim());
 
                        if (crud.buscarPorId(idEditar) == null) {
                            System.out.println("Pessoa não encontrada.");
                            break;
                        }
 
                        System.out.println("\nO que deseja editar?");
                        System.out.println("1 - Nome");
                        System.out.println("2 - Ano de nascimento");
                        System.out.println("3 - Hobby");
                        System.out.print("Escolha: ");
 
                        int campo = Integer.parseInt(sc.nextLine().trim());
 
                        if (campo == 1) {
                            System.out.print("Novo nome: ");
                            String novoNome = sc.nextLine().trim();
                            crud.atualizarNome(idEditar, novoNome);
 
                        } else if (campo == 2) {
                            System.out.print("Novo ano de nascimento: ");
                            int novoAno = Integer.parseInt(sc.nextLine().trim());
                            crud.atualizarAno(idEditar, novoAno);
 
                        } else if (campo == 3) {
                            System.out.print("Novo hobby: ");
                            String novoHobby = sc.nextLine().trim();
                            crud.atualizarHobby(idEditar, novoHobby);
 
                        } else {
                            System.out.println("Opção inválida.");
                        }
 
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: digite apenas números nas opções.");
                    }
                    break;
 
                case 5:
                    try {
                        System.out.print("ID para remover: ");
                        int idRemover = Integer.parseInt(sc.nextLine().trim());
                        crud.remover(idRemover);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: ID deve ser um número.");
                    }
                    break;
 
                case 6:
                    System.out.print("Tem certeza que deseja apagar TUDO? (s/n): ");
                    String confirmacao = sc.nextLine().trim();
                    if (confirmacao.equalsIgnoreCase("s")) {
                        crud.apagarTudo();
                    } else {
                        System.out.println("Operação cancelada.");
                    }
                    break;
 
                case 0:
                    System.out.println("Encerrando...");
                    break;
 
                default:
                    System.out.println("Opção inválida.");
            }
 
        } while (opcao != 0);
 
        sc.close();
    }
}
 
