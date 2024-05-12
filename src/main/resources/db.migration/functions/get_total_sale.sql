CREATE OR REPLACE FUNCTION get_total_sale() RETURNS FLOAT AS $$
DECLARE
    total FLOAT;
BEGIN
    SELECT SUM(quantity) INTO total
    FROM operation_product
    WHERE type = 'sale';

    RETURN total;
END;
$$ LANGUAGE plpgsql;
