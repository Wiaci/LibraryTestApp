#  Тестовое задание "Библиотека"

## Деплоймент

### 1. Необходимые настройки

Для запуска проекта на вашей машине убедитесь, что у вас установлены следующие компоненты:

- **JDK 8 или выше**: Для компиляции и запуска приложения.
- **Maven**: Для сборки проекта.
- **PostgreSQL 14**: Для работы с базой данных.

### 2. Клонирование репозитория

Клонируйте проект с GitHub:

```bash
git clone https://github.com/Wiaci/LibraryTestApp.git
```

### 3. Настройка базы данных
   
- Создайте базу данных PostgresQL
- Откройте файл `src/main/resources/application.properties` и настройте параметры подключения 
(порт сервера БД, название БД, имя пользователя и пароль):

```
spring.datasource.url=jdbc:postgresql://localhost:[порт]/[название БД]
spring.datasource.username=[имя пользователя]
spring.datasource.password=[пароль]
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 4. Сборка проекта

Для сборки проекта в директории проекта выполните следующую команду:
```bash
mvn clean package
```

### 5. Запуск проекта

Для запуска проекта выполните:
```bash
java -jar ./target/LibraryTestApp-1.jar
```

Проект можно открыть по ссылке [http://localhost:8080/books]()

## Описание основных частей проекта

### 1. Шапка
На каждой странице расположена шапка, предоставляющая доступ к основным страницам и функциям приложения. Список ссылок на шапке:
- Книги, Клиенты, Заказы - отправляют на основные страницы соответствующих разделов
- JSON заказов - создан для демонстрации работы api, возвращающей JSON с информацией о заказах
- Сгенерировать тестовые данные - генерирует 500 книг и 500 клиентов в системе

### 2. Книги
Страница для работы с книгами. Содержит:
* Таблицу со списком всех книг в системе с возможностью:
  - Редактировать книгу
  - Удалить книгу (работает корректно только если на книгу нет заказов!)
* Сверху таблицы - ссылка на форму добавления книги
* Снизу - переключение страниц списка книг

На форме добавления книг необходимо ввести название, автора и уникальный ISBN (неуникальный приведет к ошибке системы), и нажать "Добавить". 

### 2. Клиенты
Страница для работы с клиентами. Содержит:
* Таблицу со списком всех клиентов в системе с возможностью:
    - Редактировать клиента
    - Удалить книгу (работает корректно только если у клиента нет заказов!)
* Сверху таблицы - ссылка на форму добавления клиента
* Снизу - переключение страниц списка клиентов

На форме добавления клиента необходимо ввести ФИО и дату рождения.

### 3. Заказы
Страница для работы с заказами. Содержит:
* Таблицу со списком всех заказов в системе с возможностью:
    - Просмотреть заказ
    - Удалить заказ
* Сверху таблицы - ссылка на форму добавления заказа
* Снизу - переключение страниц списка заказов

На форме добавления заказа необходимо указать книгу и клиента, а также указать дату взятия книги. 
Чтобы указать книгу, нужно в поисковом окне ввести часть ее названия, и затем выбрать из предложенных книг. 
Аналогично работает с клиентами.


## Что не успел сделать, но хотел бы
* Сделать домашнюю страницу
* Обработать ошибки валидации в формах
* Обработать ошибки при удалении книги и клиента, у которых есть заказы
* При поиске клиентов и книг в добавлении заказа реже отправлять запросы на получение их списков, добавить в select
пагинацию, чтобы запрашивать не сразу все подходящие под запрос элементы