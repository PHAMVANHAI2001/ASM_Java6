-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: ASM_Java5
-- Source Schemata: ASM_Java5
-- Created: Thu Mar 10 14:22:32 2022
-- Workbench Version: 8.0.28
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema ASM_Java5
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `ASM_Java6` ;
CREATE SCHEMA IF NOT EXISTS `ASM_Java6` ;
USE `ASM_Java6`;

CREATE TABLE IF NOT EXISTS `ASM_Java6`.`Role`(
	`Id` INT NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`Id`)
);

INSERT INTO `ASM_Java6`.`Role` (`Name`) VALUES ('CUSTOMER'), ('STAFF'),('DIRECTOR');

CREATE TABLE IF NOT EXISTS `ASM_Java6`.`Authority`(
	`Id` INT NOT NULL AUTO_INCREMENT,
    `UserId` INT NOT NULL,
    `RoleId` INT NOT NULL,
    PRIMARY KEY(`Id`),
	CONSTRAINT `FK__Authority__User`
    FOREIGN KEY (`UserId`)
    REFERENCES `ASM_Java5`.`User` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
	CONSTRAINT `FK__Authority__Role`
    FOREIGN KEY (`RoleId`)
    REFERENCES `ASM_Java5`.`Role` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO `ASM_Java6`.`Authority` (`UserId`,`RoleId`) VALUES (1,1),(1,3),(2,1),(3,1),(4,1),(4,3);
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.User
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java6`.`User` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(50) NOT NULL,
  `Password` VARCHAR(128) NOT NULL,
  `Fullname` VARCHAR(128) CHARACTER SET 'utf8mb4' NOT NULL,
  `Email` VARCHAR(128) NOT NULL,
  `Address` VARCHAR(255) CHARACTER SET 'utf8mb4' NULL,
  `PhoneNumber` VARCHAR(10) NULL,
  `Photo` VARCHAR(128) NULL,
  `CreatedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Enabled` TINYINT(1) NOT NULL DEFAULT 1,
  `Authority` INT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `UQ__User__Email` (`Email` ASC) VISIBLE,
  UNIQUE INDEX `UQ__User__Username` (`Username` ASC) VISIBLE);

INSERT INTO `ASM_Java6`.`User`
    (`Id`, `Username`, `Password`, `Fullname`, `Email`, `Photo`, `Authority`)
VALUES (1, 'TeoNV', '123456789', 'Nguy???n V??n T??o',
        'teonv@gmail.com', 'TeoNV.jpeg', 0),
       (2, 'VietTN', '123456789', 'Tr???n Nh???t Vi???t',
        'vietnt@gmail.com', 'VietTN.jpg', 0),
       (3, 'MaiNT', '123456789', 'Nguy???n Th??? Mai',
        'maint@gmail.com', 'MaiNT.jpg', 1),
       (4, 'HaiPV', '$2a$10$v4Ur4Ehfz8l2guskjdE0luxk6XZC80bryFeM./KNBjbibY8WBY7rS
', 'Ph???m V??n H???i',
        'haipham2001vn@gmail.com', 'haipv.jpg', 1);
	
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.Category
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java6`.`Category` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) CHARACTER SET 'utf8mb4' NOT NULL,
  `Slug` VARCHAR(50) CHARACTER SET 'utf8mb4' NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `UQ__Category__Slug` (`Slug` ASC) VISIBLE);

INSERT INTO `ASM_Java6`.`Category`
    (`Id`, `Name`, `Slug`)
