function GoogleMap() {
	this.properties = {
            center : new google.maps.LatLng(51.508742,-0.120850),
            zoom : 10
        };
	
	this.map = new google.maps.Map(document.getElementById("googleMap"), this.properties);
	
	this.marker = new google.maps.Marker({
        position: new google.maps.LatLng(51.508742,-0.120850),
        map: this.map,
        title: 'Location'
    });
		
	this.print = function() {
		console.log(this.map);
		console.log(this.properties);
		console.log(this.marker);
	}
	
	this.changeCenter = function(lat, lng) {
		this.map.setCenter(new google.maps.LatLng(lat, lng));
		this.marker.setPosition(new google.maps.LatLng(lat, lng));
	}
}