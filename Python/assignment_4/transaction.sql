DELIMITER $$

CREATE PROCEDURE transactions(IN p_user_id INT, IN p_movie_id INT,out msg varchar(255))
BEGIN
    DECLARE curr INT;
    DECLARE maxv INT;
    DECLARE simulate_error BOOL DEFAULT FALSE;

    START TRANSACTION;

    SELECT current_views, max_views INTO curr, maxv
    FROM Movies
    WHERE movie_id = p_movie_id;

    IF curr < maxv THEN
        INSERT INTO WatchHistory (user_id, movie_id, watch_date)
        VALUES (p_user_id, p_movie_id, current_date);

        UPDATE Movies
        SET current_views = current_views + 1
        WHERE movie_id = p_movie_id;
    ELSE
        SET simulate_error = TRUE;
    END IF;

    IF simulate_error THEN
		set msg='Movie has reached the maximum view limit.';
        ROLLBACK;
    ELSE
		 set msg='Movie watched successfully.';
        COMMIT;
    END IF;
END$$

DELIMITER ;
call transactions(2,3,@result)
select @result