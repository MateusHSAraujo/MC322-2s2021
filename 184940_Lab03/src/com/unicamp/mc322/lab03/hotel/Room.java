package com.unicamp.mc322.lab03.hotel;

public class Room {
	private boolean isOccupied;
	private boolean isVip;
	private boolean isSmoking;
	private boolean hasAir;
	private String reservedBy;
	private int reservedFor;
	
	Room(boolean isVip, boolean isSmoking, boolean hasAir){
		this.isVip = isVip;
		this.isSmoking=isSmoking;
		this.hasAir=hasAir;
	}
	
	int bookRoom(String userReserv, int dayOfReserv, boolean userSmokes) {
		/*This function book a room for a user and for a certain time, turning it atribute occupied to true and collecting the name of the user and the amount of days. In fail, it returns -1 if it happened because of ocuppation and -2 if it happened because smoker user choose non smoking area or vice versa.In success, it return 1*/
		int returnal =0;
		if (!isOccupied && userSmokes==isSmoking) {
			isOccupied = true;
			reservedBy = userReserv;
			reservedFor =dayOfReserv;
			returnal= 1;
		} else if(isOccupied){
			returnal= -1;
		} else {
			returnal = -2;
		}
		return returnal;
	}
	
	int unbookRoom(String userWantsUnreserv) {
		/*This function unbook a room booked for an certain user. In fail (if the user unbooking isn't the same that book), it returns -1 .In success, it return 1*/
		int returnal = 0;
		if (reservedBy == userWantsUnreserv) {
			reservedBy = "NONE";
			reservedFor = -1;
			isOccupied=false;
			returnal = 1;
		} else {
			returnal = -1;
		}
		return returnal;
	}
	
	boolean isOccupied() {
		return this.isOccupied;
	}

	String roomInfo() {
		/*This method returns a string whith all the room informations*/
		String occupied = (isOccupied)? "Is occupied by "+reservedBy+" for "+reservedFor+" days":"Is available";
		String vip = (isVip)? "Is Vip":"Is standard";
		String air = (hasAir)? "Has air condition":"Hasn't air conditioner";
		String smoke = (isSmoking)? "Smoking allowed":"Smoking prohibited";
		String txt = String.format("\t\t-%s;\n\t\t-%s;\\n\\t\\t-%s;\\n\\t\\t-%s;\n",vip,occupied,air,smoke);
		return txt;
	}
}
