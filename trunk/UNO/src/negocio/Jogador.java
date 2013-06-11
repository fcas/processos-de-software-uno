package negocio;

import java.util.ArrayList;

public abstract class Jogador {

	ArrayList<Carta> mao;
	
	public Jogador()
	{
		mao = new ArrayList<Carta>();
	}
	
	public void receber(ArrayList<Carta> cartas)
	{
		mao.addAll(cartas);
	}
	
	public void printMao()
	{
		for (int i = 0; i < mao.size(); i++)
		{
			System.out.println("(" + i + ") " + mao.get(i).toString());
		}
	}
	
	public void receber(Carta c)
	{
		mao.add(c);
	}
	
	public ArrayList<Carta> getMao()
	{
		return mao;
	}

	public Carta jogar(Carta topo) {
		return null;
	}

	public boolean getUno() {
		// TODO Auto-generated method stub
		return false;
	}
}
