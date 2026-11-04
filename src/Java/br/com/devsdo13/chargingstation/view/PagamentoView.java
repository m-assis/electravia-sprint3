package br.com.devsdo13.chargingstation.view;

import java.util.Scanner;
import br.com.devsdo13.chargingstation.model.Pagamento;

public class PagamentoView {

    private final Scanner scanner;

    public PagamentoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String lerFormaPagamento() {
        System.out.println("\n--- FORMA DE PAGAMENTO ---");
        System.out.println("  1. Cartao de Credito");
        System.out.println("  2. Cartao de Debito");
        System.out.println("  3. Pix");
        System.out.println("  4. Dinheiro");
        System.out.print("Escolha (numero ou texto): ");
        String entrada = scanner.nextLine().trim();

        switch (entrada.toLowerCase()) {
            case "1": case "cartao de credito": case "credito":  return "Cartao de Credito";
            case "2": case "cartao de debito":  case "debito":   return "Cartao de Debito";
            case "3": case "pix":                                return "Pix";
            case "4": case "dinheiro":                           return "Dinheiro";
            default:
                System.out.println("[!] Opcao nao reconhecida. Usando 'Pix' como padrao.");
                return "Pix";
        }
    }

    public void exibirPagamentoRealizado(Pagamento pagamento) {
        System.out.println("\n--- COMPROVANTE DE PAGAMENTO ---");
        System.out.println("  Forma: " + pagamento.getFormaPagamento());
        System.out.println("  Valor: R$ " + String.format("%.2f", pagamento.getValorPago()));
        System.out.println("  Data: " + pagamento.getDataPagamento());
        System.out.println("  Autorizado: " + (pagamento.isPagamentoAutorizado() ? "SIM" : "NAO"));
        if (pagamento.isPagamentoAutorizado()) {
            System.out.println("[OK] Pagamento realizado com sucesso!");
        } else {
            System.out.println("[X] Pagamento NAO autorizado!");
        }
    }
}
