CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE creditcard (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    cardnumber VARCHAR(200),
    cvv VARCHAR(3),
    expireddate TIMESTAMP,
    totallimit NUMERIC(100, 2),
    actuallimit NUMERIC(100, 2),
    createdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);