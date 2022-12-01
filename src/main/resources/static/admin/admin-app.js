var appProduct = angular.module("appProduct", ["ngRoute"])
appProduct.config(function($routeProvider) {
    $routeProvider
	.when("/product",{
		templateUrl:"/admin/product/index.html",
		controller:"product-ctrl"
	})
	.when("/authorize",{
		templateUrl:"/admin/authority/index.html",
		controller:"authority-ctrl"
	})
	.when("/authorized",{
		templateUrl:"/admin/authority/unaauthorized.html",
		controller:"authority-ctrl"
	})
	.otherwise({
		template:"<h1 class ='text-center'> ADMIN</h1> "
	})
})