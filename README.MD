# job4j_url_shortcut

Учебный проект "Сервис - UrlShortCut"
1. Сервис работает через REST API
2. Сервис может зарегистрировать сайт через запрос POST /registration с телом запроса {site : "url"}. Результатом выполения будет уникальный код.
3. Переадресация на сайт происходит через вызов GET /redirect/CODE . 
4. Переадресация происходит без авторизации, регистрация сайта на сервисе с авторизацией
5. Авторизация через JWT
6. Сервис предоставляет статистику кол-ва запросов каждого адреса

[![Java CI with Maven](https://github.com/KarnaukhovKirill/job4j_url_shortcut/actions/workflows/github-actions-demo.yml/badge.svg)](https://github.com/KarnaukhovKirill/job4j_url_shortcut/actions/workflows/github-actions-demo.yml)