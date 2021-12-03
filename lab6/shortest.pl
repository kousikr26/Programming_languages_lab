% CHECK README FOR RUN INSTRUCTIONS


% Making faultynode a dynamic function 
:- dynamic(faultynode/1).



% Remove faultynode dynamically
removefaulty(X) :- retract(faultynode(X)).

% Add faultynode dynamically
addfaulty(X) :- assert(faultynode(X)).

% Clear rules from loaded file and retract faultynode declarations
clear_file(Z) :- retractall(faultynode(_)),unload_file(Z).



% Utility function for finding shortest path
shortest_path(Src,Target,Result):- shortest_path([[Src,[Src]]], Target,[Src],Result).

% Target reached condition
shortest_path([[Target,Path]|_], Target, _,Path). 

% Breadth first search function 
shortest_path([[Next, CurrentPath] | Queue], Target, Visited, Path):-

    % Find all child nodes and store them in Children
    findall([Child,NewPath], (mazelink(Next, Child),
                         \+ member(Child,Visited),
                         \+ faultynode(Child),
                          append(CurrentPath, [Child], NewPath)), Children),
    
    maplist(nth0(0),Children,ChildNodes),
    
    % Update visited list and Queue for bfs

    append(Visited,ChildNodes,NewVisited),
    append(Queue, Children, NextQueue),
    
    % Recursive bfs call with new Queue
    shortest_path(NextQueue, Target, NewVisited, Path).


  
tests :-
    ['Mazedata.pl'],
    print("Shortest path for Mazedata.pl"),nl,
    shortest_path(0,99,X),
    print(X),nl,
    clear_file('Mazedata.pl'),
    ['Mazedata2.pl'],
    print("Shortest path for Mazedata2.pl"),nl,
    shortest_path(0,48,Y),
    print(Y),nl,
    clear_file('Mazedata2.pl'),
    ['Mazedata3.pl'],
    print("Shortest path for Mazedata3.pl"),nl,
    shortest_path(0,24,Z),
    print(Z),nl,
    clear_file('Mazedata3.pl'),
    ['Mazedata4.pl'],
    print("Shortest path for Mazedata4.pl"),nl,
    shortest_path(0,8,P),
    print(P),nl,
    clear_file('Mazedata4.pl'),
    ['Mazedata5.pl'],
    print("Shortest path for Mazedata5.pl"),nl,
    shortest_path(0,899,Q),
    print(Q),nl,
    clear_file('Mazedata5.pl').
