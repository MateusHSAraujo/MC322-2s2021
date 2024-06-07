package com.unicamp.mc322.lab10.App.Viagem;

import java.util.ArrayList;
import java.util.List;

public class Parada {
	//Essa classe identifica uma parada que pode ser feita em uma viagem. Tem uma distância (no meio da viagem) para que a parada seja executada, uma lista de cpfs de passageiros que sairão e uma lista de cpfs de passageiros que entrarão no veículo
	private double distanciaDeParada; 
	private List<String> passageirosSaindo;
	private List<String> passageirosEntrando;
	//Outra opção seria implementar uma lista de objetos da classe Passageiro ao invés de seu cpfs, essa opção seria melhor se o programa pedido fosse menos abstrato e necessitasse que alguma mensagem fosse enviada para o passageiro quando ele entrasse ou saísse no veículo. Como isso não foi estabelecido no enunciado, optei por manter registro só de seus cpfs, reduzindo a dependencia dessa classe com outra.
	
	Parada(double distancia, String[] subindo, String[] descendo ){
		//Criação de uma parada com um vetor de pessoas subindo e descendo. Se um desses vetor é nulo, interpreta-se que não haverá passageiros entrnado/saindo
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
		//Criação de uma parada com uma pessoa subindo e uma descendo. Se um desses argumentos é nulo, interpreta-se que não haverá passageiros entrnado/saindo
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
		txt+="Distância de parada: "+distanciaDeParada+((passageirosSaindo.size()>0)? "\n\t\t\tPassageiros Saindo:":"");
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
