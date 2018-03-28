function GoogleMap(elementId) {
	// A helper converting function.
	var convert = function(lat, lng) {
		return new google.maps.LatLng(lat, lng);
	};
	
	// Properties.
	var center = convert(51.508742, -0.120850);
	var map = new google.maps.Map(document.getElementById(elementId), { center : center, zoom : 12 } );
	var marker = new google.maps.Marker({
        position: center,
        map: map
    });
		
	// Methods.
	this.changeZoom = function(zoom) {
		map.setZoom(zoom);
	};
	
	this.changeCenter = function(lat, lng) {
		center = convert(lat, lng);
		map.setCenter(center);
		marker.setPosition(center);
	};
	
	// Helper function for debugging.
	this.print = function() {
		console.log(marker);
		console.log(map);
	};
}