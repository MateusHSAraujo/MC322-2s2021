package com.unicamp.mc322.lab13.ICrazyDS;
import com.unicamp.mc322.lab13.Exceptions.CrazyDSException;
import com.unicamp.mc322.lab13.IOrder.IOrder;

public interface ICrazyDS {
	//Interface associada a estrutura de dados personalizada implementada assim como indicado pelo enunciado
	public void addElement(IOrder element);//m�todo para adicionar um elemento IOrder na fila considerando os pontos de prioridades definido pela estrat�gia utilizada
	public void printElements();//m�todo para imprimir os elementos da estrutura de dados por ordem de prioridade decrescente
	public IOrder peekElement();//m�todo para obter um elemento IOrder que atualmente tem a maior prioridade na estrutura de dados sem retir�-lo dela
	public IOrder getElementAt(int index) throws CrazyDSException;//m�todo para obter um elemento IOrder da estrutura de dados em uma posi��o indicada como argumento sem remov�-lo da estrutura de dados. Ele gera a excess�o personalizada se a posi��o estiver fora dos limites da estrutura
	public void removeElement(IOrder element) throws CrazyDSException;//m�todo para remover um elemento IOrder usando o elemento IOrder como entrada. Ele lan�a uma excess�o se o elemento passado como par�metro n�o estiver na estrutura
	

}
