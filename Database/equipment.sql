CREATE TABLE Equipment (
EquipmentID INTEGER,
LocationID INTEGER,
BranchID INTEGER,
Name text,
PRIMARY KEY (EquipmentID),
FOREIGN KEY (LocationID)
    REFERENCES Facility (LocationID),
FOREIGN KEY (BranchID)
    REFERENCES Branch (BranchID)
);




CREATE TABLE Branch (
BranchID INTEGER,
Name text,
PRIMARY KEY (BranchID)
);

INSERT INTO Branch (Name) VALUES ("Cardio");

INSERT INTO Equipment (LocationID,BranchID,Name) VALUES (3,1,"Treadmill");
INSERT INTO Equipment (LocationID,BranchID,Name) VALUES (3,1,"Treadmill");
INSERT INTO Equipment (LocationID,BranchID,Name) VALUES (3,1,"Treadmill");
INSERT INTO Equipment (LocationID,BranchID,Name) VALUES (3,1,"Rowing machine");
INSERT INTO Equipment (LocationID,BranchID,Name) VALUES (3,1,"Rowing machine");