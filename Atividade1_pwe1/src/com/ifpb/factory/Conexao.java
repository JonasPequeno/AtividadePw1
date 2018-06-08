package com.ifpb.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonaspeuqno
 */
public class Conexao {
    
    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/pw1_atividade";
            String user = "postgres";
            String senha = "";

            try {
                con = DriverManager.getConnection(url,user,senha);
            } catch (SQLException ex) {
                System.out.println("Erro ao conectar :" + ex.getMessage());
                return null;
            }
        }catch (ClassNotFoundException ex) {
            System.out.println("Erro ao Carregar o Driver" + ex.getMessage());
            return null;
        }
        return con;
    }   
}
