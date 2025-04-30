/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Naty
 */
@Entity
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cep;
    private String nome;
    private String cnpj;
    private String telefone_empresa;
    private String contato;
    private String telefone_contato;
    private boolean excluido;
    private String contato2;
    private String telefone_contato2;
    private String rua;
    private String numero;
    private String cidade;
    private String bairro;

    public Fornecedor() {
    }

    public Fornecedor(int id, String cep, String nome, String cnpj, String telefone_empresa, String contato, String telefone_contato, boolean excluido, String contato2, String telefone_contato2, String rua, String numero, String cidade, String bairro) {
        this.id = id;
        this.cep = cep;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone_empresa = telefone_empresa;
        this.contato = contato;
        this.telefone_contato = telefone_contato;
        this.excluido = excluido;
        this.contato2 = contato2;
        this.telefone_contato2 = telefone_contato2;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone_empresa() {
        return telefone_empresa;
    }

    public void setTelefone_empresa(String telefone_empresa) {
        this.telefone_empresa = telefone_empresa;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTelefone_contato() {
        return telefone_contato;
    }

    public void setTelefone_contato(String telefone_contato) {
        this.telefone_contato = telefone_contato;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public String getContato2() {
        return contato2;
    }

    public void setContato2(String contato2) {
        this.contato2 = contato2;
    }

    public String getTelefone_contato2() {
        return telefone_contato2;
    }

    public void setTelefone_contato2(String telefone_contato2) {
        this.telefone_contato2 = telefone_contato2;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Categorid[ id=" + id + " ]";
    }

}
