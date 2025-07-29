# Library Management System (LMS) - Database Schema

This repository contains the SQL scripts to create the database schema for a simple Library Management System (LMS). The schema includes tables for managing members, books, and book issuance records. It also features a comprehensive logging system using triggers to audit all major database operations.

-----

## 📋 Schema Overview

The database, `lms_db`, consists of three primary tables and three corresponding log tables.

  * **`members`**: Stores information about library members.
  * **`books`**: Manages the library's book collection.
  * **`issue_books`**: Tracks which books are issued to which members, their due dates, and return status.
  * **`members_log`**, **`books_log`**, **`issue_books_log`**: Audit tables that record all creations, updates, and deletions from the primary tables.

-----

## ✨ Key Features

  * **Automated Book ID**: A trigger automatically generates a user-friendly, formatted `book_id` (e.g., `B0001`) for each new book, abstracting the internal auto-incremented `id`.
  * **Comprehensive Auditing**: Triggers are set up on all primary tables (`members`, `books`, `issue_books`) to log every `INSERT`, `UPDATE`, and `DELETE` operation into separate log tables. This provides a complete history of data changes.
  * **Relational Integrity**: Foreign key constraints are used to ensure data consistency between members, books, and their issue records.

-----

## 🗄️ Database Structure

### Primary Tables

#### `members`

Stores details of registered library members.

```sql
CREATE TABLE IF NOT EXISTS members (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile VARCHAR(15) unique NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address varchar(150)  NOT NULL
);
```

#### `books`

Contains the catalog of all books in the library.

```sql
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id VARCHAR(10) UNIQUE,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    status CHAR(1) NOT NULL,        -- 'A' (Active) or 'I' (Inactive)
    availability CHAR(1) NOT NULL   -- 'A' (Available) or 'I' (Issued)
);
```

#### `issue_books`

A transactional table to track issued books.

```sql
CREATE TABLE issue_books (
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    book_id VARCHAR(10) NOT NULL,
    issue_date DATE NOT NULL,
    return_date DATE NOT NULL,              -- Expected due date
    actual_return_date DATE DEFAULT NULL,   -- Real return date
    CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES members(member_id),
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(book_id)
);
```

### Log Tables

  * **`books_log`**: Records all `INSERT`, `UPDATE`, and `DELETE` operations performed on the `books` table.
  * **`members_log`**: Records all `INSERT`, `UPDATE`, and `DELETE` operations performed on the `members` table.
  * **`issue_books_log`**: Records book `ISSUE` (on INSERT), `RETURN` (on UPDATE), and `DELETE` actions from the `issue_books` table.

-----

## ⚙️ Triggers

The schema uses triggers to automate ID generation and auditing.

  * **`trg_generate_book_id`**: Fired `BEFORE INSERT ON books`. Automatically generates a formatted `book_id` (e.g., `B0001`, `B0002`) for new book entries.
  * **`trg_books_*`**: A set of triggers (`trg_books_insert`, `trg_books_update`, `trg_books_delete`) that log actions on the `books` table to the `books_log`.
  * **`trg_members_*`**: A set of triggers that log actions on the `members` table to the `members_log`.
  * **`trg_issue_books_*`**: A set of triggers that log actions on the `issue_books` table to the `issue_books_log`, using custom action types like 'ISSUE' and 'RETURN' for better clarity.

-----

## 🚀 How to Use

1.  Ensure you have a MySQL server running.

2.  Save the entire SQL script provided in this repository into a single file (e.g., `schema.sql`).

3.  Connect to your database server and execute the script. You can do this via a command line or a GUI tool like MySQL Workbench or DBeaver.

    **Command Line Example:**

    ```bash
    mysql -u your_username -p < schema.sql
    ```

4.  This will create the `lms_db` database and all the required tables, triggers, and relationships. You are now ready to use the LMS database.
