# medicalEldorado

Projeto para cadastro de Médico e Usuário para agendamento de compromisso entre as partes.
Controle de acesso por login/sessão de usuário.

Projeto back-end desenvolvido em JAVA com Spring Boot.

Para inciar o projeto crie uma base em MySQL como nome "db_medical"
Para utilização dos endpoints é necessário fazer a autenticação do usuário através de um e-mail.
endpoint signup: http://localhost:8080/users/signup
  - realiza cadastro do usuário caso não exista
  - após cadastro, armazena usuário na sessão para utilização dos demais serviços
endpoint signin: http://localhost:8080/users/signin
  - armazena usuário na sessão para utilização dos demais serviços
endpoint signin: http://localhost:8080/users/signout
  - realiza logoff, invalida a sessão no spring 
 
Projeto back-end concluído 100%!


Projeto front-end desenvolvido em Angular 8.
  - Pendente...