VALUES (1, '??i???n tho???i', 'phone'),
       (2, 'M??y t??nh b???ng', 'tablet'),
       (3, 'M??y t??nh x??ch tay', 'laptop'),
       (4, '?????ng h??? th??ng minh', 'smartwatch');
       
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.Discount
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java6`.`Discount` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) CHARACTER SET 'utf8mb4' NOT NULL,
  `SaleOff` INT NOT NULL,
  `StartDate` DATETIME(6) NOT NULL,
  `EndDate` DATETIME(6) NOT NULL,
  `IsActive` TINYINT(1) NOT NULL DEFAULT 1,
  `CreatedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`));

INSERT INTO `ASM_Java6`.`Discount`
    (`Id`, `Name`, `SaleOff`, `StartDate`, `EndDate`)
VALUES (1, 'FLASH SALE', 10, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 2 MONTH)),
       (2, 'Khuy???n m??i ?????c bi???t', 20, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 MONTH));
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.Product
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java6`.`Product` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) CHARACTER SET 'utf8mb4' NOT NULL,
  `Slug` VARCHAR(255) CHARACTER SET 'utf8mb4' NOT NULL,
  `Image` LONGTEXT NOT NULL,
  `ImagePreview1` LONGTEXT NOT NULL,
  `ImagePreview2` LONGTEXT NOT NULL,
  `ImagePreview3` LONGTEXT NOT NULL,
  `Description` TEXT CHARACTER SET 'utf8mb4' NOT NULL,
  `Quantity` INT NOT NULL,
  `UnitPrice` DOUBLE NOT NULL,
  `CreatedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Available` INT NOT NULL DEFAULT 0,
  `CategoryId` INT NOT NULL,
  `DiscountId` INT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `UQ__Product__Slug` (`Slug` ASC) VISIBLE,
  CONSTRAINT `FK__Product__Category`
    FOREIGN KEY (`CategoryId`)
    REFERENCES `ASM_Java5`.`Category` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK__Product__Discount`
    FOREIGN KEY (`DiscountId`)
    REFERENCES `ASM_Java5`.`Discount` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `ASM_Java6`.`Product`
(`Id`, `Name`, `Slug`, `Image`, `ImagePreview1`, `ImagePreview2`, `ImagePreview3`, `Description`,
 `Quantity`, `UnitPrice`, `CategoryId`, `DiscountId`)
VALUES 
	-- ??i???n tho???i
		(1, 'iPhone 13 Pro Max 512GB', 'iphone-13-pro-max-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-5_7.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-5_4_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-3_5.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-2_1_4.jpg',
        'M???i l???n ra m???t phi??n b???n m???i l?? m???i l???n iPhone chi???m s??ng tr??n kh???p c??c m???t tr???n v?? l???n n??y c??i t??n khi???n v?? s??? ng?????i "s???c s??i" l?? iPhone 13 Pro, chi???c ??i???n tho???i th??ng minh v???n gi??? nguy??n thi???t k??? cao c???p, c???m 3 camera ???????c n??ng c???p, c???u h??nh m???nh m??? c??ng th???i l?????ng pin l???n ???n t?????ng.',
        1000, 38000000, 1, 1),
      (2, 'iPhone 12 Pro Max 512GB', 'iphone-12-pro-max-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_12_pro_max_blue.png',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone-12-pro-max_3__6.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/p/h/photo_2020-10-13_22-12-43.jpg_1_7_2.png',
        'https://image.cellphones.com.vn/358x/media/catalog/product/p/h/photo_2020-10-13_22-12-33.jpg_1_8.png',
        'M???i l???n ra m???t phi??n b???n m???i l?? m???i l???n iPhone chi???m s??ng tr??n kh???p c??c m???t tr???n v?? l???n n??y c??i t??n khi???n v?? s??? ng?????i "s???c s??i" l?? iPhone 12 Pro, chi???c ??i???n tho???i th??ng minh v???n gi??? nguy??n thi???t k??? cao c???p, c???m 3 camera ???????c n??ng c???p, c???u h??nh m???nh m??? c??ng th???i l?????ng pin l???n ???n t?????ng.',
        1000, 33000000, 1, 2),
		 (3, 'iPhone 11 Pro 512GB', 'iphone-11-pro-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone-11-pro-gold-select-2019_5.png',
		'https://image.cellphones.com.vn/358x/media/catalog/product/l/a/layer_22_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/l/a/layer_25_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/l/a/layer_23_2.jpg',
        'M???i l???n ra m???t phi??n b???n m???i l?? m???i l???n iPhone chi???m s??ng tr??n kh???p c??c m???t tr???n v?? l???n n??y c??i t??n khi???n v?? s??? ng?????i "s???c s??i" l?? iPhone 11 Pro, chi???c ??i???n tho???i th??ng minh v???n gi??? nguy??n thi???t k??? cao c???p, c???m 3 camera ???????c n??ng c???p, c???u h??nh m???nh m??? c??ng th???i l?????ng pin l???n ???n t?????ng.',
        1000, 27000000, 1, 1),
		-- ipad
		 (4, 'iPad Pro 12.9 2021 M1 5G 512GB', 'ipad-pro-12-9-2021-5g-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-2_4.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-2_2_1_1_1_1_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-1_2_1_1_1_1_1_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-2_4.jpg',
        'iPad Pro M1 12.9 inch WiFi Cellular 512GB (2021), m???t chi???c m??y t??nh b???ng cao c???p s??? h???u lo???t c??ng ngh??? ?????t ph?? nh?? m??n h??nh mini-LED, m???ng 5G, vi x??? l?? Apple M1 cho hi???u n??ng x??? l?? v?????t tr???i v?????t kh???i gi???i h???n.',
        1000, 39000000, 2, 2),
		(5, 'iPad Air 10.9 2020 4G 256GB', 'ipad-air-4-4g-256gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-silver_5.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-green_5.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-blue_4.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-pink_4.jpg',
        'iPad Air 4 2020 ???????c trang b??? c??ng ngh??? m??n h??nh Liquid Retina ????? ph??n gi???i 1640 x 2360 Pixels v?? c?? t???n s??? qu??t ?????n 60 Hz mang ?????n ch???t l?????ng hi???n th??? m?????t m??, c??c chuy???n ?????ng h??nh ???nh tr??n tru h??n, g???n nh?? lo???i b??? ho??n to??n t??nh tr???ng gi???t lag. N???u ??t s??? d???ng 4G, b???n c?? th??? tham kh???o th??m iPad Air 4 wifi 256Gb ????? ti???t ki???m chi ph??.',
        1000, 22000000, 2, null),
		(6, 'iPad mini 6 4G 256GB', 'ipad-mini-6-4g-256gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-5_3.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-1_3.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-01_3.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-04_3.jpg',
        'Apple iPad mini 6 4G 256GB l?? s???n ph???m iPad mini 2021 Apple gi???i thi???u ?????n to??n th??? c??ng ch??ng. Phi??n b???n n??y cung c???p h??nh th???c k???t n???i Wifi v?? c??? 4G, r???t thu???n ti???n s??? d???ng ??? b???t k??? n??i ????u. M??y c?? dung l?????ng l???n th??ch h???p l??u tr??? nhi???u d??? li???u',
        1000, 22800000, 2, null),
		-- laptop
		(7, 'MacBook Air M1 16GB 512GB 2020', 'macbook-air-2020-m1-16gb-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-air-silver-select-201810_2_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/_/0/_0003_macbook-air-gallery4-20201110_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/_/0/_0001_macbook-air-gallery2-20201110_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/_/0/_0003_dsc03720_4.jpg',
        'Macbook Air M1 2020 b???n RAM 16GB v?? b??? nh??? 512GB ???????c trang b??? m??n h??nh 13.3 inch v???i ????? ph??n gi???i 2560 x 1600 pixels. M??y ???????c cung c???p s???c m???nh b???i con chip M1 m???i nh???t c???a Apple v???i 8 nh??n, bao g???m 4 nh??n hi???u su???t cao v?? 4 nh??n hi???u su???t th???p. Theo c??ng b??? c???a Apple, vi x??? l?? n??y m???nh h??n ?????n 98% so v???i nh???ng chi???c laptop Windows v?? hi???u n??ng v?????t tr???i h??n so v???i nh???ng chi???c Macbook Air ch???y chip Intel.',
        1000, 37000000, 3, null),
		(8, 'Macbook Pro M1 Pro 16 10 CPU - 16 GPU 16GB 1TB 2021', 'macbook-pro-16-inch-2021-1tb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-05_4_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-004_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-003_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-001_2.jpg',
        'Macbook Pro 16 inch 2021 ???????c trang b??? chip Apple M1 Pro v???i 10CPU, 16GPU k???t h???p dung l?????ng RAM 16GB v?? b??? nh??? SSD 1TB mang l???i s???c m???nh v?????t tr???i. ????y l?? s???n ph???m ch??nh h??ng Apple Vi???t Nam ???????c b???o h??nh 12 th??ng',
        1000, 70990000, 3, null),
		-- ?????ng h???
		(9, 'Apple Watch Series 7 45mm (4G) Vi???n th??p d??y th??p', 'apple-watch-series-7-45mm-4g-vien-thep-day-thep',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple_watch_series_7_cellular_41mm_silver_stainless_steel_silver_milanese_loop_34fr_screen__usen_copy_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/s/e/series_7_45mm.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple_watch_series_7_cellular_41mm_graphite_stainless_steel_graphite_milanese_loop_34fr_screen__usen_copy_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple_watch_series_7_cellular_41mm_gold_stainless_steel_gold_milanese_loop_34fr_screen__usen_copy_1.jpg',
        'Apple Watch Series 7 45mm (4G). Phi??n b???n n??y kh??ng ch??? ???????c n??ng c???p ti???n ??ch m?? c??n sang tr???ng h??n v???i vi???n v?? d??y l??m t??? th??p. Th??ng tin b?? ???n m???i nh???t c???a Apple Watch ???????c b???t m?? d?????i ????y!',
        1000, 21500000, 4, null),
		(10, 'Apple Watch Series 6 44mm (4G) Vi???n Th??p D??y Th??p', 'apple-watch-series-6-44mm-4g-vien-thep-day-thep',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-day-thep-1_1_1_3.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-day-thep-3_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-day-thep-2_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-bac-day-thep_2__2_1.jpg',
        'H???? tr???? ti??nh n??ng ??o nhi??p tim va?? nhi????u ch???? ?????? s????c kho??e kha??c nhau, Apple Watch S6 44mm 4G vi????n the??p d??y the??p la?? chi????c ??????ng h???? th??ng minh sang tro??ng phu?? h????p cho ca??c iFan l????n v????n ??????ng vi??n th???? thao. S???? b???? sung d??y ??eo b????ng the??p ca??ng la??m t??ng th??m ve?? sang tro??ng cho chi????c smartwatch na??y.',
        1000, 16590000, 4, null),
		(11, 'Apple Watch SE 44mm (4G) Vi???n Nh??m - D??y V???i', 'apple-watch-se-44mm-4g',
        'https://image.cellphones.com.vn/358x/media/catalog/product/d/o/download_2__1_20.png',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-se-44mm-4g-1_1_2.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/d/o/download_1__2_19.png',
        'https://image.cellphones.com.vn/358x/media/catalog/product/d/o/download_2_27.png',
        '?????u ti??n khi nh???c ?????n chi???c ?????ng h??? Apple Watch SE 44mm m???i ng?????i s??? v?? c??ng th??ch th?? v???i ch???t li???u m?? nh?? s???n xu???t ????a v??o cho n??. V???i vi???n nh??m v?? d??y cao su c???c k??? ch???t l?????ng, gi??p chi???c ?????ng h??? tr??? n??n sang tr???ng v?? v?? c??ng b???n b???, s??? d???ng b???n l??u.',
        1000, 8400000, 4, null);
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.ShoppingCart
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java6`.`ShoppingCart` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `ProductId` INT NOT NULL,
  `Quantity` INT NULL DEFAULT 1,
  `CreatedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  CONSTRAINT `FK__ShoppingCart__User`
    FOREIGN KEY (`UserId`)
    REFERENCES `ASM_Java5`.`User` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK__ShoppingCart__Product`
    FOREIGN KEY (`ProductId`)
    REFERENCES `ASM_Java5`.`Product` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- INSERT INTO `ASM_Java5`.`ShoppingCart`
