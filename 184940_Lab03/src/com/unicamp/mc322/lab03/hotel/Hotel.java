package com.unicamp.mc322.lab03.hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
	private Room[] rooms;
	private String name;
	private String adress;
	private String phone;
	private double vipPrice;
	private double stdPrice;
	
	public Hotel(String name, String adress, String phone, double vipPrice, double stdPrice) {
		this.name=name;
		this.adress=adress;
		this.phone=phone;
		this.vipPrice=vipPrice;
		this.stdPrice=stdPrice;
		rooms= new Room[100];
	}
	
	private boolean whantsToVip(int roomNumber) {
		/*This method cheks if a room number are in the vip range or in the standard range*/
		return (roomNumber>10)? false:true;
	}
	
	private boolean isNull(int roomNumber) {
		/*This method cheks if a room in a certain room number is already allocated*/
		return (rooms[roomNumber-1]==null)? true:false;
	}
	
	public int addRoom(boolean isVip, boolean isSmoking, boolean hasAir, int roomNumber) {
		/*This function creates a room and add it into the hotel array of rooms. It first check if the room number isn't already occupied, then do the allocation. In success, it returns 1 and in fail, it return -1 (if the room number is already occupied) and -2 (when the room is vip and is trying to be allocated to a standar room place or vice versa)*/
		int returnal =0;
		boolean whantsToVip = whantsToVip(roomNumber);
		boolean isNull = isNull(roomNumber);
		
		if(isNull && whantsToVip && isVip) {
			Room newRoom = new Room(isVip,isSmoking,hasAir);
			rooms[roomNumber-1]= newRoom;
			returnal = 1;
		} else if(isNull && !whantsToVip && !isVip ){
			Room newRoom = new Room(isVip,isSmoking,hasAir);
			rooms[roomNumber-1]= newRoom;
			returnal = 1;
		} else if(!isNull){
			returnal =-1;
		} else {
			returnal = -2;
		}
		return returnal;
		
	}
	
	public List<Integer> checkUnoccupiedRooms(){
		/*This method return the index of all the unoccupied rooms in the hotel*/
		List<Integer> UnoccupiedRooms = new ArrayList<Integer>();
		for(int index = 0; index < 100; index++){
			int roomNumber = index+1;
			Room room = rooms[index];
			if(!isNull(roomNumber) && !room.isOccupied()){
				UnoccupiedRooms.add(roomNumber);
			} 	
		}
		return UnoccupiedRooms;
		
	}
	
	public int isAvailable(int roomNumber) {
		/*This method search in the array of room if a certain room is available for booking. If it is, the method returns 1. If it the room doesn't exists, the method returns -1. If it is occupied, the method returns -2*/
		Room room = rooms[roomNumber-1];
		boolean isNull=isNull(roomNumber);
		int returnal = 0;
		if (!isNull && !room.isOccupied()) {
			returnal= 1;
		} else if(isNull) {
			returnal= -1;
		} else {
			returnal= -2;
		}
		return returnal;
	}
	
	public double roomPrice(int roomNumber) {
		/*This method returns the price of a room whith a especified room number in the hotel, or -1 if the room aren't allocated yet*/
		double returnal =0;
		boolean isNull = isNull(roomNumber);
		if (!isNull && whantsToVip(roomNumber)) {
			returnal = vipPrice;
		} else if(!isNull) {
			returnal = stdPrice;
		} else {
			returnal = -1;
		}
		return returnal;
	}
		
	public String printHotelInfo() {
		/*This method retuns a string with the hotel and its rooms info*/
		String txt = String.format("Hotel Name: %s\n\tAdress: %s\n\tPhone: %s\n",name,adress,phone);
		String roomsInfo="";
		for(int index = 0; index<100;index++) {
			Room iteratedRoom = rooms[index];
			int roomNumber = index+1;
			if(!isNull(roomNumber)) {
				String roomAdress ="\tRoom number : "+roomNumber+"\n";
				String price = (whantsToVip(roomNumber))? "\t\tPrice = "+vipPrice+"$\n":"\t\tPrice = "+vipPrice+"$\n";
				String roomInfo = iteratedRoom.roomInfo();
				roomsInfo = roomAdress+price+roomInfo;
			}
		}
		txt+=roomsInfo;
		return txt;
	}
	
	public int bookRoom (int roomNumber,String userName,int days,boolean userSmokes) {
		/*This method sets the booking of a certain room. It returns 1 in success, -1 if the room booking failed and -2 if the room doesn't exists*/
		int returnal =0;
		if(!isNull(roomNumber)) {
			returnal = rooms[roomNumber-1].bookRoom(userName, days, userSmokes);
		} else {
			returnal = -1;
		}
		return returnal;
	}
	
	
	
	public int unbookRoom (int roomNumber, String userName) {
		/*This method sets the unbooking of a certain room.It returns 1 in succes, -1 if the unbooking wasn't valid and -2 if the room doesn't exists*/
		int returnal =0;
		if(!isNull(roomNumber)) {
			returnal = rooms[roomNumber-1].unbookRoom(userName);
		} else {
			returnal = -2;
		}
		return returnal;
	}
	
	
	
	
	
	
	
	
	
	
}
