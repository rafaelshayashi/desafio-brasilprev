# Desafio Brasilprev

Construir uma API REST com as funcionalidades de um cadastro de clientes
(nome, cpf, endereco)

## TODO

- [X] model e repository
- [X] controller e service
  - [X] Validação básica dos dados de entrada
  - [X] Handle exception
  - [X] i18n para as messagens de erros
- [X] Refatoração
  - [X] Organização dos packages
  - [X] Validação CPF
  - [X] Validação unicidade
- [ ] Testes
- [X] Docker e deploy Heroku
- [X] Documentaçao OpenAPI


## Dependências

* Java 8
* Maven 3.6.0


## Controle de versão e fluxo de trabalho

Foi utilizado o git flow para o desenvolvimento desse desafio, sendo
cada que cada implementação de feature foi feita em um branch
especifico, seguindo o `feature/nome-da-feature`.

Após o termino da feature foi aberto um PR para a branch `develop`. Para
poder melhor visualizar o historico do projeto pode visualizar as PR
fechados em
[PR finalizados](https://github.com/rafaelshayashi/desafio-brasilprev/pulls?q=is%3Apr+is%3Aclosed)


## Executando

```bash
mvn install
mvn docker:build
docker container run -p 8080:8080 rafaelshayashi/brasilprev
```

## Consumindo

O Deploy foi efetuado no Heroku, podendo ser acesso através
https://rafaelshayashi-brasilprev.herokuapp.com/api/v1

A documentação (OpenApi) pode ser acessada através
https://rafaelshayashi-brasilprev.herokuapp.com/swagger-ui.html
