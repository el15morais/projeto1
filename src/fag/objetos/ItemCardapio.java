package fag.objetos;

public class ItemCardapio {
    public String nome;
    public int codigo;
    public double preco;
    public boolean disponivel = true;

    public ItemCardapio(String nome, double preco, int codigo) {
        this.nome = nome;
        this.preco = preco;
        this.codigo = codigo;
    }

	@Override
	public String toString() {
		return "Nome: " + nome + ", codigo: " + codigo + ", preco: R$" + preco + ", disponivel: " + (disponivel ? "Sim" : "Não") + "\n";
	}
    
}
