package br.com.devsdo13.chargingstation.view;

import java.util.List;
import java.util.Scanner;
import br.com.devsdo13.chargingstation.model.Veiculo;

public class VeiculoView {

    private final Scanner scanner;

    public VeiculoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Veiculo lerDadosVeiculo() {
        System.out.println("\n--- CADASTRO DE VEICULO ---");
        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine().trim();
        System.out.print("Ano de fabricacao: ");
        int ano;
        try {
            ano = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[!] Ano invalido. Usando 0.");
            ano = 0;
        }

        // Tipo com opcoes
        System.out.println("Tipo do veiculo:");
        System.out.println("  1. Hatch");
        System.out.println("  2. Sedan");
        System.out.println("  3. SUV");
        System.out.println("  4. Pickup");
        System.out.println("  5. Coupe");
        System.out.print("Escolha (numero ou texto): ");
        String tipo = resolverTipoVeiculo(scanner.nextLine().trim());

        System.out.print("Documento (placa): ");
        String documento = scanner.nextLine().trim();
        System.out.print("Codigo identificador (chassi): ");
        String codigoIdentificador = scanner.nextLine().trim();

        return new Veiculo(marca, modelo, ano, tipo, documento, codigoIdentificador);
    }

    private String resolverTipoVeiculo(String entrada) {
        switch (entrada.toLowerCase()) {
            case "1": case "hatch":   return "Hatch";
            case "2": case "sedan":   return "Sedan";
            case "3": case "suv":     return "SUV";
            case "4": case "pickup":  return "Pickup";
            case "5": case "coupe":   return "Coupe";
            default:
                System.out.println("[!] Tipo nao reconhecido. Usando entrada informada: " + entrada);
                return entrada;
        }
    }

    public void exibirVeiculoCadastrado(Veiculo veiculo) {
        System.out.println("[OK] Veiculo " + veiculo.getMarca() + " " + veiculo.getModelo() + " cadastrado com sucesso!");
    }

    public int selecionarVeiculo(List<Veiculo> veiculos) {
        if (veiculos.isEmpty()) {
            System.out.println("[!] Nenhum veiculo cadastrado para este usuario.");
            return -1;
        }
        System.out.println("\n--- VEICULOS DO USUARIO ---");
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);
            System.out.println((i + 1) + ". " + v.getMarca() + " " + v.getModelo() + " (" + v.getDocumento() + ")");
        }
        System.out.print("Selecione o numero do veiculo: ");
        try {
            int opcao = Integer.parseInt(scanner.nextLine().trim());
            if (opcao < 1 || opcao > veiculos.size()) {
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
