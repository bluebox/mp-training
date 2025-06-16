USE library_db;
CREATE TABLE issue_records (
  IssueID INT AUTO_INCREMENT PRIMARY KEY,
  BookID INT NOT NULL,
  MemberID INT NOT NULL,
  Status CHAR(1) NOT NULL CHECK (Status IN ('I','R')),
  IssueDate DATE NOT NULL,
  ReturnDate DATE
);
