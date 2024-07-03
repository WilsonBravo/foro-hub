CREATE TABLE usuario_perfil (
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_usuario_perfil_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_usuario_perfil_perfil_id FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);
