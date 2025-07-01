CREATE TABLE user_roles (
    user_id UUID         NOT NULL,
    role    VARCHAR(255) NOT NULL,

    CONSTRAINT pk_user_roles PRIMARY KEY (user_id, role),
    CONSTRAINT fk_user_roles_on_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);