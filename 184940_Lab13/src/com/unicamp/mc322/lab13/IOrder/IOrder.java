package com.unicamp.mc322.lab13.IOrder;
import com.unicamp.mc322.lab13.Person.*;

public interface IOrder {
	//Interface associada aos pedidos implementada assim como indicado pelo enunciado
	
	public void addShift();//método para incrementar o número de turnos que o pedido ficou esperando
	public String getId();//método para obter o código do pedido
	public void printOwner();//método para fazer a impressão reduzida no console de quem fez o pedido
	public void printOwnerFullData();//método para fazer a impressão completa no console de quem fez o pedido
	public PersonPT getPerson();//método para obter a pessoad que fez o pedido
	public int getShifts();//método adcional que obtém o número de turnos que o pedido esperou
	
}
