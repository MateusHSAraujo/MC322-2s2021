package com.unicamp.mc322.lab13.IOrder;
import com.unicamp.mc322.lab13.Person.*;

public interface IOrder {
	//Interface associada aos pedidos implementada assim como indicado pelo enunciado
	
	public void addShift();//m�todo para incrementar o n�mero de turnos que o pedido ficou esperando
	public String getId();//m�todo para obter o c�digo do pedido
	public void printOwner();//m�todo para fazer a impress�o reduzida no console de quem fez o pedido
	public void printOwnerFullData();//m�todo para fazer a impress�o completa no console de quem fez o pedido
	public PersonPT getPerson();//m�todo para obter a pessoad que fez o pedido
	public int getShifts();//m�todo adcional que obt�m o n�mero de turnos que o pedido esperou
	
}
