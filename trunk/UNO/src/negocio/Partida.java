package negocio;

import java.util.ArrayList;
import java.util.Scanner;

public class Partida {

	Mesa mesa;
	Jogador player;
	ArrayList<Jogador> maquinas;
	boolean sentido;
	int vez;
	
	public Partida(){
		init();
	}
	
	public void init() {
		mesa = new Mesa();
		player = new JogHumano();
		maquinas = new ArrayList<Jogador>();
		//player.receber(mesa.puxar(7));
		player.receber(mesa.puxar(2));
		for (int i = 0; i < 3; i++)
		{
			maquinas.add(new JogMaquina());
			maquinas.get(i).receber(mesa.puxar(7));
		}
	}
	
	public static void main (String args[])
	{
		Partida p = new Partida();
		p.comecarPartida();
	}
	
	public void comecarPartida()
	{
		Scanner s = new Scanner(System.in);
		Carta jogada;
		vez = 3;
		sentido = true;
		int vencedor = 4;
		while (vencedor == 4)
		{
			System.out.println("Topo: " + mesa.getTopo().toString());

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (vez == 3)
			{
				if (player.getMao().size() == 1 && player.getUno() == false)
				{
					System.out.println("Você não falou UNO!!!! Puxou +4");
					player.receber(mesa.puxar(4));
				}
				System.out.println("----JOGADOR----");
				jogada = player.jogar(mesa.getTopo());
				if (jogada == null)
				{
					Carta puxada = mesa.puxar(1).get(0);
					System.out.println("Você puxou um " + puxada.toString());
					if (puxada instanceof CartaCuringa)
					{
						int cor = -1;
						while (cor < 0 || cor >3)
						{
							System.out.println("Escolha a cor que o curinga irá assumir:\n(0)Azul\n(1)Verde\n(2)Vermelho\n(3)Amarelo");
							cor = s.nextInt();
						}
						if (cor == 0)
							puxada.setCor("azul");
						else if (cor == 1)
							puxada.setCor("verde");
						else if (cor == 2)
							puxada.setCor("vermelho");
						else if (cor == 3)
							puxada.setCor("amarelo");
						mesa.jogarNoTopo(puxada);
						tratarAcao(puxada.getAcao());
					}
					else if (puxada.getCor().equals(mesa.getTopo().getCor())
							|| puxada.getAcao().equals(mesa.getTopo().getAcao()))
					{
						mesa.jogarNoTopo(puxada);
						tratarAcao(puxada.getAcao());						
					}
					else
						player.receber(puxada);
				}
				else
				{
					mesa.jogarNoTopo(jogada);
					tratarAcao(jogada.getAcao());
				}
			}
			else
			{
				System.out.println("Maquina " + (vez+1) + " jogando");
				//maquinas.get(vez).printMao();
				jogada = maquinas.get(vez).jogar(mesa.getTopo());
				if (jogada == null)
				{
					Carta puxada = mesa.puxar(1).get(0);
					if (puxada instanceof CartaCuringa)
					{
						int cor = -1;
						while (cor < 0 || cor >3)
						{
							System.out.println("Escolha a cor que o curinga irá assumir:\n(0)Azul\n(1)Verde\n(2)Vermelho\n(3)Amarelo");
							cor = s.nextInt();
						}
						if (cor == 0)
							puxada.setCor("azul");
						else if (cor == 1)
							puxada.setCor("verde");
						else if (cor == 2)
							puxada.setCor("vermelho");
						else if (cor == 3)
							puxada.setCor("amarelo");
						mesa.jogarNoTopo(puxada);
						tratarAcao(puxada.getAcao());
					}
					else if (puxada.getCor().equals(mesa.getTopo().getCor())
							|| puxada.getAcao().equals(mesa.getTopo().getAcao()))
					{
						mesa.jogarNoTopo(puxada);
						tratarAcao(puxada.getAcao());
					}
					else
					{
						System.out.println("Maquina " + (vez+1) + " não jogou");
						maquinas.get(vez).receber(puxada);
					}
				}
				else
				{
					mesa.jogarNoTopo(jogada);
					tratarAcao(jogada.getAcao());
				}

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			passarVez();
			vencedor = getVencedor();
		}
		if (vencedor == 3)
			System.out.println("\n\nVOCÊ GANHOU!!");
		else
			System.out.println("\n\nMAQUINA " + (vencedor+1) + " GANHOU!");
	}
	
	public int getVencedor()
	{
		int retorno = 4;
		if (player.getMao().size() == 0)
		{
			if (player.getUno() == true)
				retorno = 3;
			else
				player.receber(mesa.puxar(4));
		}
		else
		{
				for (int i = 0; i < 3; i++)
			{
				if (maquinas.get(i).getMao().size() == 0)
					retorno = i;
			}
		}
		return retorno;
	}
	public void tratarAcao(String acao)
	{
		if (acao.equals("inverter"))
		{
			System.out.println("Sentido invertido!");
			if (sentido == true) sentido = false;
			else sentido = true;
		}
		else if (acao.equals("pular"))
		{
			passarVez();
			if (vez == 3)
				System.out.println("Sua vez foi pulada!");
			else
				System.out.println("Máquina " + (vez+1) + " teve a vez pulada!");
		}
		else if (acao.equals("+2"))
		{
			passarVez();
			if (vez == 3)
			{
				player.receber(mesa.puxar(2));
				System.out.println("Você puxou 2 cartas!");
			}
			else
			{
				maquinas.get(vez).receber(mesa.puxar(2));
				System.out.println("Máquina " + (vez+1) + " puxou 2 cartas!");
			}
		}
		else if (acao.equals("+4"))
		{
			passarVez();
			if (vez == 3)
			{
				player.receber(mesa.puxar(4));
				System.out.println("Você puxou 4 cartas!");
			}
			else
			{
				maquinas.get(vez).receber(mesa.puxar(4));
				System.out.println("Máquina " + (vez+1) + " puxou 4 cartas!");
			}
		}
	}
	
	public void passarVez()
	{
		if (sentido == true)
			vez = (vez+1)%4;
		else
		{
			vez = vez - 1;
			if (vez == -1)
				vez = 3;
		}
	}
}
