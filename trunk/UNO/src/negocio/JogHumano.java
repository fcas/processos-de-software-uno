package negocio;

import java.util.Scanner;

public class JogHumano extends Jogador{

	Scanner s;
	
	public JogHumano()
	{
		s = new Scanner(System.in);
	}
	public Carta jogar(Carta topo)
	{
		int j = -1;
		Carta retorno = null;
		do {
			printMao();
			System.out.println("(" + mao.size() + ") Puxar carta\nDigite o número que corresponde à sua jogada: ");
			j = s.nextInt();
			if (j == mao.size())
				retorno = null;
			else if (mao.get(j) instanceof CartaCuringa)
			{
				int cor = -1;
				while (cor < 0 || cor >3)
				{
					System.out.println("Escolha a cor que o curinga irá assumir:\n(0)Azul\n(1)Verde\n(2)Vermelho\n(3)Amarelo");
					cor = s.nextInt();
				}
				if (cor == 0)
					mao.get(j).setCor("Azul");
				else if (cor == 1)
					mao.get(j).setCor("Verde");
				else if (cor == 2)
					mao.get(j).setCor("Vermelho");
				else if (cor == 3)
					mao.get(j).setCor("Amarelo");
				retorno = mao.get(j);
			}
			else if (mao.get(j).getAcao().equals(topo.getAcao()) || mao.get(j).getCor().equals(topo.getCor()))
			{
				retorno = mao.get(j);
				mao.remove(j);
			}
			else
			{
				System.out.println("Jogada inválida! Escolha outra opção!");
				printMao();
				j = -1;
			}
		} while (j > mao.size() || j < 0);
		return retorno;
	}
}
