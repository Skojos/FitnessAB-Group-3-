CREATE TABLE Customer (
    pNr INTEGER,
    Fname text,
    Lname text,
    StreetAddress text,
    ZipCode INTEGER,
    District text,
    Email text,
    Password text,
    PhoneNumber INTEGER,
    PaymentMethod INTEGER,
    PRIMARY KEY (pNr),
    FOREIGN KEY (PaymentMethod)
        REFERENCES PaymentMethod (ID)
);



CREATE TABLE CreditCardInformation (
    ID INTEGER,
    Fname text,
    LName text,
    cardNumber INTEGER,
    validDate text,
    cvcCode INTEGER,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID)
        REFERENCES customer (pNr)
);

CREATE TABLE PaymentMethod (
    ID INTEGER PRIMARY KEY,
    name text
);

INSERT INTO PaymentMethod VALUES (1,"Creditcard");
INSERT INTO PaymentMethod VALUES (2,"Invoice");



CREATE TABLE Membership (
    ID INTEGER,
    JoinDate text,
    EndDate text,
    TierType INTEGER,
    MembershipStatus INTEGER,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID)
        REFERENCES Customer (pNr),
    FOREIGN KEY (TierType)
        REFERENCES Tier (ID),
    FOREIGN KEY (MembershipStatus)
        REFERENCES MembershipStatus (ID)
);

CREATE TABLE Tier (
    ID INTEGER,
    name text,
    PRIMARY KEY (ID)
);

INSERT INTO Tier VALUES (1,"Bronze");
INSERT INTO Tier VALUES (2,"Silver");
INSERT INTO Tier VALUES (3,"Gold");

CREATE TABLE MembershipStatus (
    ID INTEGER,
    name text,
    PRIMARY KEY (ID)
);

INSERT INTO MembershipStatus VALUES (1,"Active");
INSERT INTO MembershipStatus VALUES (2,"Paused");
INSERT INTO MembershipStatus VALUES (3,"Canceled");


CREATE TABLE Staff(
employeeID Integer PRIMARY KEY,
password text
);

INSERT INTO Staff VALUES (1234,"admin");