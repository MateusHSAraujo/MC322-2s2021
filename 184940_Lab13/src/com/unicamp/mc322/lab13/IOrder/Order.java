package com.unicamp.mc322.lab13.IOrder;
import com.unicamp.mc322.lab13.Person.PersonPT;

public abstract class Order implements IOrder{
	//Classe abstrata que representa um pedido
	//Como afirmado pelo enunciado, um pedido registra a informação de quem o fez e o número de turnos que esse pedido ficou esperando.
	protected PersonPT owner;//Como para a interface IOrder é pedida uma operação que retorna a pessoa que fez o pedido, entendi que o desejado era retornar o objeto que corresponde a essa pessoa. Por isso esse atributo quarda ele
	protected String id;
	protected int shifts;
	
	protected Order(PersonPT owner, String id) {
		this.owner=owner;
		shifts=0;
		this.id=id;
	}
	
	//A descrição da funcionalidade dos métodos que sofreram sobrescrita abaixo está no código da interface IOrder
	@Override
	public void addShift() {
		shifts++;
	}
	@Override
	public int getShifts() {
		return shifts;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void printOwner() {
		System.out.println("\tOwner's name: "+owner.getName());
	}
	@Override
	public void printOwnerFullData() {
		System.out.println(owner.toString());	
	}
	
	@Override
	public PersonPT getPerson() {
		return owner;
	}
	
	@Override
	public String toString() {
		String txt="";
		txt="Order ID: "+id+" | Owner's info - "+owner.toString()+"; Waited "+shifts+" shifts";
		return txt;
	}
	
}
