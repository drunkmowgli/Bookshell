--Authors table contents
INSERT INTO authors (author_name) VALUES ('Лев Толстой') ON CONFLICT DO NOTHING;
INSERT INTO authors (author_name) VALUES ('Герберт Уэллс') ON CONFLICT DO NOTHING;