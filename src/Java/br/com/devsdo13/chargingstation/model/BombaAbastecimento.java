package br.com.devsdo13.chargingstation.model;
import java.time.LocalDate;

public class BombaAbastecimento extends Auditoria {

    private String codigoBomba;
    private String descricaoBomba;
    private double capacidade;
    private LocalDate dataUltimaManutencao;

    public BombaAbastecimento(String codigoBomba, String descricaoBomba, double capacidade, LocalDate dataUltimaManutencao) {
        this.codigoBomba = codigoBomba;
        this.descricaoBomba = descricaoBomba;
        this.capacidade = capacidade;
        this.dataUltimaManutencao = dataUltimaManutencao;
    }

    public BombaAbastecimento setInativaBomba() {
        this.setInativaStatus();
        return this;
    }

    public String getCodigoBomba() {
        return codigoBomba;
    }

    public String getDescricaoBomba() {
        return descricaoBomba;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public LocalDate getDataUltimaManutencao() {
        return dataUltimaManutencao;
    }
}
