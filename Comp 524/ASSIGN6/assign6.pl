%List All Facts

%Course
course(compilers).
course(db).
course(distributed).
course(grammars).
course(languages).
course(networking).
course(os).
course(security).
course(software).
course(web).

%Prereqs
pre(compilers, languages).
pre(distributed, networking). 
pre(distributed, compilers). 
pre(languages, grammars). 
pre(networking, os).
pre(security, os).
pre(security, db).
pre(software, web).
pre(software, db).


%Time
time(compilers, 9).
time(db, 10).
time(distributed, 11).
time(grammars, 8).
time(languages, 12).
time(networking, 9).
time(os, 1).
time(security, 2).
time(software, 1).
time(web, 10).

%Effort
effort(compilers, 5).
effort(db, 2).
effort(distributed, 3).
effort(grammars, 1).
effort(languages, 1).
effort(networking, 3).
effort(os, 5).
effort(security, 4).
effort(software, 4).
effort(web, 2).

%Project
project(compilers, 1).
project(db, 1).
project(distributed, 0).
project(grammars, 0).
project(languages, 0).
project(networking, 0).
project(os, 0).
project(security, 1).
project(software, 1).
project(web, 1).

%check Prereqs recursively
isPre(X,Y) :-
	pre(X,Y); pre(X,Z), isPre(Z,Y).

%main function
plan(X, Y, Z) :-
	%if 3 courses specified, determine if valid
	%if 2 courses specified, show all possible additions
	%if 1 course specified, return all combinations you can add
	%if 0 courses specified, shwo all possible combinations

	%courses
	course(X),
	course(Y),
	course(Z),
	%prereqs (recursively)
	not(isPre(X,Y)),
	not(isPre(X,Z)),
	not(isPre(Y,X)),
	not(isPre(Y,Z)),
	not(isPre(Z,X)),
	not(isPre(Z,Y)),
	%time
	time(X, XT),
	time(Y, YT),
	time(Z, ZT),
	not(XT = YT),
	not(XT = ZT),
	not(YT = ZT),
	%effort
	effort(X, XE),
	effort(Y, YE),
	effort(Z, ZE),
	(XE + YE + ZE =< 10),
	%project
	project(X, XP),
	project(Y, YP),
	project(Z, ZP),
	(XP + YP + ZP =< 1).

