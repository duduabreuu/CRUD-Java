import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class PessoaCRUD {

    private List<Pessoa> pessoas = new ArrayList<>();
    private static final String ARQUIVO = "pessoas.txt";

    public PessoaCRUD() {
        carregarDoArquivo();
    }

    public boolean criar(int id, String nome, int anoNascimento, String hobby) {
        if (idExiste(id)) {
            System.out.println("Erro: já existe uma pessoa com o ID " + id + ".");
            return false;
        }
        if (anoNascimento < 1910 || anoNascimento > java.time.Year.now().getValue()) {
            System.out.println("Erro: ano de nascimento inválido.");
            return false;
        }
        pessoas.add(new Pessoa(id, nome, anoNascimento, hobby));
        salvarNoArquivo();
        System.out.println("Pessoa cadastrada com sucesso!");
        return true;
    }

    public void listar() {
    if (pessoas.isEmpty()) {
        System.out.println("Nenhuma pessoa cadastrada.");
        return;
    }
    for (Pessoa pessoa : pessoas) {
        System.out.println(pessoa);
    }
}

public Pessoa buscarPorId(int id) {
    for (Pessoa pessoa : pessoas) {
        if (pessoa.getId() == id) {
            return pessoa;
        }
    }
    return null;
}

   public boolean atualizarNome(int id, String novoNome) {
        Pessoa p = buscarPorId(id);
        if (p == null) { System.out.println("Pessoa não encontrada."); return false; }
        p.setNome(novoNome);
        salvarNoArquivo();
        System.out.println("Nome atualizado com sucesso!");
        return true;
    }
 
    public boolean atualizarAno(int id, int novoAno) {
        if (novoAno < 1900 || novoAno > java.time.Year.now().getValue()) {
            System.out.println("Erro: ano de nascimento inválido.");
            return false;
        }
        Pessoa p = buscarPorId(id);
        if (p == null) { System.out.println("Pessoa não encontrada."); return false; }
        p.setAnoNascimento(novoAno);
        salvarNoArquivo();
        System.out.println("Ano de nascimento atualizado com sucesso!");
        return true;
    }
 
    public boolean atualizarHobby(int id, String novoHobby) {
        Pessoa p = buscarPorId(id);
        if (p == null) { System.out.println("Pessoa não encontrada."); return false; }
        p.setHobby(novoHobby);
        salvarNoArquivo();
        System.out.println("Hobby atualizado com sucesso!");
        return true;
    }
    public boolean remover(int id) {

        Pessoa pessoa = buscarPorId(id);

        if (pessoa != null) {
            pessoas.remove(pessoa);
            salvarNoArquivo(); 
            return true;
        }

        return false;
    }

    public void apagarTudo() {
        pessoas.clear();
        salvarNoArquivo(); 
        System.out.println("Todos os registros foram apagados.");
    }

    private boolean idExiste(int id) {
        return buscarPorId(id) != null;
    }

    private void salvarNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Pessoa p : pessoas) {
                bw.write(p.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    private void carregarDoArquivo() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    int ano = Integer.parseInt(partes[2]);
                    String hobby = partes[3];
                    pessoas.add(new Pessoa(id, nome, ano, hobby));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
    }
}
