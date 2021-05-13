flightPath(lax, nrt, 5439, 12).
flightPath(cdg, lax, 4051, 9).
flightPath(cdg, jfk, 3624, 9).
flightPath(cdg, fco, 684, 2).
flightPath(lju, cdg, 587, 2).
flightPath(lju, fco, 646, 3).
flightPath(fco, jfk, 4266, 10).
flightPath(fco, sin, 6245, 12).
flightPath(sin, nrt, 3329, 7).
flightPath(jfk, nrt, 6729, 14).
flightPath(jfk, lax, 2469, 6).

transferTime(lax, 2).
transferTime(jfk, 2).
transferTime(cdg, 1).
transferTime(fco, 1).
transferTime(lju, 2).
transferTime(sin, 1).
transferTime(nrt, 1).

%(1)
connection(Start, Destination) :- flightPath(Start, Destination, _, _).
connection(Start, Destination) :- flightPath(Start, Transfer, _, _), connection(Transfer, Destination).


%(2)
flightTime(S, D, T, P) :- flightPath(S, D, _, T), append([S],[D], P).
flightTime(S, D, T, P) :- flightPath(S, Z, _, Time), flightTime(Z, D, T1, P1), transferTime(Z, Trans), T is Time + T1 + Trans,append([S], P1, P).



%(3)
head([H|_], H).
pathLength([H, T], D) :- flightPath(H, T, D, _).
pathLength([H|T], L):- pathLength(T, L1), head(T,F), flightPath(H, F, D, _), L is L1 + D.

%(4) 

%find shortest list
rightsmall(L, M, Min) :- length(L, LenL), length(M, LenM), LenL >LenM , Min = M.
leftsmall(L, M, Min) :- length(L, LenL), length(M, LenM), LenL < LenM , Min = L.
equal(L, M, Min) :- length(L, LenL), length(M, LenM), LenL = LenM , Min = L.

%preserves the shortest path so far and carries it over
findshortest([L], Short) :- Short = L.
findshortest([H|T], Short) :- findshortest(T, Short1), 
    (rightsmall(Short1, H, Short); leftsmall(Short1, H, Short); equal(Short1, H, Short)).
% print the path
shortestPath(S, D) :- findall(P, flightTime(S, D, _, P), L), findshortest(L, Shortest), print(Shortest).


