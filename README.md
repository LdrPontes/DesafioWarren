# Desafio Mobile da Warren Brasil

Esse desafio foi desenvolvido com o intuito de apresentar alguns conhecimentos e features do Android. Qualquer sugestão ou crítica é bem vinda! :D

Contato:
https://www.linkedin.com/in/ldrpontes/

### Protótipo

https://www.figma.com/file/F6DxaMVeRfLLRXQXEspyzT/Warren-Android?node-id=0%3A1

### MAD Score (15/12/2020)

![alt text](https://i.imgur.com/sEKfEHm.png)

![alt text](https://i.imgur.com/FU9tuHx.png)

![alt text](https://i.imgur.com/VrRnUfd.png)

# O Desafio

### Feature de Login
* Buscar token de acesso na rota de login:
* Exigir login apenas uma vez.     
  ```
  Infos:
  
  POST https://enigmatic-bayou-48219.herokuapp.com/api/v2/account/login
  BODY -> RAW -> JSON: 
  Content-Type: application/json
  
  {
      "email": "mobile_test@warrenbrasil.com",
      "password": "Warren123!"
  }
  ```

### Feature de Lista de Objetivos
* Exibir uma lista ou collection com os objetivos do usuário: 
  ```
  Infos:

  GET https://enigmatic-bayou-48219.herokuapp.com/api/v2/portfolios/mine
  HEADER: access-token: "token obtido no login"
  Content-Type: application/json
  ```

### Feature Detalhes de Objetivo:
* Exibir as informações do objetivo selecionado

## Tecnologias
Recomendamos algumas tecnologias que usamos aqui na Warren (assim você ja vai estar por dentro do nosso estilo!). Se você optar por alguma diferente das recomendadas justifique no PR o motivo.

### iOS
* Swift
* SwiftUI e Combine
* SPM Modules
* Moya/Alamofire
* View Code (Sem storyboard / XIB)
* Paw/Postman/Charles para testar API
* Coordinators
* Swift Composable Architecture (https://github.com/pointfreeco/swift-composable-architecture) ou MVVM.

### Android
* Kotlin
* Retrofit
* Architecture Components
* Coroutines
* Injeção de dependências

## O que será avaliado:

### Principal
* Atenção com UI/UX (Isso é muuuito importante pra gente! Nossos apps são recomendados como inspiração);
* Colaboração através do GitHub. Abrindo Pull Requests para features isoladas no aplicativo, respondendo e resolvendo comentários e sugestões.
* Seguir guidelines e padrões do sistema iOS/Android/Web;
* Estrutura e solução dos problemas;
* Lógica, modularização, organização, clareza de código, escalabilidade e documentação;
* Testes unitários de parte do projeto.

### Interessante: (não fundamental)
* Layout customizado avançado. (Inspiração no nosso app é bem-vinda);
* Arquitetura da solução;
* Transições e animações customizadas;

## Links úteis:

Warren no Android:
https://play.google.com/store/apps/details?id=com.oiwarren.oiwarren

Warren no iOS:
https://itunes.apple.com/br/app/id1114394063

Warren na Web:
https://warrenbrasil.com.br
