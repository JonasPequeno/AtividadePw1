package com.ifpb.interfaces;
/**
 *
 * @author jonaspeuqno
 */
public interface DaoFactoryIF {

    public ClienteDaoInterface criaClienteDao();
    public PedidoDaoInterface criaPedidoDao();
    
}
