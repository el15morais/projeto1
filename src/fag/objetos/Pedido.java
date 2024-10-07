package fag.objetos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    public Mesa mesa; //atributo mesa ta recebendo um objeto mesa
    public Funcionario garcom;//atributo garçom ta recebendo um objeto funcionario, lembrando que pra fazer o pedido tem q ser garçom
    public List<ItemCardapio> itens = new ArrayList<>();
    public double total = 0;//total de vendas

    public Pedido(Mesa mesa, Funcionario garcom) {
        this.mesa = mesa;
        this.garcom = garcom;
        mesa.ocupada = true;//se o parametro mesa ja estiver em uso muda o ocupado para true
    }

    public void adicionarItem(ItemCardapio item) {

        if (item.disponivel) {
            itens.add(item);
            total += item.preco;
            item.disponivel = false;//ao usar o item 1 vez o torna ele indisponivel para uso novamente
        } else {
            System.out.println("Esse item não está disponível.");
        }
    }

    public void fecharConta() {
        garcom.registrarVenda(total);
        mesa.ocupada = false;//ao fechar a conta da mesa, a mesa é liberada para o uso novamente tornando o atributo ocupado como falso
        System.out.printf("Conta fechada! Total: %.2f\n", total);
    }
}
