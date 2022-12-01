
            const defaultImg = "https://www.ikks.com/dw/image/v2/BFQN_PRD/on/demandware.static/-/Sites-ikks_master_v0/default/dw943f40dd/produits/XR60075-10/IKKS-BODY-MILK-_-PERSONNALISER-EN-COTON-BIO-BEBE--XR60075-10_8.gif?sw=580&sh=746";
            const hostio = "http://localhost:8080";
            const entity = "rest/products";

            appProduct.controller("product-ctrl", function ($scope, $http) {
                $scope.defaultImg = defaultImg;
                

                // get data on host
                $http.get(getLink(null)).then(
                    (result) => {
                        if(result.status == 200){
                            $scope.refresh();
                            $scope.categories = result.data.categories;
                            $scope.products = result.data.products;
                           	console.log('product.id')
                        } else {
                            console.log(`Status is ${result.status}`);
                        }
                    }
                ).catch((err) => console.error(`Cannot get data on host ${hostio + "/" + entity}`, err));

                /**
                 * ---------------------------------------------------------------- CRUD
                 */
                // function create
                $scope.create = function () {
                    if(!$scope.exist()) $http.post(getLink(), $scope.product).then(result => {
                        if(result.status == 200){
                            $scope.products.unshift(result.data);
                            $scope.refresh(`Save the ${$scope.product.name} successfully.`)
                            $scope.product = {};
                        } else $scope.refresh(`Save ${$scope.product.name} failed!`)
                    }).catch(err => {
                        $scope.refresh(`Error saving ${$scope.product.name}!`)
                        console.error(`Error saving ${$scope.product.name}`, err);
                    }); else $scope.refresh(`${$scope.product ? $scope.product.id : 'Product'} already exists, cannot save!`)
                }

                // function update
                $scope.update = function (key) {
                    if($scope.exist()) $http.put(getLink(), $scope.product).then(result => {
                        if(result.status == 200){
                            $scope.products[$scope.getIndex(key)] = {...$scope.product}
                            $scope.refresh(`Update the ${$scope.product.name} successfully.`)
                            $scope.product = {};
                        } else $scope.refresh(`Update ${$scope.product.name} failed!`)
                    }).catch(err => {
                        $scope.refresh(`Error updating ${$scope.product.name}!`)
                        console.error(`Error updating ${$scope.product.name}`, err);
                    }); else $scope.refresh(`${$scope.product ? $scope.product.id : 'Product'} doesn't exists, cannot update!`)
                }

                // function delete
                $scope.delete = function (key) {
                    if($scope.exist()) $http.delete(getLink(key)).then(result => {
                        if(result.status == 200){
                            $scope.products.splice($scope.getIndex(key),1);
                            $scope.refresh(`Delete the ${$scope.product.name} successfully.`)  
                            $scope.product = {};
                        } else if(result.status == 500) $scope.refresh(`Cannot delete ${$scope.product.name}`)
                    }).catch(err => {
                        $scope.refresh(`Cannot delete ${$scope.product.name} references id Orders table!\n${err.data.message}`)
                        console.error(`Error delete ${$scope.product.name}`, err);
                    }); else $scope.refresh(`${$scope.product ? $scope.product.id : 'Product'} doesn't exists, cannot delete!`)
                }



                // _____________________________________________ SUPPORTS
                

                $scope.read = function(entity){
                    $scope.product = {...entity};
                    $scope.product.createDate = new Date(entity.createDate);
                    $scope.refresh(`Lấy thông tin ${$scope.product.name}`);
                }

                // function refresh
                $scope.refresh = function (message) {
                    $scope.message = message == null || message == undefined ? "form được làm mới." : message;
                }

                $scope.getIndex = function(key){
                    if(!$scope.product) return -1;
                    else if(!key) key = $scope.product.id;
                    for (let index = 0; index < $scope.products.length; index++) {
                        if($scope.products[index].id == key) return index;
                    }
                    return -1;
                }

                // check entity exists
                $scope.exist = function(){
                    return this.getIndex() > -1;
                }
                
            });

            // config url path
            appProduct.config(function ($routeProvider) {
                $routeProvider.when(
                    "/home", { templateUrl: "/index.html" }
                ).when(
                    "/manager/:page", { templateUrl: "pages/productDetail.html", controller: "managerCtrl" }
                ).otherwise(
                    { redirectTo: "/home" }
                );
            });

            // link support
            function getLink(id) {
                return `${hostio}/${entity}${id == undefined || id == null ? "" : "/" + id}`;
            };