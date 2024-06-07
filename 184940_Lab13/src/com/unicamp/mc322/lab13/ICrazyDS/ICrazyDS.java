package com.unicamp.mc322.lab13.ICrazyDS;
import com.unicamp.mc322.lab13.Exceptions.CrazyDSException;
import com.unicamp.mc322.lab13.IOrder.IOrder;

public interface ICrazyDS {
	//Interface associada a estrutura de dados personalizada implementada assim como indicado pelo enunciado
	public void addElement(IOrder element);//método para adicionar um elemento IOrder na fila considerando os pontos de prioridades definido pela estratégia utilizada
	public void printElements();//método para imprimir os elementos da estrutura de dados por ordem de prioridade decrescente
	public IOrder peekElement();//método para obter um elemento IOrder que atualmente tem a maior prioridade na estrutura de dados sem retirá-lo dela
	public IOrder getElementAt(int index) throws CrazyDSException;//método para obter um elemento IOrder da estrutura de dados em uma posição indicada como argumento sem removê-lo da estrutura de dados. Ele gera a excessão personalizada se a posição estiver fora dos limites da estrutura
	public void removeElement(IOrder element) throws CrazyDSException;//método para remover um elemento IOrder usando o elemento IOrder como entrada. Ele lança uma excessão se o elemento passado como parâmetro não estiver na estrutura
	

}
