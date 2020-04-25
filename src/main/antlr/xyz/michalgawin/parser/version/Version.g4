grammar Version;

options {
    language = Java;
}

@header { package xyz.michalgawin.parser.version; }

versionrange :
    lowerversionlimit ',' upperversionlimit ;

lowerversionlimit :
    (LEFTBRACKET? VERSION) | LEFTBRACKET ;

upperversionlimit :
    (VERSION RIGTHBRACKET?) | RIGTHBRACKET ;

version :
    LEFTBRACKET (VERSION) RIGTHBRACKET | (VERSION) ;


VERSION :
    (FOURVERSION) PLUS? |
    (THREEVERSION) DASHPLUS? |
    (TWOVERSION | ONEVERSION) DOTPLUS? ;

ONEVERSION : NUMBER ;
TWOVERSION : NUMBER '.' NUMBER ;
THREEVERSION : NUMBER '.' NUMBER '.' NUMBER ;
FOURVERSION : NUMBER '.' NUMBER '.' NUMBER '-' ALPHANUM ;

LEFTBRACKET : '[' | '(' ;
RIGTHBRACKET : ']' | ')' ;

fragment ALPHANUM : [a-zA-Z0-9]+ ;
fragment NUMBER : DIGIT+ ;
fragment DIGIT : [0-9] ;
fragment PLUS : '+' ;
fragment DOTPLUS : ('.'? '+') ;
fragment DASHPLUS : ('-'? '+') ;
