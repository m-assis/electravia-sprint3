package br.com.devsdo13.chargingstation.model;
import java.time.LocalDateTime;

public class Abastecimento extends Auditoria {

    private Usuario usuario;
    private Veiculo veiculo;
    private BombaAbastecimento bomba;
    private Status status;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
    private double quantidadeAbastecida;
    private double valorKw;
    private double custoTotal;
    private Pagamento pagamento;

    public Abastecimento(Usuario usuario, Veiculo veiculo, BombaAbastecimento bomba, Status status, double valorKw) {
        this.usuario = usuario;
        this.veiculo = veiculo;
        this.bomba = bomba;
        this.status = status;
        this.valorKw = valorKw;
        // Inicia o abastecimento com a data atual
        this.dataInicio = LocalDateTime.now();
    }

    public Abastecimento finalizarAbastecimento(double quantidadeAbastecida) {
        this.quantidadeAbastecida = quantidadeAbastecida;
        this.dataTermino = LocalDateTime.now();
        this.custoTotal = this.quantidadeAbastecida * this.valorKw;
        return this;
    }

    public boolean realizarPagamento(String formaPagamento) {
        this.pagamento = new Pagamento(formaPagamento, this.custoTotal);
        return this.pagamento.isPagamentoAutorizado();
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void inativaAbastecimento() {
        this.setInativaStatus();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public BombaAbastecimento getBomba() {
        return bomba;
    }

    public Status getStatus() {
        return status;
    }

    public Abastecimento setStatus(Status status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataTermino() {
        return dataTermino;
    }

    public double getQuantidadeAbastecida() {
        return quantidadeAbastecida;
    }

    public double getValorKw() {
        return valorKw;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public String getFormaPagamento() {
        return pagamento != null ? pagamento.getFormaPagamento() : null;
    }
}
