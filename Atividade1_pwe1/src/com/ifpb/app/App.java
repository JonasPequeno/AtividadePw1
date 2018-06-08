/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.app;

import com.ifpb.dao.Gerenciar_Cliente;
import com.ifpb.dao.Gerenciar_Pedido;
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
        //Gerenciar_Cliente gCliente = new Gerenciar_Cliente();
        //ArrayList<Cliente> clientes = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        
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
                            for(Cliente c : listarClientes() ){
                                System.out.println("Cliente :" + c.toString());
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
                       Cliente c = login();
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
                                    for(Pedido p : listarPedidos()){
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
       new Gerenciar_Cliente().inserir(cliente);
    }
    
    public static void editarCliente(){
      Scanner s = new Scanner(System.in);
      Cliente cliente = new Cliente();
      System.out.println("DIGITE SEU NOME :");
      cliente.setNome(s.next());
      System.out.println("DIGITE O NUMERO DO SEU DOCUMENTO :");
      cliente.setDocumento(s.next());
      System.out.println("DIGITE O SEU SALDO :");
      cliente.setSaldo(s.nextFloat());
      cliente.setStatus(ativoEnum.ATIVO);
    }
    
    public static List<Cliente> listarClientes(){
        List<Cliente> clientes = new Gerenciar_Cliente().listar();
        return clientes;
    }        
    public static void removeCliente(){
        Scanner s = new Scanner(System.in);
        System.out.println("DIGITE O ID DO CLIENTE:");
        int id = s.nextInt();
        
        new Gerenciar_Cliente().excluir(id);
    }
   
    public static void insirPedido(Cliente c){
      Scanner s = new Scanner(System.in);
      Pedido pedido = new Pedido();
        System.out.println("DIGITE O VALOR DO PEDIDO :");
        pedido.setValor(s.nextFloat());
        pedido.setCliente(c.getId());
        pedido.setData(LocalDate.now());
        new Gerenciar_Pedido().inserir(pedido);
    }
    public static void editarPedido(){
        Scanner s = new Scanner(System.in);
        Pedido pedido = new Pedido();
        System.out.println("DIGITE O VALOR DO PEDIDO :");
        pedido.setValor(s.nextFloat());
        new Gerenciar_Pedido().editar(pedido);
    }
    public static void removerPedido(){
       Scanner s = new Scanner(System.in);
       System.out.println("DIGITE O VALOR DO PEDIDO :"); 
        int id = s.nextInt();
        new Gerenciar_Pedido().excluir(id);
    }
    public static List<Pedido> listarPedidos(){
      List<Pedido> pedidos = new Gerenciar_Pedido().listar();
      return pedidos;
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
