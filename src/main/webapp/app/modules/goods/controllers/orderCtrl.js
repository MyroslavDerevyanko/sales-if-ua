goods
	.controller('OrderCtrl', ['$scope', '$http', function($scope, $http){
		// fields for future JSON
		$scope.goodId = "";
		$scope.firstName = "";
		$scope.lastName = "";
		$scope.email = "";
		// card field
		$scope.card = {
			number: "",
			type: "",
			expireMonth: "",
			expireYear: "",
			cvv2: ""
		};

		// regexps
		$scope.namePattern = /^[A-Za-zА-Яа-яі]*$/;

		// function that sends order data to the REST
		$scope.makeOrder = function(){
			// alert("Hello");
			var order = {
				goodsId: $scope.goodId,
				firstName: $scope.firstName,
				lastName: $scope.lastName,
				email: $scope.email,
				card: $scope.card
			};
			console.log("User has sent an order:", order);
		};

		
		// styling function
		$scope.getClass = function(fieldStatus, pristine){
			if(!pristine){
				return{
					'has-success': fieldStatus,
					'has-error': !fieldStatus
				};
			}
		};
		// styling for icons function
		$scope.getIconClass = function(fieldStatus, pristine){
			if(!pristine){
				return{
					'glyphicon-ok': fieldStatus,
					'glyphicon-remove': !fieldStatus
				};
			};
		};

		// function that checks input credit number with the Luhn algorithm
		$scope.creditValid = false;
		// validation function of creadit number
		$scope.validateCredit = function(number){
			numberPattern = /^[0-9]{16}$/;
			console.log(number);
			console.log(typeof number);
			console.log(numberPattern.test(number));
			if(!numberPattern.test(number)){ 
				console.log('You have to enter credit number');
				$scope.creditValid = false;
				return false;
			};

			valid=false;
			digitArray = [];
			digit="";
			sum = 0;
			for(var i=0; i<number.length; i++){
				digitArray[i] = parseInt(number[i]);
			}
			

			for(var i=0; i<digitArray.length; i++){

				digit = digitArray[i];

				if(i%2 == 0){
					digit = 2 * digit;

					if(digit > 9)
						digit = digit - 9;
				}
					
				
				sum = sum + digit;
			};
			console.log(digitArray);
			if(sum % 10 == 0)
				valid = true;
			else
				valid = false;

			if(valid){
				console.log("valid");
				$scope.creditValid = true;
			}
			else{
				// return false;
				$scope.creditValid = false;
				console.log("not valid");
				}
		};


		// function that checks expiration date of the card
		$scope.expDateValid = false;
		//validate expiration date
		$scope.validateExpDate = function(month, year){
			console.log("go");
			if(month==NaN || year==false){
				console.log("You've not chosen expiration date");
				return false;
			}
			month = parseInt(month);
			year = parseInt(year);
			date = new Date();

			currMonth = date.getMonth() + 1;
			currYear = date.getFullYear();

			if(year==currYear && month<currMonth)
				{
					console.log("Card is expired");
					$scope.expDateValid = false;
				}
				
				else 
					// return true;
				{
					console.log("Validation confirmed");
					$scope.expDateValid = true;
				}
				

		};


	}]);