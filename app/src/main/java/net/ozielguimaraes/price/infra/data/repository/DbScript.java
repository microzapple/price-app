package net.ozielguimaraes.price.infra.data.repository;

/**
 * Created by Oziel on 01/09/2016.
 */
public class DbScript {
    public static String createTableDespesa() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" CREATE TABLE IF NOT EXISTS Despesa ( ");
        sqlBuilder.append("_id                INTEGER       NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("Descricao         VARCHAR (150), ");
        sqlBuilder.append("Valor           REAL), ");
        sqlBuilder.append("Vencimento      DATE ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }
}
