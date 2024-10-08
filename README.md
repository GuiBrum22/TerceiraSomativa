# Sistema de Gestão de RH

## Sobre o Projeto

Bem-vindo ao Sistema de Gestão de RH – uma solução completa para o gerenciamento de funcionários e controle de recursos humanos. Nosso objetivo é fornecer uma plataforma eficiente para automatizar tarefas de RH, como controle de ponto, geração de folha de pagamento, e relatórios detalhados de desempenho.

O Sistema de Gestão de RH foi desenvolvido para ajudar empresas de todos os portes a otimizar seus processos internos e melhorar a gestão de seus funcionários, proporcionando uma visão clara e detalhada do desempenho individual e coletivo.

## Objetivo do Projeto

**Objetivo:** Desenvolvimento e Implementação do Sistema de Gestão de RH

- **Específico:** Criar uma plataforma de gerenciamento de recursos humanos que permita cadastrar e gerenciar funcionários, controlar ponto, gerar folha de pagamento e emitir relatórios detalhados.
- **Mensurável:** A plataforma deve incluir as funcionalidades principais, como cadastro de funcionários, controle de ponto e folha de pagamento, com pelo menos 3 tipos de relatórios disponíveis (por funcionário, departamento e geral).
- **Atingível:** A equipe de desenvolvimento utilizará tecnologias como Java, PostgreSQL, e uma interface gráfica em Swing/JavaFX para entregar uma solução robusta e escalável.
- **Relevante:** O sistema ajudará as empresas a automatizar seus processos de RH, reduzir erros manuais e melhorar a produtividade.
- **Temporal:** A conclusão do sistema está prevista para 4 meses, com a primeira versão pronta para testes em 12 semanas e o lançamento final 2 semanas após os testes.

## Funcionalidades

- Cadastro e Gerenciamento de Funcionários
- Controle de Ponto (Entrada/Saída)
- Geração de Folha de Pagamento
- Relatórios Detalhados por Funcionário e Departamento
- Persistência de Dados com PostgreSQL/MongoDB
- Interface Gráfica com Swing/JavaFX

## Cronograma

- **Fase de Planejamento (2 semanas)**
  - Definição de requisitos e escopo
  - Planejamento técnico e de recursos

- **Fase de Design (3 semanas)**
  - Criação de wireframes e protótipos da interface
  - Revisão e aprovação do design com stakeholders

- **Fase de Desenvolvimento (8 semanas)**
  - Implementação do backend em Java
  - Desenvolvimento da interface gráfica em Swing/JavaFX
  - Integração com banco de dados PostgreSQL

- **Fase de Testes (2 semanas)**
  - Testes de integração e usabilidade
  - Correção de bugs e melhorias

- **Fase de Lançamento (1 semana)**
  - Preparação do ambiente de produção
  - Lançamento e entrega do sistema

## Análise de Risco

- **Risco: Atrasos no Desenvolvimento**
  - **Probabilidade:** Média
  - **Impacto:** Alto
  - **Mitigação:** Estabelecer marcos claros, revisar o progresso semanalmente e ajustar as prioridades conforme necessário.

- **Risco: Integração com o Banco de Dados**
  - **Probabilidade:** Média
  - **Impacto:** Médio
  - **Mitigação:** Garantir que a arquitetura do sistema permita uma fácil integração e realizar testes frequentes durante o desenvolvimento.

- **Risco: Desempenho da Interface Gráfica**
  - **Probabilidade:** Baixa
  - **Impacto:** Alto
  - **Mitigação:** Otimizar o código e testar a interface em diferentes ambientes.

- **Risco: Problemas com Escalabilidade**
  - **Probabilidade:** Baixa
  - **Impacto:** Médio
  - **Mitigação:** Implementar boas práticas de POO e garantir que a arquitetura permita a escalabilidade futura.

## Recursos

- **Equipe de Desenvolvimento**
  - Desenvolvedores Java
  - Especialista em interface gráfica (Swing/JavaFX)
  - Administrador de banco de dados
  - Testadores de software

