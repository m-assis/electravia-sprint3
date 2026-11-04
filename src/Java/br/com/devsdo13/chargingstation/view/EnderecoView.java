package br.com.devsdo13.chargingstation.view;

import java.util.Scanner;

public class EnderecoView {

    private final Scanner scanner;

    public EnderecoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] lerDadosEndereco() {
        System.out.println("\n--- CADASTRO DE ENDERECO ---");
        System.out.print("Codigo Postal (CEP): ");
        String codigoPostal = scanner.nextLine().trim();
        System.out.print("Numero: ");
        String numero = scanner.nextLine().trim();
        System.out.print("Complemento: ");
        String complemento = scanner.nextLine().trim();

        // Tipo de local com opcoes
        System.out.println("Tipo de local:");
        System.out.println("  1. Residencial");
        System.out.println("  2. Comercial");
        System.out.print("Escolha (numero ou texto): ");
        String tipoLocal = resolverTipoLocal(scanner.nextLine().trim());

        System.out.print("Pais: ");
        String pais = scanner.nextLine().trim();
        System.out.print("Estado: ");
        String estado = scanner.nextLine().trim();

        return new String[]{codigoPostal, numero, complemento, tipoLocal, pais, estado};
    }

    private String resolverTipoLocal(String entrada) {
        switch (entrada.toLowerCase()) {
            case "1": case "residencial":  return "Residencial";
            case "2": case "comercial":    return "Comercial";
            default:
                System.out.println("[!] Tipo nao reconhecido. Usando entrada informada: " + entrada);
                return entrada;
        }
    }

    public void exibirEnderecoCadastrado() {
        System.out.println("[OK] Endereco cadastrado com sucesso!");
    }
}
