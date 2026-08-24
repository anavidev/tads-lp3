package br.edu.ifsp.orderflow.service;

import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;

public interface IEstoqueService {

    /**
     * DocString
     *
     * Repoe unidades de um produto no estoque
     *
     * @param p
     * @param qtd
     * @return void
     */

    public void adicionarEstoque(Produto p, int qtd);


    /**
     *
     * Quantidade disponivel para um produto
     *
     * @param p
     * @return int
     */

    public int qtdDisponivel(Produto p);


    /**
     * Tenta reservar o estoque de todos os itens do pedido
     *
     * @param p
     * @return true se conseguiu reservar, false do contrario
     */

    public boolean reservar(Pedido p);



    /**
     * Devolve ao estoque os itens de um pedido (ex.: pagamento recusado)
     * @param p
     * @return
     */

    public void liberar(Pedido p);

}
