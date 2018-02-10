# Funcionamento da aplicação:

A aplicação foi feita utilizando os seguintes frameworks: spring boot, flyway db, bootstrap, jpa hibernate.

Como a aplicação possui o flyway, ao ser inicializada, ela criará o banco de dados sozinha.

Para que a aplicação rode não há a necessidade de tomcat, pois ela já tem um tomcat embutido.

Basta rodar um mvn install utilizando alguma IDE:

Recomendado executar o mvn install da seguinte maneira: No eclipse, ou spring tool suite:

1) Importar projeto insight como existing maven project
2) Botão direito -> Run as 5 Maven build...
3) Goals: clean install
4) Run

Após isso será gerado o war que deverá ser explodido com o seguinte comando no cmd:
java -jar insight-0.0.1-SNAPSHOT.war dentro da pasta duxus-desafio-fork-leonardo-prog\insight\target

# Requisitos para a aplicação subir:

Necessário Java JRE 1.8.
Necessário Mysql.

# Alterações que devem ser feitas a fim de que crie o banco de dados:

Na pasta a seguir: duxus-desafio-fork-leonardo-prog\insight\src\main\resources
o arquivo application.properties possui nome de usuário e senha para o banco de dados, como a seguir:

spring.datasource.username=root
spring.datasource.password=4142135

Basta alterar root para o nome de usuário do banco de dados MySql instalado, e também a senha.

spring.datasource.username=USUARIO
spring.datasource.password=SENHA

# Ao startar a aplicação:

Acesse a tela inicial via: localhost:8080/login

Usuário: anderson
Senha: anderson

# API:

Para consumir os dados da API: seguem as urls:

Para buscar um único portifólio por id: localhost:8080/buscar/{id}
Para buscar todos os portifólios do banco: localhost:8080/buscar/all

