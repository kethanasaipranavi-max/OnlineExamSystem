CREATE DATABASE IF NOT EXISTS online_exam;

USE online_exam;

-- ==========================================================
-- USERS TABLE
-- ==========================================================

CREATE TABLE IF NOT EXISTS users (

    id INT PRIMARY KEY AUTO_INCREMENT,

    username VARCHAR(50) UNIQUE NOT NULL,

    password VARCHAR(100) NOT NULL,

    role VARCHAR(20) NOT NULL
);

-- ==========================================================
-- QUESTIONS TABLE
-- ==========================================================

CREATE TABLE IF NOT EXISTS questions (

    id INT PRIMARY KEY AUTO_INCREMENT,

    subject VARCHAR(100) NOT NULL,

    question_text VARCHAR(500) NOT NULL,

    option_a VARCHAR(255) NOT NULL,

    option_b VARCHAR(255) NOT NULL,

    option_c VARCHAR(255) NOT NULL,

    option_d VARCHAR(255) NOT NULL,

    correct_answer INT NOT NULL
);

-- ==========================================================
-- RESULTS TABLE
-- ==========================================================

CREATE TABLE IF NOT EXISTS results (

    id INT PRIMARY KEY AUTO_INCREMENT,

    username VARCHAR(50),

    subject VARCHAR(100),

    total_questions INT,

    correct_answers INT,

    wrong_answers INT,

    unanswered INT,

    score DOUBLE,

    percentage DOUBLE,

    result VARCHAR(20),

    exam_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================================================
-- DEFAULT USERS
-- ==========================================================

INSERT IGNORE INTO users
(username, password, role)
VALUES
('student', 'student', 'STUDENT');

INSERT IGNORE INTO users
(username, password, role)
VALUES
('admin', 'admin', 'ADMIN');

-- ==========================================================
-- JAVA FUNDAMENTALS
-- ==========================================================

INSERT INTO questions
(subject, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES

(
'Java Fundamentals',
'Which keyword is used to create a class in Java?',
'class',
'struct',
'define',
'object',
0
),

(
'Java Fundamentals',
'Which method is the entry point of a Java program?',
'start()',
'main()',
'run()',
'execute()',
1
),

(
'Java Fundamentals',
'Which keyword is used to create an object?',
'object',
'create',
'new',
'this',
2
),

(
'Java Fundamentals',
'Which data type stores true or false values?',
'int',
'boolean',
'char',
'double',
1
),

(
'Java Fundamentals',
'Which keyword is used for inheritance?',
'implements',
'extends',
'inherits',
'super',
1
),

(
'Java Fundamentals',
'Which collection stores elements in a dynamic array?',
'ArrayList',
'String',
'Scanner',
'Thread',
0
),

(
'Java Fundamentals',
'Which symbol terminates a Java statement?',
':',
'.',
';',
',',
2
),

(
'Java Fundamentals',
'Which keyword prevents a variable from being changed?',
'static',
'final',
'constant',
'private',
1
),

(
'Java Fundamentals',
'Which concept hides implementation details?',
'Inheritance',
'Abstraction',
'Compilation',
'Iteration',
1
),

(
'Java Fundamentals',
'Which package contains ArrayList?',
'java.io',
'java.util',
'java.sql',
'java.net',
1
);

-- ==========================================================
-- MACHINE LEARNING
-- ==========================================================

INSERT INTO questions
(subject, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES

(
'Machine Learning',
'What is Machine Learning?',
'A method where computers learn from data',
'A hardware device',
'A programming language',
'A database',
0
),

(
'Machine Learning',
'Which is an example of supervised learning?',
'Clustering',
'Classification',
'Compression',
'Sorting',
1
),

(
'Machine Learning',
'Which algorithm is commonly used for classification?',
'Linear Regression',
'K-Means',
'Decision Tree',
'PCA',
2
),

(
'Machine Learning',
'What does a training dataset contain?',
'Data used to train a model',
'Only program code',
'Only images',
'Only output values',
0
),

(
'Machine Learning',
'Which technique divides data into groups?',
'Classification',
'Clustering',
'Compilation',
'Sorting',
1
),

(
'Machine Learning',
'What is overfitting?',
'Model performs too well only on training data',
'Model has no data',
'Model has no features',
'Model cannot run',
0
),

(
'Machine Learning',
'Which algorithm is commonly used for clustering?',
'K-Means',
'Linear Regression',
'Decision Tree',
'Naive Bayes',
0
),

(
'Machine Learning',
'What is a feature?',
'An input variable',
'The final answer',
'A database',
'A programming language',
0
),

(
'Machine Learning',
'Which metric is commonly used for classification?',
'Accuracy',
'File size',
'CPU speed',
'Memory address',
0
),

(
'Machine Learning',
'Which learning method uses unlabelled data?',
'Supervised learning',
'Unsupervised learning',
'Reinforcement learning',
'Manual learning',
1
);

-- ==========================================================
-- COMPUTER NETWORKS
-- ==========================================================

INSERT INTO questions
(subject, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES

(
'Computer Networks',
'What does LAN stand for?',
'Local Area Network',
'Large Area Network',
'Long Access Network',
'Local Access Node',
0
),

(
'Computer Networks',
'Which device connects different networks?',
'Switch',
'Router',
'Keyboard',
'Monitor',
1
),

(
'Computer Networks',
'What does IP stand for?',
'Internet Protocol',
'Internal Program',
'Internet Process',
'Interface Protocol',
0
),

(
'Computer Networks',
'Which protocol is used to browse websites?',
'FTP',
'HTTP',
'SMTP',
'POP3',
1
),

(
'Computer Networks',
'Which device connects computers within a LAN?',
'Switch',
'Router',
'Modem',
'Printer',
0
),

(
'Computer Networks',
'Which protocol is used to send email?',
'HTTP',
'FTP',
'SMTP',
'DNS',
2
),

(
'Computer Networks',
'What is the full form of DNS?',
'Domain Name System',
'Data Network Service',
'Digital Name Server',
'Domain Network Service',
0
),

(
'Computer Networks',
'Which OSI layer is responsible for routing?',
'Physical',
'Data Link',
'Network',
'Application',
2
),

(
'Computer Networks',
'Which protocol automatically assigns IP addresses?',
'DNS',
'DHCP',
'HTTP',
'FTP',
1
),

(
'Computer Networks',
'How many layers are in the OSI model?',
'5',
'6',
'7',
'8',
2
);

-- ==========================================================
-- OPERATING SYSTEMS
-- ==========================================================

INSERT INTO questions
(subject, question_text, option_a, option_b, option_c, option_d, correct_answer)
VALUES

(
'Operating Systems',
'What is an Operating System?',
'Software that manages computer resources',
'A programming language',
'A hardware component',
'A database',
0
),

(
'Operating Systems',
'Which is an example of an Operating System?',
'Linux',
'Java',
'HTML',
'MySQL',
0
),

(
'Operating Systems',
'What is a process?',
'A program in execution',
'A hardware device',
'A file extension',
'A network cable',
0
),

(
'Operating Systems',
'Which scheduling algorithm executes processes in arrival order?',
'FCFS',
'DFS',
'DNS',
'HTTP',
0
),

(
'Operating Systems',
'What is deadlock?',
'Processes waiting indefinitely for resources',
'Fast execution',
'Memory allocation',
'File deletion',
0
),

(
'Operating Systems',
'Which part of an OS manages memory?',
'Memory Management',
'File System',
'Compiler',
'Browser',
0
),

(
'Operating Systems',
'What is a thread?',
'A small unit of CPU execution',
'A storage device',
'A network',
'A file',
0
),

(
'Operating Systems',
'Which technique allows programs to use more memory than physical RAM?',
'Virtual Memory',
'Cache',
'Compilation',
'Formatting',
0
),

(
'Operating Systems',
'Which component manages files and directories?',
'File System',
'CPU',
'ALU',
'Compiler',
0
),

(
'Operating Systems',
'Which scheduling algorithm gives processes a time slice?',
'FCFS',
'Round Robin',
'SJF',
'Priority only',
1
);
