package br.com.devsdo13.chargingstation.model;
import java.time.LocalDateTime;

public class Pagamento extends Auditoria {

    private String formaPagamento;
    private double valorPago;
    private LocalDateTime dataPagamento;
    private boolean pagamentoAutorizado;

    public Pagamento(String formaPagamento, double valorPago) {
        this.formaPagamento = formaPagamento;
        this.valorPago = valorPago;
        this.dataPagamento = LocalDateTime.now();
        this.pagamentoAutorizado = autorizarPagamento();
    }

    private boolean autorizarPagamento() {
        // Simula a autorização: pagamento é autorizado se a forma não for vazia e o valor for positivo
        return this.formaPagamento != null && !this.formaPagamento.isEmpty() && this.valorPago > 0;
    }

    public Pagamento setInativaPagamento() {
        this.setInativaStatus();
        return this;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public boolean isPagamentoAutorizado() {
        return pagamentoAutorizado;
    }
}
