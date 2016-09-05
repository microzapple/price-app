package net.ozielguimaraes.price.domain.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Oziel on 01/09/2016.
 */
public class Despesa implements Serializable {
    public static String Tabela = "Despesa";
    public static String Id = "_id";
    public static String Descricao = "Descricao";
    public static String Valor = "Valor";
    public static String Vencimento = "Vencimento";

    private long id;
    private String descricao;
    private Double valor;
    private String vencimento;

    public Despesa(){id = 0;
    }

    public long getId() { return id;    }
    public void setId(long id) {this.id = id;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String desc) {this.descricao = desc;}

    public Double getValor() {return valor;}
    public void setValor(Double val) {this.valor = val;}

    public String getVencimento() {return vencimento;}
    public void setVencimento(String venc) {this.vencimento = venc;}

    @Override
    public String toString()
    {
        return "Descrição:" + descricao + " | Valor: " + valor + " | Vencimento: " + vencimento;
    }
}