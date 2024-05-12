CREATE OR REPLACE FUNCTION get_total_sale_by_fuel(fuel_name fuel) RETURNS FLOAT AS $$
DECLARE
    total FLOAT;
BEGIN
    SELECT COALESCE(SUM(quantity), 0)  total
    FROM operation_product op
             JOIN product p ON op.product_id = p.id
             JOIN product_template pt ON p.product_template_id = pt.id
    WHERE op.type = 'sale' AND pt.name = fuel_name;

    RETURN total;
EXCEPTION
    WHEN OTHERS THEN
        RETURN 0;
END;
$$ LANGUAGE plpgsql;
