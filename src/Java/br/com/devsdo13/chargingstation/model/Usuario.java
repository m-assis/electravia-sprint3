package br.com.devsdo13.chargingstation.model;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Auditoria {

    private String nomeUsuario;
    private Cadastro cadastro;
    private Endereco endereco;
    private List<DocumentoIdentificacao> listaDocumentos;
    private List<Veiculo> listaVeiculos;

    public Usuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.listaDocumentos = new ArrayList<>();
        this.listaVeiculos = new ArrayList<>();
    }

    public Usuario setCadastroUsuario(String email, String telefone, String planoContratado) {
        this.cadastro = new Cadastro(email, telefone, planoContratado);
        return this;
    }

    public Usuario setEnderecoUsuario(String codigoPostal, String numeroEndereco, String complementoEndereco,
            String tipoLocal, String pais, String estado) {
        this.endereco = new Endereco(codigoPostal, numeroEndereco, complementoEndereco, tipoLocal, pais, estado);
        return this;
    }

    public Usuario setAdicionarDocumento(DocumentoIdentificacao documento) {
        this.listaDocumentos.add(documento);
        return this;
    }

    public Usuario setAdicionarVeiculo(Veiculo veiculo) {
        this.listaVeiculos.add(veiculo);
        return this;
    }

    public Usuario setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        return this;
    }

    public Usuario setInativaUsuario() {
        this.setInativaStatus();

        // Desativar todas as relações do usuário
        if (this.cadastro != null) {
            this.cadastro.setInativaCadastro();
        }
        if (this.endereco != null) {
            this.endereco.setInativaEndereco();
        }
        for (DocumentoIdentificacao doc : this.listaDocumentos) {
            doc.setInativaDocumento();
        }
        for (Veiculo veiculo : this.listaVeiculos) {
            veiculo.setInativaVeiculo();
        }

        return this;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public Cadastro getCadastro() {
        return cadastro;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<DocumentoIdentificacao> getDocumentos() {
        return listaDocumentos;
    }

    public List<Veiculo> getVeiculos() {
        return listaVeiculos;
    }
}
