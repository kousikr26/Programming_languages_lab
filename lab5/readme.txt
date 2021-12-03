Kousik Rajesh
180101094

Works on both swipl and gprolog

Part 1

?- [sqrt].
?- runTests.

squareroot(1.5,X,0.00001) = 1.2247448713915892 
squareroot(16,X,0.00001) = 4.000000000000004 
squareroot(5,X,0.000001) = 2.23606797749979 
squareroot(3.14,X,0.0001) = 1.772004515298368 
squareroot(100000,X,0.00001) = 316.22776601683796 
true.

OR run 

?- squareroot(5,X,0.001).
X = 2.236067977915804.

-------------------------------------------------------------------------------------------------------
Part 2


?- [sublist].

?- sublistFunc([a,b],[a,e,b,d,s,e]).
true .


