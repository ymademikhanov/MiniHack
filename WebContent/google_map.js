function GoogleMap(elementId) {
	this.center = new google.maps.LatLng(51.508742,-0.120850);
	this.map = new google.maps.Map(document.getElementById(elementId), { center : this.center, zoom : 10 } );
	this.marker = new google.maps.Marker({
        position: this.center,
        map: this.map
    });
		
	this.changeCenter = function(lat, lng) {
		this.center = new google.maps.LatLng(lat, lng);
		this.map.setCenter(this.center);
		this.marker.setPosition(this.center);
	}
	
//	Helper function for debugging.
	this.print = function() {
		console.log(this.map);
		console.log(this.properties);
		console.log(this.marker);
	}
}