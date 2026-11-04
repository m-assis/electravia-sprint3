package br.com.devsdo13.chargingstation.view;

import java.util.Scanner;

public class CadastroView {

    private final Scanner scanner;

    public CadastroView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] lerDadosCadastro() {
        System.out.println("\n--- CADASTRO DE ACESSO ---");
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();

        // Plano com opcoes
        System.out.println("Plano contratado:");
        System.out.println("  1. Basico");
        System.out.println("  2. Premium");
        System.out.println("  3. Enterprise");
        System.out.print("Escolha (numero ou texto): ");
        String planoContratado = resolverPlano(scanner.nextLine().trim());

        return new String[]{email, telefone, planoContratado};
    }

    private String resolverPlano(String entrada) {
        switch (entrada.toLowerCase()) {
            case "1": case "basico":      return "Basico";
            case "2": case "premium":     return "Premium";
            case "3": case "enterprise":  return "Enterprise";
            default:
                System.out.println("[!] Plano nao reconhecido. Usando entrada informada: " + entrada);
                return entrada;
        }
    }

    public void exibirCadastroCriado() {
        System.out.println("[OK] Dados de acesso cadastrados com sucesso!");
    }
}
