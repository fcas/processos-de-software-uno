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
		mesa = new Mesa();
		player = new JogHumano();
		maquinas = new ArrayList<Jogador>();
		player.receber(mesa.puxar(7));
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
		while (player.getMao().size() > 0)
		{
			System.out.println("Topo: " + mesa.getTopo().toString());
			if (vez == 3)
			{
				System.out.println("----JOGADOR----");
				jogada = player.jogar(mesa.getTopo());
				if (jogada == null)
				{
					Carta puxada = mesa.puxar(1).get(0);
					if (puxada.getCor().equals(mesa.getTopo().getCor())
							|| puxada.getAcao().equals(mesa.getTopo().getAcao()))
					{
						mesa.jogarNoTopo(puxada);
						System.out.println("vai jogar " + puxada.toString());
						tratarAcao(puxada.getAcao());						
					}
					else
						player.receber(puxada);
				}
				else
				{
					mesa.jogarNoTopo(jogada);
					System.out.println("vai jogar " + jogada.toString());
					tratarAcao(jogada.getAcao());
				}
			}
			else
			{
				System.out.println("maquina " + vez + ": " + maquinas.get(vez).getMao().size() + " cartas na mão");
				//maquinas.get(vez).printMao();
				jogada = maquinas.get(vez).jogar(mesa.getTopo());
				if (jogada == null)
				{
					Carta puxada = mesa.puxar(1).get(0);
					if (puxada.getCor().equals(mesa.getTopo().getCor())
							|| puxada.getAcao().equals(mesa.getTopo().getAcao()))
					{
						mesa.jogarNoTopo(puxada);
						System.out.println("vai jogar " + puxada.toString());
						tratarAcao(puxada.getAcao());
					}
					else
					{
						System.out.println("Maquina " + vez + " não jogou");
						maquinas.get(vez).receber(puxada);
					}
				}
				else
				{
					mesa.jogarNoTopo(jogada);
					System.out.println("vai jogar " + jogada.toString());
					tratarAcao(jogada.getAcao());
				}
				//s.next();
			}
			passarVez();
		}
	}
	
	public void tratarAcao(String acao)
	{
		if (acao.equals("inverter"))
			if (sentido == true) sentido = false;
			else sentido = true;
		else if (acao.equals("pular"))
			passarVez();
		else if (acao.equals("+2"))
		{
			passarVez();
			if (vez == 3)
				player.receber(mesa.puxar(2));
			else
				maquinas.get(vez).receber(mesa.puxar(2));
		}
		else if (acao.equals("+4"))
		{
			passarVez();
			if (vez == 3)
				player.receber(mesa.puxar(4));
			else
				maquinas.get(vez).receber(mesa.puxar(4));
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
