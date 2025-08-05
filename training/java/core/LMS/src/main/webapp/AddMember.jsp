<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Member</title>
    <link rel="stylesheet" type="text/css" href="../../application/css/addMember.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        .member-form {
            width: 800px;
            height: 550px;
            margin: 0 auto;
            position: relative;
            background-color: #f9f9f9;
        }

        .form-container {
            width: 400px;
            position: absolute;
            top: 50px;
            left: 200px;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 15px;
        }

        .form-title {
            font-size: 22px;
            font-weight: bold;
            text-align: center;
        }

        .form-group {
            width: 100%;
            display: flex;
            flex-direction: column;
            gap: 3px;
        }

        .form-row {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .form-row label {
            min-width: 80px;
            color: #333;
            font-size: 16px;
        }

        .form-row input,
        .form-row select,
        .form-row textarea {
            width: 220px;
            padding: 6px;
            font-size: 14px;
        }

        .error-label {
            font-size: 10px;
            color: red;
            padding-left: 90px;
        }

        .form-buttons {
            display: flex;
            gap: 20px;
            justify-content: center;
        }

        .book-button, .book-buttons {
            padding: 8px 20px;
            font-size: 14px;
            cursor: pointer;
        }

        .book-button {
            background-color: #4CAF50;
            color: white;
            border: none;
        }

        .book-buttons {
            background-color: #f44336;
            color: white;
            border: none;
        }

        textarea {
            resize: none;
        }

        .message {
            text-align: center;
            font-size: 14px;
            margin-bottom: 10px;
            transition: opacity 0.5s ease;
        }

        .success {
            color: green;
        }

        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="member-form">
        <form action="RegisterMemberController" method="post" class="form-container">
            <div class="form-title">Register Member</div>

            <div id="messageContainer" class="message">
                <% if (request.getAttribute("successMessage") != null) { %>
                    <div class="success"><%= request.getAttribute("successMessage") %></div>
                <% } else if (request.getAttribute("errorMessage") != null) { %>
                    <div class="error"><%= request.getAttribute("errorMessage") %></div>
                <% } %>
            </div>

            <div class="form-group">
                <div class="form-row">
                    <label for="nameField">Name:</label>
                    <input type="text" id="nameField" name="name"
                           value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>"
                           placeholder="Enter full name">
                </div>
                <div class="error-label"><%= request.getAttribute("nameError") != null ? request.getAttribute("nameError") : "" %></div>
            </div>

            <div class="form-group">
                <div class="form-row">
                    <label for="emailField">Email:</label>
                    <input type="email" id="emailField" name="email"
                           value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
                           placeholder="Enter email">
                </div>
                <div class="error-label"><%= request.getAttribute("emailError") != null ? request.getAttribute("emailError") : "" %></div>
            </div>

            <div class="form-group">
                <div class="form-row">
                    <label for="mobileField">Mobile:</label>
                    <input type="text" id="mobileField" name="mobile"
                           value="<%= request.getAttribute("mobile") != null ? request.getAttribute("mobile") : "" %>"
                           placeholder="Enter mobile number">
                </div>
                <div class="error-label"><%= request.getAttribute("mobileError") != null ? request.getAttribute("mobileError") : "" %></div>
            </div>

            <div class="form-group">
                <div class="form-row">
                    <label for="genderCombo">Gender:</label>
                    <select id="genderCombo" name="gender">
                        <option value="" disabled <%= request.getAttribute("gender") == null ? "selected" : "" %>>Select gender</option>
                        <option value="Male" <%= "Male".equals(request.getAttribute("gender")) ? "selected" : "" %>>Male</option>
                        <option value="Female" <%= "Female".equals(request.getAttribute("gender")) ? "selected" : "" %>>Female</option>
                    </select>
                </div>
                <div class="error-label"><%= request.getAttribute("genderError") != null ? request.getAttribute("genderError") : "" %></div>
            </div>

            <div class="form-group">
                <div class="form-row">
                    <label for="addressArea">Address:</label>
                    <textarea id="addressArea" name="address" rows="3" placeholder="Enter address"><%= request.getAttribute("address") != null ? request.getAttribute("address") : "" %></textarea>
                </div>
                <div class="error-label"><%= request.getAttribute("addressError") != null ? request.getAttribute("addressError") : "" %></div>
            </div>

            <div class="form-buttons">
                <button type="submit" class="book-button">Register</button>
                <button type="button" class="book-buttons" onclick="window.location.href='AddMemberOptions.jsp'">Back</button>
            </div>
        </form>
    </div>


    <script>
        window.addEventListener('DOMContentLoaded', () => {
            const message = document.getElementById('messageContainer');
            if (message && message.textContent.trim() !== "") {
                setTimeout(() => {
                    message.style.opacity = '0';
                    setTimeout(() => {
                        message.style.display = 'none';
                    }, 300);
                }, 3000);
            }

            const form = document.querySelector('form');
            form.addEventListener('submit', function (event) {
                let valid = true;

                
                document.querySelectorAll('.error-label').forEach(el => el.textContent = '');

             
                const name = document.getElementById('nameField').value.trim();
                if (name === "") {
                    setError('nameField', 'Name is required');
                    valid = false;
                } else if (!/^[A-Za-z\s]+$/.test(name)) {
                    setError('nameField', 'Name must contain only letters and spaces');
                    valid = false;
                }

              
                const email = document.getElementById('emailField').value.trim();
                const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (email === "") {
                    setError('emailField', 'Email is required');
                    valid = false;
                } else if (!emailRegex.test(email)) {
                    setError('emailField', 'Invalid email format');
                    valid = false;
                }

              
                const mobile = document.getElementById('mobileField').value.trim();
                if (mobile === "") {
                    setError('mobileField', 'Mobile number is required');
                    valid = false;
                } else if (!/^\d{10}$/.test(mobile)) {
                    setError('mobileField', 'Mobile number must be 10 digits');
                    valid = false;
                }

                const gender = document.getElementById('genderCombo').value;
                if (!gender) {
                    setError('genderCombo', 'Gender is required');
                    valid = false;
                }

                const address = document.getElementById('addressArea').value.trim();
                if (address === "") {
                    setError('addressArea', 'Address is required');
                    valid = false;
                }

                if (!valid) {
                    event.preventDefault();
                }
            });

            function setError(fieldId, message) {
                const field = document.getElementById(fieldId);
                const errorLabel = field.closest('.form-group').querySelector('.error-label');
                if (errorLabel) {
                    errorLabel.textContent = message;
                }
            }
        });
    </script>
</
