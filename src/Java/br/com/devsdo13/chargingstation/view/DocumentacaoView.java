package br.com.devsdo13.chargingstation.view;

import java.util.Scanner;
import br.com.devsdo13.chargingstation.model.DocumentoIdentificacao;

public class DocumentacaoView {

    private final Scanner scanner;

    public DocumentacaoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public DocumentoIdentificacao lerDadosDocumento() {
        System.out.println("\n--- CADASTRO DE DOCUMENTO ---");

        // Tipo do documento com opcoes
        System.out.println("Tipo do documento:");
        System.out.println("  1. CNH");
        System.out.println("  2. RG");
        System.out.println("  3. Passaporte");
        System.out.println("  4. CPF");
        System.out.print("Escolha (numero ou texto): ");
        String tipoDocumento = resolverTipoDocumento(scanner.nextLine().trim());

        System.out.print("Numero do documento: ");
        String codigo = scanner.nextLine().trim();

        System.out.print("Descricao (observacao opcional): ");
        String descricao = scanner.nextLine().trim();
        if (descricao.isEmpty()) {
            descricao = tipoDocumento;
        }

        System.out.print("Pais de origem: ");
        String paisOrigem = scanner.nextLine().trim();

        System.out.print("Estado de origem: ");
        String estadoOrigem = scanner.nextLine().trim();

        return new DocumentoIdentificacao(codigo, descricao, tipoDocumento, paisOrigem, estadoOrigem);
    }

    private String resolverTipoDocumento(String entrada) {
        switch (entrada.toLowerCase()) {
            case "1": case "cnh":         return "CNH";
            case "2": case "rg":          return "RG";
            case "3": case "passaporte":  return "Passaporte";
            case "4": case "cpf":         return "CPF";
            default:
                System.out.println("[!] Tipo nao reconhecido. Usando entrada informada: " + entrada);
                return entrada;
        }
    }

    public void exibirDocumentoCadastrado(DocumentoIdentificacao documento) {
        System.out.println("[OK] Documento '" + documento.getTipoDocumento() + "' (" + documento.getCodigo() + ") cadastrado com sucesso!");
    }
}
