-- Table for Loan Entity
CREATE TABLE loan (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    principal DOUBLE NOT NULL,
    annual_interest_rate DOUBLE NOT NULL,
    term_in_months INT NOT NULL,
    balloon_payment DOUBLE NOT NULL
);
