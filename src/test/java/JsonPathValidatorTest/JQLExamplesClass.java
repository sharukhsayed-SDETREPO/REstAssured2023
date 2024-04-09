package JsonPathValidatorTest;
/*
 * 1.                                                                  $[*].id 
 * 2.                                                                  $[*].title 
 * 3.                                                                  $[*].rating.rate 
 * 4.                                                                  $[*]..rate 
 * 5.                                                                  $[*]..rate,count 
 * 6. rates which less than 3:                                         $[?(@.rating.rate <3)].rating.rate
 * 7.                                                                  $[*].title 
 * 8. fetch the product price where id is 3                            $[?(@.id==3)].price
 * 9. fetch titles and prices of the product where category = jewelery $[?(@.category == 'jewelery')].[title,price]
 * 10. fetch title of the products with rating rate >=4.5              $[?(@.rating.rate>=4.5)].[title]
 * 11. fetch title of the producs where price is less than 30$         $[?(@.price<30)].[title]
 * 12. fetch title and jewellery category of the products where 
 * rate is between 2 and 4:                                            $[?(@.category == 'jewelery' && @.rating.rate >=2 && @.rating.rate<= 4)].[title,category] --> List<Map<String, String>
 * 13. fetch title and women's clothing category where rating 
 * count >=100 and price <10$                                          $[?(@.category == 'women\'s clothing' && @.rating.count >=100&& @.price < 10)].[tile,category] --> --> List<Map<String, String>
 * 
 * 14.                                                                  $[?(@.category == 'jewelery')].[title,price,count,rate] -->List<Map<String, String>
 * 
 * 
 * 
 * https://fakestoreapi.com/products
 * 
 */