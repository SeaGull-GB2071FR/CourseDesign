CREATE TABLE `users`
(
    id       INT(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    NAME     VARCHAR(255) NOT NULL,
    TYPE     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE TABLE order_forms
(
    id                             INT(11) NOT NULL AUTO_INCREMENT,
    department                     VARCHAR(255) NOT NULL,
    course                         VARCHAR(255) NOT NULL,
    textbook_name                  VARCHAR(255) NOT NULL,
    publisher                      VARCHAR(255) NOT NULL,
    quantity                       INT(11) NOT NULL,
    is_approved_by_department_head TINYINT(1) NOT NULL DEFAULT '0',
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;


CREATE TABLE approval_opinions
(
    id                 INT(11) NOT NULL AUTO_INCREMENT,
    order_form_id      INT(11) NOT NULL,
    department_head_id INT(11) NOT NULL,
    is_approved        TINYINT(1) NOT NULL DEFAULT '0',
    opinion            VARCHAR(256),
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;


ALTER TABLE order_forms
    ADD KEY ix_department (department);
-- 索引

ALTER TABLE order_forms
    ADD KEY ix_publisher (publisher);

ALTER TABLE approval_opinions
    ADD KEY ix_order_form_id (order_form_id);

ALTER TABLE approval_opinions
    ADD KEY ix_department_head_id (department_head_id);

ALTER TABLE approval_opinions
    ADD CONSTRAINT fk_order_form_id FOREIGN KEY (order_form_id) REFERENCES order_forms (id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE approval_opinions
    ADD CONSTRAINT fk_department_head_id FOREIGN KEY (department_head_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE;