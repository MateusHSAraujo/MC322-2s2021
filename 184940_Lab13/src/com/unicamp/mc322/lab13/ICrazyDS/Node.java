package com.unicamp.mc322.lab13.ICrazyDS;

import com.unicamp.mc322.lab13.IOrder.IOrder;

class Node implements Comparable<Node>{
	//Essa classe representa um nó de lista da estrutura encadeada da aplicação. Ela quarda uma ordem e uma pontuação definida pela estratégia de prioridade do sistema.
	//É interessante fazer com que esse nó de lista implemente a interface Comparable, pois isso permite que a lista seja facilmente organizada com a função sort() da biblioteca Collections.
	//Me pareceu mais correto fazer com que o as informações da pontuação de prioridade e os métodos para alterá-la e compará-la ficassem dentro de uma classe inacessível ao cliente, pois assim é impossível que o cliente altere a prioridade das ordens de alguma forma irregular.
	private IOrder order;
	private double score;
	
	Node(IOrder order, double score){
		this.order=order;
		this.score=score;
	}

	@Override
	public int compareTo(Node o) {
		//Implementação do método estabelecido pela interface com o intutito de permitir que a lista seja facilmente organizada
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
	
	IOrder getOrder() {//Getter para a ordem presente no nó
		return order;
	}
	
	void updatePriority(double newScore) {//Método para atualizar a prioridade do pedido
		score=newScore;
	}
	
	boolean equals(IOrder element) { //Método que compara se o nó tem o elemento indicado como parâmetro
		return this.order.getId().equals(element.getId());
	}
	
	@Override
	public String toString() {
		String txt="";
		txt+=order.toString()+"; Priority Score:"+String.format("%.3f", score);	
		return txt;
	}
}
