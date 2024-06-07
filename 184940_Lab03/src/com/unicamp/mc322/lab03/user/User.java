package com.unicamp.mc322.lab03.user;



public class User {
	private String name;
	private String cpf;
	private String birth;
	private String gender;
	private double balance;
	private boolean smokes;
	
	public User(String name, String cpf, String birth, String gender, int balance, boolean smokes) {
		this.name=name;
		this.cpf=cpf;
		this.birth=birth;
		this.gender=gender;
		this.balance=balance;
		this.smokes=smokes;
	}
	
	public int recieveMoney(double quantity) {
		/*This function adds a certain quantity to user's balance. It will return -1 if the quantity is invalid (negative or zero)*/
		int returnal = 0;
		if( quantity<=0) {
			returnal =-1;
		} else {
			balance = balance+quantity;
			returnal =1;
		}
		return returnal;
	}
	
	public int payMoney(double quantity) {
		/*This method decreeses a certain quantity from user's balance. It will return 1 in success and -1 if the quantity is invalid or the user don't have enought money to give*/
		int returnal =0;
		if(quantity>0 && balance>=quantity) {
			balance = balance - quantity;
			returnal = 1;
		} else {
			returnal = -1;
		}
		return returnal;
	}
	
	public double checkBalance() {
		/*This method return user's current balance*/
		return this.balance;
	}
	
	public String printInfo(){
		/*This method return a string whit all users*/
		String smokes;
		if (this.smokes) {
			smokes ="yes";
		} else {
			smokes = "no";
		}
		String txt= String.format("\tName: %s.\n\tCpf: %s.\n\tBirth: %s.\n\tGender: %s.\n\tSmokes? %s.\n",name,cpf,birth,gender,smokes);
		return txt;
	}
	
	public boolean getSmoker() {
		return smokes;
	}
	
	public String getName() {
		return name;
	}
	

}
