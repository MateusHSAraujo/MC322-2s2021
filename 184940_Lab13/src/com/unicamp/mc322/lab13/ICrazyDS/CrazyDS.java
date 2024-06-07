package com.unicamp.mc322.lab13.ICrazyDS;
import com.unicamp.mc322.lab13.IOrderStrategy.*;


import java.util.Collections;
import java.util.LinkedList;


import com.unicamp.mc322.lab13.Exceptions.CrazyDSException;
import com.unicamp.mc322.lab13.IOrder.*;

public class CrazyDS implements ICrazyDS{
	//Essa classe identifica a estrutura de dados usada na aplicação, ela implementa a interface ICrazyDS assim como pedido pelo enunciado.
	private IOrderStrategy strategy;//A estrutura guarda uma referência para a estratégia de prioridade que usará em sua organização
	private LinkedList<Node> orderList;//A estrutura se baseia em uma lista encadeada ordenada pela estratégia de prioridade fornecida. Essa lista é compostas por nós que contém um objeto do tipo pedido e um número indicando seus pontos de prioridade
	
	
	public CrazyDS(IOrderStrategy strategy){
		this.strategy=strategy;
		orderList= new LinkedList<Node>();
	}

	private void addShift() {
		//Método usado para adicionar um turno em cada pedido da lista assim que um pedido for removido
		for(Node itrNode : orderList) { //Itera sobre toda a lista aumentando em um o número de turnos de cada pedido
			itrNode.getOrder().addShift();
		}
	}
	
	private void recalculateOrdersPriority() {
		//Método usado para recalcular a prioridade de todos os elementos da lista logo após um elemento ser removido e o número de turnos ser alterado
		for(Node itrNode : orderList) { //Itera sobre a lista de pedidos atualizando seus pontos de prioridade
			itrNode.updatePriority(strategy.calculatePriorityPoints(itrNode.getOrder()));
		}
	}
	
	//A descrição dos métodos que sofreram override abaixo está no código da interface ICrazyDS
	@Override
	public void addElement(IOrder element) {
		Node newNode = new Node(element, strategy.calculatePriorityPoints(element));
		boolean added=false;
		for(int i=0; i<orderList.size() && !added; i++) {//Percorre a lista até que ela acabe, ou até que um pedido com prioridade menor do que aquele que se deseja inserir seja encontrado
			if(orderList.get(i).compareTo(newNode)>0) {
				//Encontrado o pedido de prioridade menor, o novo nó é inserido na posição dele. Assim a ordem de prioridade é mantida como decrescente.
				orderList.add(i, newNode);
				added=true;
			}
		}
		if(!added) {
			//Se a lista acabar sem que um pedido de prioridade menor seja encontrado, adiciona-se o novo nó no fim da lista por que ele é o de menor prioridade da lista.
			orderList.addLast(newNode);
		}
	}

	@Override
	public void printElements() {
		String txt="";
		for(Node itrNode : orderList) {//Percorre toda lista imprimindo o formato em string de cada ordem
			txt+=itrNode.toString()+"\n";
		}
		System.out.println(txt);
	}


	@Override
	public IOrder peekElement() { //Algo que poderia ter sido implementado é adicionar uma exceção nesse método para quando é feita uma tentativa de obter o primeiro elemento sem que haja nenhum elemento na lista, evitando assim que um apontador nulo seja retornado. Eu não adicionei isso para manter a escrita inalterada do uso desse método na Main fornecida
		return orderList.peekFirst().getOrder();
	}


	@Override                                    
	public IOrder getElementAt(int index) throws CrazyDSException { 
		try {
			return orderList.get(index).getOrder();
		}catch (IndexOutOfBoundsException e) {
			throw new CrazyDSException("Index out of the CrazyDS queue bounds!",e);
		}
	}
	@Override
	public void removeElement(IOrder element) throws CrazyDSException{
		boolean removed=false;
		for(int i=0;i<orderList.size() && !removed ;i++) { //Percorre a lista de pedido enquanto o pedido fornecido como parâmetro não foi removido.
			if(orderList.get(i).equals(element)) { //Se esse elemento é encontrado na lista, ele é removido
				orderList.remove(i);
				removed=true;
			}
		}
		if(removed) { //Se o elemento foi removido a estrutura deve aumentar o turno de cada pedido, recalcular a prioridade deles e organizá-los novamente
			addShift();
			recalculateOrdersPriority();
			Collections.sort(orderList);
		} else { //Ele só não será removido se ele não existir na lista, por isso, nesse caso, uma exceção é lançada.
			throw new CrazyDSException("Element not found in the CrazyDS queue!");
		}
		
	}
	
}