- **Tecnologias e Ferramentas**
  - **Backend:** Java
  - **Frontend:** Swing/JavaFX
  - **Banco de Dados:** PostgreSQL ou MongoDB
  - **Infraestrutura:** Hospedagem local ou em nuvem (AWS, Google Cloud)
  - **Ferramentas de Gestão:** Trello, GitHub

## Identidade Visual

- **Nome da Marca:** Sistema de Gestão de RH
- **Slogan:** “Automatize e Simplifique seu RH”
- **Fontes:** Arial, Helvetica

## Diagramas

1. Classe

```mermaid
classDiagram
    class Funcionario {
        +int id
        +string nome
        +string cargo
        +double salario
        +registrarPonto()
        +calcularSalario()
    }

    class Departamento {
        +int id
        +string nome
        +gerarRelatorio()
    }

    class Ponto {
        +int id
        +int funcionarioId
        +datetime entrada
        +datetime saida
        +calcularHoras()
    }

    Funcionario "1" -- "1" Departamento : pertence >
    Funcionario "1" -- "0..*" Ponto : registra >

```

  2. Uso
```mermaid
  flowchart TD
    A[Início] --> B{Tipo de Ação}
    B -->|RH| C[Cadastrar Funcionário]
    B -->|Funcionário| D[Registrar Ponto]
    B -->|RH| E[Gerar Folha de Pagamento]
    B -->|RH| F[Emitir Relatórios]

    C --> G[Fim]
    D --> G
    E --> G
    F --> G

```

  3. Fluxo
```mermaid
  flowchart TD
    A[Início] --> B{Funcionário logado?}
    B -- Sim --> C[Registrar Ponto]
    B -- Não --> D[Login]
    D --> E[Cadastrar Funcionário ou Login]

    E --> C
    C --> F[Verificar Horas]
    F --> G[Fim]

    B --> H[RH gera folha de pagamento]
    H --> G

```

# Manual de Usuario

## Descrição
O **Sistema de Gestão de RH** é uma aplicação desktop desenvolvida em Java, utilizando a biblioteca Swing para a interface gráfica. O sistema permite gerenciar o cadastro de funcionários, registrar o ponto eletrônico, gerar a folha de pagamento e emitir relatórios detalhados.

## Funcionalidades
- **Cadastro de Funcionários**: Permite adicionar novos funcionários ao sistema, preenchendo informações como nome, cargo e departamento.
- **Registro de Ponto**: Permite registrar o ponto eletrônico dos funcionários, controlando a entrada e saída.
- **Geração de Folha de Pagamento**: Gera a folha de pagamento de acordo com os registros de ponto e dados salariais.
- **Listar Funcionários**: Exibe uma lista de todos os funcionários cadastrados no sistema.
- **Geração de Relatórios**: Gera relatórios detalhados sobre o quadro de funcionários, folha de pagamento e pontos registrados.

## Pré-requisitos
- Java 8 ou superior.
- Ambiente de desenvolvimento integrado (IDE) com suporte para Java, como Eclipse ou IntelliJ IDEA.
- Biblioteca Swing para criação da interface gráfica (já inclusa no JDK padrão).

## Como Executar
1. Clone ou faça o download do código-fonte.
2. Abra o projeto em sua IDE de preferência.
3. Compile e execute a classe principal: `RHManagementSystem.java`.
4. A janela principal do sistema será exibida com as opções de gerenciamento de RH.

## Interface Gráfica
Ao executar o sistema, a interface gráfica exibirá as seguintes opções:

- **Cadastrar Funcionário**: Abre uma nova janela onde você pode adicionar um novo funcionário.
- **Registrar Ponto**: Permite o registro de ponto de um funcionário existente.
- **Gerar Folha de Pagamento**: Gera um arquivo de folha de pagamento com base nos dados do funcionário.
- **Listar Funcionários**: Exibe uma tabela com todos os funcionários cadastrados no sistema.
- **Gerar Relatórios**: Permite gerar relatórios detalhados, exportando-os em formato de texto.

## Contato
Para dúvidas ou mais informações, entre em contato com o desenvolvedor.

