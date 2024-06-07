package com.unicamp.mc322.lab10.App.Viagem;

import java.util.ArrayList;
import java.util.List;

public class Parada {
	//Essa classe identifica uma parada que pode ser feita em uma viagem. Tem uma dist�ncia (no meio da viagem) para que a parada seja executada, uma lista de cpfs de passageiros que sair�o e uma lista de cpfs de passageiros que entrar�o no ve�culo
	private double distanciaDeParada; 
	private List<String> passageirosSaindo;
	private List<String> passageirosEntrando;
	//Outra op��o seria implementar uma lista de objetos da classe Passageiro ao inv�s de seu cpfs, essa op��o seria melhor se o programa pedido fosse menos abstrato e necessitasse que alguma mensagem fosse enviada para o passageiro quando ele entrasse ou sa�sse no ve�culo. Como isso n�o foi estabelecido no enunciado, optei por manter registro s� de seus cpfs, reduzindo a dependencia dessa classe com outra.
	
	Parada(double distancia, String[] subindo, String[] descendo ){
		//Cria��o de uma parada com um vetor de pessoas subindo e descendo. Se um desses vetor � nulo, interpreta-se que n�o haver� passageiros entrnado/saindo
		passageirosSaindo=new ArrayList<String>();
		passageirosEntrando=new ArrayList<String>();
		if(subindo==null && descendo!=null) {
			for(String cpf : descendo) {
				passageirosSaindo.add(cpf);
			}			
		} else if(subindo!=null && descendo==null) {
			for(String cpf : subindo) {
				passageirosEntrando.add(cpf);
			}			
		} else {
			for(String cpf : descendo) {
				passageirosSaindo.add(cpf);
			}
			for(String cpf : subindo) {
				passageirosEntrando.add(cpf);
			}
		}
		distanciaDeParada=distancia;
	}
	
	Parada(double distancia, String subindo, String descendo ){
		//Cria��o de uma parada com uma pessoa subindo e uma descendo. Se um desses argumentos � nulo, interpreta-se que n�o haver� passageiros entrnado/saindo
		passageirosSaindo=new ArrayList<String>();
		passageirosEntrando=new ArrayList<String>();
		
		if(subindo==null && descendo!=null) {
			passageirosSaindo.add(descendo);			
		} else if(subindo!=null && descendo==null) {
			passageirosEntrando.add(subindo);			
		} else{
			passageirosSaindo.add(descendo);
			passageirosEntrando.add(subindo);
		}
		distanciaDeParada=distancia;
	}
	
	Parada(double distancia){
		passageirosSaindo=new ArrayList<String>();
		passageirosEntrando=new ArrayList<String>();
		distanciaDeParada=distancia;
	}
	
	@Override
	public String toString() {
		String txt="";
		txt+="Dist�ncia de parada: "+distanciaDeParada+((passageirosSaindo.size()>0)? "\n\t\t\tPassageiros Saindo:":"");
		for(String cpfSaindo : passageirosSaindo) {
			txt+=" "+cpfSaindo+";";
		}
		txt+=((passageirosEntrando.size()>0)? "\n\t\t\tPassageiros Entrando:":"");
		for(String cpfEntrando : passageirosEntrando) {
			txt+=" "+cpfEntrando+";";
		}
		txt+="\n";
		return txt;
	}
}
