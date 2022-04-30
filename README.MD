# job4j_url_shortcut

Прроект "Сервис - UrlShortCut". Сервис служит для сокращения url. По сокращенному URl сервис перенаправляет клиента на 
нужный ресурс.

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

## Запуск приложение в Docker ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

:point_right: Для запуска понадобиться Docker. [install manual](https://docs.docker.com/engine/install/)

:point_right: И docker-compose для упрощения запуска сервиса. [install manual](https://docs.docker.com/compose/install/)

Создаём кластер для хранения данных. Если 
````
docker run -d \
    --name postgres \
    -e POSTGRES_PASSWORD=password \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -p 5432:5432 \
    -v postgres_db:/var/lib/postgresql/data \
    postgres
````

Создаём файл docker-compose.yml и копируем туда содержимое файла по ссылке ниже
````
https://raw.githubusercontent.com/KarnaukhovKirill/job4j_url_shortcut/main/docker-compose.yml
````

Запускаем консоль в той же папке, что и файл docker-composer.yml и выполняем команду
````
docker-compose up
````

## Как пользоваться 
#### Регистрация в сервисе
````
curl --location --request POST 'localhost:8080/registration' --header 'Content-Type: application/json' --data-raw '{"site": "{your_site}}"}'
````
В ответном JSON вы получите логин и пароль, который нужен для авторизации

![alt text](https://github.com/KarnaukhovKirill/job4j_url_shortcut/blob/main/img/Registration.png)
#### Авторизация в сервисе
````
curl --location --request POST 'localhost:8080/login' --header 'Content-Type: application/json' --data-raw '{"login": "{your_login}", "password": "{your_password}"}'
````
В headers ответа будет указан header 'Authorization'. Он содержит JWT токен для поддержания сессии.

![alt text](https://github.com/KarnaukhovKirill/job4j_url_shortcut/blob/main/img/Auth.png)
#### Сокращение url
````
curl --location --request POST 'localhost:8080/convert' --header 'Authorization: {your_JWT_token}' --header 'Content-Type: application/json' --data-raw '{"url": "{your_full_url}"}'
````
В ответном JSON будет сокращённый код url

![alt text](https://github.com/KarnaukhovKirill/job4j_url_shortcut/blob/main/img/Convert.png)
#### Redirect на нужный ресурс по сокращённой ссылке
````
localhost:8080/redirect/{your_code}
````
#### Статистика вызовов 
Для просмотра использования redirect все url вашего домена можно запросить следующий запрсо
````
curl --location --request GET 'localhost:8080/statistic' --header 'Authorization: {your_JWT_code}'
````
![alt text](https://github.com/KarnaukhovKirill/job4j_url_shortcut/blob/main/img/Statistic.png)

### TODO
- [ ] tests

## Контакты

Есть вопросы по проекту? Свяжитесь со мной в [![alt text](https://badges.aleen42.com/src/telegram.svg)](https://t.me/Tur_Boyama)

Или пишите на почту: karnaukhov.kirill23@gmail.com

[![Java CI with Maven](https://github.com/KarnaukhovKirill/job4j_url_shortcut/actions/workflows/github-actions-demo.yml/badge.svg)](https://github.com/KarnaukhovKirill/job4j_url_shortcut/actions/workflows/github-actions-demo.yml)