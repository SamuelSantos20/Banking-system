
💻 Sistema Bancário

🏦
Este projeto é uma aplicação simples de um Sistema Bancário desenvolvido em Java, utilizando Spring Boot com Spring Security e autenticação em Base64. O sistema permite gerenciar bancos, clientes e contas, realizar operações bancárias e acompanhar o histórico de transações. 🚀

⚙️ Funcionalidades

🧑‍💼 Gerenciamento de Clientes: Criação e gerenciamento de clientes com nome e CPF.

🏦 Gerenciamento de Contas Bancárias: Criação de contas bancárias associadas a clientes.

🔒 Autenticação Segura: Autenticação de usuários utilizando Spring Security com criptografia em Base64, onde o CPF é utilizado como identificador para login.

💸 Operações Bancárias: Realizar depósitos e saques em contas.

📊 Consulta de Saldo: Visualização do saldo atual da conta.

🕒 Histórico de Transações: Registro detalhado de todas as transações realizadas (depósitos, saques).

🔗 Relação entre Bancos e Contas: Cada banco pode gerenciar múltiplas contas.

🔒 Segurança

O sistema utiliza o Spring Security para garantir a segurança das operações bancárias, com autenticação de usuários via credenciais em Base64. Apenas usuários autenticados podem acessar as funcionalidades de gerenciamento de contas e realizar transações.

Autenticação Basic (Base64): Implementada para proteger o acesso ao sistema, exigindo que os usuários enviem suas credenciais (CPF e senha) codificadas em Base64 no cabeçalho da requisição HTTP.

🛠️ Tecnologias Utilizadas

Java 17 ☕

Spring Boot para a configuração e execução da aplicação

Spring Security para autenticação e controle de acesso

JPA (Java Persistence API) para mapeamento objeto-relacional

MySQL como banco de dados relacional

H2 Database (para testes, pode ser removido em produção)
