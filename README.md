# 1221SystemsTest
Конечно! Вот переписанный `README.md` файл, который вы можете скопировать и вставить в свой репозиторий. Он адаптирован для удобного использования в GitHub.

---

# Calorie Tracker API

REST API для отслеживания дневной нормы калорий пользователя и учета съеденных блюд.

## Описание проекта

Проект предоставляет REST API для управления пользователями, блюдами и приемами пищи. Основные функции:
- Добавление пользователей с автоматическим расчетом дневной нормы калорий (формула Харриса-Бенедикта).
- Добавление блюд с указанием калорийности и БЖУ.
- Учет приемов пищи с возможностью добавления нескольких блюд.
- Формирование отчетов:
  - Отчет за день с суммой всех калорий и списком приемов пищи.
  - Проверка, уложился ли пользователь в свою дневную норму калорий.
  - История питания по дням.

## Технологии
- **Spring Boot** + **Spring Data JPA**
- **PostgreSQL** (база данных)
- **Docker Compose** (для запуска приложения и базы данных)
- **Flyway** (для миграции базы данных)

---

## Запуск проекта

### Предварительные требования
1. Установленный Docker и Docker Compose.
2. Клонированный репозиторий.

### Шаги для запуска
1. Склонируйте репозиторий:
   ```bash
   git clone https://github.com/your-repo-url.git
   cd your-repo-folder
   ```

2. Запустите приложение и базу данных через Docker Compose:
   ```bash
   docker-compose up --build
   ```

3. После запуска приложение будет доступно по адресу:
   ```
   http://localhost:8080
   ```

4. Для остановки контейнеров выполните:
   ```bash
   docker-compose down
   ```

### Настройка базы данных
База данных настраивается автоматически через Flyway. Миграционные скрипты находятся в папке `src/main/resources/db/migration`.

---

## API документация

### 1. Пользователи

#### Создание пользователя (`POST /users`)
Запрос:
```json
POST http://localhost:8080/users
Content-Type: application/json

{
    "username": "user3",
    "email": "test3@mail.ru",
    "age": 19,
    "weight": 80,
    "height": 188,
    "goal": "GAIN_WEIGHT",
    "gender": "MALE"
}
```

Ответ (при успехе):
```json
{
    "username": "user3",
    "email": "test3@mail.ru",
    "age": 19,
    "weight": 80.0,
    "height": 188.0,
    "dailyCalories": 1985.0,
    "goal": "GAIN_WEIGHT",
    "gender": "MALE"
}
```

Ошибка (при некорректных данных):
```json
{
    "message": "age-You can use service after you are 18;"
}
```

#### Получение всех пользователей (`GET /users`)
Запрос:
```bash
GET http://localhost:8080/users
```

Ответ:
```json
[
    {
        "username": "user3",
        "email": "test3@mail.ru",
        "age": 18,
        "weight": 80.0,
        "height": 188.0,
        "dailyCalories": 1985.0,
        "goal": "GAIN_WEIGHT",
        "gender": "MALE"
    },
    {
        "username": "user4",
        "email": "test3@mail.ru",
        "age": 18,
        "weight": 88.0,
        "height": 188.0,
        "dailyCalories": 2095.0,
        "goal": "GAIN_WEIGHT",
        "gender": "MALE"
    }
]
```

#### Получение конкретного пользователя (`GET /users/{id}`)
Запрос:
```bash
GET http://localhost:8080/users/2
```

Ответ:
```json
{
    "username": "user4",
    "email": "test3@mail.ru",
    "age": 18,
    "weight": 88.0,
    "height": 188.0,
    "dailyCalories": 2095.0,
    "goal": "GAIN_WEIGHT",
    "gender": "MALE"
}
```

---

### 2. Блюда

#### Создание блюда (`POST /dishes`)
Запрос:
```json
POST http://localhost:8080/dishes
Content-Type: application/json

{
    "name": "Tea",
    "calorie": 40.0,
    "protein": 0.0,
    "fat": 0.0,
    "carbs": 10.0
}
```

#### Получение всех блюд (`GET /dishes`)
Запрос:
```bash
GET http://localhost:8080/dishes
```

Ответ:
```json
[
    {
        "id": 1,
        "name": "scramble",
        "calorie": 300.0,
        "protein": 20.0,
        "fat": 8.0,
        "carbs": 3.0
    },
    {
        "id": 2,
        "name": "spagheti",
        "calorie": 380.0,
        "protein": 16.0,
        "fat": 20.0,
        "carbs": 300.0
    },
    {
        "id": 3,
        "name": "tea",
        "calorie": 40.0,
        "protein": 0.0,
        "fat": 0.0,
        "carbs": 10.0
    }
]
```

---

### 3. Прием пищи

#### Добавление приема пищи (`POST /intake`)
Запрос:
```json
POST http://localhost:8080/intake
Content-Type: application/json

{
    "userId": 2,
    "dishesName": ["Spagheti", "scramble", "Tea"]
}
```

---

### 4. Отчеты

#### Отчет за день (`GET /reports/daily/{userId}`)
Запрос:
```bash
GET http://localhost:8080/reports/daily/2
```

#### Проверка калорий (`GET /reports/calorie-check/{userId}`)
Запрос:
```bash
GET http://localhost:8080/reports/calorie-check/2
```

#### История питания (`GET /reports/history/{userId}`)
Запрос:
```bash
GET http://localhost:8080/reports/history/2
```

---

## Postman-коллекция

Вы можете использовать [Postman-коллекцию](postman-collection/CalorieTracker.postman_collection.json) для тестирования API. Она содержит все примеры запросов, описанные выше.

---

## Дополнительная информация

- **Логи:** Логи приложения можно просмотреть через команду:
  ```bash
  docker logs testtask_app
  ```

- **Миграции:** Если вы меняете структуру базы данных, добавьте новые миграционные скрипты в папку `src/main/resources/db/migration`.

- **Переменные окружения:** Все параметры подключения к базе данных настраиваются через переменные окружения в `docker-compose.yml`.

---

Если у вас возникнут вопросы или потребуется помощь, обращайтесь! 😊

---

Теперь вы можете скопировать этот текст и вставить его в файл `README.md` вашего репозитория. Не забудьте заменить `https://github.com/your-repo-url.git` на реальный URL вашего репозитория.
