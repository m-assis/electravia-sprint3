package br.com.devsdo13.chargingstation.model;
public class DocumentoIdentificacao extends Auditoria {

    private String codigo;
    private String descricao;
    private String tipoDocumento;
    private String paisOrigem;
    private String estadoOrigem;

    public DocumentoIdentificacao(String codigo, String descricao, String tipoDocumento, String paisOrigem, String estadoOrigem) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipoDocumento = tipoDocumento;
        this.paisOrigem = paisOrigem;
        this.estadoOrigem = estadoOrigem;
    }

    public DocumentoIdentificacao setInativaDocumento() {
        this.setInativaStatus();
        return this;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public String getEstadoOrigem() {
        return estadoOrigem;
    }
}
