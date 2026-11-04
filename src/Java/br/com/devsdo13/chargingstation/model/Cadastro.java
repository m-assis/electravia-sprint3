package br.com.devsdo13.chargingstation.model;

public class Cadastro extends Auditoria {

    private String email;
    private String telefone;
    private String planoContratado;

    public Cadastro(String email, String telefone, String planoContratado) {
        this.email = email;
        this.telefone = telefone;
        this.planoContratado = planoContratado;
    }

    public Cadastro setInativaCadastro() {
        this.setInativaStatus();
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getPlanoContratado() {
        return planoContratado;
    }
}
