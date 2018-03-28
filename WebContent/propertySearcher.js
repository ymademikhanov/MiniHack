function PropertySearcher(global_map, button, location, minprice, maxprice) {
	button.addEventListener("click", function(e) {
		var loc = location.value;
	    	var min = minprice.options[minprice.selectedIndex].value;
	    	var max = maxprice.options[maxprice.selectedIndex].value;
	    	
	    	console.log(loc);
	    	console.log(min);
	    	console.log(max);
	    	
		e.preventDefault();
		$.ajax({
	        url: "services/searchProperties?location=" + loc + "&minprice=" + min + "&maxprice=" + max,
	        type: 'GET',
	        success: function(response) {
	        		global_map.clearMarkers();
	        		let obj = $.parseJSON(response);
	            for (let i = 0; i < obj.length; i++) {
	            		var myLatlng = new google.maps.LatLng(obj[i]["lat"],obj[i]["lng"]);
	            		var pinColor = "00B2EE";
	            		var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
	            		        new google.maps.Size(21, 34),
	            		        new google.maps.Point(0,0),
	            		        new google.maps.Point(10, 34));
	        			var marker = new google.maps.Marker({
	        			    position: myLatlng,
	        			    icon: pinImage,
	        			    title: obj[i]["address"]
	        			});
	        			global_map.addMarker(marker);
	            }
	        }
		});
	});
}