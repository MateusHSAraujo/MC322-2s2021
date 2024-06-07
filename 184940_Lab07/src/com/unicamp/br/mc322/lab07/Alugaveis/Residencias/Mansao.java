package com.unicamp.br.mc322.lab07.Alugaveis.Residencias;
import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

public class Mansao extends Residencia{
	//Classe para mansões alugáveis. Ela herda de Residência o atributo nome, bem como os atributos e métodos padrões de podrutos de Alugáveis.
	
	//Pelo enunciado, toda casa tem as seguintes informações extras:
	private double metrosQ;//metros quadrados
	
	public Mansao(String id, double valor, Point endereco, String nome,double metrosQ){
		//Construtor normal para mansão alugável, setando seus atributos e atributos herdados
		super(id,valor,endereco,nome);
		this.metrosQ=metrosQ;
	}
	@Override
	public double valorReserva(int dias,  int adultos,int menores) {
		//Sobrescrita do método da classe Alugável, definido do mesmo modo que o indicado no enunciado
		double valorFinal=0, valorPorDia;
		double pessoas=adultos+menores;//convertendo o número de pessoas para double
		for(int i=0;i<dias;i++) {
			valorPorDia=valorBase*(Math.pow(((100*pessoas)/metrosQ),i));//Função dada para o cálculo da diária efetiva de mansões. Ela é estranha pois o enunciado indica que ela deveria crescer, mas se metrosQ > 100*pessoas (o que é possível), ela decresce
			valorFinal+=valorPorDia;
		}
	
		return valorFinal;
	}
	
	@Override
	public String toString() {
		//Sobrescrita do método da classe Object. É usado para passar todas as informações de uma mansão
		String txt="";
		txt="\tMansão "+nome+" ("+id+"):\n\t\tEndereço: "+endereco.toString()+"\n\t\tÁrea: "+metrosQ+"\n\t\tPreço base: "+valorBase+"\n";
		return txt;
	}
}
