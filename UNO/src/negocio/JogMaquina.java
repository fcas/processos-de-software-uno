package negocio;

import java.util.Random;

public class JogMaquina extends Jogador{

	public Carta jogar(Carta topo)
	{
		Carta retorno = null;
		for (int i = 0; i<mao.size(); i++)
		{
			if (mao.get(i) instanceof CartaCuringa)
			{
				Random r = new Random();
				int cor = r.nextInt()%4;
				if (cor == 0)
					mao.get(i).setCor("Azul");
				else if (cor == 1)
					mao.get(i).setCor("Verde");
				else if (cor == 2)
					mao.get(i).setCor("Vermelho");
				else if (cor == 3)
					mao.get(i).setCor("Amarelo");
			}
			if (mao.get(i).getAcao().equals(topo.getAcao()))
			{
				retorno = mao.get(i);
				mao.remove(i);
				i = mao.size();
			}
			
			else if(mao.get(i).getCor().equals(topo.getCor()))
			{
				retorno = mao.get(i);
				mao.remove(i);
				i = mao.size();
			}
		}
		return retorno;
	}
}
