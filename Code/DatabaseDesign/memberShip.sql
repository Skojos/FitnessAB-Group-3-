CREATE TABLE Members (
    Pnr INTEGER,
    Fname text,
    Lname text,
    PhoneNumber INTEGER,
    Email text,
    Password text,
    PaymentID INTEGER,
    PRIMARY KEY (Pnr),
    FOREIGN KEY (PaymentID)
        REFERENCES PaymentMethod (PaymentID)
);



CREATE TABLE CreditCardInformation (
    Pnr INTEGER,
    Fname text,
    LName text,
    cardNumber INTEGER,
    validDate text,
    cvcCode INTEGER,
    PRIMARY KEY (Pnr),
    FOREIGN KEY (Pnr)
        REFERENCES Members (Pnr)
);

CREATE TABLE PaymentMethod (
    PaymentID INTEGER PRIMARY KEY,
    name text
);

INSERT INTO PaymentMethod VALUES (1,"Creditcard");
INSERT INTO PaymentMethod VALUES (2,"Invoice");


CREATE TABLE MembershipTier (
    TierID INTEGER,
    name text,
    PRIMARY KEY (TierID)
);

INSERT INTO MembershipTier VALUES (1,"Bronze");
INSERT INTO MembershipTier VALUES (2,"Silver");
INSERT INTO MembershipTier VALUES (3,"Gold");

CREATE TABLE MembershipStatus (
    Pnr INTEGER,
    TierID text,
    StartDate text,
    EndDate text,
    Status text,
    PRIMARY KEY (Pnr),
    FOREIGN KEY (Pnr)
        REFERENCES Members (Pnr),
    FOREIGN KEY (TierID)
        REFERENCES MembershipTier (TierID)
);



CREATE TABLE Staff(
employeeID Integer PRIMARY KEY,
password text
);

INSERT INTO Staff VALUES (1234,"admin");