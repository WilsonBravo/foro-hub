CREATE TABLE topico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status VARCHAR(100) NOT NULL,
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topico_autor_id FOREIGN KEY (autor_id) REFERENCES usuario(id),
    CONSTRAINT fk_topico_curso_id FOREIGN KEY (curso_id) REFERENCES curso(id)
);
