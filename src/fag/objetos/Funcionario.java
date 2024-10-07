package fag.objetos;

public class Funcionario {
    public String nome;
    public int id;
    public String cargo;
    public double totalVendas = 0;
// aqui é para o objeto funcionario receber os atributos mas com a limitação dos cargos, objeto so vai receber atributo se for garçom, cozinheiro ou gerente 
    public Funcionario(String nome, int id, String cargo) {
        if (cargo.equalsIgnoreCase("Garçom") || cargo.equalsIgnoreCase("Cozinheiro") || cargo.equalsIgnoreCase("Gerente")) {
            this.nome = nome;
            this.id = id;
            this.cargo = cargo;
        }else {
        	System.out.println("Cargo inválido, escolha um dos existentes: Garçom, Cozinheiro ou Gerente.");
        }

    }
// so pode registrar venda se o cargo for garçom
    public void registrarVenda(double valor) {
        if (cargo.equalsIgnoreCase("Garçom")) {
            totalVendas += valor;
        }
    }
}
