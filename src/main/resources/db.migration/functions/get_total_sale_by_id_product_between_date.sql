CREATE OR REPLACE FUNCTION get_total_sale_by_id_product_between_date(id_product VARCHAR,start_date TIMESTAMP ,end_date TIMESTAMP) RETURNS FLOAT AS $$
DECLARE
    total FLOAT;
BEGIN
    SELECT COALESCE(SUM(quantity), 0)  INTO total
    FROM operation_product op
             JOIN product p ON op.product_id = p.id
    WHERE op.type = 'sale'
      AND p.id = id_product
      AND  op.date BETWEEN start_date AND end_date;

    RETURN total;
EXCEPTION
    WHEN OTHERS THEN
        RETURN 0;
END;
$$ LANGUAGE plpgsql;
