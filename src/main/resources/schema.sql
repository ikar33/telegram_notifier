CREATE TABLE IF NOT EXISTS
        TELEGRAM_USERS(
        id int AUTO_INCREMENT  PRIMARY KEY,
        userId NVARCHAR,
        firstName NVARCHAR,
        lastName NVARCHAR,
        username NVARCHAR,
        password NVARCHAR,
        chatId NVARCHAR,
        registered BOOLEAN,
        notifications BOOLEAN,
        admin BOOLEAN,
        updatetime TIMESTAMP);


