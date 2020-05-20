CREATE TABLE ClassEnrollment (
Pnr INTEGER ,
ClassScheduleID INTEGER,
Date text,
PRIMARY KEY (Pnr,ClassScheduleID),
FOREIGN KEY (Pnr)
    REFERENCES Members (Pnr),
FOREIGN KEY (ClassScheduleID)
    REFERENCES ClassSchedule (ClassScheduleID)
);



CREATE TABLE Class (
ClassID INTEGER,
Name text,
PRIMARY KEY (ClassID)
);

INSERT INTO Class VALUES (1, "Yoga");
INSERT INTO Class VALUES (2, "Spinning");
INSERT INTO Class VALUES (3, "Body pump");


CREATE TABLE ClassInstructor(
    ClassID INTEGER,
    InstructorID INTEGER,
    PRIMARY KEY (ClassID,InstructorID),
    FOREIGN KEY (ClassID)
        REFERENCES Class (ClassID),
    FOREIGN KEY (InstructorID)
        REFERENCES Instructor (InstructorID)
);




CREATE TABLE ClassSchedule (
ClassScheduleID INTEGER,
ClassID INTEGER,
RoomID INTEGER,
InstructorID INTEGER,
Date text,
StartTime text,
EndTime text,
SeatsLeft INTEGER,
Seats INTEGER,
PRIMARY KEY (ClassScheduleID),
FOREIGN KEY (RoomID)
    REFERENCES Room (RoomID),
FOREIGN KEY (InstructorID)
    REFERENCES Instructor (InstructorID),
FOREIGN KEY (ClassID)
    REFERENCES Class (ClassID)
    
);



CREATE TABLE Facility ( 
LocationID INTEGER,
Name text,
PRIMARY KEY (LocationID)
);

INSERT INTO Facility VALUES (1, "Majorna");
INSERT INTO Facility VALUES (2, "Lindholmen");
INSERT INTO Facility VALUES (3, "Eriksberg");





CREATE TABLE Room (
RoomID INTEGER,
LocationID INTEGER,
Capacity INTEGER,
PRIMARY KEY (RoomID),
FOREIGN KEY (LocationID)
    REFERENCES Facility (LocationID)
);

INSERT INTO Room VALUES (1001, 1, 15);
INSERT INTO Room VALUES (1002, 1, 25);
INSERT INTO Room VALUES (2001, 2, 50);
INSERT INTO Room VALUES (2002, 2, 150);
INSERT INTO Room VALUES (3001, 3, 50);
INSERT INTO Room VALUES (3002, 3, 35);
INSERT INTO Room VALUES (3003, 3, 100);





CREATE TABLE Instructor(
InstructorID INTEGER,
Fnamn text,
Enamn text,
PRIMARY KEY (InstructorID)
);

INSERT INTO Instructor VALUES (901212, "Martin", "Eriksson");
INSERT INTO Instructor VALUES (601212, "Katarina", "Ek");
INSERT INTO Instructor VALUES (891212, "Jenny", "Andersson");





