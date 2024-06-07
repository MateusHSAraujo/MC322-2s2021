package com.unicamp.mc322.lab13.IOrderStrategy;

import com.unicamp.mc322.lab13.IOrder.IOrder;

public class YourStrategy2 implements IOrderStrategy{
	//Classe que representa a estratégia de prioridade definida por mim. Como o enunciado não estabeleceu nenhuma especificação quanto a ela, optei por usar a prioridade por chegada (a ordem feita primeiro sera a de maior prioridade)
	@Override
	public double calculatePriorityPoints(IOrder order) {
		double priorityScore= order.getShifts();
		return priorityScore;
	}

}
