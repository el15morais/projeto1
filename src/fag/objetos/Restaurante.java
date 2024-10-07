package fag.objetos;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    public List<Funcionario> funcionarios = new ArrayList<>();
    public List<Mesa> mesas = new ArrayList<>();
    public static List<ItemCardapio> cardapio = new ArrayList<>();
    public List<Pedido> pedidos = new ArrayList<>();

    public void cadastrarFuncionario(String nome, int id, String cargo) {
        funcionarios.add(new Funcionario(nome, id, cargo));
    }

    public void cadastrarMesa(int numero, int capacidade) {
        mesas.add(new Mesa(numero, capacidade));
    }

    public void adicionarItemCardapio(String nome, double preco, int codigo) {

        cardapio.add(new ItemCardapio(nome, preco, codigo));
    }
    
    public boolean removerItemCardapio(int codigo) {
        for (ItemCardapio item : cardapio) {
            if (item.codigo == codigo) {
                cardapio.remove(item);
                System.out.println("Item removido do cardápio: " + item.nome);
                return true;
            }else {
            	System.out.println("Codigo inválido!");
            }
        }
        return false;
    }
    
    public void verItens() {
        System.out.println("Itens do Cardápio:");
        for (ItemCardapio item : cardapio) {
        	String status;
        	if (item.disponivel) {
        	    status = "Disponível";
        	} else {
        	    status = "Indisponível";
        	}
            System.out.printf("Nome: %s, Código: %d, Preço: R$%.2f, Status: %s\n", item.nome, item.codigo, item.preco, status);
        }
    }
    public static void mostrarCardapio() {
    	for (ItemCardapio itemCardapio : cardapio) {
			System.out.println(itemCardapio);
		}
    }
    public Pedido criarPedido(Mesa mesa, Funcionario garcom) {
        Pedido pedido = new Pedido(mesa, garcom);
        pedidos.add(pedido);
        return pedido;
    }

    public void relatorioVendas() {
        System.out.println("Relatório de Vendas por Funcionário:");
        for (Funcionario f : funcionarios) {
            if (f.cargo.equalsIgnoreCase("Garçom")) {//o relatorio de vendas sera mostrado apenas se o cargo do funcionario for garçom
                System.out.printf("Funcionário: %s ID: %d - Total Vendas: %.2f\n", f.nome, f.id, f.totalVendas);
            }
        }
    }
}
