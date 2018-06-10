package com.ifpb.dao;

import com.ifpb.factory.Conexao;
import com.ifpb.interfaces.PedidoDaoInterface;
import com.ifpb.model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonaspeuqno
 */
public class Gerenciar_Pedido implements PedidoDaoInterface{

    @Override
    public boolean inserir(Pedido pedido) {
      try {
        Connection con = Conexao.getConnection();
        String sql = "INSERT INTO PEDIDO(DATA, CLIENTE, VALOR)"
                + "VALUES(?, ?, ?)";
        PreparedStatement state = con.prepareStatement(sql);
        state.setObject(1,pedido.getData());
        state.setInt(2, pedido.getCliente());
        state.setDouble(3, pedido.getValor());
        state.execute();
        state.close();
        con.close();
        } catch (SQLException ex) {
           System.out.println("ERRO AO INSERIR O PEDIDO :" + ex.getMessage());
           return  false; 
        } 
      return true;
    }

    @Override
    public boolean excluir(int id) {
        try {
            Connection con = Conexao.getConnection();
            
            String sqlDelete =("DELETE FROM PEDIDO WHERE id = ?") ;
            PreparedStatement state = con.prepareStatement(sqlDelete);
            state.setInt(1,id);
            state.execute();
            state.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO DELETAR O PEDIDO :" + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean editar(Pedido pedido) {
        try {
            Connection con = Conexao.getConnection();
            String updateSql = "UPDATE PEDIDO SET ID = ?";
            PreparedStatement state = con.prepareStatement(updateSql);
            state.setInt(1,pedido.getId());
            state.executeUpdate(updateSql);
            state.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("ERRO AO EDITAR O PEDIDO :" + ex.getMessage());
            return false;
        }
        return true;        
    }
    
    @Override
    public List<Pedido> listar() {
        ArrayList<Pedido> pedidos = new ArrayList<>();
      try {
          Connection con = Conexao.getConnection();
          String sql = "SELECT * FROM PEDIDO";
          Statement state = con.createStatement(
            ResultSet.CONCUR_UPDATABLE,
            ResultSet.HOLD_CURSORS_OVER_COMMIT,
            ResultSet.TYPE_SCROLL_SENSITIVE
            );
          ResultSet result = state.executeQuery(sql);
          while(result.next()){
             Pedido pedido = new Pedido();
             pedido.setId(result.getInt(1));
             pedido.setData(result.getObject(2, LocalDate.class));
             pedido.setValor(result.getDouble(4));
             pedido.setCliente(result.getInt(3));
             pedidos.add(pedido);
           }
        } catch (SQLException ex) {
            System.out.println("ERRO AO BUSCAR OS PEDIDOS :" +ex.getMessage());
            return null;
        } 
        return pedidos;
    }
}
