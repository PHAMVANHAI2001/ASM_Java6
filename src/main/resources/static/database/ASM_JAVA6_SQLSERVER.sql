USE master
GO

DROP DATABASE [ASM_Java6]
GO

CREATE DATABASE [ASM_Java6]
GO

USE [ASM_Java6]
GO

---- Create Tables ----

CREATE TABLE [Role](
	Id INT				NOT NULL IDENTITY(1,1),
    [Name] NVARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY(Id)
);
GO

CREATE TABLE [User]
(
    Id          INT IDENTITY(1,1),
    Username    VARCHAR(50)			NOT NULL UNIQUE,
    [Password]  VARCHAR(128)		NOT NULL,
    Fullname    NVARCHAR(128)		NOT NULL,
    Email       VARCHAR(128)		NOT NULL UNIQUE,
    [Address]   NVARCHAR(255)		NULL,
    PhoneNumber VARCHAR(10)			NULL,
    Photo       VARCHAR(128)		NULL,
    CreatedDate DATETIME			NOT NULL DEFAULT GETDATE(),
	[Enabled]   BIT					NOT NULL DEFAULT 1,
    PRIMARY KEY(Id)
);
GO

CREATE TABLE [Authority] (
	Id INT		NOT NULL IDENTITY(1,1),
    UserId INT	NOT NULL,
    RoleId INT	NOT NULL,
    PRIMARY KEY(Id),
	FOREIGN KEY (UserId) REFERENCES [User] (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (RoleId) REFERENCES [Role] (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

CREATE TABLE [Category]
(
    Id     INT IDENTITY(1,1),
    [Name] NVARCHAR(50)		NOT NULL UNIQUE,
    Slug   NVARCHAR(50)		NOT NULL UNIQUE,
	PRIMARY KEY(Id)
);
GO

CREATE TABLE [Discount]
(
    Id          INT IDENTITY(1,1),
    [Name]      NVARCHAR(50)	NOT NULL,
    SaleOff     INT				NOT NULL,
    StartDate   DATETIME		NOT NULL,
    EndDate     DATETIME		NOT NULL,
    IsActive    BIT				NOT NULL DEFAULT 1,
    CreatedDate DATETIME		NOT NULL DEFAULT GETDATE(),
	PRIMARY KEY(Id)
);
GO

CREATE TABLE [Product]
(
    Id            INT IDENTITY(1, 1),
    [Name]        NVARCHAR(255) NOT NULL,
    Slug          NVARCHAR(255) NOT NULL UNIQUE,
    [Image]       VARCHAR(MAX)  NOT NULL,
    ImagePreview1 VARCHAR(MAX)  NOT NULL,
    ImagePreview2 VARCHAR(MAX)  NOT NULL,
    ImagePreview3 VARCHAR(MAX)  NOT NULL,
    [Description] NVARCHAR(MAX) NOT NULL,
    Quantity      INT           NOT NULL,
    UnitPrice     FLOAT         NOT NULL,
    CreatedDate   DATETIME      NOT NULL DEFAULT GETDATE(), 
    Available     INT           NOT NULL DEFAULT 0, -- 0: Còn hàng, 1: Hết hàng, 2: Ngừng kinh doanh
    CategoryId    INT           NOT NULL,
    DiscountId    INT           NULL,
	PRIMARY KEY(Id),
    FOREIGN KEY (CategoryId) REFERENCES Category (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (DiscountId) REFERENCES Discount (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

CREATE TABLE [Cart]
(
    Id          INT IDENTITY(1, 1),
    UserId      INT      NOT NULL,
    ProductId   INT      NOT NULL,
    Quantity    INT      NOT NULL DEFAULT 1,
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(),
	PRIMARY KEY(Id),
    FOREIGN KEY (UserId) REFERENCES [User] (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ProductId) REFERENCES Product (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

CREATE TABLE [Order]
(
    Id          INT IDENTITY(1, 1) PRIMARY KEY,
    OrderCode   VARCHAR(11)		   NOT NULL UNIQUE,
    UserId      INT                NOT NULL,
    CreatedDate DATETIME           NOT NULL DEFAULT GETDATE(),
    [Status]    INT                NOT NULL DEFAULT 0, -- 0: In Progress, 1: Delivered, 2: Delayed, 3: Canceled
    Fullname    NVARCHAR(128)      NOT NULL,
    [Address]   NVARCHAR(255)      NOT NULL,
    Email       VARCHAR(128)       NOT NULL,
    PhoneNumber VARCHAR(10)        NOT NULL,
	TotalUnitPrice FLOAT		   NOT NULL,
    FOREIGN KEY (UserId) REFERENCES [User] (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

CREATE TABLE [OrderDetails]
(
    Id        INT IDENTITY (1, 1) PRIMARY KEY,
    OrderId   INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity  INT NOT NULL,
	TotalUnitPrice FLOAT NOT NULL,
    FOREIGN KEY (OrderId) REFERENCES [Order] (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ProductId) REFERENCES Product (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

---- Insert Data ----
SET IDENTITY_INSERT [Role] ON
INSERT INTO [Role] (Id,[Name]) 
	VALUES	(1,'CUSTOMER'), 
			(2,'STAFF'),
			(3,'DIRECTOR')
SET IDENTITY_INSERT [Role] OFF
GO

SET IDENTITY_INSERT [User] ON
INSERT INTO [User]
    (Id, Username, [Password], Fullname, Email)
	VALUES	(1,'TeoNV','123456789',N'Nguyễn Văn Tèo','teonv@gmail.com'),
			(2,'VietTN','123456789',N'Trần Nhật Việt','vietnt@gmail.com'),
			(3,'MaiNT','123456789',N'Nguyễn Thị Mai','maint@gmail.com'),
			(4,'HaiPV','123456789',N'Phạm Văn Hải','haipham2001vn@gmail.com')
SET IDENTITY_INSERT [User] OFF
GO

SET IDENTITY_INSERT [Authority] ON
INSERT INTO [Authority] (Id,UserId,RoleId) 
	VALUES	(1,1,1),
			(2,2,1),
			(3,3,1),
			(4,3,2),
			(5,4,1),
			(6,4,3)
SET IDENTITY_INSERT [Authority] OFF
GO

SET IDENTITY_INSERT [Category] ON
INSERT INTO [Category]
    (Id, Name, Slug)
VALUES (1, N'Điện thoại', N'phone'),
       (2, N'Máy tính bảng', N'tablet'),
       (3, N'Máy tính xách tay', N'laptop'),
       (4, N'Đồng hồ thông minh', N'smartwatch')
SET IDENTITY_INSERT [Category] OFF
GO

SET IDENTITY_INSERT [Discount] ON
INSERT INTO [Discount]
    (Id, [Name], SaleOff, StartDate, EndDate)
VALUES (1, N'FLASH SALE', 10, GETDATE(), DATEADD(MONTH, 2, GETDATE())),
       (2, N'Khuyến mãi đặc biệt', 20, GETDATE(), DATEADD(MONTH, 2, GETDATE()))
SET IDENTITY_INSERT [Discount] OFF
GO

SET IDENTITY_INSERT [Product] ON
INSERT INTO [Product]
(Id, [Name], Slug, [Image], ImagePreview1, ImagePreview2, ImagePreview3, [Description],
 Quantity, UnitPrice, CategoryId, DiscountId)
VALUES 
	-- điện thoại
		(1, N'iPhone 13 Pro Max 512GB', N'iphone-13-pro-max-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-5_7.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-5_4_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-3_5.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_13-_pro-2_1_4.jpg',
        N'Mỗi lần ra mắt phiên bản mới là mỗi lần iPhone chiếm sóng trên khắp các mặt trận và lần này cái tên khiến vô số người "sục sôi" là iPhone 13 Pro, chiếc điện thoại thông minh vẫn giữ nguyên thiết kế cao cấp, cụm 3 camera được nâng cấp, cấu hình mạnh mẽ cùng thời lượng pin lớn ấn tượng.',
        1000, 38000000, 1, 1),
		(2, N'iPhone 12 Pro Max 512GB', N'iphone-12-pro-max-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone_12_pro_max_blue.png',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone-12-pro-max_3__6.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/p/h/photo_2020-10-13_22-12-43.jpg_1_7_2.png',
        'https://image.cellphones.com.vn/358x/media/catalog/product/p/h/photo_2020-10-13_22-12-33.jpg_1_8.png',
        N'Mỗi lần ra mắt phiên bản mới là mỗi lần iPhone chiếm sóng trên khắp các mặt trận và lần này cái tên khiến vô số người "sục sôi" là iPhone 12 Pro, chiếc điện thoại thông minh vẫn giữ nguyên thiết kế cao cấp, cụm 3 camera được nâng cấp, cấu hình mạnh mẽ cùng thời lượng pin lớn ấn tượng.',
        1000, 33000000, 1, 2),
		 (3, N'iPhone 11 Pro 512GB', N'iphone-11-pro-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/iphone-11-pro-gold-select-2019_5.png',
		'https://image.cellphones.com.vn/358x/media/catalog/product/l/a/layer_22_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/l/a/layer_25_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/l/a/layer_23_2.jpg',
        N'Mỗi lần ra mắt phiên bản mới là mỗi lần iPhone chiếm sóng trên khắp các mặt trận và lần này cái tên khiến vô số người "sục sôi" là iPhone 11 Pro, chiếc điện thoại thông minh vẫn giữ nguyên thiết kế cao cấp, cụm 3 camera được nâng cấp, cấu hình mạnh mẽ cùng thời lượng pin lớn ấn tượng.',
        1000, 27000000, 1, null),
		-- ipad
		 (4, N'iPad Pro 12.9 2021 M1 5G 512GB', N'ipad-pro-12-9-2021-5g-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-2_4.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-2_2_1_1_1_1_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-1_2_1_1_1_1_1_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-pro-12-9-2021-2_4.jpg',
        N'iPad Pro M1 12.9 inch WiFi Cellular 512GB (2021), một chiếc máy tính bảng cao cấp sở hữu loạt công nghệ đột phá như màn hình mini-LED, mạng 5G, vi xử lý Apple M1 cho hiệu năng xử lý vượt trội vượt khỏi giới hạn.',
        1000, 39000000, 2, null),
		(5, N'iPad Air 10.9 2020 4G 256GB', N'ipad-air-4-4g-256gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-silver_5.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-green_5.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-blue_4.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-air-2020-pink_4.jpg',
        N'iPad Air 4 2020 được trang bị công nghệ màn hình Liquid Retina độ phân giải 1640 x 2360 Pixels và có tần số quét đến 60 Hz mang đến chất lượng hiển thị mượt mà, các chuyển động hình ảnh trơn tru hơn, gần như loại bỏ hoàn toàn tình trạng giật lag. Nếu ít sử dụng 4G, bạn có thể tham khảo thêm iPad Air 4 wifi 256Gb để tiết kiệm chi phí.',
        1000, 22000000, 2, null),
		(6, N'iPad mini 6 4G 256GB', N'ipad-mini-6-4g-256gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-5_3.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-1_3.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-01_3.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/i/p/ipad-mini-6-04_3.jpg',
        N'Apple iPad mini 6 4G 256GB là sản phẩm iPad mini 2021 Apple giới thiệu đến toàn thể công chúng. Phiên bản này cung cấp hình thức kết nối Wifi và cả 4G, rất thuận tiện sử dụng ở bất kỳ nơi đâu. Máy có dung lượng lớn thích hợp lưu trữ nhiều dữ liệu',
        1000, 22800000, 2, null),
		-- laptop
		(7, N'MacBook Air M1 16GB 512GB 2020', N'macbook-air-2020-m1-16gb-512gb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-air-silver-select-201810_2_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/_/0/_0003_macbook-air-gallery4-20201110_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/_/0/_0001_macbook-air-gallery2-20201110_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/_/0/_0003_dsc03720_4.jpg',
        N'Macbook Air M1 2020 bản RAM 16GB và bộ nhớ 512GB được trang bị màn hình 13.3 inch với độ phân giải 2560 x 1600 pixels. Máy được cung cấp sức mạnh bởi con chip M1 mới nhất của Apple với 8 nhân, bao gồm 4 nhân hiệu suất cao và 4 nhân hiệu suất thấp. Theo công bố của Apple, vi xử lý này mạnh hơn đến 98% so với những chiếc laptop Windows và hiệu năng vượt trội hơn so với những chiếc Macbook Air chạy chip Intel.',
        1000, 37000000, 3, null),
		(8, N'Macbook Pro M1 Pro 16 10 CPU - 16 GPU 16GB 1TB 2021', N'macbook-pro-16-inch-2021-1tb',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-05_4_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-004_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-003_2.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/m/a/macbook-pro-2021-001_2.jpg',
        N'Macbook Pro 16 inch 2021 được trang bị chip Apple M1 Pro với 10CPU, 16GPU kết hợp dung lượng RAM 16GB và bộ nhớ SSD 1TB mang lại sức mạnh vượt trội. Đây là sản phẩm chính hãng Apple Việt Nam được bảo hành 12 tháng',
        1000, 70990000, 3, null),
		-- đồng hồ
		(9, N'Apple Watch Series 7 45mm (4G) Viền thép dây thép', N'apple-watch-series-7-45mm-4g-vien-thep-day-thep',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple_watch_series_7_cellular_41mm_silver_stainless_steel_silver_milanese_loop_34fr_screen__usen_copy_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/s/e/series_7_45mm.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple_watch_series_7_cellular_41mm_graphite_stainless_steel_graphite_milanese_loop_34fr_screen__usen_copy_1_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple_watch_series_7_cellular_41mm_gold_stainless_steel_gold_milanese_loop_34fr_screen__usen_copy_1.jpg',
        N'Apple Watch Series 7 45mm (4G). Phiên bản này không chỉ được nâng cấp tiện ích mà còn sang trọng hơn với viền và dây làm từ thép. Thông tin bí ẩn mới nhất của Apple Watch được bật mí dưới đây!',
        1000, 21500000, 4, null),
		(10, N'Apple Watch Series 6 44mm (4G) Viền Thép Dây Thép', N'apple-watch-series-6-44mm-4g-vien-thep-day-thep',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-day-thep-1_1_1_3.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-day-thep-3_1.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-day-thep-2_1.jpg',
        'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-series-6-44mm-4g-vien-thep-bac-day-thep_2__2_1.jpg',
        N'Hỗ trợ tính năng đo nhịp tim và nhiều chế độ sức khỏe khác nhau, Apple Watch S6 44mm 4G viền thép dây thép là chiếc đồng hồ thông minh sang trọng phù hợp cho các iFan lẫn vận động viên thể thao. Sự bổ sung dây đeo bằng thép càng làm tăng thêm vẻ sang trọng cho chiếc smartwatch này.',
        1000, 16590000, 4, null),
		(11, N'Apple Watch SE 44mm (4G) Viền Nhôm - Dây Vải', N'apple-watch-se-44mm-4g',
        'https://image.cellphones.com.vn/358x/media/catalog/product/d/o/download_2__1_20.png',
		'https://image.cellphones.com.vn/358x/media/catalog/product/a/p/apple-watch-se-44mm-4g-1_1_2.jpg',
		'https://image.cellphones.com.vn/358x/media/catalog/product/d/o/download_1__2_19.png',
        'https://image.cellphones.com.vn/358x/media/catalog/product/d/o/download_2_27.png',
        N'Đầu tiên khi nhắc đến chiếc đồng hồ Apple Watch SE 44mm mọi người sẽ vô cùng thích thú với chất liệu mà nhà sản xuất đưa vào cho nó. Với viền nhôm và dây cao su cực kỳ chất lượng, giúp chiếc đồng hồ trở nên sang trọng và vô cùng bền bỉ, sử dụng bền lâu.',
        1000, 8400000, 4, null)
SET IDENTITY_INSERT [Product] OFF
GO

