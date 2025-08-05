<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Management System</title>
    <style>
        .welcome-container {
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .welcome-title {
            color: #333;
            margin-bottom: 25px;
            padding-bottom: 10px;
            border-bottom: 2px solid #4C7298;
        }

        .section {
            margin: 25px 0;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 6px;
            border: 1px solid #ddd;
        }

        .section h2 {
            color: #4C7298;
            margin-top: 0;
        }

        .feature-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
            margin: 20px 0;
        }

        .feature-item {
            background-color: white;
            padding: 15px;
            border-radius: 4px;
            border: 1px solid #ddd;
        }

        .feature-item h4 {
            color: #4C7298;
            margin: 0 0 10px 0;
        }

        .quick-links {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 15px;
            margin-top: 20px;
        }

        .quick-links a {
            display: block;
            padding: 12px;
            background-color: #4C7298;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            text-align: center;
            transition: background-color 0.3s;
        }

        .quick-links a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <h1 class="welcome-title">Welcome to Library Management System</h1>

        <div class="section">
            <h2>About the System</h2>
            <p>A comprehensive digital solution designed to streamline library operations and enhance user experience. Manage books, members, and transactions efficiently with our integrated system.</p>
        </div>

        <div class="section">
            <h2>Core Features</h2>
            <div class="feature-grid">
                <div class="feature-item">
                    <h4>Book Management</h4>
                    <p>Add, update, and manage your book inventory with ease</p>
                </div>
                <div class="feature-item">
                    <h4>Member Management</h4>
                    <p>Handle member registrations and profile updates</p>
                </div>
                <div class="feature-item">
                    <h4>Issue & Return</h4>
                    <p>Streamlined book borrowing and return process</p>
                </div>
                <div class="feature-item">
                    <h4>Reports Generation</h4>
                    <p>Comprehensive reporting and analytics tools</p>
                </div>
            </div>
        </div>

    </div>
</body>
</html>
