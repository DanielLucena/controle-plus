INSERT INTO tb_user (login, senha) 
VALUES ('gerente', '$2a$10$3ZteQJUBZObiydpizGmvt.JPc8gTZrC2OVeZZg455/ZGAg73l6ayu')
ON CONFLICT (login) DO NOTHING; 

INSERT INTO tb_role (id,name) VALUES (1,'GERENTE') ON CONFLICT (name) DO NOTHING;
INSERT INTO tb_role (id,name) VALUES (2,'CAIXA') ON CONFLICT (name) DO NOTHING;
INSERT INTO tb_role (id,name) VALUES (3,'REPOSITOR') ON CONFLICT (name) DO NOTHING;
INSERT INTO tb_role (id,name) VALUES (4,'CLIENTE') ON CONFLICT (name) DO NOTHING;

INSERT INTO tb_user_role (user_id, role_id) 
VALUES (1, 1)
ON CONFLICT (user_id, role_id) DO NOTHING;

