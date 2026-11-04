# 🚀 Sprint 3 - ElectraVia

**Equipa:** Devs do Treze (Jorge, Leo, S, Shirley, Vitor, Mike)  
**Projeto:** Sistema de Gestão Inteligente para Redes de Recarga de Veículos Elétricos (ElectraVia)

---

## 🎯 1. Resumo das Entregas (Sprint 3)
Esta entrega consolida o avanço do projeto nas vertentes de engenharia de dados, robustez em programação orientada a objetos, algoritmos de busca e gestão ágil:

* **[x] a) Projeto físico da base de dados:** Criação do script DDL completo (11 tabelas no SQL Server) com tipagens corretas, chaves primárias (PK), chaves estrangeiras (FK) e restrições de integridade (`charging_station`).
* **[x] b) Robustez e orientação a objetos:** Implementação de encapsulamento, herança (ex: classe `Auditoria`), polimorfismo e tratamento robusto de exceções através da criação da classe `RegraNegocioException` com blocos `try/catch`.
* **[x] c) Busca nos dados:** Desenvolvimento do algoritmo de Busca Sequencial (Linear Search) aplicado ao histórico de abastecimentos, justificado tecnicamente pela ausência de ordenação alfabética prévia na coleção.
* **[x] d) Ciência de dados:** Levantamento inicial de perguntas de negócio e cálculo de métricas simuladas para análise exploratória.
* **[x] e) Ritmo ágil e Kanban:** Organização das tarefas da Sprint e das Histórias de Usuário futuras utilizando o GitHub Projects como quadro Kanban.

---

## 📊 2. Primeiro Olhar de Ciência de Dados (Item D)

**Perguntas de Negócio Mapeadas:**
1. **Quais são os postos e bombas com maior volume de utilização diária?** (Para direcionar manutenções preventivas e priorizar a expansão da rede).
2. **Quais são os horários de pico e de ociosidade nas estações de recarga?** (Para fundamentar futuras políticas de preços dinâmicos e incentivar o uso fora de pico).
3. **Qual é o perfil de consumo médio (em kW e em Reais) por sessão de recarga?** (Para prever o fluxo de caixa e otimizar a compra de energia).

**Métricas e Análise Exploratória (Dados de Exemplo):**
* **Ticket Médio por Recarga:** R$ 52,50 (permite projetar receitas futuras e balizar campanhas de fidelização).
* **Energia Média por Sessão:** 35 kW (essencial para o dimensionamento da infraestrutura elétrica dos postos, evitando sobrecargas locais).
* **Taxa de Ocupação no Horário de Pico (18h-21h):** 85% das bombas em uso (indica a necessidade iminente de expansão física nos pontos de maior movimento).

---

## 🔄 3. Organização Ágil e Retrospectiva (Item F)

**Rastreabilidade com o Backlog (Fase 1):**
* A implementação do algoritmo de busca no histórico (Item C) atendeu diretamente aos requisitos da **HU05** (*Como motorista, quero consultar o histórico detalhado das recargas*).
* A estrutura da base de dados (Item A) e a refatoração orientada a objetos do fluxo de abastecimento (Item B) estabeleceram a fundação técnica necessária para a **HU06** (*registrar início e término de cada sessão*) e **HU07** (*assinar plano e cadastrar meios de pagamento*).

**Retrospectiva da Sprint 3:**
* **O que funcionou bem:** A divisão de tarefas entre os membros foi altamente eficiente; o adiantamento da infraestrutura de banco de dados e das classes base permitiu direcionar o foco total para a lógica de busca e métricas na reta final. O trabalho de pareamento para solucionar exceções e erros de execução do código Java foi ágil.
* **O que atrapalhou:** Instabilidades técnicas com o ambiente local (nomeadamente falhas no Oracle Data Modeler no Windows) e a fragmentação inicial de ficheiros de projeto em ferramentas de nuvem (OneDrive/Google Drive), o que atrasou a consolidação do script DDL.
* **Plano de Melhoria para a Próxima Sprint:** Padronizar as ferramentas e versões de desenvolvimento (como o JDK) entre todos os membros antes do início das tarefas e abandonar o armazenamento de código em pastas sincronizadas por nuvem, focando exclusivamente no versionamento contínuo através deste repositório no GitHub.

---

## 📁 Estrutura do Repositório
* `bd/` — Contém o script DDL oficial (`electravia_ddl_sprint3.sql`).
* `src/` — Código-fonte em Java puro estruturado em arquitetura limpa (MVC com classes de Modelo, Views e exceções personalizadas).
* `docs/` — Relatórios complementares e documentações auxiliares da Sprint.
