CREATE TABLE respuesta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(500) NOT NULL,
    solucion TINYINT(1) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    topico_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_respuesta_autor_id FOREIGN KEY (autor_id) REFERENCES usuario(id),
    CONSTRAINT fk_respuesta_topico_id FOREIGN KEY (topico_id) REFERENCES topico(id)
);
