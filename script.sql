CREATE TABLE `user`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `user` ADD PRIMARY KEY `user_id_primary`(`id`);
CREATE TABLE `image`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `nameImage` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `image` ADD PRIMARY KEY `image_id_primary`(`id`);
CREATE TABLE `container`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `nameContainer` INT NOT NULL,
    `imageId` INT NOT NULL,
    `userId` INT NOT NULL,
    `cpus` DOUBLE(8, 2) NOT NULL,
    `ram` DOUBLE(8, 2) NOT NULL,
    `ipSSH` VARCHAR(255) NOT NULL,
    `serverId` INT NOT NULL
);
ALTER TABLE
    `container` ADD PRIMARY KEY `container_id_primary`(`id`);
CREATE TABLE `server`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `ipServer` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `server` ADD PRIMARY KEY `server_id_primary`(`id`);
ALTER TABLE
    `container` ADD CONSTRAINT `container_imageid_foreign` FOREIGN KEY(`imageId`) REFERENCES `image`(`id`);
ALTER TABLE
    `container` ADD CONSTRAINT `container_userid_foreign` FOREIGN KEY(`userId`) REFERENCES `user`(`id`);
ALTER TABLE
    `container` ADD CONSTRAINT `container_serverid_foreign` FOREIGN KEY(`serverId`) REFERENCES `server`(`id`);