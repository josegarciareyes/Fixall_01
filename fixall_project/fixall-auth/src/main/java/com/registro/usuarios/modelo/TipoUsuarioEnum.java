package com.registro.usuarios.modelo;

public enum TipoUsuarioEnum {

CLIENTE(1L),
    TECNICO(2L);

    private final Long id;

    TipoUsuarioEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static TipoUsuarioEnum fromId(Long id) {
        for (TipoUsuarioEnum tipo : values()) {
            if (tipo.id.equals(id)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID de tipo usuario inválido: " + id);
    }

}
