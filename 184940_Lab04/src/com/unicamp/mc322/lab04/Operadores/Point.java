package com.unicamp.mc322.lab04.Operadores;
public class Point {
	//Essa classe serve basicamente como estrutura de dados para a operação das demais classes
	private int x;
	private int y;
	
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public double distance(Point b) {
		return Math.sqrt(Math.pow(x-b.x, 2)+Math.pow(y-b.y, 2));
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
	
}
