CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE "process-payment" (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    paymentid VARCHAR(200),
    cardnumber VARCHAR(100),
    cvv VARCHAR(3),
    amount NUMERIC(100, 2),
    expireddate TIMESTAMP,
    statuspayment VARCHAR(100),
    createdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);