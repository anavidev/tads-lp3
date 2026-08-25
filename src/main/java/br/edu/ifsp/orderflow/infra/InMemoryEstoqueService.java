package br.edu.ifsp.orderflow.infra;

import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import br.edu.ifsp.orderflow.service.IEstoqueService;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEstoqueService implements IEstoqueService {

    private final Map<String, Integer> estoque = new HashMap<>();

    @Override
    public void adicionarEstoque(Produto p, int qtd) {
        int qtdAtual = this.estoque.getOrDefault(p.getId(), 0);
        this.estoque.put(p.getId(), qtd + qtdAtual);
    }

    @Override
    public int qtdDisponivel(Produto p) {
        return this.estoque.getOrDefault(p.getId(),0);
    }


    @Override
    public boolean reservar(Pedido pedido) {

        // reservar apenas caso todos os itens do pedido estiverem disponivel no estoque
        for (ItemPedido item : pedido.getItens()){

            int qtdDisponivel = this.qtdDisponivel(item.getProduto());

            if(item.getQuantidade() > qtdDisponivel) {
                return false; // se NAO retornar 'falso' todos os itens possuem estoque
            }

        }

        // remover quantidade garantida em pedido
        for (ItemPedido item : pedido.getItens()){

            Produto produto = item.getProduto();
            String produtoId = produto.getId(); // item.getProduto().getId()
            int qtdAtual = this.estoque.getOrDefault(produtoId, 0);

            // reservar qtd do item no estoque
            this.estoque.put(produtoId, qtdAtual - item.getQuantidade());

        }

        return true;

    }


    @Override
    public void liberar(Pedido pedido) {

        // devolver para o estoque (ex. erro de pagamento)
        for (ItemPedido item : pedido.getItens()){

//            String produto = item.getProduto().getId();
//            int qtdAtual = this.estoque.getOrDefault(produto, 0);
//
//            this.estoque.put(produto, qtdAtual + item.getQuantidade());

            String produto = item.getProduto().getId();
            int qtdAtual = this.estoque.getOrDefault(produto, 0);

            // devolve qtd do item no estoque
            this.adicionarEstoque(item.getProduto(), item.getQuantidade());

        }

    }

}