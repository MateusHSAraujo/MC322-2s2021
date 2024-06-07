package com.unicamp.mc322.lab10.App.Viagem;

import java.util.ArrayList;
import java.util.List;
import com.unicamp.mc322.lab10.App.Veiculo.Veiculo;

public class Viagem {
	//Essa classe representa uma viagem a ser redistrada no aplicativo
	private List<String> passageiros; //Atributo que identifica as pessoas que participar�o da viagem. Na implementa��o optei por identificar as pessoas somentes pela string de seus cpfs pois o programa desejado n�o executaria nenhum m�todo espec�fico dos objetos da classe passageiro. Em uma implementa��o menos abstrata, as viagens muito provavelmente precisariam comunicar diretamente com os passageiros. Nesse caso, implementar uma lista de passageiros seria melhor. Fui aconselhado pelo PAD �ngelo a tomar essa decis�o.
	private String motorista; //Atributo que identifica o motorista respons�vel pela viagem. Na implementa.Na implementa��o optei por identific�-lo somente pela string de sua cnh pois o programa desejado n�o executaria nenhum m�todo espec�fico dos objetos da classe motorista.  Em uma implementa��o menos abstrata, as viagens muito provavelmente precisariam comunicar diretamente com o motorista. Nesse caso, um atributo do tipo Motorista seria melhor. Fui aconselhado pelo PAD �ngelo a tomar essa decis�o.
	private Veiculo carro; //Atributo que identifica o ve�culo da corrida. Dessa vez o atributo foi usado como um Objeto da classe ve�culo para acessar o m�todo que verifica se o ve�culo � ou n�o de luxo.
	private double distancia; //Atributo que identifica a dist�ncia da viagem
	private List<Parada> paradas;//Atributo que identifica todas as paradas a serem realizadas em uma viagem
	//Os atributos a seguir s�o uma s�rie de valores padronizados dado no enunciado, a inten��o de coloc�-los dentro da classe viagem foi facilitar a mudan�a deles no caso da autaliza��o de pre�os
	private double precoBaseComum=3.00;
	private double precoDistanciaComum=2.00;
	private double precoParadaComum=1.50;	
	private double precoBaseLuxo=7.00;
	private double precoDistanciaLuxo=3.50;
	private double precoParadaLuxo=2.70;

	
	public Viagem(String cpfPassageiro, String cnhMotorista, Veiculo carro, double distancia) {
		//Instanciando uma viagem com um �nico passageiro 
		passageiros=new ArrayList<String>();
		paradas=new ArrayList<Parada>();
		passageiros.add(cpfPassageiro);
		motorista=cnhMotorista;
		this.carro=carro;
		this.distancia=distancia;

	}
	
	public Viagem(String[] cpfsPassageiros, String cnhMotorista, Veiculo carro, double distancia) {
		//Instanciando uma viagem com mais de um passageiro
		passageiros=new ArrayList<String>();
		paradas=new ArrayList<Parada>();
		for(String cpfIterado : cpfsPassageiros) {
			passageiros.add(cpfIterado);			
		}
		motorista=cnhMotorista;
		this.carro=carro;
		this.distancia=distancia;

	}
	public void adicionarParada(double distanciaDeParada, String[] pessoasEntrando, String[] pessoasSaindo) {
		//Esse m�todo cria uma parada para v�rias pessoas descerem e v�rias subirem
		paradas.add(new Parada(distanciaDeParada,pessoasEntrando,pessoasSaindo));
	}
	public void adicionarParada(double distanciaDeParada, String pessoaEntrando, String pessoaSaindo) {
		//Esse m�todo cria uma parada para uma pessoa descer e uma subir
		paradas.add(new Parada(distanciaDeParada,pessoaEntrando,pessoaSaindo));
	}
	
	public void adicionarParada(double distanciaDeParada) {
		//Sobrecarga dos m�todos acima para a cria��o de uma parada onde nenhum dos passageiros encerra a viagem
		paradas.add(new Parada(distanciaDeParada));
	}
	
