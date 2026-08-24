package br.edu.ifsp.orderflow;

import br.edu.ifsp.orderflow.domain.Cliente;
import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Produto p1 = new Produto("SKU-1", "Mouse sem fio", new BigDecimal("150.00"));
        Produto p2 = new Produto("SKU-2", "Teclado mecânico", new BigDecimal("350.00"));
        Produto p3 = new Produto("SKU-3", "Monitor", new BigDecimal("550.00"));

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println();

        Cliente c1 = new Cliente("Ana Alves", "anaalves@gmail.com");
        Cliente c2 = new Cliente("Bruno Silva", "brunosilva@gmail.com");

        System.out.println(c1);
        System.out.println(c2);
        System.out.println();

        Pedido pedido1 = new Pedido(c1);
        pedido1.adicionarItem(new ItemPedido(p1, 2));
        pedido1.adicionarItem(new ItemPedido(p3, 1));

        Pedido pedido2 = new Pedido(c2);
        pedido2.adicionarItem(new ItemPedido(p2, 1));
        pedido2.adicionarItem(new ItemPedido(p3, 3));

        System.out.println(pedido1);
        System.out.println(pedido2);

    }

}