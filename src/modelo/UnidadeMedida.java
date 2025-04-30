/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author otoniel.aalves
 */
@Entity
@Table(name = "UnidadeMedida")
public class UnidadeMedida implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUnidade")
    private Integer idUnidade;
        @Column(name = "nomeUnidade")
    private String nomeUnidade;

    public UnidadeMedida() {
    }

    public UnidadeMedida(Integer idUnidade, String nomeUnidade) {
        this.idUnidade = idUnidade;
        this.nomeUnidade = nomeUnidade;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idUnidade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UnidadeMedida other = (UnidadeMedida) obj;
        if (!Objects.equals(this.idUnidade, other.idUnidade)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeUnidade; //To change body of generated methods, choose Tools | Templates.
    }

}
