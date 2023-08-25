CREATE TABLE IF NOT EXISTS public.item (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(50) NOT NULL,
    price VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.user_account (
    id BIGINT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    authority_level BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.auth_session (
    id BIGINT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    ip VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.order_table (
    id BIGINT PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    item_ordered_id VARCHAR(50) NOT NULL,
    location VARCHAR(255) NOT NULL,
    shipping_details VARCHAR(255) NOT NULL,
);