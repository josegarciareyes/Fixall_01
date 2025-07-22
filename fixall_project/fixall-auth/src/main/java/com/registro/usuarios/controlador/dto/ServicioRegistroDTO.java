package com.registro.usuarios.controlador.dto;



public class ServicioRegistroDTO {
    private Long tipoServicioId;
    private Long especializacionId; // Permitir múltiples especializaciones
    private String descripcion;

    public ServicioRegistroDTO() {
    }

    public ServicioRegistroDTO(Long tipoServicioId, Long especializacionId, String descripcion) {
        this.tipoServicioId = tipoServicioId;
        this.especializacionId = especializacionId;
        this.descripcion = descripcion;
    }

    public Long getTipoServicioId() {
        return tipoServicioId;
    }

    public void setTipoServicioId(Long tipoServicioId) {
        this.tipoServicioId = tipoServicioId;
    }

    public Long getEspecializacionId() {
        return especializacionId;
    }

    public void setEspecializacionId(Long especializacionId) {
        this.especializacionId = especializacionId;
    }
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
