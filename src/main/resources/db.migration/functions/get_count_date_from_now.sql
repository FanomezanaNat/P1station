CREATE OR REPLACE FUNCTION get_count_date_from_now() RETURNS INTEGER AS $$
DECLARE
    interval_days INTEGER;
BEGIN
    SELECT EXTRACT(DAY FROM AGE(now(), MIN(date)))
    INTO interval_days
    FROM operation_product;

    RETURN interval_days;

END;
$$ LANGUAGE plpgsql;
