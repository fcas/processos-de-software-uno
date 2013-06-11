package negocio;

import java.util.Scanner;

public class JogHumano extends Jogador{

	Scanner s;
	boolean UNO;
	boolean perdeuUno;
	
	public JogHumano()
	{
		UNO = false;
		perdeuUno = true;
		s = new Scanner(System.in);
	}
	public Carta jogar(Carta topo)
	{
		if (perdeuUno == true)
			UNO = false;
		if (UNO == true)
			perdeuUno = true;
		
		int j = -1;
		Carta retorno = null;
		do {
			printMao();
			System.out.println("(" + mao.size() + ") --Puxar carta--\n(" + (mao.size()+1) + ") --UNO--\nDigite o número que corresponde à sua jogada: ");
			j = s.nextInt();
			if (j < mao.size() && j >= 0) //escolheu uma carta
			{
				if (mao.get(j) instanceof CartaCuringa)
				{
					int cor = -1;
					while (cor < 0 || cor >3)
					{
						System.out.println("Escolha a cor que o curinga irá assumir:\n(0)Azul\n(1)Verde\n(2)Vermelho\n(3)Amarelo");
						cor = s.nextInt();
					}
					if (cor == 0)
						mao.get(j).setCor("azul");
					else if (cor == 1)
						mao.get(j).setCor("verde");
					else if (cor == 2)
						mao.get(j).setCor("vermelho");
					else if (cor == 3)
						mao.get(j).setCor("amarelo");
					retorno = mao.get(j);
					mao.remove(j);
				}
				else if (mao.get(j).getAcao().equals(topo.getAcao()) || mao.get(j).getCor().equals(topo.getCor()))
				{
					retorno = mao.get(j);
					mao.remove(j);
				}
				else
				{
					System.out.println("Jogada inválida! Escolha outra opção!\nTopo: " + topo.toString());
					j = -1;
				}
			}
			else if (j == mao.size()) //nao tem a carta
				retorno = null;
			else if (j == mao.size()+1) //gritou uno
			{
				if (mao.size() == 2)
				{
					System.out.println("Você gritou UNO!!!\n");
					UNO = true;
					perdeuUno = false;
				}
				else
					System.out.println("Você não pode gritar UNO ainda!!!\nTopo: " + topo.toString());
			}
			else
			{
				System.out.println("Jogada inválida! Escolha outra opção!\nTopo: " + topo.toString());
				j = -1;
			}
		} while (j > mao.size() || j < 0);
		return retorno;
	}
	
	public boolean getUno()
	{
		return UNO;
	}
}
