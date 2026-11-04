package br.com.devsdo13.chargingstation.model;

public class Endereco extends Auditoria {

    private String codigoPostal;
    private String numeroEndereco;
    private String complementoEndereco;
    private String tipoLocal;
    private String pais;
    private String estado;

    public Endereco(String codigoPostal, String numeroEndereco, String complementoEndereco, String tipoLocal,
            String pais, String estado) {
        this.codigoPostal = codigoPostal;
        this.numeroEndereco = numeroEndereco;
        this.complementoEndereco = complementoEndereco;
        this.tipoLocal = tipoLocal;
        this.pais = pais;
        this.estado = estado;
    }

    public Endereco setInativaEndereco() {
        this.setInativaStatus();
        return this;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public String getTipoLocal() {
        return tipoLocal;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

}
