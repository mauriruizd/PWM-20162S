function $(selector) {
	switch (selector.charAt(0)) {
	case "#":
		return document.getElementById(selector.substr(1));
		break;
	case ".":
		return document.getElementsByClassName(selector.substr(1));
		break;
	default:
		return document.getElementsByTagName(selector);
		break;
	}
}

function clean(selector) {
	var target = $(selector);
	if (target.length != "undefined") {
		for(var i = 0; i < target.length; i++) {
			target[i].value = "";
		}
	} else {
		target.value = "";
	}
}