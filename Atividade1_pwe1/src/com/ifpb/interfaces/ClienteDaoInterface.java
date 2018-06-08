package com.ifpb.interfaces;

import com.ifpb.model.Cliente;
import java.util.List;
/**
 *
 * @author jonaspeuqno
 */
public interface ClienteDaoInterface {
    
    public boolean inserir(Cliente cliente);
    public boolean excluir(int id);
    public boolean editar(Cliente cliente);
    public List<Cliente> listar();
    
}