--     (`Id`, `UserId`, `ProductId`, `Quantity`)
-- VALUES (1, 1, 1, 1),
--        (2, 1, 4, 1),
--        (3, 1, 8, 1);
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.Order
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java6`.`Order` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `OrderCode` VARCHAR(11) NOT NULL,
  `UserId` INT NOT NULL,
  `CreatedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Status` INT NOT NULL DEFAULT 0,
  `Fullname` VARCHAR(128) CHARACTER SET 'utf8mb4' NOT NULL,
  `Address` VARCHAR(255) CHARACTER SET 'utf8mb4' NOT NULL,
  `Email` VARCHAR(128) NOT NULL,
  `PhoneNumber` VARCHAR(10) NOT NULL,
  `TotalUnitPrice` DOUBLE NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `UQ__Order__OrderCode` (`OrderCode` ASC) VISIBLE,
  CONSTRAINT `FK__Order__User`
    FOREIGN KEY (`UserId`)
    REFERENCES `ASM_Java5`.`User` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- INSERT INTO `ASM_Java5`.`Order`
--     (`Id`, `OrderCode`, `UserId`, `CreatedDate`, `Status`, `Fullname`, `Address`, `Email`, `PhoneNumber`, `TotalUnitPrice`)
-- VALUES (1, '34VB5540K83', 1, GETDATE(), 0, N'Nguy???n V??n T??o', N'Ph?????ng Trung M??? T??y, Qu???n 12, TP.HCM', 'teonv@gmail.com', '0923235511', 1),
--        (2, '35VB5540K83', 1, GETDATE(), 0, N'Nguy???n V??n T??o', N'Ph?????ng Trung M??? T??y, Qu???n 12, TP.HCM', 'teonv@gmail.com', '0923235511', 1),
--        (3, '36VB5540K83', 1, GETDATE(), 1, N'Nguy???n V??n T??o', N'Ph?????ng Trung M??? T??y, Qu???n 12, TP.HCM', 'teonv@gmail.com', '0923235511', 1);
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.OrderDetail
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java6`.`OrderDetail` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `OrderId` INT NOT NULL,
  `ProductId` INT NOT NULL,
  `Quantity` INT NOT NULL,
  `TotalUnitPrice` DOUBLE NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `FK__OrderDetail__Order`
    FOREIGN KEY (`OrderId`)
    REFERENCES `ASM_Java5`.`Order` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK__OrderDetail__Product`
    FOREIGN KEY (`ProductId`)
    REFERENCES `ASM_Java5`.`Product` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
SET FOREIGN_KEY_CHECKS = 1;


