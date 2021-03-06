$( document ).on( "pageinit", "#internaluse-input", function() {
	console.log("Page Init Input Internal Use");
	$("#pdCode").on("input", function(e) {
		var schTxt = $(this).val();
		console.log("schTxt "+schTxt);
		
		var url = contextPath+'/findProductByKey';
		var params = {srhKey: schTxt};
		
		$.getJSON(url, params, function (data) {
			console.log(data);
			if(data != null ){
				console.log("Product Id "+data.productId);
				console.log("Product Key "+data.productSrhKey);
				console.log("Product name "+data.productName);
				
				$("#productName").html(data.productName);
				$("#productId").val(data.productId);
				$("#asiId").focus();
			}
			else{
				$("#productName").html("");
				$("#productId").val("");
			}
		});
	});
	
	$("#asiId").on("input", function(e) {
		var schTxt = $(this).val();
		
		var url = contextPath+'/findASIById';
		var params = {asiId: schTxt};
		
		$.getJSON(url, params, function (data) {
			console.log(data);
			if(data != null ){
				console.log("ASI Description "+data.description);
				
				$("#asiDesc").html(data.description);
				$("#usedQty").focus();
			}
			else{
				$("#asiDesc").html("");
			}
		});
	});
});