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
DROP SCHEMA IF EXISTS `ASM_Java5` ;
CREATE SCHEMA IF NOT EXISTS `ASM_Java5` ;
USE `ASM_Java5`;


-- ----------------------------------------------------------------------------
-- Table ASM_Java5.User
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java5`.`User` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(50) NOT NULL,
  `Password` VARCHAR(128) NOT NULL,
  `Fullname` VARCHAR(128) CHARACTER SET 'utf8mb4' NOT NULL,
  `Email` VARCHAR(128) NOT NULL,
  `Address` VARCHAR(255) CHARACTER SET 'utf8mb4' NULL,
  `PhoneNumber` VARCHAR(10) NULL,
  `Photo` VARCHAR(128) NULL,
  `CreatedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IsAdmin` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `UQ__User__A9D1053468D1479E` (`Email` ASC) VISIBLE,
  UNIQUE INDEX `UQ__User__536C85E4ADD86813` (`Username` ASC) VISIBLE);

INSERT INTO `ASM_Java5`.`User`
    (`Id`, `Username`, `Password`, `Fullname`, `Email`, `Photo`, `IsAdmin`)
VALUES (1, 'TeoNV', '123456789', 'Nguyễn Văn Tèo',
        'teonv@gmail.com', 'TeoNV.jpeg', 0),
       (2, 'VietTN', '123456789', 'Trần Nhật Việt',
        'vietnt@gmail.com', 'VietTN.jpg', 0),
       (3, 'MaiNT', '123456789', 'Nguyễn Thị Mai',
        'maint@gmail.com', 'MaiNT.jpg', 1),
       (4, 'HaiPV', '123456789', 'Phạm Văn Hải',
        'haipham2001vn@gmail.com', 'haipv.jpg', 1);
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.Category
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java5`.`Category` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) CHARACTER SET 'utf8mb4' NOT NULL,
  `Slug` VARCHAR(50) CHARACTER SET 'utf8mb4' NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `UQ__Category__BC7B5FB616342B29` (`Slug` ASC) VISIBLE);

INSERT INTO `ASM_Java5`.`Category`
    (`Id`, `Name`, `Slug`)
