INSERT INTO tb_user (login, senha) 
VALUES ('gerente', '$2a$10$3ZteQJUBZObiydpizGmvt.JPc8gTZrC2OVeZZg455/ZGAg73l6ayu')
ON CONFLICT (login) DO NOTHING; 

INSERT INTO tb_role (role_name) VALUES ('GERENTE') ON CONFLICT DO NOTHING;
INSERT INTO tb_role (role_name) VALUES ('CAIXA') ON CONFLICT DO NOTHING;
INSERT INTO tb_role (role_name) VALUES ('REPOSITOR') ON CONFLICT DO NOTHING;
INSERT INTO tb_role (role_name) VALUES ('CLIENTE') ON CONFLICT DO NOTHING;

INSERT INTO tb_user_role (user_id, role_id) 
VALUES (1, 1)
ON CONFLICT (user_id, role_id) DO NOTHING;

