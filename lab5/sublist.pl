sublistFunc([],_).

sublistFunc([X|Y],[X|Z]) :-
    sublistFunc(Y,Z).
sublistFunc([X|Y],[_|Z]) :-
    sublistFunc([X|Y],Z).

/*

?- sublistFunc([a,b],[a,e,b,d,s,e]).
true .

?-  sublistFunc([a,b],[a,e,e,f]).
false.

?- sublistFunc([a,b],[b,a]).
false.

?- sublistFunc([],[a,e,e,f]).
true.

?- sublistFunc([a],[]).
false.


*/