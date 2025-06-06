INSERT INTO product (name, description, brand, price, category, release_date, product_available, stock_quantity)
VALUES ('EchoBuds Pro',
        ' Wireless noise-canceling earbuds with voice assistant integration and up to 8 hours of playtime.',
        'EchoTech',
        129.99,
        'Electronics',
        '2024-11-15',
        true,
        520),
       ('ArcticShield Winter Jacket',
       'Waterproof, windproof, insulated jacket designed for extreme winter conditions.',
       'NorthPeak Gear',
        179.99,
        'Apparel',
        '2023-10-05',
        false,
        0),
       ('SnapCam 360',
       'Compact 360° action camera with 4K video, live streaming, and waterproof casing.',
       'SnapMotion',
        289.00,
        'Electronics',
        '2023-06-21',
        true,
        75),
       ('AeroMist Humidifier',
       'Ultrasonic cool mist humidifier with essential oil tray and night light mode.',
       'AirLume',
       39.99,
       'Home Appliances',
       '2024-12-02',
       true,
       410),
       ('ArcticShield Everyday Jacket',
       'Everyday jacket designed for extreme conditions.',
       'NorthPeak Gear',
        89.99,
        'Apparel',
        '2023-10-05',
        true,
        10);

INSERT INTO user_access (username, password, role) VALUES ('nurdin_admin', '$2a$12$jZ0nXr9boxDRmdh89HzSZusJ1djPUyaW8jOk0l3LS.JTrokt6QS2C', 'ADMIN'); -- password 'na@123'
INSERT INTO user_access (username, password, role) VALUES ('nurdin_user', '$2a$12$L4wvtg0Syry4Tf3rWwkz8umOFhY1d3OdeMBaWfDdIyjAzuj0hKiva', 'USER'); -- password 'nu@123'