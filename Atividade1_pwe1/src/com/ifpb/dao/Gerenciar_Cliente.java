package com.ifpb.dao;

import com.ifpb.factory.Conexao;
import com.ifpb.interfaces.ClienteDaoInterface;
import com.ifpb.model.Cliente;
import com.ifpb.model.ativoEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonaspeuqno
 */
public class Gerenciar_Cliente implements ClienteDaoInterface{
    @Override
    public boolean inserir(Cliente cliente){
      try {
            Connection con = Conexao.getConnection();
            String insertSql;
            insertSql = "INSERT INTO CLIENTE (NOME, DOCUMENTO, SALDO, STATUS) "
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement state = con.prepareStatement(insertSql);
            state.setString(1,cliente.getNome());
            state.setString(2,cliente.getDocumento());
            state.setFloat(3,cliente.getSaldo());
            state.setString(4, cliente.getStatus().toString());
            state.execute();
            state.close();
            con.close();
        } catch (SQLException ex ) {
            System.out.println("ERRO AO INSERIR UM NOVO CLIENTE :" +ex.fillInStackTrace());
            return false;
        }
      return true;     
    }   
    
    @Override
    public boolean excluir(int id) {
        
        String sqlDelete =("DELETE FROM CLIENTE WHERE id = ?") ;
        try {
            Connection con = Conexao.getConnection();
            PreparedStatement state = con.prepareStatement(sqlDelete);
            state.setInt(1,id);
            state.execute();
            state.close();;
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("ERRO AO EXCLUIR O CLIENTE :" + ex.getMessage());
            return false;
        }
        return true;      
        
    }

    @Override
    public boolean editar(Cliente c) {
        try {
            Connection con = Conexao.getConnection();
            String updateSql = "UPDATE CLIENTE SET NOME = ?, DOCUMENTO = ?, SALDO = ?, STATUS = ?"
                    + "WHERE ID = ?";
            PreparedStatement state = con.prepareStatement(updateSql);
            state.setString(1,c.getNome());
            state.setString(2,c.getDocumento());
            state.setFloat(3,c.getSaldo());
            state.setString(4,c.getStatus().toString());
            state.setInt(5, c.getId());
            state.execute();
            state.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO EDITAR O CLIENTE :" + ex.getMessage());
            return false;
        }
        return true;      
    }

    @Override
    public List<Cliente> listar() {
        ArrayList<Cliente> clientes  = new ArrayList<>();
        try {
            Connection con = Conexao.getConnection();
            String sql = "SELECT * FROM CLIENTE";
            
            Statement state = con.createStatement(
                    ResultSet.CONCUR_UPDATABLE,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT,
                    ResultSet.TYPE_SCROLL_SENSITIVE
            );
            
            ResultSet result = state.executeQuery(sql);
            
            while(result.next()){
                Cliente cliente = new Cliente(
                        result.getInt(1),
                        result.getString(3), 
                        result.getString(4),
                        result.getFloat(5),
                        ativoEnum.valueOf(result.getString(6))
                );
                clientes.add(cliente);
               
            }
          result.close();
          state.close();
          con.close(); 
          return clientes; 
        } catch (SQLException ex) {
            System.out.println("ERRO AO BUSCAR OS CLIENTES :" + ex.getMessage());
            return null;
        }
    }
}
