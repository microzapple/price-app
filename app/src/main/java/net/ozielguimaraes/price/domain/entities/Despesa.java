package net.ozielguimaraes.price.domain.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private Date vencimento;
    private String vencimentoString;

    public Despesa(){id = 0;
    }

    public long getId() { return id;    }
    public void setId(long id) {this.id = id;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String desc) {this.descricao = desc;}

    public Double getValor() {return valor;}
    public void setValor(Double val) {this.valor = val;}

    public Date getVencimento() {return toDate(vencimentoString);}
    public void setVencimento(String venc) {this.vencimento = toDate(venc);}

    @Override
    public String toString()
    {
        return "Descrição:" + descricao + " | Valor: " + valor + " | Vencimento: " + vencimento;
    }

    private Date toDate(String value) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}