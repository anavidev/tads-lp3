package br.edu.ifsp.orderflow;

import br.edu.ifsp.orderflow.domain.Cliente;
import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import br.edu.ifsp.orderflow.infra.InMemoryEstoqueService;
import br.edu.ifsp.orderflow.service.IEstoqueService;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // IEstoqueService = contrato (define comportamento/declara metodos)
        // InMemoryEstoqueService() = implementa o EstoqueService com logica dos metodos seguindo comportamento da interface
        IEstoqueService estoqueService = new InMemoryEstoqueService();

        Produto mouse = new Produto("SKU-1", "Mouse sem fio", new BigDecimal("150.00"));
        Produto teclado = new Produto("SKU-2", "Teclado mecânico", new BigDecimal("350.00"));
        Produto monitor = new Produto("SKU-3", "Monitor", new BigDecimal("550.00"));
        
        estoqueService.adicionarEstoque(mouse,10);
        estoqueService.adicionarEstoque(teclado,6);
        estoqueService.adicionarEstoque(monitor,2);

        System.out.println(mouse);
        System.out.println(teclado);
        System.out.println(monitor);
        System.out.println();

        Cliente c1 = new Cliente("Ana Alves", "anaalves@gmail.com");
        Cliente c2 = new Cliente("Bruno Silva", "brunosilva@gmail.com");

        System.out.println(c1);
        System.out.println(c2);
        System.out.println();

        Pedido pedido1 = new Pedido(c1);
        pedido1.adicionarItem(new ItemPedido(mouse, 2));
        pedido1.adicionarItem(new ItemPedido(teclado, 2));

        boolean reservado = estoqueService.reservar(pedido1);

        if (!reservado){
            System.out.println("Não foi reservado" + "\n");
        } else {
            System.out.println("Reservado com sucesso" + "\n");
        }

        Pedido pedido2 = new Pedido(c2);
        pedido2.adicionarItem(new ItemPedido(teclado, 1));
        pedido2.adicionarItem(new ItemPedido(monitor, 3));



        System.out.println(pedido1);
        System.out.println(pedido2);

    }

}