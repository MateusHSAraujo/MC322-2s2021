package com.unicamp.mc322.lab13.IOrderStrategy;
import com.unicamp.mc322.lab13.IOrder.*;
public interface IOrderStrategy {
	//Interface associada a estrat�gia de prioridade implementada assim como indicado pelo enunciado
	public double calculatePriorityPoints(IOrder order);//M�todo para calcular a pontua��o de prioridade do pedido dado o elemento IOrder
}
