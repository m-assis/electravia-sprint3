package br.com.devsdo13.chargingstation.view;

import java.util.List;
import java.util.Scanner;
import br.com.devsdo13.chargingstation.model.Usuario;

public class UsuarioView {

    private final Scanner scanner;

    public UsuarioView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String lerNomeUsuario() {
        System.out.print("Informe o nome do usuario: ");
        return scanner.nextLine().trim();
    }

    public void exibirUsuarioCriado(Usuario usuario) {
        System.out.println("\n[OK] Usuario '" + usuario.getNomeUsuario() + "' criado com sucesso!");
    }

    public void exibirListaUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("[!] Nenhum usuario cadastrado.");
            return;
        }
        System.out.println("\n--- USUARIOS CADASTRADOS ---");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNomeUsuario());
        }
    }

    public int selecionarUsuario(List<Usuario> usuarios) {
        exibirListaUsuarios(usuarios);
        if (usuarios.isEmpty()) return -1;
        System.out.print("Selecione o numero do usuario: ");
        try {
            int opcao = Integer.parseInt(scanner.nextLine().trim());
            if (opcao < 1 || opcao > usuarios.size()) {
                System.out.println("[!] Opcao invalida.");
                return -1;
            }
            return opcao - 1;
        } catch (NumberFormatException e) {
            System.out.println("[!] Entrada invalida.");
            return -1;
        }
    }

    public void exibirResumoUsuario(Usuario usuario) {
        System.out.println("\n--- RESUMO DO USUARIO ---");
        System.out.println("Nome: " + usuario.getNomeUsuario());
        if (usuario.getCadastro() != null) {
            System.out.println("Email: " + usuario.getCadastro().getEmail());
            System.out.println("Telefone: " + usuario.getCadastro().getTelefone());
            System.out.println("Plano: " + usuario.getCadastro().getPlanoContratado());
        }
        if (usuario.getEndereco() != null) {
            System.out.println("CEP: " + usuario.getEndereco().getCodigoPostal());
            System.out.println("Numero: " + usuario.getEndereco().getNumeroEndereco());
            System.out.println("Estado: " + usuario.getEndereco().getEstado());
        }
        System.out.println("Documentos: " + usuario.getDocumentos().size());
        System.out.println("Veiculos: " + usuario.getVeiculos().size());
    }
}
