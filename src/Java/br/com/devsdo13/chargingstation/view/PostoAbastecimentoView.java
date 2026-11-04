package br.com.devsdo13.chargingstation.view;

import java.util.List;
import java.util.Scanner;
import br.com.devsdo13.chargingstation.model.PostoAbastecimento;

public class PostoAbastecimentoView {

    private final Scanner scanner;

    public PostoAbastecimentoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] lerDadosPosto() {
        System.out.println("\n--- CADASTRO DE POSTO DE ABASTECIMENTO ---");
        System.out.print("Numero de Identificacao Fiscal (CNPJ): ");
        String cnpj = scanner.nextLine().trim();
        System.out.print("Codigo do Posto: ");
        String codigoPosto = scanner.nextLine().trim();
        System.out.print("Descricao do Posto: ");
        String descricaoPosto = scanner.nextLine().trim();

        return new String[]{cnpj, codigoPosto, descricaoPosto};
    }

    public void exibirPostoCriado(PostoAbastecimento posto) {
        System.out.println("[OK] Posto '" + posto.getDescricaoPosto() + "' cadastrado com sucesso!");
    }

    public void exibirListaPostos(List<PostoAbastecimento> postos) {
        if (postos.isEmpty()) {
            System.out.println("[!] Nenhum posto cadastrado.");
            return;
        }
        System.out.println("\n--- POSTOS CADASTRADOS ---");
        for (int i = 0; i < postos.size(); i++) {
            PostoAbastecimento p = postos.get(i);
            System.out.println((i + 1) + ". " + p.getDescricaoPosto() + " (Codigo: " + p.getCodigoPosto() + ")");
        }
    }

    public int selecionarPosto(List<PostoAbastecimento> postos) {
        exibirListaPostos(postos);
        if (postos.isEmpty()) return -1;
        System.out.print("Selecione o numero do posto: ");
        try {
            int opcao = Integer.parseInt(scanner.nextLine().trim());
            if (opcao < 1 || opcao > postos.size()) {
                System.out.println("[!] Opcao invalida.");
                return -1;
            }
            return opcao - 1;
        } catch (NumberFormatException e) {
            System.out.println("[!] Entrada invalida.");
            return -1;
        }
    }
}
