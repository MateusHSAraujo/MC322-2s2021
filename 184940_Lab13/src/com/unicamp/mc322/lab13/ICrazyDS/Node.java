package com.unicamp.mc322.lab13.ICrazyDS;

import com.unicamp.mc322.lab13.IOrder.IOrder;

class Node implements Comparable<Node>{
	//Essa classe representa um n� de lista da estrutura encadeada da aplica��o. Ela quarda uma ordem e uma pontua��o definida pela estrat�gia de prioridade do sistema.
	//� interessante fazer com que esse n� de lista implemente a interface Comparable, pois isso permite que a lista seja facilmente organizada com a fun��o sort() da biblioteca Collections.
	//Me pareceu mais correto fazer com que o as informa��es da pontua��o de prioridade e os m�todos para alter�-la e compar�-la ficassem dentro de uma classe inacess�vel ao cliente, pois assim � imposs�vel que o cliente altere a prioridade das ordens de alguma forma irregular.
	private IOrder order;
	private double score;
	
	Node(IOrder order, double score){
		this.order=order;
		this.score=score;
	}

	@Override
	public int compareTo(Node o) {
		//Implementa��o do m�todo estabelecido pela interface com o intutito de permitir que a lista seja facilmente organizada
		int answer=0;
		if(this.score==o.score) {
			answer=0; //Retorna zero se a prioridade das duas ordens forem iguais
		}else if(this.score<o.score) {
			answer=1; //Retorna um inteiro negativo se a prioridade da ordem comparada for maior
		} else{
			answer=-1; //Retorna um inteiro positivo se a prioridade da ordem comparada for menor
		}
		return answer;
	}
	
	IOrder getOrder() {//Getter para a ordem presente no n�
		return order;
	}
	
	void updatePriority(double newScore) {//M�todo para atualizar a prioridade do pedido
		score=newScore;
	}
	
	boolean equals(IOrder element) { //M�todo que compara se o n� tem o elemento indicado como par�metro
		return this.order.getId().equals(element.getId());
	}
	
	@Override
	public String toString() {
		String txt="";
		txt+=order.toString()+"; Priority Score:"+String.format("%.3f", score);	
		return txt;
	}
}
