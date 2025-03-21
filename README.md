# Library Management System

## Overview
The Library Management System is a command-line interface (CLI) application built in Java that provides a way to manage books, magazines, users, transactions, and reviews. This system offers a simple but powerful way to handle library operations through text-based commands.

## Features
- **User Management**: Register, authenticate, and manage different user roles
- **Product Catalog**: Organize books and magazines with detailed information
- **Author Database**: Maintain author profiles and bibliographies
- **Category Organization**: Classify products by customizable categories
- **Transaction Tracking**: Record and monitor all purchase transactions
- **Review System**: Allow users to rate products and provide feedback

## Database Schema
The system uses a MySQL database with the following structure:

### Users
Stores user account information and authentication details.
- `user_id`: Unique identifier for each user
- `username`: Unique login name for the user
- `password`: Securely stored password
- `email`: User's email address
- `role`: User role (e.g., admin, customer)

### Author
Contains information about authors of books and magazines.
- `author_id`: Unique identifier for each author
- `first_name`: Author's first name
- `last_name`: Author's last name
- `bio`: Biographical information about the author

### Categories
Defines product categories for organization.
- `category_id`: Unique identifier for each category
- `name`: Category name
- `description`: Detailed description of the category

### Products
Stores information about all available library items.
- `product_id`: Unique identifier for each product
- `type`: Product type (Book or Magazine)
- `title`: Title of the product
- `category_id`: Reference to the product's category
- `author_id`: Reference to the product's author
- `price`: Purchase price
- `available_copies`: Number of copies currently available
- `isbn`: ISBN number (for books only)

### Transactions
Records all purchase transactions in the system.
- `transaction_id`: Unique identifier for each transaction
- `user_id`: Reference to the user making the purchase
- `product_id`: Reference to the purchased product
- `cost`: Total cost of the transaction
- `date`: Timestamp of when the transaction occurred (I know this one has a logical error in the program)

### Reviews
Stores user reviews and ratings for products.
- `review_id`: Unique identifier for each review
- `user_id`: Reference to the user who wrote the review
- `product_id`: Reference to the product being reviewed
- `rating`: Numerical rating (0-5 stars)

## Installation

### Prerequisites
- Java 11 or higher
- MySQL 8.0 or higher
- JDBC Driver for MySQL

### Setup
1. Clone the repository:
     ```git
     git clone https://github.com/yourusername/library-management-system.git
     cd library-management-system
     ```
2. Set up the database:
     Download MySQL Workbench and then run Database.sq
3. Configure database connection:
     That can be done through the DBconnector.java where it says:
     ```java
     // Edit the user=root and the password=root parts
     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_system?user=root&password=root");
     ```
4. Compile the Java files:
     ```bash
     # Make Sure You Are in The Right Repo
     javac -d bin -cp "lib/" src/**/.java
     ```
5. Run the application:
     ```bash
     # Enjoy the app.
     # If this did not work for you, use Intellij to run the code.
     java -cp bin/* com.library.Main
     ```


## Technologies Used
- **Language**: Java
- **Database**: MySQL
- **Database Connectivity**: JDBC
- **Interface**: Command Line

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Contact
Project Link: https://github.com/ahm4dd/library-management-system
