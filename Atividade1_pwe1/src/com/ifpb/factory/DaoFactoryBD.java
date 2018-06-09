package com.ifpb.factory;
import com.ifpb.dao.Gerenciar_Cliente;
import com.ifpb.dao.Gerenciar_Pedido;
import com.ifpb.interfaces.ClienteDaoInterface;
import com.ifpb.interfaces.DaoFactoryIF;
import com.ifpb.interfaces.PedidoDaoInterface;
/**
 *
 * @author jonaspeuqno
 */
public class DaoFactoryBD implements DaoFactoryIF {

    @Override
    public ClienteDaoInterface criaClienteDao() {
       return new Gerenciar_Cliente();
    }

    @Override
    public PedidoDaoInterface criaPedidoDao() {
        return new Gerenciar_Pedido();
    }
    
}
