package br.edu.ifsp.orderflow.infra;

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
    public boolean reservar(Pedido p) {

    }


    @Override
    public void liberar(Pedido p) {

    }

}
