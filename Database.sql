CREATE DATABASE library_system;
USE library_system;


-- Other Tables
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE author (
    author_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    bio TEXT NOT NULL
);

CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    description TEXT NOT NULL
);

-- Unified Products Table
CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    type ENUM('Book', 'Magazine') NOT NULL,
    title VARCHAR(255) NOT NULL,
    category_id INT NOT NULL,
    author_id INT NOT NULL,
    price INT NOT NULL,
    available_copies INT DEFAULT 1 CHECK (available_copies >= 0),
    isbn VARCHAR(45) DEFAULT NULL, -- Only for books, NULL for magazines
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (author_id) REFERENCES author(author_id)
);

CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    cost INT NOT NULL,
    date DATETIME DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE reviews (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    rating INT NOT NULL CHECK (rating >= 0 AND rating <= 5),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT INTO users (username, password, email, role) VALUES ('ahmed','123','ahmed@gmail.com','Admin');
-- Users Data (5 Admins, 15 Members)
INSERT INTO users (username, password, email, role) VALUES
-- Admin users
('sarah_admin', 'SecurePass123!', 'sarah.admin@library.com', 'Admin'),
('john_admin', 'AdminPass456!', 'john.admin@library.com', 'Admin'),
('emma_admin', 'EmmaAdmin789!', 'emma.admin@library.com', 'Admin'),
('michael_admin', 'MikePass321!', 'michael.admin@library.com', 'Admin'),
('lisa_admin', 'LisaSecure567!', 'lisa.admin@library.com', 'Admin'),
-- Regular members
('james_member', 'JamesPass123!', 'james@email.com', 'Member'),
('alice_reader', 'AliceRead456!', 'alice@email.com', 'Member'),
('robert_user', 'RobertUser789!', 'robert@email.com', 'Member'),
('maria_lib', 'MariaLib321!', 'maria@email.com', 'Member'),
('david_books', 'DavidRead567!', 'david@email.com', 'Member'),
('susan_reader', 'SusanPass890!', 'susan@email.com', 'Member'),
('peter_lib', 'PeterLib234!', 'peter@email.com', 'Member'),
('karen_books', 'KarenRead567!', 'karen@email.com', 'Member'),
('thomas_user', 'ThomasUser890!', 'thomas@email.com', 'Member'),
('laura_member', 'LauraLib123!', 'laura@email.com', 'Member'),
('christopher_r', 'ChrisRead456!', 'chris@email.com', 'Member'),
('patricia_lib', 'PatriciaLib789!', 'patricia@email.com', 'Member'),
('kevin_reader', 'KevinPass321!', 'kevin@email.com', 'Member'),
('nancy_books', 'NancyRead567!', 'nancy@email.com', 'Member'),
('daniel_user', 'DanielUser890!', 'daniel@email.com', 'Member');

-- Authors Data
INSERT INTO author (first_name, last_name, bio) VALUES
('Elena', 'Martinez', 'Award-winning fiction author with 15 years of experience in contemporary literature'),
('Richard', 'Thompson', 'Specialized in historical fiction and biographical works'),
('Sarah', 'Chen', 'Bestselling author of science fiction and fantasy novels'),
('Mohammed', 'Al-Rashid', 'Expert in middle eastern literature and cultural studies'),
('Julia', 'Anderson', 'Renowned mystery and thriller writer with multiple international awards'),
('David', 'Smith', 'Specialist in technical writing and computer science books'),
('Maria', 'Garcia', 'Popular romance and contemporary fiction author'),
('James', 'Wilson', 'Leading authority on business and self-development books'),
('Anna', 'Kowalski', 'Expert in children literature and educational books'),
('Michael', 'Brown', 'Accomplished poet and literary critic');

-- Categories Data
INSERT INTO categories (name, description) VALUES
('Fiction', 'Stories and literature created from imagination, including novels and short stories'),
('Non-Fiction', 'Factual books including biographies, textbooks, and reference materials'),
('Science Fiction', 'Books exploring futuristic concepts, space travel, and advanced technology'),
('Mystery', 'Novels focusing on crime solving and suspense'),
('Romance', 'Stories centered around romantic relationships and love'),
('Technical', 'Books covering technology, programming, and technical subjects'),
('Business', 'Books about business management, economics, and entrepreneurship'),
('Children', 'Literature specifically written for young readers'),
('History', 'Books about historical events, periods, and figures'),
('Self-Help', 'Books focused on personal development and improvement');

-- Products Data
INSERT INTO products (type, title, category_id, author_id, price, available_copies, isbn) VALUES
-- Books
('Book', 'The Digital Age', 6, 6, 45, 5, '978-0-123456-78-1'),
('Book', 'Mystery at Midnight', 4, 5, 35, 3, '978-0-123456-78-2'),
('Book', 'Love in Paris', 5, 7, 30, 4, '978-0-123456-78-3'),
('Book', 'Future Worlds', 3, 3, 40, 2, '978-0-123456-78-4'),
('Book', 'The Business Mind', 7, 8, 50, 6, '978-0-123456-78-5'),
('Book', 'Historical Tales', 9, 2, 45, 3, '978-0-123456-78-6'),
('Book', 'Learn to Code', 6, 6, 55, 4, '978-0-123456-78-7'),
('Book', 'Tales for Kids', 8, 9, 25, 7, '978-0-123456-78-8'),
('Book', 'Poetry Collection', 1, 10, 30, 5, '978-0-123456-78-9'),
('Book', 'Middle Eastern Stories', 1, 4, 35, 4, '978-0-123456-78-0'),
-- Magazines
('Magazine', 'Tech Monthly', 6, 6, 15, 10, NULL),
('Magazine', 'Business Weekly', 7, 8, 12, 15, NULL),
('Magazine', 'Science Fiction Today', 3, 3, 10, 8, NULL),
('Magazine', 'Romance Weekly', 5, 7, 8, 12, NULL),
('Magazine', 'Kids Magazine', 8, 9, 7, 20, NULL),
('Magazine', 'History Now', 9, 2, 14, 10, NULL),
('Magazine', 'Mystery Digest', 4, 5, 9, 15, NULL),
('Magazine', 'Poetry Monthly', 1, 10, 11, 8, NULL),
('Magazine', 'Fiction Review', 1, 1, 13, 12, NULL),
('Magazine', 'Self Development Digest', 10, 8, 10, 10, NULL);


-- Transactions Data (15 Transactions)
-- Ensure available_copies > 0 before performing the transaction
UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 1 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (6, 1, (SELECT price FROM products WHERE product_id = 1));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 2 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (7, 2, (SELECT price FROM products WHERE product_id = 2));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 3 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (8, 3, (SELECT price FROM products WHERE product_id = 3));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 4 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (9, 4, (SELECT price FROM products WHERE product_id = 4));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 5 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (10, 5, (SELECT price FROM products WHERE product_id = 5));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 6 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (11, 6, (SELECT price FROM products WHERE product_id = 6));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 7 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (12, 7, (SELECT price FROM products WHERE product_id = 7));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 8 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (13, 8, (SELECT price FROM products WHERE product_id = 8));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 9 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (14, 9, (SELECT price FROM products WHERE product_id = 9));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 10 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (15, 10, (SELECT price FROM products WHERE product_id = 10));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 11 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (6, 11, (SELECT price FROM products WHERE product_id = 11));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 12 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (7, 12, (SELECT price FROM products WHERE product_id = 12));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 13 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (8, 13, (SELECT price FROM products WHERE product_id = 13));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 14 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (9, 14, (SELECT price FROM products WHERE product_id = 14));

UPDATE products SET available_copies = available_copies - 1 WHERE product_id = 15 AND available_copies > 0;
INSERT INTO transactions (user_id, product_id, cost) 
VALUES (10, 15, (SELECT price FROM products WHERE product_id = 15));



-- Reviews Data
INSERT INTO reviews (user_id, product_id, rating) VALUES
(6, 1, 5),  -- Review for "The Digital Age"
(7, 2, 4),  -- Review for "Mystery at Midnight"
(8, 3, 3),  -- Review for "Love in Paris"
(9, 4, 5),  -- Review for "Future Worlds"
(10, 5, 4), -- Review for "The Business Mind"
(11, 6, 4), -- Review for "Historical Tales"
(12, 7, 5), -- Review for "Learn to Code"
(13, 8, 4), -- Review for "Tales for Kids"
(14, 9, 5), -- Review for "Poetry Collection"
(15, 10, 3), -- Review for "Middle Eastern Stories"
(6, 11, 5), -- Review for "Tech Monthly"
(7, 12, 4), -- Review for "Business Weekly"
(8, 13, 4), -- Review for "Science Fiction Today"
(9, 14, 3), -- Review for "Romance Weekly"
(10, 15, 5), -- Review for "Kids Magazine"
(11, 16, 4), -- Review for "History Now"
(12, 17, 3), -- Review for "Mystery Digest"
(13, 18, 5), -- Review for "Poetry Monthly"
(14, 19, 4), -- Review for "Fiction Review"
(15, 20, 5); -- Review for "Self Development Digest"

-- Basic price statistics for all products
SELECT 
    type,
    AVG(price) as average_price,
    MIN(price) as minimum_price,
    MAX(price) as maximum_price,
    SUM(price) as total_value,
    COUNT(*) as product_count
FROM products
GROUP BY type;

-- Calculate revenue and sales metrics
SELECT 
    SUM(cost) as total_revenue,
    COUNT(*) as total_transactions,
    AVG(cost) as average_transaction,
    SUM(cost)/COUNT(DISTINCT user_id) as average_spend_per_user
FROM transactions;

-- Price comparison between books and magazines
SELECT 
    'Price Difference' as metric,
    (SELECT AVG(price) FROM products WHERE type = 'Book') -
    (SELECT AVG(price) FROM products WHERE type = 'Magazine') as book_vs_magazine_difference;

-- Category performance analysis
SELECT 
    c.name as category_name,
    COUNT(t.transaction_id) as number_of_sales,
    SUM(t.cost) as total_revenue,
    AVG(t.cost) as average_sale,
    COUNT(t.transaction_id) * 100.0 / (SELECT COUNT(*) FROM transactions) as sales_percentage
FROM categories c
JOIN products p ON c.category_id = p.category_id
JOIN transactions t ON p.product_id = t.product_id
GROUP BY c.category_id, c.name;

-- Product availability and sales ratio
SELECT 
    title,
    type,
    available_copies,
    COUNT(t.transaction_id) as times_purchased,
    (available_copies * 100.0 / (available_copies + COUNT(t.transaction_id))) as availability_percentage
FROM products p
LEFT JOIN transactions t ON p.product_id = t.product_id
GROUP BY p.product_id, title, type, available_copies;

-- User purchase statistics
SELECT 
    u.username,
    COUNT(t.transaction_id) as purchase_count,
    SUM(t.cost) as total_spent,
    AVG(t.cost) as average_purchase,
    MAX(t.cost) as highest_purchase,
    MIN(t.cost) as lowest_purchase
FROM users u
JOIN transactions t ON u.user_id = t.user_id
GROUP BY u.user_id, u.username;

-- Rating statistics by product type
SELECT 
    p.type,
    AVG(r.rating) as average_rating,
    COUNT(r.review_id) as review_count,
    MIN(r.rating) as lowest_rating,
    MAX(r.rating) as highest_rating
FROM products p
JOIN reviews r ON p.product_id = r.product_id
GROUP BY p.type;
