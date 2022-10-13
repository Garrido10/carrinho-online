package main;

import model.Produto;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mercado {

    private static Scanner ler = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    public static void main(String[] args) {

        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    private static void menu() {

        System.out.println("------------------------------------------------------------");
        System.out.println("------------- Bem vindo ao Mercado 10! ------------------");
        System.out.println("***** Selecione a operação que deseja realizar *****");
        System.out.println("------------------------------------------------------------");
        System.out.println("|       Opção 1 - Cadastrar novo produto    |");
        System.out.println("|       Opção 2 - Listar produtos           |");
        System.out.println("|       Opção 3 - Comprar                   |");
        System.out.println("|       Opção 4 - Ver Carrinho              |");
        System.out.println("|       Opção 5 - Sair                      |");

        int opcao = ler.nextInt();

        switch (opcao) {
            case 1:
                cadastrarProdutos();
                break;
            case 2:
                listarProdutos();
                break;
            case 3:
                comprarProdutos();
                break;
            case 4:
                verCarrinho();
                break;
            case 5:
                System.out.println("Volte sempre!!!");
                System.exit(0);
            default:
                System.out.println("Opção Inválida!");
                menu();
                break;
        }

    }

    public static void cadastrarProdutos() {
        System.out.println("Nome do produto: ");
        String nome = ler.next();

        System.out.println("Preço do produto: ");
        Double preco = ler.nextDouble();

        Produto produto = new Produto(nome, preco);
        produtos.add(produto);

        System.out.println(produto.getNome() + " cadastrado com sucesso!");
        menu();

    }

    public static void listarProdutos() {
        if (produtos.size() > 0) {
            System.out.println("\nSegue lista de produtos disponíveis: \n");

            for (Produto novoProduto : produtos) {
                System.out.println(novoProduto + "\n");
            }
        } else {
            System.out.println("Nenhum produto cadastrado!");
        }
        menu();

    }

    private static void comprarProdutos() {
        if (produtos.size() > 0) {


            System.out.println("\n---------- Produtos disponíveis ----------\n");
            System.out.println("Digite o código do produto desejado: \n");
            for (Produto novoProduto : produtos) {
                System.out.println(novoProduto + "\n");
            }                                                                   //Listar Produtos
            int id = Integer.parseInt(ler.next());                              //Convertendo Id do produto de String para Inteiro
            boolean sePresente = false;

            for (Produto novoProduto : produtos) {
                if (novoProduto.getId() == id) {
                    int quantidade = 0;
                    try {
                        quantidade = carrinho.get(novoProduto);
                        // chega se o produto já existe no carrinho, se não, adiciona.
                        carrinho.put(novoProduto, +1);
                    } catch (NullPointerException e) {
                        // se o produto for o primeiro no carrinho.
                        carrinho.put(novoProduto, 1);
                    }
                    System.out.println(novoProduto.getNome() + " adicionado ao carrinho.");
                    sePresente = true;

                    if (sePresente) {
                        System.out.println("Deseja adicionar outro produto no carrinho? ");
                        System.out.println("Digite '1' para sim, ou '0' para finalizar a compra. \n");
                        int opcao = Integer.parseInt(ler.next());

                        if (opcao == 1) {
                            comprarProdutos();
                        } else {
                            finalizarCompra();
                        }
                    }
                } else {
                    System.out.println("Produto não encontrado.");
                    menu();
                }
            }
        } else {
            System.out.println("Não existem produtos cadastrados!");
            menu();
        }
    }

    public static void verCarrinho() {
        System.out.println("--- Produtos no seu carrinho: ---");
        if (carrinho.size() > 0) {
            for (Produto novoProduto : carrinho.keySet()) {
                System.out.println("Produto: " + novoProduto + "\nQuantidade: " + carrinho.get(novoProduto));
            }
        } else {
            System.out.println("Seu carrinho está vazio!");
        }
        menu();
    }

    private static void finalizarCompra() {
        Double totalDaCompra = 0.0;
        System.out.println("Estes são os seus itens comprados: \n");

        for (Produto novoProduto : carrinho.keySet()) {
            int qtd = carrinho.get(novoProduto);
            totalDaCompra += novoProduto.getPreco() * qtd;
            System.out.println(novoProduto);
            System.out.println("Quantidade: " + qtd);
            System.out.println("--------------------");
        }

        System.out.println("O valor total da compra é: " + Utils.doubleParaString(totalDaCompra));
        carrinho.clear();
        System.out.println("Obrigado pela preferência!");
        menu();

    }
}
