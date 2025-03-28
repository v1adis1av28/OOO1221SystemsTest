# 1221SystemsTest

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
   git clone https://github.com/v1adis1av28/OOO1221SystemsTest.git
   cd ooo1221SystemsTest && cd TestTask
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

Ошибка (при некорректных данных, например при некорректно введенном возрасте):
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
#### Получение всех приемов пищи (`GET /intake`)
Запрос:
```json
GET http://localhost:8080/intake
```
Ответ:
```json
[
   {
        "userId": 6,
        "dishesName": [
            "okroshka"
        ]
    },
    {
        "userId": 10,
        "dishesName": [
            "scramble",
            "okroshka"
        ]
    },
    {
        "userId": 11,
        "dishesName": [
            "scramble",
            "okroshka"
        ]
    },
    {
        "userId": 11,
        "dishesName": [
            "scramble"
        ]
    }
]
```

---

### 4. Отчеты

#### Отчет за день (`GET /reports/daily?userId={userId}`)
Запрос:
```bash
GET http://localhost:8080/report/daily?userId=10
```

Ответ(если пользователь не добавил ничего сегодня  ):
```json
[
   {
    "userId": 10,
    "calorieCount": 0,
    "intakeCount": 0,
    "dishNameList": []
  }
]
```
Ответ(если добавил сегодня):
```json
{
    "userId": 10,
    "calorieCount": 530,
    "intakeCount": 2,
    "dishNameList": [
        [
            "scramble"
        ],
        [
            "okroshka"
        ]
    ]
}
```

#### Проверка калорий (`GET /reports/check?userId={userId}`)
Запрос:
```bash
GET http://localhost:8080/report/check?userId=10
```
Ответ():
```json
{
    "userDailyCalorie": 1360.0,
    "currentCalorieCount": 530.0,
    "message": "Calorie check successful! You have 830.0 calories left."
}
```


#### История питания (`GET /reports/history?userId={userId}`)
Запрос:
```bash
GET http://localhost:8080/report/history?userId=10
```
Ответ():
```json
{
    "userId": 10,
    "daysIntakeList": [
        {
            "date": "2025-03-28",
            "dishesName": [
                "scramble",
                "okroshka"
            ]
        },
        {
            "date": "2025-03-27",
            "dishesName": [
                "scramble",
                "okroshka"
            ]
        }
    ]
}
```


## Дополнительная информация

- **Логи:** Логи приложения можно просмотреть через команду:
  ```bash
  docker logs testtask_app
  ```
- **Тесты**: Функционал сервисы покрыт тестами с использованием Mockito и JUnit, ознакомиться с ними можно в папке src/test/java/com/test/TestTask/services/
- **Переменные окружения:** Все параметры подключения к базе данных настраиваются через переменные окружения в `docker-compose.yml`.


