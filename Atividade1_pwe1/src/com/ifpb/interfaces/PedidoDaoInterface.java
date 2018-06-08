package com.ifpb.interfaces;

import com.ifpb.model.Pedido;
import java.util.List;

/**
 *
 * @author jonaspeuqno
 */
public interface PedidoDaoInterface {
    
    public boolean inserir(Pedido pedido);
    public boolean excluir(int id);
    public boolean editar(Pedido pedido);
    public List<Pedido> listar();
    
}
