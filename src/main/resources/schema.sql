CREATE TABLE IF NOT EXISTS
        TELEGRAM_USERS(
        id int AUTO_INCREMENT  PRIMARY KEY,
        userId NVARCHAR,
        chatId NVARCHAR,
        registered BOOLEAN,
        updatetime TIMESTAMP);