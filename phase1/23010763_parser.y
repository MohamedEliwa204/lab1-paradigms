%{
    #include<stdio.h>
    #include<stdlib.h>
    int yylex();
    void yyerror(const char *s);
%}
%union{
    int ival;
}

%token <ival>NUMBER
%token ADD SUB MULTIPLY DIV LPAREN RPAREN NEWLINE

%type <ival>exp

%left ADD SUB
%left MULTIPLY DIV
%%
input: 
 | input line;


line : NEWLINE | 
exp NEWLINE {printf("%d\n", $1);}|
error NEWLINE {yyerrok;};

exp: NUMBER { $$ = $1; } |
exp ADD exp  {$$ = $1 + $3;}|
exp SUB exp {$$ = $1 - $3;}|
exp MULTIPLY exp {$$ = $1 * $3;}|
exp DIV exp {
    if($3 == 0){
    yyerror("Division by 0!");
    $$ = 0;}
    else{
        $$ = $1 / $3;
    }
    }|
LPAREN exp RPAREN {$$ = $2;};
 
%%


void yyerror(const char *s) {
    fprintf(stderr, "Error: %s\n", s);
}

int main(){
    printf("Enter an integer:\n");
    yyparse();
    return 0;
}