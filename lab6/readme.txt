Kousik Rajesh
180101094
kousik18@iitg.ac.in

Dynamic addition and removal of faulty nodes is implemented as addfaulty(X), removefaulty(X) respectively

Please use swi-prolog to run

To run : 
On terminal execute:

>>> swipl

?- ['shortest.pl'].
?- ['Mazedata.pl'].
?- shortest_path(0,99,X).
X = [0, 1, 2, 12, 13, 14, 24, 34, 35, 36, 37, 47, 48, 49, 59, 69, 68, 67, 77, 87, 97, 98, 99] .

% In case entire path not printed press w on keyboard to reveal full path

% To remove faulty nodes dynamically 

    ?- removefaulty(57).
    true.

    ?- shortest_path(0,99,X).
    X = [0, 1, 2, 12, 13, 14, 24, 34, 35, 36, 37, 47, 57, 67, 77, 87, 97, 98, 99] .

% To add faulty nodes dynamically

    ?- addfaulty(57).
    true.

    ?- shortest_path(0,99,X).
    X = [0, 1, 2, 12, 13, 14, 24, 34, 35, 36, 37, 47, 48, 49, 59, 69, 68, 67, 77, 87, 97, 98, 99] .
% In case no path exists false would be printed
    ?- shortest_path(0,43,X).
    false.


% To clear loaded maze
    ?- clear_file('Mazedata.pl').
    true.
% Other files can be loaded for testing now
    ?- ['Mazedata2.pl'].
    ?- shortest_path(0,48,X).
    X = [0, 1, 2, 3, 4, 11, 18, 25, 32, 31, 30, 37, 44, 45, 46, 47, 48] .

% To run all 5 tests directly
    ?- tests.
    "Shortest path for Mazedata.pl"
    [0,1,2,12,13,14,24,34,35,36,37,47,48,49,59,69,68,67,77,87,97,98,99]
    "Shortest path for Mazedata2.pl"
    [0,1,2,3,4,11,18,25,32,31,30,37,44,45,46,47,48]
    "Shortest path for Mazedata3.pl"
    [0,1,6,11,12,13,18,19,24]
    "Shortest path for Mazedata4.pl"
    [0,3,4,5,8]
    "Shortest path for Mazedata5.pl"
    [0,30,60,90,120,150,180,210,240,270,300,330,360,390,420,450,480,510,540,570,600,630,660,690,691,721,751,781,811,841,842,843,844,845,846,847,848,849,850,851,852,853,854,855,856,857,858,859,860,890,891,892,893,894,895,896,897,898,899]