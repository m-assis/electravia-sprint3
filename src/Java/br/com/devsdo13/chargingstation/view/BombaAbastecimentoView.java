package br.com.devsdo13.chargingstation.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import br.com.devsdo13.chargingstation.model.BombaAbastecimento;

public class BombaAbastecimentoView {

    private final Scanner scanner;

    public BombaAbastecimentoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public BombaAbastecimento lerDadosBomba() {
        System.out.println("\n--- CADASTRO DE BOMBA DE ABASTECIMENTO ---");
        System.out.print("Codigo da bomba: ");
        String codigoBomba = scanner.nextLine().trim();
        System.out.print("Descricao da bomba: ");
        String descricaoBomba = scanner.nextLine().trim();
        System.out.print("Capacidade (kW): ");
        double capacidade;
        try {
            capacidade = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[!] Valor invalido. Usando 0.0.");
            capacidade = 0.0;
        }
        // Data da última manutenção como a data atual por simplicidade
        LocalDate dataManutencao = LocalDate.now();
        System.out.println("   Data da ultima manutencao definida como: " + dataManutencao);

        return new BombaAbastecimento(codigoBomba, descricaoBomba, capacidade, dataManutencao);
    }

    public void exibirBombaCadastrada(BombaAbastecimento bomba) {
        System.out.println("[OK] Bomba '" + bomba.getDescricaoBomba() + "' cadastrada com sucesso!");
    }

    public int selecionarBomba(List<BombaAbastecimento> bombas) {
        if (bombas.isEmpty()) {
            System.out.println("[!] Nenhuma bomba cadastrada neste posto.");
            return -1;
        }
        System.out.println("\n--- BOMBAS DISPONIVEIS ---");
        for (int i = 0; i < bombas.size(); i++) {
            BombaAbastecimento b = bombas.get(i);
            System.out.println((i + 1) + ". " + b.getDescricaoBomba() + " (Codigo: " + b.getCodigoBomba() + " | Capacidade: " + b.getCapacidade() + " kW)");
        }
        System.out.print("Selecione o numero da bomba: ");
        try {
            int opcao = Integer.parseInt(scanner.nextLine().trim());
            if (opcao < 1 || opcao > bombas.size()) {
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