	public double calcularPrecoViagem() {
		//Esse m�todo calcula o pre�o total da viagem se baseando no pedido no enunciado
		double precoCorrida=0;
		if(carro.getLuxo()) {
			precoCorrida=precoBaseLuxo+((distancia/100)*precoDistanciaLuxo)+(paradas.size()*precoParadaLuxo);
		} else {
			precoCorrida=precoBaseComum+((distancia/100)*precoDistanciaComum)+(paradas.size()*precoParadaComum);
		}
		return precoCorrida;
	}
	
	public boolean estaSendoDirigidaPor(String cnh) {
		//Esse m�todo verifica se o motorista da viagem tem a cnh passada como par�metro
		return cnh.equals(motorista);
	}
	
	private boolean passageiroEstaNoCarro(String cpf) {
		//Esse m�todo verifica se um passageiro est� no carro
		boolean retorno=false;
		for(String cpfIteradoString : passageiros) {
			if(cpfIteradoString.equals(cpf)) {
				retorno=true;
			}
		}
		return retorno;
	}
	private boolean passageiroEstaNoCarro(String[] cpfs) {
		//Esse m�todo verifica se uma lista de passageiros est� no carro
		boolean retorno=false;
		for(String cpfArgumento : cpfs) {
			for(String cpfIteradoString : passageiros) {
				if(cpfIteradoString.equals(cpfArgumento)) {
					retorno=true;
				}
			}
			if(!retorno) {
				break;
			}
		}
		return retorno;
	}
	
	private boolean distanciaCompativelDeParada(double distancia) {
		//Esse m�todo verifica se uma certa dist�ncia � v�lida para se fazer uma parada. Uma dist�ncia � v�lida quando ela � positiva e menor que a dist�ncia da corrida.
		boolean retorno=false;
		if(distancia>=0 && distancia<=this.distancia) {
			retorno=true;
		}
		return retorno;
	}
	
	public boolean podeFazerParada(String cpfSaindo,double distancia) {
		//Esse m�todo verifica se o ve�culo pode fazer uma certa parada com certas pessoas entrando a uma dada dist�ncia. Para que a parada seja v�lida, a dist�ncia tem que ser positiva e menor que a dist�ncia da corrida, bem como os passageiros que v�o descer do ve�culo devem estar nele
		boolean retorno=false, passageiroNoCarro=passageiroEstaNoCarro(cpfSaindo), distanciaCompativel=distanciaCompativelDeParada(distancia);
		if ((passageiroNoCarro && distanciaCompativel)||(cpfSaindo==null && distanciaCompativel)) {
			retorno=true;
		}
		return retorno;
	}
	
	public boolean podeFazerParada(String[] cpfsSaindo,double distancia) {
		//Sobrecarga do m�todo acima para que se aceite um vetor de pessaos que sair�o do ve�culo
		boolean retorno=false, passageiroNoCarro=passageiroEstaNoCarro(cpfsSaindo), distanciaCompativel=distanciaCompativelDeParada(distancia);
		if ((passageiroNoCarro && distanciaCompativel)||(cpfsSaindo==null && distanciaCompativel)) {
			retorno=true;
		}
		return retorno;
	}
	
	@Override
	public String toString() {
		String txt="";
		txt="\t\tCnh do motorista respons�vel: "+motorista+"\n\t\tPassageiros no carro:";
		for(String cpf : passageiros) {
			txt+=" "+cpf+";";
		}
		txt+="\n\t\tDist�ncia da viagem: "+distancia+"\n";
		if(paradas.size()>0) {
			txt+="\t\tParadas cadastradas:\n";
			int i=1;
			for(Parada parada : paradas) {
				txt+="\t\t("+i+") "+parada.toString();
				i++;
			}
		}
		txt+="\t\tPre�o total da viagem: "+calcularPrecoViagem()+"\n\n";
		return txt;
	}
	
}
