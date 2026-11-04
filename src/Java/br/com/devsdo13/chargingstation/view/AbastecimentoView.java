package br.com.devsdo13.chargingstation.view;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import br.com.devsdo13.chargingstation.model.Abastecimento;

public class AbastecimentoView {

    private final Scanner scanner;

    public AbastecimentoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public double lerValorKw() {
        System.out.print("Informe o valor do kW (R$): ");
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[!] Valor invalido. Usando R$ 0.50 como padrao.");
            return 0.50;
        }
    }

    public double lerQuantidadeAbastecida() {
        System.out.print("Informe a quantidade abastecida (kW): ");
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[!] Valor invalido. Usando 0.0.");
            return 0.0;
        }
    }

    public void exibirAbastecimentoIniciado() {
        System.out.println("\n[>>] Abastecimento iniciado...");
    }

    public void exibirAbastecimentoFinalizado(Abastecimento abastecimento) {
        System.out.println("[OK] Abastecimento finalizado!");
        System.out.println("   Quantidade: " + abastecimento.getQuantidadeAbastecida() + " kW");
        System.out.println("   Valor kW: R$ " + abastecimento.getValorKw());
        System.out.println("   Custo Total: R$ " + String.format("%.2f", abastecimento.getCustoTotal()));
    }

    public void exibirHistoricoAbastecimentos(String nomeUsuario, Stack<Abastecimento> historico) {
        if (historico.isEmpty()) {
            System.out.println("[!] Nenhum abastecimento registrado para " + nomeUsuario + ".");
            return;
        }

        System.out.println("\n============= HISTORICO DE RECARGAS DE " + nomeUsuario.toUpperCase() + " =============");
        // Cria uma cópia para não destruir a pilha original
        Stack<Abastecimento> copia = new Stack<>();
        copia.addAll(historico);

        int numeroRecarga = copia.size();
        while (!copia.isEmpty()) {
            Abastecimento recarga = copia.pop();
            System.out.println("Recarga #" + numeroRecarga);
            System.out.println("  Data/Hora Inicio: " + recarga.getDataInicio());
            System.out.println("  Data/Hora Termino: " + recarga.getDataTermino());
            System.out.println("  Veiculo: " + recarga.getVeiculo().getMarca() + " " + recarga.getVeiculo().getModelo());
            System.out.println("  Bomba: " + recarga.getBomba().getDescricaoBomba());
            System.out.println("  Quantidade Abastecida: " + recarga.getQuantidadeAbastecida() + " kW");
            System.out.println("  Custo Total: R$ " + String.format("%.2f", recarga.getCustoTotal()));
            System.out.println("  Forma de Pagamento: " + recarga.getFormaPagamento());
            System.out.println();
            numeroRecarga--;
        }
    }

    public void exibirHistoricoAbastecimentos(String nomeUsuario, List<Abastecimento> historico) {
        if (historico.isEmpty()) {
            System.out.println("[!] Nenhum abastecimento registrado para " + nomeUsuario + ".");
            return;
        }

        System.out.println("\n============= HISTORICO DE RECARGAS DE " + nomeUsuario.toUpperCase() + " =============");
        for (int i = historico.size() - 1; i >= 0; i--) {
            Abastecimento recarga = historico.get(i);
            System.out.println("Recarga #" + (i + 1));
            System.out.println("  Data/Hora Inicio: " + recarga.getDataInicio());
            System.out.println("  Data/Hora Termino: " + recarga.getDataTermino());
            System.out.println("  Veiculo: " + recarga.getVeiculo().getMarca() + " " + recarga.getVeiculo().getModelo());
            System.out.println("  Bomba: " + recarga.getBomba().getDescricaoBomba());
            System.out.println("  Quantidade Abastecida: " + recarga.getQuantidadeAbastecida() + " kW");
            System.out.println("  Custo Total: R$ " + String.format("%.2f", recarga.getCustoTotal()));
            System.out.println("  Forma de Pagamento: " + recarga.getFormaPagamento());
            System.out.println();
        }
    }
}
