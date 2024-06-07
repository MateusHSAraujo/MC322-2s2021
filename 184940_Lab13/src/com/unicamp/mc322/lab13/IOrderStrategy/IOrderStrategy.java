package com.unicamp.mc322.lab13.IOrderStrategy;
import com.unicamp.mc322.lab13.IOrder.*;
public interface IOrderStrategy {
	//Interface associada a estratégia de prioridade implementada assim como indicado pelo enunciado
	public double calculatePriorityPoints(IOrder order);//Método para calcular a pontuação de prioridade do pedido dado o elemento IOrder
}
