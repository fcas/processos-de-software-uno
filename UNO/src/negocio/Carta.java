package negocio;

public class Carta {

	protected String cor;
	private String acao;
	
	public Carta (String cor, String acao)
	{
		this.cor = cor;
		this.acao = acao;
	}
	
	public String getCor()
	{
		return cor;
	}
	
	public String getAcao()
	{
		return acao;
	}
	
	public String toString()
	{
		return acao + " " + cor;
	}

	public void setCor(String string) {
		// TODO Auto-generated method stub
		
	}
}
