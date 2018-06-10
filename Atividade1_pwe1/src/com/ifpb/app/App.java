/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.app;

import com.ifpb.dao.Gerenciar_Cliente;
import com.ifpb.dao.Gerenciar_Pedido;
import com.ifpb.factory.DaoFactoryBD;
import com.ifpb.model.Cliente;
import com.ifpb.model.Pedido;
import com.ifpb.model.ativoEnum;
import static java.lang.System.out;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jonaspeuqno
 */
public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Cliente  c;
        
        int opcao,op;
        
        do{
            System.out.println("1 - GERENCIAR CLIENTES");
            //System.out.println("2 - GERENCIAR PEDIDOS");
            System.out.println("3 - LOGIN");
            System.out.println("0 - SAIR");
            opcao = scan.nextInt();
            switch(opcao){
                case 1: {
                    do{
                        System.out.println("1 - INSERIR NOVO CLIENTE");
                        System.out.println("2 - EDITAR CLIENTE");
                        System.out.println("3 - EXCLUIR CLIENTE");
                        System.out.println("4 - LISTAR CLIENTES");
                        System.out.println("5 - VOLTAR");
                        op = scan.nextInt();
                        switch(op){
                            case 1 : {
                             criaCliente();
                             break;
                            }
                            case 2 :{
                             editarCliente();
                             break;
                            }
                            case 3 :{
                             removeCliente();
                            }
                            case 4 : {
                            //List<Cliente> listar = gCliente.listar();
                            for(Cliente cliente : listarClientes()){
                                        System.out.println(cliente.toString());
                                    }
                            break;
                           }
                        }
                     }while(op!=5);
                     break;
                    }
                case 2: {
                    break;
                    }
                case 3: {
                       c = login();
                       if(c != null){
                            do{
                                System.out.println("1 - INSERIR NOVO PEDIDO");
                                System.out.println("2 - EDITAR PEDIDO");
                                System.out.println("3 - EXCLUIR PEDIDO");
                                System.out.println("4 - LISTAR PEDIDOS");
                                System.out.println("5 - VOLTAR");
                                op = scan.nextInt();
                            switch(op){
                                case 1 :{
                                    insirPedido(c);
                                    break;
                                }
                                case 2 :{
                                    editarPedido();
                                    break;
                                }
                                case 3 :{
                                    removerPedido();
                                }
                                case 4 :{
                                    for(Pedido p : listarPedidos(c.getId())){
                                        System.out.println("Pedido :"+p.toString());
                                    }
                                }
                             }
                            }while(op!=5);
                       }
                       break;
                    }
            }
        }while(opcao!=0);
    }
    
    public static void criaCliente(){
      Scanner s = new Scanner(System.in);
      Cliente cliente = new Cliente(); 
      System.out.println("DIGITE SEU NOME :");
      cliente.setNome(s.next());
      System.out.println("DIGITE O NUMERO DO SEU DOCUMENTO :");
      cliente.setDocumento(s.next());
      System.out.println("DIGITE O SEU SALDO :");
      cliente.setSaldo(s.nextFloat());
      cliente.setStatus(ativoEnum.ATIVO);
      new DaoFactoryBD().criaClienteDao().inserir(cliente);
    }
    
    public static boolean editarCliente(){
      Scanner s = new Scanner(System.in);
      List<Cliente> clientes = listarClientes();
      Cliente cliente = new Cliente();
      System.out.println("DIGITE O SEU DOCUMENTO:");
      String documento = s.next();
      for(int i=0;i<clientes.size();i++){
          if(clientes.get(i).getDocumento().equals(documento)){
            System.out.println("DIGITE SEU NOME :");
            cliente.setNome(s.next());
            System.out.println("DIGITE O NUMERO DO SEU DOCUMENTO :");
            cliente.setDocumento(s.next());
            System.out.println("DIGITE O SEU SALDO :");
            cliente.setSaldo(s.nextFloat());
            cliente.setStatus(ativoEnum.ATIVO);
            new DaoFactoryBD().criaClienteDao().editar(cliente);
            return true;
          }
      }
      System.out.println("CLIENTE INESISTENTE!");
      return false;
    }
    
    public static List<Cliente> listarClientes(){
        List<Cliente> clientes = new DaoFactoryBD().criaClienteDao().listar();
        return clientes;
    }        
    public static void removeCliente(){
        Scanner s = new Scanner(System.in);
        System.out.println("DIGITE O ID DO CLIENTE:");
        int id = s.nextInt();
        
        new DaoFactoryBD().criaClienteDao().excluir(id);
    }   
    public static void insirPedido(Cliente c){
      Scanner s = new Scanner(System.in);
      Pedido pedido = new Pedido();
        System.out.println("DIGITE O VALOR DO PEDIDO :");
        pedido.setValor(s.nextFloat());
        pedido.setCliente(c.getId());
        pedido.setData(LocalDate.now());
        new DaoFactoryBD().criaPedidoDao().inserir(pedido);
    }
    public static void editarPedido(){
        Scanner s = new Scanner(System.in);
        Pedido pedido = new Pedido();
        System.out.println("DIGITE O NOVO VALOR DO PEDIDO :");
        pedido.setValor(s.nextFloat());
        new DaoFactoryBD().criaPedidoDao().editar(pedido);
    }
    public static void removerPedido(){
       Scanner s = new Scanner(System.in);
       System.out.println("DIGITE O VALOR O ID DO PEDIDO :"); 
        int id = s.nextInt();
        new DaoFactoryBD().criaPedidoDao().excluir(id);
    }
    public static List<Pedido> listarPedidos(int idCliente){
      System.out.println(idCliente);
      List<Pedido> pedidosUser = new ArrayList<>();
      List<Pedido> pedidos = new DaoFactoryBD().criaPedidoDao().listar();
      for(int i=0;i<pedidos.size();i++){
          if(pedidos.get(i).getCliente() == idCliente){
              pedidosUser.add(pedidos.get(i));
          }
      }
      return pedidosUser;
    }
    public static Cliente login(){
        Scanner s = new Scanner(System.in);
        System.out.println("DIGITE SEU NOME PARA LOGIN :");
        String nome = s.next();
        
        for(Cliente cliente : listarClientes() ){
            if(cliente.getNome().equals(nome))
                return cliente;
        }
        return null;
    }
}
