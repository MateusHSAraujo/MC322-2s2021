package com.unicamp.br.mc322.lab07.Alugaveis.Residencias;
import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

public class Mansao extends Residencia{
	//Classe para mans�es alug�veis. Ela herda de Resid�ncia o atributo nome, bem como os atributos e m�todos padr�es de podrutos de Alug�veis.
	
	//Pelo enunciado, toda casa tem as seguintes informa��es extras:
	private double metrosQ;//metros quadrados
	
	public Mansao(String id, double valor, Point endereco, String nome,double metrosQ){
		//Construtor normal para mans�o alug�vel, setando seus atributos e atributos herdados
		super(id,valor,endereco,nome);
		this.metrosQ=metrosQ;
	}
	@Override
	public double valorReserva(int dias,  int adultos,int menores) {
		//Sobrescrita do m�todo da classe Alug�vel, definido do mesmo modo que o indicado no enunciado
		double valorFinal=0, valorPorDia;
		double pessoas=adultos+menores;//convertendo o n�mero de pessoas para double
		for(int i=0;i<dias;i++) {
			valorPorDia=valorBase*(Math.pow(((100*pessoas)/metrosQ),i));//Fun��o dada para o c�lculo da di�ria efetiva de mans�es. Ela � estranha pois o enunciado indica que ela deveria crescer, mas se metrosQ > 100*pessoas (o que � poss�vel), ela decresce
			valorFinal+=valorPorDia;
		}
	
		return valorFinal;
	}
	
	@Override
	public String toString() {
		//Sobrescrita do m�todo da classe Object. � usado para passar todas as informa��es de uma mans�o
		String txt="";
		txt="\tMans�o "+nome+" ("+id+"):\n\t\tEndere�o: "+endereco.toString()+"\n\t\t�rea: "+metrosQ+"\n\t\tPre�o base: "+valorBase+"\n";
		return txt;
	}
}
