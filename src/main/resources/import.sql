-- "users" tablosuna bir kullanıcı ekler. Kullanıcı adı "admin", şifre bcrypt ile hashlenmiştir.
-- Eğer aynı "id" ile kayıt zaten varsa, işlem yapılmaz ("ON CONFLICT DO NOTHING").
INSERT INTO "users" ("id", "name", "password", "created", "version")
VALUES (0, 'admin', '$2a$10$7b.9iLgXFVh.r1u9HEbMv.EDL3JcJgldsWHUg4etSUh4wCNGuExye', NOW(), 0)
    ON CONFLICT DO NOTHING;

-- "user_roles" tablosuna "admin" rolü ekler. Kullanıcı "id" 0 ile ilişkilendirilmiştir.
-- Eğer aynı kayıt zaten varsa, işlem yapılmaz ("ON CONFLICT DO NOTHING").
INSERT INTO "user_roles" ("id", "role") VALUES (0, 'admin')
    ON CONFLICT DO NOTHING;

-- "user_roles" tablosuna "user" rolü ekler. Kullanıcı "id" 0 ile ilişkilendirilmiştir.
-- Eğer aynı kayıt zaten varsa, işlem yapılmaz ("ON CONFLICT DO NOTHING").
INSERT INTO "user_roles" ("id", "role") VALUES (0, 'user')
    ON CONFLICT DO NOTHING;

-- Hibernate tarafından kullanılan "hibernate_sequence" dizisini sıfırlar ve sonraki değeri 10 olarak ayarlar.
-- Bu, yeni kayıtların "id" değerinin çakışmasını önlemek için yapılır.
ALTER SEQUENCE IF EXISTS hibernate_sequence RESTART WITH 10;
