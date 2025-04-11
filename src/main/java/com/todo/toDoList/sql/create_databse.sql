DO $$
BEGIN
    IF NOT EXISTS (
        SELECT FROM pg_database WHERE datname = 'todo_service'
    ) THEN
        CREATE DATABASE todo_service;
END IF;
END
$$;


CREATE TABLE IF NOT EXISTS to_do_list (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS point (
                                     id SERIAL PRIMARY KEY,
                                     description VARCHAR(255) NOT NULL,
    list_id INT NOT NULL,
    CONSTRAINT fk_to_do_list FOREIGN KEY (list_id)
    REFERENCES to_do_list (id)
    ON DELETE CASCADE
    );

INSERT INTO to_do_list (name)
SELECT 'Groceries'
    WHERE NOT EXISTS (
    SELECT 1 FROM to_do_list WHERE name = 'Groceries'
);

INSERT INTO to_do_list (name)
SELECT 'Work Tasks'
    WHERE NOT EXISTS (
    SELECT 1 FROM to_do_list WHERE name = 'Work Tasks'
);

INSERT INTO point (description, list_id)
SELECT 'Buy milk', id
FROM to_do_list
WHERE name = 'Groceries'
  AND NOT EXISTS (
    SELECT 1 FROM point WHERE description = 'Buy milk'
);

INSERT INTO point (description, list_id)
SELECT 'Buy bread', id
FROM to_do_list
WHERE name = 'Groceries'
  AND NOT EXISTS (
    SELECT 1 FROM point WHERE description = 'Buy bread'
);

INSERT INTO point (description, list_id)
SELECT 'Complete project report', id
FROM to_do_list
WHERE name = 'Work Tasks'
  AND NOT EXISTS (
    SELECT 1 FROM point WHERE description = 'Complete project report'
);

INSERT INTO point (description, list_id)
SELECT 'Prepare presentation', id
FROM to_do_list
WHERE name = 'Work Tasks'
  AND NOT EXISTS (
    SELECT 1 FROM point WHERE description = 'Prepare presentation'
);
