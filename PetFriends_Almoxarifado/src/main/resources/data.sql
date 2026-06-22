INSERT INTO estoques (produto_id, valor)
SELECT 1, 100
    WHERE NOT EXISTS (
  SELECT 1 FROM estoques WHERE produto_id = 1
);