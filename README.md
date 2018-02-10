# Desafio de Desenvolvimento

O objetivo deste desafio é obter uma ideia das habilidades que o candidato possui, da organização de tempo e também do código.

_Imagine que você é um freelancer que deseja construir uma aplicação WEB para gerenciar seu portfolio de trabalhos e exibir para os seus possíveis clientes, sendo um desenvolvedor, você decide criar uma solução customizada._

Sua tarefa é construir a melhor solução no tempo combinado, considerando os requisitos que estarão descritos abaixo.

**Você pode usar a criatividade pois não existe uma solução definitiva para o desafio.**

## Considerações Importantes

- Use seu tempo de forma inteligente. Uma solução simples primeiro e depois avance.
- **Você pode utilizar bibliotecas e frameworks, não reinvente a roda.**
- Comentários sempre são bem-vindos em métodos ou estruturas mais complexas.
- **Faça testes unitários**, não existe necessidade de 100% de cobertura, mas construa testes que validem sua solução.
- Faça commits **frequentes**, assim podemos ver a evolução da sua solução.


## Itens Obrigatórios

Algums itens são obrigatórios pois fazem parte da avaliação. São eles:

- Utilize Java `>= 7`;
- O pacote final da sua solução deve ser um arquivo WAR, será executado em um Tomcat;
- Se sua aplicação necessitar de configurações (ex: banco de dados), inclua as indicações em um arquivo `setup.md` na raíz do projeto;
- **Também inclua no `setup.md` como podemos executar a sua aplicação;**

## Funcionalidades

### 1) Listagem e Cadastro

Você precisará de um CRUD para os trabalhos do portfolio. É interessante incluir as informações abaixo em cada item:

- Apelido do projeto
- Nome do Cliente
- Descrição
- Screenshot (url da imagem é suficiente)
- Status (em desenvolvimento ou concluído)

### 2) Exportação

Você que ficar seguro e não quer correr o risco de perder o cadastro de todo seu portfolio, assim você decide incluir uma opção para download do conteúdo armazenado em algum formato de texto (JSON, XML ou CSV).

### 3) API

Uma aplicação mobile apresentando seu portfolio funcionaria muito bem para mostrar suas habilidades, então para não ter suas informações em dois locais você decide **incluir no sistema do seu portfolio uma API** para obter os dados cadastrados. Sua API precisará de dois endpoints:

- Um para listar todos os trabalhos;
- Outro para obter informação sobre um trabalho específico (pelo ID por exemplo);

### 4) Pesquisa

Os seus possíveis clientes podem querer pesquisar no seu grande portfolio, uma forma de fazer essa pesquisa seria por tags nos itens do portfolio.

As tags podem ser textos como `ecommerce`, `html5`, `php`, `css3`, `java`, etc.

## Extras

Se houver tempo, você pode desenvolver os pontos abaixo.

- Inclua alguma autenticação na sua aplicação, afinal você não quer ninguém cadastrando ou removendo itens do seu portfolio;
- Melhorias na apresentação (UI/UX);
- Uma autenticação na API também seria interessante;