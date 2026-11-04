package br.com.devsdo13.chargingstation.model;
import java.time.LocalDateTime;

public abstract class Auditoria {

    private boolean statusAtivo;
    private LocalDateTime dataCriacao;
    private String usuarioCriacao;
    private LocalDateTime dataAtualizacao;
    private String usuarioAtualizacao;

    public Auditoria() {
        LocalDateTime dataAtual = LocalDateTime.now();
        this.statusAtivo = true;
        this.dataCriacao = dataAtual;
        this.usuarioCriacao = "JavaAdmin";
        this.dataAtualizacao = dataAtual;
        this.usuarioAtualizacao = "JavaAdmin";
    }

    public Auditoria setAtivaStatus() {
        this.statusAtivo = true;
        this.dataAtualizacao = LocalDateTime.now();
        this.usuarioAtualizacao = "JavaAdmin";
        return this;
    }

    public Auditoria setInativaStatus() {
        this.statusAtivo = false;
        this.dataAtualizacao = LocalDateTime.now();
        this.usuarioAtualizacao = "JavaAdmin";
        return this;
    }

    public boolean isStatusAtivo() {
        return statusAtivo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getUsuarioCriacao() {
        return usuarioCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public String getUsuarioAtualizacao() {
        return usuarioAtualizacao;
    }
}
