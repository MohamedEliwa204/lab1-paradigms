%{
#include "parser.tab.h"
#include <stdlib.h>
%}

%%
[0-9]+ {yylval.ival = atoi(yytext);
        return NUMBER;}

"+"     {
    return ADD;
}

"-" {
    return SUB;
}

"*" {
    return MULTIPLY;
}

"/" {
    return DIV;
}

"(" {
    return LPAREN;
}

")" {
    return RPAREN;
}

\n {
    return NEWLINE;
}

[ \t] {

}

. {
    printf("unknown character: %s!", yytext);
}

%%
int yywrap(){
    return 1;
}
