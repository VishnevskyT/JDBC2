CREATE DATABASE HomeDevices;

USE HomeDevices;

CREATE TABLE TurnedOnDevices(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    power DOUBLE NOT NULL,
    manufacturer VARCHAR(50) NOT NULL
);