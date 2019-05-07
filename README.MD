# DK-021

 Technology     | Badge |
|:--------------:|:-----:|
| License        | [![LICENSE](https://img.shields.io/badge/LICENSE-Apache%202.0-blue.svg)](LICENSE.md) |
| Travis CI      | [![Build Status](https://travis-ci.org/ArtemMurk/dk021.svg?branch=master)](https://travis-ci.org/ArtemMurk/dk021) |


## Информация о проекте
Данный микросервис работает с классификатором ДК-021. Архитектура построена по REST принципу с использованием Spring. Формат отдаваемых данных JSON.
Основная БД Postgres, в качестве тестовой базы данных используется hsqlDb. 

## Сборка проекта
Для сборки проекта вам понадобиться сборщик **Maven** установленный на вашем компьютере. 
Данный проект собирается в **war** файл для деполя в ваш сервлет контейнер.

Команда для сборки проекта.
> mvn clean package

## Конфигурирование

* Настроки доступа к базе данных в файле [db.properties](https://github.com/ArtemMurk/dk021/blob/master/src/main/resources/db.properties)

* Настрока пути для обновления данных клссификаторов [classificator.properties](https://github.com/ArtemMurk/dk021/blob/master/src/main/resources/classificator.properties)

## База данных

База данных имеет древовидную структуру.

**Скрипт инициализации БД [dk_021.sql](https://github.com/ArtemMurk/dk021/blob/master/dk_021.sql)**

**Создание индексов для базы данных [dk_021_indexes.sql](https://github.com/ArtemMurk/dk021/blob/master/dk_021_indexes.sql)**

## API для взаимодействия

> host:port/dk021/classificator/{code}

Получение узла классификатора по его коду

> host:port/dk021/classificator/nodes/{code}

 Получение списка подчиненных узлов первого порядка для заданного узла

> host:port/dk021/classificator/nodes/

 Получение списка подчиненных узлов первого порядка для корневого узла

> host:port/dk021/update

Принудительное обновление справочника из внешнего источника. Защищено с помощью Spring Security. 
Требует авторизации **_Basic Auth_**. 
При выполнениии запроса поставьте username:**admin**, password:**password**

## Технологии используемые в проекте

#### База данных
* Apache Сonnection Pool
* JDBC

#### Сериализация и десериализация
* Jackson

#### Логирование
* Slf4j
* Logback

#### Тестировование
* JUnit
* AssertJ
* Mockito
* Hamcrest
* Spring Test
* Spring Security Test
* Jsonpath

#### Spring
* Spring Security
* Spring MVC

#### Другие
* Lombok

## Контакты
Если у вас возникли предложения или замечания по проекту с удовольствием их выслушаю.

* **Автор**: Артём Муравлёв
* **Linkeidn**: [My Linkeidn](https://linkedin.com/in/artem-muravlov)
* **Telegram**: @artem_murk
* **Почта**: muravlevars@gmail.com


 
