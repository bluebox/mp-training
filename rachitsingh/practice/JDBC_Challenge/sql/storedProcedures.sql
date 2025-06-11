DELIMITER $$

CREATE PROCEDURE insertOrderItem (
    IN p_orderDate DATETIME,
    IN p_productName VARCHAR(100),
    IN p_quantity INT,
    IN p_price DECIMAL(10,3),
    OUT p_orderID INT
)
BEGIN
    DECLARE fresh_orderID INT;

    SELECT order_id INTO fresh_orderID
    FROM orders WHERE order_date = p_orderDate;

    IF fresh_orderID IS NULL THEN
        INSERT INTO orders(order_date) VALUES (p_orderDate);

        SELECT order_id INTO fresh_orderID
        FROM orders
        WHERE order_date = p_orderDate;
    END IF;

    INSERT INTO order_details (order_id, product_name, quantity, price)
    VALUES (fresh_orderID, p_productName, p_quantity, p_price);

    SET p_orderID = fresh_orderID;

END$$

DELIMITER ;

