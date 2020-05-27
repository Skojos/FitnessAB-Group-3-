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


INSERT INTO ClassInstructor VALUES (1,6512120000);
INSERT INTO ClassInstructor VALUES (2,8012120000);
INSERT INTO ClassInstructor VALUES (3,9012120000);
INSERT INTO ClassInstructor VALUES (3,6012120000);
INSERT INTO ClassInstructor VALUES (3,8912120000);





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


INSERT INTO ClassSchedule (ClassID,RoomID,InstructorID,Date,StartTime,EndTime,SeatsLeft,Seats) VALUES (3,2001,8912120000,"2020-06-04","12:00","13:00",50,50);
INSERT INTO ClassSchedule (ClassID,RoomID,InstructorID,Date,StartTime,EndTime,SeatsLeft,Seats) VALUES (3,1002,6012120000,"2020-06-04","17:00","18:00",25,25);
INSERT INTO ClassSchedule (ClassID,RoomID,InstructorID,Date,StartTime,EndTime,SeatsLeft,Seats) VALUES (3,3003,9012120000,"2020-06-04","17:00","18:00",40,40);
INSERT INTO ClassSchedule (ClassID,RoomID,InstructorID,Date,StartTime,EndTime,SeatsLeft,Seats) VALUES (1,3002,6512120000,"2020-06-04","12:00","13:00",20,20);
INSERT INTO ClassSchedule (ClassID,RoomID,InstructorID,Date,StartTime,EndTime,SeatsLeft,Seats) VALUES (2,3001,8012120000,"2020-06-04","16:30","17:30",30,30);






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
INSERT INTO Room VALUES (2002, 2, 40);
INSERT INTO Room VALUES (3001, 3, 30);
INSERT INTO Room VALUES (3002, 3, 20);
INSERT INTO Room VALUES (3003, 3, 40);





CREATE TABLE Instructor(
InstructorID INTEGER,
Fnamn text,
Enamn text,
PRIMARY KEY (InstructorID)
);

INSERT INTO Instructor VALUES (9012120000, "Mia", "Eriksson");
INSERT INTO Instructor VALUES (6012120000, "Katarina", "Ek");
INSERT INTO Instructor VALUES (8912120000, "Lina", "Andersson");
INSERT INTO Instructor VALUES (6512120000, "Lisa", "Berg");
INSERT INTO Instructor VALUES (8012120000, "Martin", "Johansson");





