package br.com.devsdo13.chargingstation.view;

import java.util.Scanner;

public class MenuView {

    private final Scanner scanner;

    public MenuView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int exibirMenuPrincipal() {
        System.out.println("\n  ╔═════════════════════════════════════════════════╗");
        System.out.println("    ║       SISTEMA DE POSTOS DE ABASTECIMENTO        ║");
        System.out.println("    ╠═════════════════════════════════════════════════╣");
        System.out.println("    ║  1. Cadastrar Usuario                           ║");
        System.out.println("    ║  2. Cadastrar Posto de Abastecimento            ║");
        System.out.println("    ║  3. Realizar Abastecimento                      ║");
        System.out.println("    ║  4. Consultar Historico de Abastecimentos       ║");
        System.out.println("    ║  0. Sair                                        ║");
        System.out.println("    ╚═════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opcao: ");
        return lerOpcao();
    }

    public int exibirSubMenuUsuario() {
        System.out.println("\n  ╔═════════════════════════════════════════════════╗");
        System.out.println("    ║             MENU USUARIO                        ║");
        System.out.println("    ╠═════════════════════════════════════════════════╣");
        System.out.println("    ║  1. Informar dados pessoais                     ║");
        System.out.println("    ║  2. Cadastrar Endereco                          ║");
        System.out.println("    ║  3. Cadastrar Documentacao                      ║");
        System.out.println("    ║  4. Cadastrar Dados de Acesso (Cadastro)        ║");
        System.out.println("    ║  5. Cadastrar Veiculo                           ║");
        System.out.println("    ║  6. Finalizar cadastro do Usuario               ║");
        System.out.println("    ╚═════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opcao: ");
        return lerOpcao();
    }

    public int exibirSubMenuPosto() {
        System.out.println("\n  ╔═════════════════════════════════════════════════╗");
        System.out.println("    ║         MENU POSTO DE ABASTECIMENTO             ║");
        System.out.println("    ╠═════════════════════════════════════════════════╣");
        System.out.println("    ║  1. Informar dados do Posto                     ║");
        System.out.println("    ║  2. Adicionar Bomba de Abastecimento            ║");
        System.out.println("    ║  3. Finalizar cadastro do Posto                 ║");
        System.out.println("    ╚═════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opcao: ");
        return lerOpcao();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    private int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine().trim());
            return opcao;
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida! Digite um numero.");
            return -1;
        }
    }
}
