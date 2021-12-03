squareroot(X, Result, Accuracy) :-
    X1 is X/2,
    Y is (X1*X1 +X)/(X),
    once(rec(X1,Y,X,Result,Accuracy)).
rec(X,Y0,_, Result,Accuracy) :-
    abs(X-Y0) =< Accuracy,
    Result is Y0.
rec(X,Y0,Const,Result, Accuracy) :-
    abs(X-Y0) > Accuracy,
    X0 is Y0,
    Y1 is (X0*X0 + Const)/(2*X0),
    rec(X0,Y1,Const,Result,Accuracy).
runTests :-
    squareroot(1.5,A,0.00001),
    format("squareroot(1.5,X,0.00001) = ~w \n",[A]),
    squareroot(16,B,0.00001),
    format("squareroot(16,X,0.00001) = ~w \n",[B]),
    squareroot(5,C,0.000001),
    format("squareroot(5,X,0.000001) = ~w \n",[C]),
    squareroot(3.14,D,0.0001),
    format("squareroot(3.14,X,0.0001) = ~w \n",[D]),
    squareroot(100000,E,0.00001),
    format("squareroot(100000,X,0.00001) = ~w \n",[E]).

