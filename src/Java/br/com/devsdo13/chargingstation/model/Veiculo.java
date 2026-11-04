package br.com.devsdo13.chargingstation.model;

public class Veiculo extends Auditoria {

    private String marca;
    private String modelo;
    private int anoFabricacao;
    private String tipo;
    private String documento;
    private String codigoIdentificador;

    public Veiculo(String marca, String modelo, int anoFabricacao, String tipo, String documento, String codigoIdentificador) {
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.tipo = tipo;
        this.documento = documento;
        this.codigoIdentificador = codigoIdentificador;
    }

    public Veiculo setInativaVeiculo() {
        this.setInativaStatus();
        return this;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDocumento() {
        return documento;
    }

    public String getCodigoIdentificador() {
        return codigoIdentificador;
    }
}
