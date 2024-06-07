package com.unicamp.mc322.lab03;

import java.util.ArrayList;
import java.util.List;

import com.unicamp.mc322.lab03.hotel.Hotel;
import com.unicamp.mc322.lab03.user.User;

public class Booking {
	private List<Hotel> hotelList;
	private List<User> userList;
	
	public void main(String args[]) {
		/*Main function not specified*/
		hotelList = new ArrayList<Hotel>();
		userList = new ArrayList<User>();
	}
	
	
	Booking(){
		hotelList = new ArrayList<Hotel>();
		userList = new ArrayList<User>();
	}
	
	void registerHotel (String name, String adress, String phone, double vipPrice, double stdPrice) {
		/*This method register a hotel and adds it in the hotel list of the booking service*/
		Hotel newHotel = new Hotel(name,adress,phone,vipPrice,stdPrice);
		hotelList.add(newHotel);
	}
	
	void registerUser (String name, String cpf, String birth, String gender, int balance, boolean smokes) {
		/*This method register a User and adds it in the User list of the booking service*/
		User newUser = new User(name,cpf,birth,gender,balance,smokes);
		userList.add(newUser);
	}
	
	int registerRoomInHotel (Hotel hotel, boolean isVip, boolean isSmoking, boolean hasAir, int roomNumber) {
		/*This method register an room off a hotel, returning 1 in success and -1 if the room number is already occupied and -2 when the room is vip and is trying to be allocated to a standar room place or vice versa*/
		return hotel.addRoom(isVip, isSmoking, hasAir, roomNumber);
		
	}
	
	int createReservation(Hotel hotel, User user, int roomNumber, int days) {
		/*This method creates a reservation in an hotel for an user. It returns 1 if the reservation was fully succeded, -1 if the reservation failed,-2 if user can't pay the reservation  */
		int returnal = 0;
		boolean userCanPay= (user.checkBalance()>=hotel.roomPrice(roomNumber))? true:false;
		boolean reservationSucesseded=false;
		if (userCanPay) {
			reservationSucesseded = (hotel.bookRoom(roomNumber, user.getName(), days, user.getSmoker())>0)? true:false;
			if (reservationSucesseded) {
				returnal = user.payMoney(returnal);
			} else {
				returnal = -1;
			}
		} else { 
			returnal = -2;
		}
			
		return returnal;
	}
	
	int cancelReservation(Hotel hotel, User user, int roomNumber) {
		/*This method cancels a reservation of the a certain room from user in the hotel, returning 1 in success, -1 if the unbooking wasn't valid and -2 if the room doesn't exists*/
		int returnal = 0;
		int reservationUndone =hotel.unbookRoom(roomNumber, user.getName());
		if (reservationUndone>0) {
			user.recieveMoney((hotel.roomPrice(roomNumber))*(0.7));
			returnal = 1;
		} else {
			returnal = reservationUndone;
		}
		return returnal;
	}
	
	
	
	
	
	
}
