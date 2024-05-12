CREATE OR REPLACE FUNCTION get_total_supply() RETURNS FLOAT AS $$
DECLARE
    total FLOAT;
BEGIN
    SELECT SUM(quantity) INTO total
    FROM operation_product
    WHERE type = 'supply';

    RETURN total;
END;
$$ LANGUAGE plpgsql;
