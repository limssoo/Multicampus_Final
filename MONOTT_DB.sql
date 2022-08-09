drop table user;

create table User (
	ID varchar(20) not null primary key,
    Password varchar(20) not null,
    Name varchar(20) not null,
    Phone varchar(20) not null,
    Birth Date not null,
    Address varchar(50),
    Point int,
    Level int
);

create table Board(
	Board_Key int auto_increment not null unique,
    ID varchar(20) not null,
    Category_Key int not null,
    Board_Title varchar(100) not null,
    Board_Content varchar(500) not null,
    Board_View int,
    Write_Date Datetime not null,
    Board_Like int,
    Primary key(Board_Key, ID, Category_Key),
    foreign key (ID) REFERENCES User (ID),
    foreign key (Category_Key) REFERENCES Category (Category_Key)
);

create table Category(
	Category_Key int not null primary key,
    Category_Name varchar(20) not null,
    Parent_Category varchar(10) not null
);

create table Comment(
	Comment_Number int not null,
    ID varchar(20) not null,
    Board_Key int auto_increment not null  unique,
    Comment_Content varchar(200) not null,
    Comment_Date Datetime not null,
    Comment_Like int,
    primary key(Comment_Number, ID, Board_Key),
    foreign key (ID) references User (ID),
    foreign key (Board_Key) references Board (Board_Key)
);

create table Contents(
	Content_Key int auto_increment not null,
    Category_Key int not null,
    Theater_Name varchar(20) not null,
    Content_Name varchar(100) not null,
    Content_Info varchar(500) not null,
    Content_Image BLOB,
    Content_Score int not null,
    primary key(Content_Key, Category_Key, Theater_Name),
    foreign key (Category_Key) references Category (Category_Key),
    foreign key (Theater_Name) references Theater (Theater_Name)
);

drop table Contents;
drop table Theater;

create Table Theater (
	Theater_Name varchar(20) not null primary key,
    Brand_Name varchar(20) not null,
    Info varchar(100) not null,
    Map varchar(100) not null
);

create Table Review (
	Review_Number int not null,
    ID varchar(20) not null,
    Content_Key int auto_increment not null,
    Review_Content varchar(200) not null,
    Review_Date date not null,
    Review_Score int not null,
    Review_Like int,
    primary key (Review_Number, ID, Content_Key),
    foreign key (ID) references User (ID),
    foreign key (Content_Key) references Contents (Content_Key)
);

create table administrator (
	Admin_ID varchar(20) not null unique primary key,
    password varchar(20) not null
);

Create table Advertisement (
	Ad_Key int not null auto_increment,
    Admin_ID varchar(20) not null,
    Ad_Image BLOB,
    Ad_Company varchar(20) not null,
    Ad_uploadDate Date not null,
    Ad_EndDate Date not null,
    primary key (Ad_Key, Admin_ID),
    foreign key (Admin_ID) references administrator (Admin_ID)
);

create table statistic (
	Statistic_Key int auto_increment not null primary key,
    Name varchar(20) not null,
    value varchar(500) not null,
    Image BLOB
);