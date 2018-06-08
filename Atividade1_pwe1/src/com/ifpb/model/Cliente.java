package com.ifpb.model;

import java.util.Objects;

/**
 *
 * @author jonaspeuqno
 */
public class Cliente {
    
    private int id;
    private String nome;
    private String documento;
    private float saldo;
    private ativoEnum status;
   
    public Cliente(int id, String nome, String documento, float saldo, ativoEnum status) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.saldo =  saldo;
        this.status = status;
    };
    public Cliente(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    
    public ativoEnum getStatus() {
        return status;
    }

    public void setStatus(ativoEnum status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.nome);
        hash = 43 * hash + Objects.hashCode(this.documento);
        hash = 43 * hash + Float.floatToIntBits(this.saldo);
        hash = 43 * hash + Objects.hashCode(this.status);
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
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.saldo) != Float.floatToIntBits(other.saldo)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", documento=" + documento + ", saldo=" + saldo + ", status=" + status + '}';
    }
    
}
