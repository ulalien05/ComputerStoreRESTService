# EasyBotTest
test assignment for EasyBot

Инструкции на русском языка прилагаются ниже.

The application represents a computer store service operating with stock items data via in-memory database (H2).
When starting the application the embedded database is initialized with 4 tables corresponding to the goods categories and the tables get filled with test data.

Available operations

GET method at the following urls: 
http://localhost:8080/desktop - returns the list of existing items of a desktop computer type;
http://localhost:8080/desktop/{id} - returns the item of a desktop computer type with the specified id;
http://localhost:8080/monitor - returns the list of existing items of a computer monitor type;
http://localhost:8080/monitor/{id} - returns the item of a computer monitor type with the specified id;
http://localhost:8080/hard_drive - returns the list of existing items of a hard drive type;
http://localhost:8080/hard_drive/{id} - returns the item of a hard drive type with the specified id;
http://localhost:8080/laptop - returns the list of existing items of a laptop type;
http://localhost:8080/laptop/{id} - returns the item of a laptop type with the specified id;

POST method at the following urls:
http://localhost:8080/desktop - creates a new desktop computer item with the fields values received in the request body as json and saves it in the database;
http://localhost:8080/monitor - creates a new computer monitor item with the fields values received in the request body as json and saves it in the database;
http://localhost:8080/hard_drive - creates a new hard drive item with the fields values received in the request body as json and saves it in the database;
http://localhost:8080/laptop - creates a new laptop item with the fields values received in the request body as json and saves it in the database;

PATCH method at the following urls:
http://localhost:8080/desktop/{id} - updates and saves the desctop computer item with the given id changing its fields values according to the values received in the request body as json;
http://localhost:8080/monitor/{id} - updates and saves the computer monitor item with the given id changing its fields values according to the values received in the request body as json;
http://localhost:8080/hard_drive/{id} - updates and saves the hard drive item with the given id changing its fields values according to the values received in the request body as json;
http://localhost:8080/laptop/{id} - updates and saves the laptop item with the given id changing its fields values according to the values received in the request body as json;

---

Приложение представляется собой сервис для компьютерного магазина, позволяющий работать с данными об имеющихся товарах с помощью встроенной базы данных (H2).
При запуске приложения встроенная база данных инициализируется четырьмя таблицами в соответствии с категориями товаров, а таблицы заполняются тестовыми данными.

Доступные операции:

GET метод по следующим URL адресам:
http://localhost:8080/desktop - возвращает список существующих товарных единиц категории настольный компьютер;
http://localhost:8080/desktop/{id} - возвращает товарную единицу категории настольный компьютер с указанным идентификатором;
http://localhost:8080/monitor - возвращает список существующих товарных единиц категории монитор;
http://localhost:8080/monitor/{id} - возвращает товарную единицу категории монитор с указанным идентификатором;
http://localhost:8080/hard_drive - возвращает список существующих товарных единиц категории жесткий диск;
http://localhost:8080/hard_drive/{id} - возвращает товарную единицу категории жесткий диск с указанным идентификатором;
http://localhost:8080/laptop - возвращает список существующих товарных единиц категории ноутбук;
http://localhost:8080/laptop/{id} - возвращает товарную единицу категории ноутбук с указанным идентификатором;

POST метод по следующим URL адресам:
http://localhost:8080/desktop - создает новую единицу категории настольный компьютер со значениями полей, полученными в теле запроса в формате json, и сохраняет ее в базе;
http://localhost:8080/monitor - создает новую единицу категории монитор со значениями полей, полученными в теле запроса в формате json, и сохраняет ее в базе;
http://localhost:8080/hard_drive - создает новую единицу категории жесткий диск со значениями полей, полученными в теле запроса в формате json, и сохраняет ее в базе;
http://localhost:8080/laptop - создает новую единицу категории ноутбук со значениями полей, полученными в теле запроса в формате json, и сохраняет ее в базе;

PATCH метод по следующим URL адресам:
http://localhost:8080/desktop/{id} - обновляет и сохраняет единицу категории настольный комьютер с указанным идентификатором, заменяя значения его полей полученными в теле запроса в формате json;
http://localhost:8080/monitor/{id} - обновляет и сохраняет единицу категории монитор с указанным идентификатором, заменяя значения его полей полученными в теле запроса в формате json;
http://localhost:8080/hard_drive/{id} - обновляет и сохраняет единицу категории жесткий диск с указанным идентификатором, заменяя значения его полей полученными в теле запроса в формате json;
http://localhost:8080/laptop/{id} - обновляет и сохраняет единицу категории ноутбук с указанным идентификатором, заменяя значения его полей полученными в теле запроса в формате json;

