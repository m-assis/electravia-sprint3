package br.com.devsdo13.chargingstation.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import br.com.devsdo13.chargingstation.model.Abastecimento;
import br.com.devsdo13.chargingstation.model.BombaAbastecimento;
import br.com.devsdo13.chargingstation.model.DocumentoIdentificacao;
import br.com.devsdo13.chargingstation.model.Pagamento;
import br.com.devsdo13.chargingstation.model.PostoAbastecimento;
import br.com.devsdo13.chargingstation.model.Status;
import br.com.devsdo13.chargingstation.model.Usuario;
import br.com.devsdo13.chargingstation.model.Veiculo;
import br.com.devsdo13.chargingstation.exception.RegraNegocioException;

public class Main {

    // Listas globais de dados cadastrados
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<PostoAbastecimento> postos = new ArrayList<>();
    // Histórico de abastecimentos por nome de usuário (usando Stack/Pilha)
    private static Map<String, Stack<Abastecimento>> historicoAbastecimentos = new HashMap<>();

    // Status reutilizáveis
    private static Status statusEmAndamento = new Status("AND", "Em andamento");
    private static Status statusConcluido = new Status("CON", "Concluido");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciando todas as Views
        MenuView menuView = new MenuView(scanner);
        UsuarioView usuarioView = new UsuarioView(scanner);
        VeiculoView veiculoView = new VeiculoView(scanner);
        EnderecoView enderecoView = new EnderecoView(scanner);
        DocumentacaoView documentacaoView = new DocumentacaoView(scanner);
        CadastroView cadastroView = new CadastroView(scanner);
        PostoAbastecimentoView postoView = new PostoAbastecimentoView(scanner);
        BombaAbastecimentoView bombaView = new BombaAbastecimentoView(scanner);
        AbastecimentoView abastecimentoView = new AbastecimentoView(scanner);
        PagamentoView pagamentoView = new PagamentoView(scanner);

        boolean executando = true;

        while (executando) {
            // NOVO: Adicione a opção "5 - Buscar abastecimentos" no método exibirMenuPrincipal() da sua MenuView
            int opcao = menuView.exibirMenuPrincipal();

            switch (opcao) {
                case 1:
                    cadastrarUsuario(menuView, usuarioView, veiculoView, enderecoView, documentacaoView, cadastroView);
                    break;
                case 2:
                    cadastrarPosto(menuView, postoView, enderecoView, bombaView);
                    break;
                case 3:
                    realizarAbastecimento(menuView, usuarioView, veiculoView, postoView, bombaView, abastecimentoView, pagamentoView);
                    break;
                case 4:
                    consultarHistorico(usuarioView, abastecimentoView);
                    break;
                case 5: // NOVO: Chamada para o Item C (Busca)
                    buscarAbastecimento(menuView, scanner);
                    break;
                case 0:
                    menuView.exibirMensagem("\nEncerrando o sistema. Ate logo!");
                    executando = false;
                    break;
                default:
                    menuView.exibirMensagem("[!] Opcao invalida. Tente novamente.");
            }
        }

