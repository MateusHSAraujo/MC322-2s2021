package com.unicamp.mc322.lab10.App.Viagem;

import java.util.ArrayList;
import java.util.List;
import com.unicamp.mc322.lab10.App.Veiculo.Veiculo;

public class Viagem {
	//Essa classe representa uma viagem a ser redistrada no aplicativo
	private List<String> passageiros; //Atributo que identifica as pessoas que participarão da viagem. Na implementação optei por identificar as pessoas somentes pela string de seus cpfs pois o programa desejado não executaria nenhum método específico dos objetos da classe passageiro. Em uma implementação menos abstrata, as viagens muito provavelmente precisariam comunicar diretamente com os passageiros. Nesse caso, implementar uma lista de passageiros seria melhor. Fui aconselhado pelo PAD Ângelo a tomar essa decisão.
	private String motorista; //Atributo que identifica o motorista responsável pela viagem. Na implementa.Na implementação optei por identificá-lo somente pela string de sua cnh pois o programa desejado não executaria nenhum método específico dos objetos da classe motorista.  Em uma implementação menos abstrata, as viagens muito provavelmente precisariam comunicar diretamente com o motorista. Nesse caso, um atributo do tipo Motorista seria melhor. Fui aconselhado pelo PAD Ângelo a tomar essa decisão.
	private Veiculo carro; //Atributo que identifica o veículo da corrida. Dessa vez o atributo foi usado como um Objeto da classe veículo para acessar o método que verifica se o veículo é ou não de luxo.
	private double distancia; //Atributo que identifica a distância da viagem
	private List<Parada> paradas;//Atributo que identifica todas as paradas a serem realizadas em uma viagem
	//Os atributos a seguir são uma série de valores padronizados dado no enunciado, a intenção de colocá-los dentro da classe viagem foi facilitar a mudança deles no caso da autalização de preços
	private double precoBaseComum=3.00;
	private double precoDistanciaComum=2.00;
	private double precoParadaComum=1.50;	
	private double precoBaseLuxo=7.00;
	private double precoDistanciaLuxo=3.50;
	private double precoParadaLuxo=2.70;

	
	public Viagem(String cpfPassageiro, String cnhMotorista, Veiculo carro, double distancia) {
		//Instanciando uma viagem com um único passageiro 
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
		//Esse método cria uma parada para várias pessoas descerem e várias subirem
		paradas.add(new Parada(distanciaDeParada,pessoasEntrando,pessoasSaindo));
	}
	public void adicionarParada(double distanciaDeParada, String pessoaEntrando, String pessoaSaindo) {
		//Esse método cria uma parada para uma pessoa descer e uma subir
		paradas.add(new Parada(distanciaDeParada,pessoaEntrando,pessoaSaindo));
	}
	
	public void adicionarParada(double distanciaDeParada) {
		//Sobrecarga dos métodos acima para a criação de uma parada onde nenhum dos passageiros encerra a viagem
		paradas.add(new Parada(distanciaDeParada));
	}
	
	public double calcularPrecoViagem() {
		//Esse método calcula o preço total da viagem se baseando no pedido no enunciado
		double precoCorrida=0;
		if(carro.getLuxo()) {
			precoCorrida=precoBaseLuxo+((distancia/100)*precoDistanciaLuxo)+(paradas.size()*precoParadaLuxo);
		} else {
			precoCorrida=precoBaseComum+((distancia/100)*precoDistanciaComum)+(paradas.size()*precoParadaComum);
		}
		return precoCorrida;
	}
	
	public boolean estaSendoDirigidaPor(String cnh) {
		//Esse método verifica se o motorista da viagem tem a cnh passada como parâmetro
		return cnh.equals(motorista);
	}
	
	private boolean passageiroEstaNoCarro(String cpf) {
		//Esse método verifica se um passageiro está no carro
		boolean retorno=false;
		for(String cpfIteradoString : passageiros) {
			if(cpfIteradoString.equals(cpf)) {
				retorno=true;
			}
		}
		return retorno;
	}
	private boolean passageiroEstaNoCarro(String[] cpfs) {
		//Esse método verifica se uma lista de passageiros está no carro
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
		//Esse método verifica se uma certa distância é válida para se fazer uma parada. Uma distância é válida quando ela é positiva e menor que a distância da corrida.
		boolean retorno=false;
		if(distancia>=0 && distancia<=this.distancia) {
			retorno=true;
		}
		return retorno;
	}
	
	public boolean podeFazerParada(String cpfSaindo,double distancia) {
		//Esse método verifica se o veículo pode fazer uma certa parada com certas pessoas entrando a uma dada distância. Para que a parada seja válida, a distância tem que ser positiva e menor que a distância da corrida, bem como os passageiros que vão descer do veículo devem estar nele
		boolean retorno=false, passageiroNoCarro=passageiroEstaNoCarro(cpfSaindo), distanciaCompativel=distanciaCompativelDeParada(distancia);
		if ((passageiroNoCarro && distanciaCompativel)||(cpfSaindo==null && distanciaCompativel)) {
			retorno=true;
		}
		return retorno;
	}
	
	public boolean podeFazerParada(String[] cpfsSaindo,double distancia) {
		//Sobrecarga do método acima para que se aceite um vetor de pessaos que sairão do veículo
		boolean retorno=false, passageiroNoCarro=passageiroEstaNoCarro(cpfsSaindo), distanciaCompativel=distanciaCompativelDeParada(distancia);
		if ((passageiroNoCarro && distanciaCompativel)||(cpfsSaindo==null && distanciaCompativel)) {
			retorno=true;
		}
		return retorno;
	}
	
	@Override
	public String toString() {
		String txt="";
		txt="\t\tCnh do motorista responsável: "+motorista+"\n\t\tPassageiros no carro:";
		for(String cpf : passageiros) {
			txt+=" "+cpf+";";
		}
		txt+="\n\t\tDistância da viagem: "+distancia+"\n";
		if(paradas.size()>0) {
			txt+="\t\tParadas cadastradas:\n";
			int i=1;
			for(Parada parada : paradas) {
				txt+="\t\t("+i+") "+parada.toString();
				i++;
			}
		}
		txt+="\t\tPreço total da viagem: "+calcularPrecoViagem()+"\n\n";
		return txt;
	}
	
}
