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
@Table(name = "Setor")
public class Setor implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSetor")
    private Integer idSetor;
    @Column(name = "nomeSetor")
    private String nomeSetor;
    @Column(name = "tipoSetor")
    private String tipoSetor;

    public Setor() {
    }

    public Setor(Integer idSetor, String nomeSetor, String tipoSetor) {
        this.idSetor = idSetor;
        this.nomeSetor = nomeSetor;
        this.tipoSetor = tipoSetor;
    }

    public Integer getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public String getTipoSetor() {
        return tipoSetor;
    }

    public void setTipoSetor(String tipoSetor) {
        this.tipoSetor = tipoSetor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idSetor);
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
        final Setor other = (Setor) obj;
        if (!Objects.equals(this.idSetor, other.idSetor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeSetor;
    }

}
