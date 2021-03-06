USE `kiki's` ;
set foreign_key_checks=0;

drop table if exists address;
drop table if exists clients;
drop table if exists delivery;
drop table if exists credit_card;
drop table if exists cash;
drop table if exists center;
drop table if exists parcel;

create table address
	(country varchar(20),
	province varchar(2),
	city varchar(20),
	street_name varchar(30),
	house_num int NOT NULL,
	PC varchar(7) NOT NULL,
	PRIMARY KEY(PC, house_num)
	);

create table clients
	(clID int(11) NOT NULL,
	fname varchar(30),
	lname varchar(30),
	PC varchar(7) NOT NULL,
	house_num int NOT NULL,
	phone_num varchar(12),
	PRIMARY KEY(clID),
	FOREIGN KEY(PC, house_num) REFERENCES address(PC, house_num)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
	);

create table delivery
	(
	dID int(6) not null,
	type varchar(20),
	status varchar(20),
	sender_ID int(6) not null,
	receiver_ID int(6) not null,
	PRIMARY KEY(dID),
	FOREIGN KEY(sender_ID) REFERENCES clients(clID),
	FOREIGN KEY(receiver_ID) REFERENCES clients(clID)
		ON DELETE CASCADE
		ON UPDATE CASCADE
	);
    
create table credit_card
	(payID int(3) NOT NULL,
	amount float,
	onDate varchar(10),
	credit_card_num varchar(30),
	CSV int,
	name varchar(30),
	expiry_date varchar(5),
	type varchar(255),
	dID int(6),
	PRIMARY KEY(payID),
	FOREIGN KEY(dID) REFERENCES delivery(dID)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
	);

create table cash
	(amount float,
	payID int(3) NOT NULL,
	onDate varchar(10),
	dID int(6),
	PRIMARY KEY(payID),
	FOREIGN KEY(dID) REFERENCES delivery(dID)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
	);

create table center
    (cID varchar(30) NOT NULL,
    center_addr varchar(100),
    PRIMARY KEY(cID)
    );

create table parcel
	(pID int(6) not null,
	length float,
	width float,
	weight float,
    height float,
    cID varchar(30),
    next_cID varchar(30),
	dID int(6) not null,
	PRIMARY KEY(pID, dID),
	FOREIGN KEY (cID) REFERENCES center(cID),
	FOREIGN KEY(dID) REFERENCES delivery(dID)
		ON DELETE CASCADE
		ON UPDATE CASCADE
	);

insert into address
	values ('Canada' , 'QC' , 'Mont-Joli' , 'Lindsay Rd.' , '304', 'G5H 9R0');
	
insert into address
	values ('Canada' , 'AB' , 'Calgary' , '5th Avenue' , '640' , 'T2P 7E9');

insert into address
	values ('Canada' , 'BC' , 'Langley' , 'Dewdney Trunk Rd.' , '22561' , 'V2X 0S8');

insert into address
	values ('Canada' , 'NL' , 'Corner Brook' , 'Riverside Dr.' , '55' , 'A2H 2X6');

insert into address
	values ('USA' , 'CA' , 'Beverly Hills' , 'Rodeo Dr.' , '5' , '90210');

insert into clients
	values ('458384' , 'Stella' , 'Fang' , 'G5H 9R0' , '304' , '778-838-2222');

insert into clients
	values ('203049' , 'Lansi' , 'Chu' , 'T2P 7E9' , '2001' , '604-333-4234');

insert into clients
	values ('393934' , 'Daniel' , 'Choi' , 'V2X 0S8' , '13583' , '778-311-3948');

insert into clients
	values ('139284' , 'Ben' , 'Gee' , 'A2H 2X6' , '21' , '778-343-1018');

insert into clients
	values ('378439' , 'Yoony' , 'Ok' , 'D1M 8E6' , '49' , '604-534-9876');

insert into delivery
	values ('342350' , 'expedited' , 'just left' , '234324' , '0000');

insert into delivery
	values ('193030' , 'express' , 'in transit' , '3422' , '4353');

insert into delivery
	values ('383899' , 'standard' , 'arrived' , '35327' , '4444');

