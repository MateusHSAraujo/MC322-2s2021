package com.unicamp.mc322.lab13.ICrazyDS;
import com.unicamp.mc322.lab13.IOrderStrategy.*;


import java.util.Collections;
import java.util.LinkedList;


import com.unicamp.mc322.lab13.Exceptions.CrazyDSException;
import com.unicamp.mc322.lab13.IOrder.*;

public class CrazyDS implements ICrazyDS{
	//Essa classe identifica a estrutura de dados usada na aplica��o, ela implementa a interface ICrazyDS assim como pedido pelo enunciado.
	private IOrderStrategy strategy;//A estrutura guarda uma refer�ncia para a estrat�gia de prioridade que usar� em sua organiza��o
	private LinkedList<Node> orderList;//A estrutura se baseia em uma lista encadeada ordenada pela estrat�gia de prioridade fornecida. Essa lista � compostas por n�s que cont�m um objeto do tipo pedido e um n�mero indicando seus pontos de prioridade
	
	
	public CrazyDS(IOrderStrategy strategy){
		this.strategy=strategy;
		orderList= new LinkedList<Node>();
	}

	private void addShift() {
		//M�todo usado para adicionar um turno em cada pedido da lista assim que um pedido for removido
		for(Node itrNode : orderList) { //Itera sobre toda a lista aumentando em um o n�mero de turnos de cada pedido
			itrNode.getOrder().addShift();
		}
	}
	
	private void recalculateOrdersPriority() {
		//M�todo usado para recalcular a prioridade de todos os elementos da lista logo ap�s um elemento ser removido e o n�mero de turnos ser alterado
		for(Node itrNode : orderList) { //Itera sobre a lista de pedidos atualizando seus pontos de prioridade
			itrNode.updatePriority(strategy.calculatePriorityPoints(itrNode.getOrder()));
		}
	}
	
	//A descri��o dos m�todos que sofreram override abaixo est� no c�digo da interface ICrazyDS
	@Override
	public void addElement(IOrder element) {
		Node newNode = new Node(element, strategy.calculatePriorityPoints(element));
		boolean added=false;
		for(int i=0; i<orderList.size() && !added; i++) {//Percorre a lista at� que ela acabe, ou at� que um pedido com prioridade menor do que aquele que se deseja inserir seja encontrado
			if(orderList.get(i).compareTo(newNode)>0) {
				//Encontrado o pedido de prioridade menor, o novo n� � inserido na posi��o dele. Assim a ordem de prioridade � mantida como decrescente.
				orderList.add(i, newNode);
				added=true;
			}
		}
		if(!added) {
			//Se a lista acabar sem que um pedido de prioridade menor seja encontrado, adiciona-se o novo n� no fim da lista por que ele � o de menor prioridade da lista.
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
	public IOrder peekElement() { //Algo que poderia ter sido implementado � adicionar uma exce��o nesse m�todo para quando � feita uma tentativa de obter o primeiro elemento sem que haja nenhum elemento na lista, evitando assim que um apontador nulo seja retornado. Eu n�o adicionei isso para manter a escrita inalterada do uso desse m�todo na Main fornecida
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
		for(int i=0;i<orderList.size() && !removed ;i++) { //Percorre a lista de pedido enquanto o pedido fornecido como par�metro n�o foi removido.
			if(orderList.get(i).equals(element)) { //Se esse elemento � encontrado na lista, ele � removido
				orderList.remove(i);
				removed=true;
			}
		}
		if(removed) { //Se o elemento foi removido a estrutura deve aumentar o turno de cada pedido, recalcular a prioridade deles e organiz�-los novamente
			addShift();
			recalculateOrdersPriority();
			Collections.sort(orderList);
		} else { //Ele s� n�o ser� removido se ele n�o existir na lista, por isso, nesse caso, uma exce��o � lan�ada.
			throw new CrazyDSException("Element not found in the CrazyDS queue!");
		}
		
	}
	
}