VALUES (1, 'Điện thoại', 'phone'),
       (2, 'Máy tính bảng', 'tablet'),
       (3, 'Máy tính xách tay', 'laptop'),
       (4, 'Đồng hồ thông minh', 'smartwatch');
       
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.Discount
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java5`.`Discount` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) CHARACTER SET 'utf8mb4' NOT NULL,
  `SaleOff` INT NOT NULL,
  `StartDate` DATETIME(6) NOT NULL,
  `EndDate` DATETIME(6) NOT NULL,
  `IsActive` TINYINT(1) NOT NULL DEFAULT 1,
  `CreatedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`));

INSERT INTO `ASM_Java5`.`Discount`
    (`Id`, `Name`, `SaleOff`, `StartDate`, `EndDate`)
VALUES (1, 'FLASH SALE', 10, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 2 MONTH)),
       (2, 'Khuyến mãi đặc biệt', 20, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 MONTH));
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.Product
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java5`.`Product` (
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
  UNIQUE INDEX `UQ__Product__BC7B5FB661EF50BC` (`Slug` ASC) VISIBLE,
  CONSTRAINT `FK__Product__Categor__33D4B598`
    FOREIGN KEY (`CategoryId`)
    REFERENCES `ASM_Java5`.`Category` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK__Product__Discoun__34C8D9D1`
    FOREIGN KEY (`DiscountId`)
    REFERENCES `ASM_Java5`.`Discount` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `ASM_Java5`.`Product`
(`Id`, `Name`, `Slug`, `Image`, `ImagePreview1`, `ImagePreview2`, `ImagePreview3`, `Description`,
 `Quantity`, `UnitPrice`, `CategoryId`, `DiscountId`)
VALUES 
	-- điện thoại
		(1, 'iPhone 13 Pro Max 512GB', 'iphone-13-pro-max-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-5_7.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-5_4_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-3_5.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-2_1_4.jpg',
        'Mỗi lần ra mắt phiên bản mới là mỗi lần iPhone chiếm sóng trên khắp các mặt trận và lần này cái tên khiến vô số người "sục sôi" là iPhone 13 Pro, chiếc điện thoại thông minh vẫn giữ nguyên thiết kế cao cấp, cụm 3 camera được nâng cấp, cấu hình mạnh mẽ cùng thời lượng pin lớn ấn tượng.',
        1000, 38000000, 0, null),
      (2, 'iPhone 12 Pro Max 512GB', 'iphone-12-pro-max-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_12_pro_max_blue.png',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone-12-pro-max_3__6.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/p/h/photo_2020-10-13_22-12-43.jpg_1_7_2.png',
        'https://image.cellphones.com.vn/358x/media/catalog/product/p/h/photo_2020-10-13_22-12-33.jpg_1_8.png',
        'Mỗi lần ra mắt phiên bản mới là mỗi lần iPhone chiếm sóng trên khắp các mặt trận và lần này cái tên khiến vô số người "sục sôi" là iPhone 12 Pro, chiếc điện thoại thông minh vẫn giữ nguyên thiết kế cao cấp, cụm 3 camera được nâng cấp, cấu hình mạnh mẽ cùng thời lượng pin lớn ấn tượng.',
        1000, 33000000, 0, null),
		 (3, 'iPhone 11 Pro 512GB', 'iphone-11-pro-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone-11-pro-gold-select-2019_5.png',
		'https://image.cellphones.com.vn/358x/media/catalog/product/l/a/layer_22_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/l/a/layer_25_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/l/a/layer_23_2.jpg',
        'Mỗi lần ra mắt phiên bản mới là mỗi lần iPhone chiếm sóng trên khắp các mặt trận và lần này cái tên khiến vô số người "sục sôi" là iPhone 11 Pro, chiếc điện thoại thông minh vẫn giữ nguyên thiết kế cao cấp, cụm 3 camera được nâng cấp, cấu hình mạnh mẽ cùng thời lượng pin lớn ấn tượng.',
        1000, 27000000, 0, null),
		-- ipad
		 (4, 'iPad Pro 12.9 2021 M1 5G 512GB', 'ipad-pro-12-9-2021-5g-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-2_4.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-2_2_1_1_1_1_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-1_2_1_1_1_1_1_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-2_4.jpg',
        'iPad Pro M1 12.9 inch WiFi Cellular 512GB (2021), một chiếc máy tính bảng cao cấp sở hữu loạt công nghệ đột phá như màn hình mini-LED, mạng 5G, vi xử lý Apple M1 cho hiệu năng xử lý vượt trội vượt khỏi giới hạn.',
        1000, 39000000, 1, null),
		(5, 'iPad Air 10.9 2020 4G 256GB', 'ipad-air-4-4g-256gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-silver_5.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-green_5.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-blue_4.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-pink_4.jpg',
        'iPad Air 4 2020 được trang bị công nghệ màn hình Liquid Retina độ phân giải 1640 x 2360 Pixels và có tần số quét đến 60 Hz mang đến chất lượng hiển thị mượt mà, các chuyển động hình ảnh trơn tru hơn, gần như loại bỏ hoàn toàn tình trạng giật lag. Nếu ít sử dụng 4G, bạn có thể tham khảo thêm iPad Air 4 wifi 256Gb để tiết kiệm chi phí.',
        1000, 22000000, 1, null),
		(6, 'iPad mini 6 4G 256GB', 'ipad-mini-6-4g-256gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-5_3.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-1_3.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-01_3.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-04_3.jpg',
        'Apple iPad mini 6 4G 256GB là sản phẩm iPad mini 2021 Apple giới thiệu đến toàn thể công chúng. Phiên bản này cung cấp hình thức kết nối Wifi và cả 4G, rất thuận tiện sử dụng ở bất kỳ nơi đâu. Máy có dung lượng lớn thích hợp lưu trữ nhiều dữ liệu',
        1000, 22800000, 1, null),
		-- laptop
		(7, 'MacBook Air M1 16GB 512GB 2020', 'macbook-air-2020-m1-16gb-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-air-silver-select-201810_2_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/_/0/_0003_macbook-air-gallery4-20201110_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/_/0/_0001_macbook-air-gallery2-20201110_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/_/0/_0003_dsc03720_4.jpg',
        'Macbook Air M1 2020 bản RAM 16GB và bộ nhớ 512GB được trang bị màn hình 13.3 inch với độ phân giải 2560 x 1600 pixels. Máy được cung cấp sức mạnh bởi con chip M1 mới nhất của Apple với 8 nhân, bao gồm 4 nhân hiệu suất cao và 4 nhân hiệu suất thấp. Theo công bố của Apple, vi xử lý này mạnh hơn đến 98% so với những chiếc laptop Windows và hiệu năng vượt trội hơn so với những chiếc Macbook Air chạy chip Intel.',
        1000, 37000000, 2, null),
		(8, 'Macbook Pro M1 Pro 16 10 CPU - 16 GPU 16GB 1TB 2021', 'macbook-pro-16-inch-2021-1tb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-05_4_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-004_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-003_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-001_2.jpg',
        'Macbook Pro 16 inch 2021 được trang bị chip Apple M1 Pro với 10CPU, 16GPU kết hợp dung lượng RAM 16GB và bộ nhớ SSD 1TB mang lại sức mạnh vượt trội. Đây là sản phẩm chính hãng Apple Việt Nam được bảo hành 12 tháng',
        1000, 70990000, 2, null),
		-- đồng hồ
		(9, 'Apple Watch Series 7 45mm (4G) Viền thép dây thép', 'apple-watch-series-7-45mm-4g-vien-thep-day-thep',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple_watch_series_7_cellular_41mm_silver_stainless_steel_silver_milanese_loop_34fr_screen__usen_copy_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/s/e/series_7_45mm.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple_watch_series_7_cellular_41mm_graphite_stainless_steel_graphite_milanese_loop_34fr_screen__usen_copy_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple_watch_series_7_cellular_41mm_gold_stainless_steel_gold_milanese_loop_34fr_screen__usen_copy_1.jpg',
        'Apple Watch Series 7 45mm (4G). Phiên bản này không chỉ được nâng cấp tiện ích mà còn sang trọng hơn với viền và dây làm từ thép. Thông tin bí ẩn mới nhất của Apple Watch được bật mí dưới đây!',
        1000, 21500000, 3, null),
		(10, 'Apple Watch Series 6 44mm (4G) Viền Thép Dây Thép', 'apple-watch-series-6-44mm-4g-vien-thep-day-thep',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-day-thep-1_1_1_3.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-day-thep-3_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-day-thep-2_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-bac-day-thep_2__2_1.jpg',
        'Hỗ trợ tính năng đo nhịp tim và nhiều chế độ sức khỏe khác nhau, Apple Watch S6 44mm 4G viền thép dây thép là chiếc đồng hồ thông minh sang trọng phù hợp cho các iFan lẫn vận động viên thể thao. Sự bổ sung dây đeo bằng thép càng làm tăng thêm vẻ sang trọng cho chiếc smartwatch này.',
        1000, 16590000, 3, null),
		(11, 'Apple Watch SE 44mm (4G) Viền Nhôm - Dây Vải', 'apple-watch-se-44mm-4g',
        'https://image.cellphones.com.vn/358x/media/catalog/product/d/o/download_2__1_20.png',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-se-44mm-4g-1_1_2.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/d/o/download_1__2_19.png',
        'https://image.cellphones.com.vn/358x/media/catalog/product/d/o/download_2_27.png',
        'Đầu tiên khi nhắc đến chiếc đồng hồ Apple Watch SE 44mm mọi người sẽ vô cùng thích thú với chất liệu mà nhà sản xuất đưa vào cho nó. Với viền nhôm và dây cao su cực kỳ chất lượng, giúp chiếc đồng hồ trở nên sang trọng và vô cùng bền bỉ, sử dụng bền lâu.',
        1000, 8400000, 3, null);
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.ShoppingCart
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java5`.`ShoppingCart` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `ProductId` INT NOT NULL,
  `Quantity` INT NULL DEFAULT 1,
  `CreatedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  CONSTRAINT `FK__ShoppingC__UserI__398D8EEE`
    FOREIGN KEY (`UserId`)
    REFERENCES `ASM_Java5`.`User` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK__ShoppingC__Produ__3A81B327`
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
CREATE TABLE IF NOT EXISTS `ASM_Java5`.`Order` (
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
  UNIQUE INDEX `UQ__Order__999B52298E8B51DC` (`OrderCode` ASC) VISIBLE,
  CONSTRAINT `FK__Order__UserId__403A8C7D`
    FOREIGN KEY (`UserId`)
    REFERENCES `ASM_Java5`.`User` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- INSERT INTO `ASM_Java5`.`Order`
--     (`Id`, `OrderCode`, `UserId`, `CreatedDate`, `Status`, `Fullname`, `Address`, `Email`, `PhoneNumber`, `TotalUnitPrice`)
-- VALUES (1, '34VB5540K83', 1, GETDATE(), 0, N'Nguyễn Văn Tèo', N'Phường Trung Mỹ Tây, Quận 12, TP.HCM', 'teonv@gmail.com', '0923235511', 1),
--        (2, '35VB5540K83', 1, GETDATE(), 0, N'Nguyễn Văn Tèo', N'Phường Trung Mỹ Tây, Quận 12, TP.HCM', 'teonv@gmail.com', '0923235511', 1),
--        (3, '36VB5540K83', 1, GETDATE(), 1, N'Nguyễn Văn Tèo', N'Phường Trung Mỹ Tây, Quận 12, TP.HCM', 'teonv@gmail.com', '0923235511', 1);
-- ----------------------------------------------------------------------------
-- Table ASM_Java5.OrderDetail
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASM_Java5`.`OrderDetail` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `OrderId` INT NOT NULL,
  `ProductId` INT NOT NULL,
  `Quantity` INT NOT NULL,
  `TotalUnitPrice` DOUBLE NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `FK__OrderDeta__Order__4316F928`
    FOREIGN KEY (`OrderId`)
    REFERENCES `ASM_Java5`.`Order` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK__OrderDeta__Produ__440B1D61`
    FOREIGN KEY (`ProductId`)
    REFERENCES `ASM_Java5`.`Product` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
SET FOREIGN_KEY_CHECKS = 1;


