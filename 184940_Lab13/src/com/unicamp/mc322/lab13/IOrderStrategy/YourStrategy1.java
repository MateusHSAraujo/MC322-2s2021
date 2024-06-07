package com.unicamp.mc322.lab13.IOrderStrategy;

import com.unicamp.mc322.lab13.IOrder.IOrder;

public class YourStrategy1 implements IOrderStrategy{
	//Classe que representa a estratégia de prioridade do empresário, baseada na equação matemática abaixo.
	@Override
	public double calculatePriorityPoints(IOrder order) {
		double priorityScore= ((order.getPerson().getAge())*0.01)+(0.07*(order.getShifts()));
		return priorityScore;
	}
	
	
}
