package negocio;

import java.util.ArrayList;
import java.util.Collections;

public class Mesa {

	ArrayList<Carta> baralho;
	ArrayList<Carta> topo;
	
	public Mesa ()
	{
		init();
	}
	
	public ArrayList<Carta> puxar(int x)
	{
		if (baralho.size() < x)
		{
			ArrayList<Carta> aux = new ArrayList();
			aux = topo;
			topo = new ArrayList<Carta>();
			topo.add(aux.get(aux.size()-1));
			aux.remove(aux.size()-1);
			restaurarCuringas(aux);
			baralho.addAll(aux);
			Collections.shuffle(baralho);
		}
		ArrayList<Carta> p = new ArrayList();
		while (x>0)
		{
			p.add(baralho.get(0));
			baralho.remove(0);
			x--;
		}
		return p;
	}
	
	public void restaurarCuringas(ArrayList<Carta> cartas)
	{
		for (int i = 0; i < cartas.size(); i++)
		{
			if (cartas.get(i) instanceof CartaCuringa)
			{
				cartas.get(i).setCor("curinga");
			}
		}
		
	}
	
	public Carta getTopo()
	{
		return topo.get(topo.size()-1);
	}
	
	public void jogarNoTopo(Carta c)
	{
		topo.add(c);
	}
	
	public void init()
	{
		baralho = new ArrayList<Carta>();
		topo = new ArrayList<Carta>();
		addCartasPorCor("verde");
		addCartasPorCor("azul");
		addCartasPorCor("amarelo");
		addCartasPorCor("vermelho");
		addCartasPretas();
		Collections.shuffle(baralho);
		topo.add(baralho.get(0));
		baralho.remove(0);
	}
	
	public void addCartasPorCor(String cor)
	{
		for (int i=0; i<=9; i++)
			baralho.add(new CartaColorida(cor, Integer.toString(i)));
		baralho.add(new CartaColorida(cor, "+2"));
		baralho.add(new CartaColorida(cor, "inverter"));
		baralho.add(new CartaColorida(cor, "pular"));
	}
	
	public void addCartasPretas()
	{
		baralho.add(new CartaCuringa("curinga", "+4"));
		baralho.add(new CartaCuringa("curinga", ""));
	}
}
