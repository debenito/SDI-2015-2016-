
function search() {
	var input = document.getElementById('searchbar');
	
	input.onkeyup = function() {
		var filter = input.value;
		var lis = document.getElementsByClassName('program');
		for (var i = 0; i < lis.length; i++) {
		
           var name = lis[i].innerHTML.toString();
			if ((name.toLowerCase().indexOf(filter.toLowerCase())  > -1 )){
				lis[i].style.display = 'list-item';
				lis[i].innerHTML = list[i].getElementsByTagName("a").innerHTML;
			 }else {
                lis[i].style.display = 'none';
            }
		}
	}
}
   