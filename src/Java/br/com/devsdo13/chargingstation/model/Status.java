package br.com.devsdo13.chargingstation.model;
public class Status extends Auditoria {

    private String codigo;
    private String descricao;

    public Status(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Auditoria setInativaStatus() {
        this.setInativaStatus();
        return this;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
