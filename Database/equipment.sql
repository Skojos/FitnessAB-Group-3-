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
PRIMARY KEY (BranchID,Name)
);