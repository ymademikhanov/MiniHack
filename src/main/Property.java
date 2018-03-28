package main;

public class Property {
	public String address;
	public double lat;
	public double lng;
	public int price;
	
	public Property(String address, double lat, double lng, int price) {
		this.address = address;
		this.lat = lat;
		this.lng = lng;
		this.price = price;
	}
	
	public boolean doesBelong(String location) {
		return address.toLowerCase().indexOf(location.toLowerCase()) != -1;
	}
}
