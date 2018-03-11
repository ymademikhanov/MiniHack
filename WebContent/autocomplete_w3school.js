function autocomplete(inp, arr) {
	
  function suggestion_item(input, matched_part, other_part, place_id) {
	b = document.createElement("DIV");
	b.innerHTML = "<strong>" + matched_part + "</strong>" + other_part;
	
	var data = {"description" : matched_part + other_part, "place_id" : place_id};
	b.innerHTML += "<input type='hidden' value='" +  JSON.stringify(data) + "'>";
	
	b.addEventListener("click", function(e) {
		var object = JSON.parse(this.getElementsByTagName("input")[0].value);
		input.value = unescape(object["description"]);
		
		$.ajax({
	        url: "/MiniHack_1/services/geocode?place_id=" + object["place_id"],
	        type: 'GET',
	        success: function(response) {
	        		var coord = JSON.parse(response);
	        		map.setCenter(new google.maps.LatLng(coord["lat"], coord["lng"]));
	        		marker.setPosition(new google.maps.LatLng(coord["lat"], coord["lng"]));
	        }
	      });
		closeAllLists();
	});
	return b;
  };

  var currentFocus;
  inp.addEventListener("input", function(e) {
      var a, b, i, val = this.value;
      /*close any already open lists of autocompleted values*/
      closeAllLists();
      if (!val) { 
        return false;
      }

      currentFocus = -1;
      /*create a DIV element that will contain the items (values):*/
      a = document.createElement("DIV");
      a.setAttribute("id", this.id + "autocomplete-list");
      a.setAttribute("class", "autocomplete-items");
      /*append the DIV element as a child of the autocomplete container:*/
      this.parentNode.appendChild(a);
      /*for each item in the array...*/

      $.ajax({
        url: "/MiniHack_1/services/findPlace?input=" + val,
        type: 'GET',
        success: function(response) {
            let obj = $.parseJSON(response);
            for (let i = 0; i < obj.length; i++) {
              
              desc = unescape(obj[i]["description"]);
              place_id = unescape(obj[i]["place_id"]);
              
              if (desc.substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                let matched_part = desc.substr(0, val.length);
                let other_part = desc.substr(val.length);
                a.appendChild(new suggestion_item(inp, matched_part, other_part, place_id));
              }
            }
        }
      });
  });

  /*execute a function presses a key on the keyboard:*/
  inp.addEventListener("keydown", function(e) {
      var x = document.getElementById(this.id + "autocomplete-list");
      if (x) x = x.getElementsByTagName("div");
      if (e.keyCode == 40) {
        /*If the arrow DOWN key is pressed, increase the currentFocus variable:*/
        currentFocus++;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 38) { //up
        /*If the arrow UP key is pressed,
        decrease the currentFocus variable:*/
        currentFocus--;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 13) {
        /*If the ENTER key is pressed, prevent the form from being submitted,*/
        e.preventDefault();
        if (currentFocus > -1) {
          /*and simulate a click on the "active" item:*/
          if (x) x[currentFocus].click();
        }
      }
  });

  function addActive(x) {
    /*a function to classify an item as "active":*/
    if (!x) return false;
    /*start by removing the "active" class on all items:*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    /*add class "autocomplete-active":*/
    x[currentFocus].classList.add("autocomplete-active");
  }

  function removeActive(x) {
    /*a function to remove the "active" class from all autocomplete items:*/
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }

  function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
    except the one passed as an argument:*/
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
      x[i].parentNode.removeChild(x[i]);
    }
  }
}
/*execute a function when someone clicks in the document:*/
document.addEventListener("click", function (e) {
    closeAllLists(e.target);
});
}