        scanner.close();
    }

    // =====================================================================
    // FLUXO 1: CADASTRO DE USUARIO (Mantido inalterado)
    // =====================================================================
    private static void cadastrarUsuario(MenuView menuView, UsuarioView usuarioView, VeiculoView veiculoView,
                                         EnderecoView enderecoView, DocumentacaoView documentacaoView,
                                         CadastroView cadastroView) {
        boolean cadastrando = true;
        Usuario usuario = null;

        while (cadastrando) {
            int opcao = menuView.exibirSubMenuUsuario();

            switch (opcao) {
                case 1:
                    String nome = usuarioView.lerNomeUsuario();
                    usuario = new Usuario(nome);
                    usuarioView.exibirUsuarioCriado(usuario);
                    break;

                case 2:
                    if (usuario == null) {
                        menuView.exibirMensagem("[!] Primeiro informe os dados pessoais (opcao 1).");
                        break;
                    }
                    String[] dadosEndereco = enderecoView.lerDadosEndereco();
                    usuario.setEnderecoUsuario(dadosEndereco[0], dadosEndereco[1], dadosEndereco[2],
                            dadosEndereco[3], dadosEndereco[4], dadosEndereco[5]);
                    enderecoView.exibirEnderecoCadastrado();
                    break;

                case 3:
                    if (usuario == null) {
                        menuView.exibirMensagem("[!] Primeiro informe os dados pessoais (opcao 1).");
                        break;
                    }
                    DocumentoIdentificacao documento = documentacaoView.lerDadosDocumento();
                    usuario.setAdicionarDocumento(documento);
                    documentacaoView.exibirDocumentoCadastrado(documento);
                    break;

                case 4:
                    if (usuario == null) {
                        menuView.exibirMensagem("[!] Primeiro informe os dados pessoais (opcao 1).");
                        break;
                    }
                    String[] dadosCadastro = cadastroView.lerDadosCadastro();
                    usuario.setCadastroUsuario(dadosCadastro[0], dadosCadastro[1], dadosCadastro[2]);
                    cadastroView.exibirCadastroCriado();
                    break;

                case 5:
                    if (usuario == null) {
                        menuView.exibirMensagem("[!] Primeiro informe os dados pessoais (opcao 1).");
                        break;
                    }
                    Veiculo veiculo = veiculoView.lerDadosVeiculo();
                    usuario.setAdicionarVeiculo(veiculo);
                    veiculoView.exibirVeiculoCadastrado(veiculo);
                    break;

                case 6:
                    if (usuario == null) {
                        menuView.exibirMensagem("[!] Nenhum usuario foi criado. Voltando ao menu principal.");
                    } else {
                        usuarios.add(usuario);
                        historicoAbastecimentos.put(usuario.getNomeUsuario(), new Stack<>());
                        usuarioView.exibirResumoUsuario(usuario);
                        menuView.exibirMensagem("\n[OK] Cadastro do usuario finalizado e salvo!");
                    }
                    cadastrando = false;
                    break;

                default:
                    menuView.exibirMensagem("[!] Opcao invalida. Tente novamente.");
            }
        }
    }

    // =====================================================================
    // FLUXO 2: CADASTRO DE POSTO DE ABASTECIMENTO (Mantido inalterado)
    // =====================================================================
    private static void cadastrarPosto(MenuView menuView, PostoAbastecimentoView postoView,
                                       EnderecoView enderecoView, BombaAbastecimentoView bombaView) {
        boolean cadastrando = true;
        PostoAbastecimento posto = null;

        while (cadastrando) {
            int opcao = menuView.exibirSubMenuPosto();

            switch (opcao) {
                case 1:
                    String[] dadosPosto = postoView.lerDadosPosto();
                    posto = new PostoAbastecimento(dadosPosto[0], dadosPosto[1], dadosPosto[2]);

                    menuView.exibirMensagem("\nAgora informe o endereco do posto:");
                    String[] dadosEndereco = enderecoView.lerDadosEndereco();
                    posto.setEnderecoPosto(dadosEndereco[0], dadosEndereco[1], dadosEndereco[2],
                            dadosEndereco[3], dadosEndereco[4], dadosEndereco[5]);
                    postoView.exibirPostoCriado(posto);
                    break;

                case 2:
                    if (posto == null) {
                        menuView.exibirMensagem("[!] Primeiro informe os dados do posto (opcao 1).");
                        break;
                    }
                    BombaAbastecimento bomba = bombaView.lerDadosBomba();
                    posto.setAdicionarBomba(bomba);
                    bombaView.exibirBombaCadastrada(bomba);
                    break;

                case 3:
                    if (posto == null) {
                        menuView.exibirMensagem("[!] Nenhum posto foi criado. Voltando ao menu principal.");
                    } else {
                        postos.add(posto);
                        menuView.exibirMensagem("[OK] Posto '" + posto.getDescricaoPosto() + "' salvo com "
                                + posto.getListaBombas().size() + " bomba(s)!");
                    }
                    cadastrando = false;
                    break;

                default:
                    menuView.exibirMensagem("[!] Opcao invalida. Tente novamente.");
            }
        }
    }

    // =====================================================================
    // FLUXO 3: REALIZAR ABASTECIMENTO (COM TRY/CATCH)
    // =====================================================================
    private static void realizarAbastecimento(MenuView menuView, UsuarioView usuarioView, VeiculoView veiculoView,
                                              PostoAbastecimentoView postoView, BombaAbastecimentoView bombaView,
                                              AbastecimentoView abastecimentoView, PagamentoView pagamentoView) {

        if (usuarios.isEmpty() || postos.isEmpty()) {
            menuView.exibirMensagem("[!] Faltam cadastros de usuario ou posto.");
            return;
        }

        try {
            // 1. Selecionar Usuário e Veículo
            menuView.exibirMensagem("\n============= NOVO ABASTECIMENTO =============");
            int idxUsuario = usuarioView.selecionarUsuario(usuarios);
            if (idxUsuario == -1) return;
            Usuario usuario = usuarios.get(idxUsuario);

            if (usuario.getVeiculos().isEmpty()) {
                // NOVO: Disparando a exceção personalizada
                throw new RegraNegocioException("Usuario nao possui veiculos cadastrados para abastecer.");
            }
            int idxVeiculo = veiculoView.selecionarVeiculo(usuario.getVeiculos());
            if (idxVeiculo == -1) return;
            Veiculo veiculo = usuario.getVeiculos().get(idxVeiculo);

            // 2. Selecionar Posto e Bomba
            int idxPosto = postoView.selecionarPosto(postos);
            if (idxPosto == -1) return;
            PostoAbastecimento posto = postos.get(idxPosto);

            if (posto.getListaBombas().isEmpty()) {
                // NOVO: Disparando a exceção personalizada
                throw new RegraNegocioException("O posto selecionado nao possui bombas cadastradas.");
            }
            int idxBomba = bombaView.selecionarBomba(posto.getListaBombas());
            if (idxBomba == -1) return;
            BombaAbastecimento bomba = posto.getListaBombas().get(idxBomba);

            // 3. Informar valores
            double valorKw = abastecimentoView.lerValorKw();
            if (valorKw <= 0) {
                // NOVO: Disparando a exceção personalizada
                throw new RegraNegocioException("O valor do kW deve ser maior que zero.");
            }

            Abastecimento abastecimento = new Abastecimento(usuario, veiculo, bomba, statusEmAndamento, valorKw);
            abastecimentoView.exibirAbastecimentoIniciado();

            double quantidade = abastecimentoView.lerQuantidadeAbastecida();
            if (quantidade <= 0) {
                throw new RegraNegocioException("A quantidade abastecida deve ser maior que zero.");
            }

            // 4. Finalizar e Pagar
            abastecimento.finalizarAbastecimento(quantidade);
            abastecimento.setStatus(statusConcluido);
            abastecimentoView.exibirAbastecimentoFinalizado(abastecimento);

            String formaPagamento = pagamentoView.lerFormaPagamento();
            abastecimento.realizarPagamento(formaPagamento);
            pagamentoView.exibirPagamentoRealizado(abastecimento.getPagamento());

            // 5. Salvar no histórico
            historicoAbastecimentos.computeIfAbsent(usuario.getNomeUsuario(), k -> new Stack<>()).push(abastecimento);

            menuView.exibirMensagem("\n[OK] Abastecimento registrado com sucesso!");

            // NOVO: Captura da exceção de regra de negócio
        } catch (RegraNegocioException e) {
            menuView.exibirMensagem("\n[ERRO DE NEGOCIO] " + e.getMessage());
            menuView.exibirMensagem("Operacao de abastecimento cancelada.");
        } catch (Exception e) {
            menuView.exibirMensagem("\n[ERRO INESPERADO] " + e.getMessage());
        }
    }

    // =====================================================================
    // FLUXO 4: CONSULTAR HISTÓRICO DE ABASTECIMENTOS (Mantido inalterado)
    // =====================================================================
    private static void consultarHistorico(UsuarioView usuarioView, AbastecimentoView abastecimentoView) {
        if (usuarios.isEmpty()) return;

        int idxUsuario = usuarioView.selecionarUsuario(usuarios);
        if (idxUsuario == -1) return;
        Usuario usuario = usuarios.get(idxUsuario);

        Stack<Abastecimento> historico = historicoAbastecimentos.get(usuario.getNomeUsuario());
        if (historico == null || historico.isEmpty()) {
            System.out.println("[!] Nenhum abastecimento registrado para " + usuario.getNomeUsuario() + ".");
            return;
        }

        abastecimentoView.exibirHistoricoAbastecimentos(usuario.getNomeUsuario(), historico);
    }

    // =====================================================================
    // NOVO: FLUXO 5 (ITEM C): BUSCA NOS DADOS
    // =====================================================================
    /**
     * ITEM C: Busca nos dados
     *
     * Estratégia de busca: Busca Sequencial (Linear Search)
     * Justificativa: Optamos pela busca sequencial pois estamos procurando por um atributo
     * de texto parcial (nome do usuario) dentro das chaves de um Hashmap. Como as chaves de um
     * HashMap nao estao ordenadas alfabeticamente (e sim por hash), nao podemos utilizar a busca
     * binaria diretamente. Alem disso, o volume de chaves (usuarios) em um contexto de teste é pequeno,
     * tornando a busca sequencial a abordagem mais simples e correta tecnicamente.
     */
    private static void buscarAbastecimento(MenuView menuView, Scanner scanner) {
        menuView.exibirMensagem("\n============= BUSCA DE DADOS =============");
        System.out.print("Digite o nome (ou parte do nome) do usuario para buscar os abastecimentos: ");
        String termoBusca = scanner.nextLine().toLowerCase();

        boolean encontrouAlguem = false;

        // Implementação da busca sequencial iterando sobre as chaves do Map
        for (Map.Entry<String, Stack<Abastecimento>> entry : historicoAbastecimentos.entrySet()) {
            String nomeUsuarioMap = entry.getKey();

            // Se o nome contiver o termo de busca, exibe os resultados
            if (nomeUsuarioMap.toLowerCase().contains(termoBusca)) {
                encontrouAlguem = true;
                Stack<Abastecimento> historico = entry.getValue();

                System.out.println("\n--- Resultados encontrados para: " + nomeUsuarioMap + " ---");
                System.out.println("Total de recargas: " + historico.size());

                for (Abastecimento abast : historico) {
                    System.out.println("- Data: " + abast.getDataInicio() +
                            " | Qtd: " + abast.getQuantidadeAbastecida() + " kW" +
                            " | Valor Total: R$ " + String.format("%.2f", abast.getCustoTotal()));
                }
            }
        }

        if (!encontrouAlguem) {
            menuView.exibirMensagem("[!] Nenhuma recarga encontrada para usuarios contendo: '" + termoBusca + "'");
        }
    }
}
