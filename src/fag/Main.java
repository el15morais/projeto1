package fag;

import java.util.Scanner;

import fag.objetos.Funcionario;
import fag.objetos.ItemCardapio;
import fag.objetos.Mesa;
import fag.objetos.Pedido;
import fag.objetos.Restaurante;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1- Cadastrar funcionário");
            System.out.println("2- Cadastrar mesa");
            System.out.println("3- Gerenciar Itens do cardápio");
            System.out.println("4- Criar pedido");
            System.out.println("5- Fechar conta");
            System.out.println("6- Relatório de vendas");
            System.out.println("7- Sair");
            int opcao = scanner.nextInt();

            switch (opcao) {
            case 1: // Cadastrar o funcionário com nome, id e cargo
                System.out.print("Nome: ");
                String nome = scanner.next();
                System.out.print("ID: ");
                int id = scanner.nextInt();
                System.out.print("Cargo (Garçom, Cozinheiro, Gerente): ");
                String cargo = scanner.next();
                
                // Verifica se o cargo é válido antes de cadastrar
                if (cargo.equalsIgnoreCase("Garçom") || cargo.equalsIgnoreCase("Cozinheiro") || cargo.equalsIgnoreCase("Gerente")) {
                    restaurante.cadastrarFuncionario(nome, id, cargo);
                    System.out.println("Funcionário cadastrado.");
                } else {
                    System.out.println("Cargo inválido, escolha entre Garçom, Cozinheiro ou Gerente.");
                }
                break;

                case 2://cadastra a mesa com o numero e a capacidade de pessoas
                    System.out.print("Número da Mesa: ");
                    int numeroMesa = scanner.nextInt();
                    System.out.print("Capacidade: ");
                    int capacidade = scanner.nextInt();
                    restaurante.cadastrarMesa(numeroMesa, capacidade);
                    break;

                case 3://entra no gerenciamento do cardapio
                    boolean continuarGerenciamento = true;
                    while (continuarGerenciamento) {
                        System.out.println("1- Adicionar itens");
                        System.out.println("2- Remover itens");
                        System.out.println("3- Ver itens");
                        System.out.println("4- Voltar");
                        int escolha = scanner.nextInt();

                        switch (escolha) {
                            case 1://adicionar itens com nome, preço e codigo
                                System.out.print("Nome do Item: ");
                                String nomeItem = scanner.next();
                                System.out.print("Preço: ");
                                double preco = scanner.nextDouble();
                                System.out.print("Código: ");
                                int codigo = scanner.nextInt();
                                restaurante.adicionarItemCardapio(nomeItem, preco, codigo);
                                break;

                            case 2://remove o item desejado
                                System.out.print("Digite o código do item que deseja remover: ");
                                int codigoRemover = scanner.nextInt();
                                restaurante.removerItemCardapio(codigoRemover);
                                break;

                            case 3://mostra a lista de itens e seus status
                                restaurante.verItens();
                                break;

                            case 4://volta pro menu principal
                                continuarGerenciamento = false;
                                break;

                            default:
                                System.out.println("Opção inválida!");
                        }
                    }
                    break;

                case 4://cadastra as mesas com numero da mesa e o id do funcionario que esta cadastrando
                    System.out.print("Número da Mesa: ");
                    numeroMesa = scanner.nextInt();
                    System.out.print("ID do Funcionário: ");
                    id = scanner.nextInt();

                    Mesa mesa = null;//mesa nula para verificar se o numero do usuario bate com o numero de alguma mesa
                    for (Mesa m : restaurante.mesas) {
                        if (m.numero == numeroMesa) {
                            mesa = m;
                            break;
                        }
                    }

                    Funcionario funcionario = null;//funcionario nulo para verificar se o id que o usuario usou bate com o id de algum funcionario
                    for (Funcionario f : restaurante.funcionarios) {
                        if (f.id == id) {
                            funcionario = f;
                            break;
                        }
                    }

                    if (mesa != null && funcionario != null) {//verifica se mesa e funcionario é diferente de nulo, se for ele verifica se o funcionario é garçom para dar andamento ao pedido
                        if (funcionario.cargo.equalsIgnoreCase("Garçom")) {
                            Pedido pedido = restaurante.criarPedido(mesa, funcionario);
                           Restaurante.mostrarCardapio();
                            while (true) {//loop para adicionar itens até o ususario digitar "fechar", quebrando o loop com o break;
                                System.out.print("Nome do Item para adicionar (ou 'fechar' para finalizar): ");
                                String nomeItemPedido = scanner.next();
                                if (nomeItemPedido.equalsIgnoreCase("fechar")) {
                                    break; 
                                }

                                ItemCardapio item = null;//item do cardapio nulo para verificar se o item existe e se esta disponivel
                                for (ItemCardapio i : Restaurante.cardapio) {
                                    if (i.nome.equalsIgnoreCase(nomeItemPedido) && i.disponivel) {
                                        item = i;
                                        break;
                                    }
                                }

                                if (item != null) {//verificar se o item esta diferente de nulo para assim adicionar no pedido
                                    pedido.adicionarItem(item);
                                    System.out.println("Item " + item.nome + " adicionado ao pedido.");
                                } else {
                                    System.out.println("Item não encontrado ou indisponível.");
                                }
                            }
                        } else {
                            System.out.println("Erro: Apenas garçons podem criar pedidos.");
                        }
                    } else {
                        System.out.println("Mesa ou Funcionário não encontrado.");
                    }
                    break;

                case 5://para fechar a conta da mesa selecionada
                    System.out.print("Número da Mesa: ");
                    numeroMesa = scanner.nextInt();
                    mesa = null;
                    for (Mesa m : restaurante.mesas) {
                        if (m.numero == numeroMesa) {
                            mesa = m;
                            break;
                        }
                    }

                    if (mesa != null && mesa.ocupada) {//verificar se a mesa existe ou se esta liberada
                        for (Pedido pedido : restaurante.pedidos) {
                            if (pedido.mesa.numero == mesa.numero) {
                                pedido.fecharConta();
                                break;
                            }
                        }
                    } else {
                        System.out.println("Mesa não encontrada ou já liberada.");
                    }
                    break;

                case 6:// mostra o relatorio de vendas com todos os garçons e seus faturamentos
                    restaurante.relatorioVendas();
                    break;

                case 7:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
