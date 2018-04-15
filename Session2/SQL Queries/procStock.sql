CREATE OR REPLACE FUNCTION checkIntegrity() RETURNS boolean AS $$
DECLARE
  global_cash integer;
  customers_cash integer;
  global_sales integer;
  integrity boolean;

BEGIN

  SELECT INTO global_cash sum(amount) from cash;

  SELECT INTO customers_cash sum(credit)  from customer;

  SELECT INTO global_sales sum(s.qty*p.price)
  FROM public.sales_log s,
  public.product p
  WHERE p.pk_id = s.fk_product_id;

  if (global_cash = global_sales + customers_cash)
  then return true ;
  else return false;
  end if;

END;
$$ LANGUAGE plpgsql;


select checkIntegrity();
