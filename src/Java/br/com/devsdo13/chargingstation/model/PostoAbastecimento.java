package br.com.devsdo13.chargingstation.model;
import java.util.ArrayList;
import java.util.List;

public class PostoAbastecimento extends Auditoria {

    private String numeroIdentificacaoFiscal;
    private String codigoPosto;
    private String descricaoPosto;
    private Endereco endereco;
    private List<BombaAbastecimento> ListaBombas;

    public PostoAbastecimento(String numeroIdentificacaoFiscal, String codigoPosto, String descricaoPosto) {
        this.numeroIdentificacaoFiscal = numeroIdentificacaoFiscal;
        this.codigoPosto = codigoPosto;
        this.descricaoPosto = descricaoPosto;
        this.ListaBombas = new ArrayList<>();
    }

    public PostoAbastecimento setEnderecoPosto(String codigoPostal, String numeroEndereco, String complementoEndereco,
                                                String tipoLocal, String pais, String estado) {
        this.endereco = new Endereco(codigoPostal, numeroEndereco, complementoEndereco, tipoLocal, pais, estado);
        return this;
    }

    public PostoAbastecimento setAdicionarBomba(BombaAbastecimento bomba) {
        this.ListaBombas.add(bomba);
        return this;
    }

    public PostoAbastecimento setInativaPosto() {
        this.setInativaStatus();
        return this;
    }

    public String getNumeroIdentificacaoFiscal() {
        return numeroIdentificacaoFiscal;
    }

    public String getCodigoPosto() {
        return codigoPosto;
    }

    public String getDescricaoPosto() {
        return descricaoPosto;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<BombaAbastecimento> getListaBombas() {
        return ListaBombas;
    }
}