insert into delivery
	values ('301274' , 'expedited' , 'in transit' , '43334' , '4444');

insert into delivery
	values ('394857' , 'express' , 'just left' , '234' , '3333');

insert into delivery
	values ('123456' , 'express' , 'just left' , '458384' , '3333');

insert into delivery
	values ('234567' , 'expedited' , 'in transit' , '203049' , '2222');

insert into delivery
	values ('345678' , 'standard' , 'arrived' , '393934' , '0000');

insert into delivery
	values ('456789' , 'express' , 'in transit' , '000033' , '0000');

insert into delivery
	values ('567890' , 'expedited' , 'just left' , '378439', '3435');

insert into cash
	values ('15.39' , '678' , '2000-02-28' , '342350');

insert into cash
	values ('45.22' , '789' , '1993-01-01' , '193030');

insert into cash
	values ('90.01' , '890' , '2015-04-19' , '383899');

insert into cash
	values ('13.56' , '901' , '1999-05-23' , '301274');

insert into cash
	values ('53.45' , '902' , '2016-09-15' , '394857');

insert into credit_card
    values ('123' , '15.39' , '2000-02-28' , '4520 3450 2234 1988' ,
    '456' , 'Stella Fang' , '09/18' , 'Visa' , '123456');

insert into credit_card
	values ('234' , '45.22' , '1993-01-01' , '5198 2352 3451 9870' , 
		'111' , 'Lansi Chu' , '07/16' , 'MasterCard', '234567');

insert into credit_card
	values ('345' , '90.01' , '2015-04-19' , '347 2350 2377 1558' , 
		'222' , 'Daniel Choi' , '06/19' , 'American Express' , '345678');

insert into credit_card
	values ('456' , '13.56' , '1999-05-23' , '4520 3459 4520 2429' , 
		'333' , 'Ben Gee' , '04/19' , 'Visa' , '456789');

insert into credit_card
	values ('567' , '53.45' , '2016-09-15' , '375 3459 4903 8612' , 
		'444' , 'Yoony Ok' , '10/17' , 'American Express' , '567890');

insert into parcel
	values ('123125' , '23.4' , '34.6' , '12.3' , '0.34' , 'ubc', 'toronto3', '342350');

insert into parcel
	values ('342353' , '12.0' , '13.2' , '20.4' , '0.23' , 'coquitlam', 'ubc', '193030');

insert into parcel
	values ('323' , '14.2' , '15.3' , '8.0' , '0.11' , 'toronto3', 'vancouver1', '383899');

insert into parcel
	values ('4355' , '34.0' , '20.4' , '15.3' , '1.00' ,'burnabysouth', 'burnabynorth', '301274');

insert into parcel
	values ('234' , '23.5' , '18.3' , '20.3' , '2.32' , 'toronto3', 'coquitlam', '394857');

insert into parcel
	values ('564738' , '14.2' , '54.2' , '7.41' , '0.98' , 'ubc', 'sfu', '567890');

insert into parcel
	values ('901234' , '43.7' , '54.2' , '67.3' , '8.34' , 'burnabysouth', 'burnabynorth', '456789');

insert into parcel
	values ('345671' , '57.1' , '52.9' , '98.4' , '6.32' , 'toronto3', 'coquitlam', '345678');

insert into parcel
	values ('092874' , ' 65.9' , '82.6' , '65.2' , '8.30' , 'surrey3', 'surrey2', '234567');

insert into parcel
	values ('234875' , '45.6' , '76.2' , '65.9' , '3.48' , 'portcoquitlam', 'langley', '123456');

insert into center
	values ('portcoquitlam', '234 seward ave. port coquitlam v3r 5y7');

insert into center
	values ('ubc', '3334 university blvd. vancouver v6t 2l4');

insert into center
	values ('sfu', '1113 simple st. burnaby y3u 8r2');

insert into center
	values ('burnabysouth', '189 sandy st. burnaby 7h9 9e9');

insert into center
	values ('burnabynorth', '454 deer lake pkwy. burnaby 3u3 8g0');

