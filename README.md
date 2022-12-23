# Simple-Lexical-Analyzer

Lexical Analysis is the first phase of a compiler. It converts a source code written in a
high level program language into a sequence of lexemes along with their tokens.The
task in this assignment is to write a simplified lexical analyzer that extracts lexemes
from an input source code written in Java and determines their tokens as specified
below.<br />

Precisely, the first column of the following table shows the set of lexemes you need to
recognize while the right shows their tokens.<br />

Lexeme Token<br />
for FOR_STATEMENT<br />
( LPARANT<br />
) RPARANT<br />
int INT_TYPE<br />
char CHAR_TYPE<br />
= ASSIGNM<br />
; SEMICOLON<br />
> GREATER<br />
< LESS<br />
>= GRE_EQ<br />
<= LESS_EQ<br />
{ LCURLYB<br />
} RCURLYB<br />
return RETURN_STMT<br />
- SUBT<br />
/ DIV<br />
* MULT<br />
+ ADD<br />
identifier An identifier consists of a single letter<br />
integer constant INT_LIT<br />

Sample input file and the output of the lexical analyzer:<br />

Input:<br />

for (int i = 0; i < 10; i=i+1)<br />
{<br />
a = f;<br />
char ch1;<br />
}<br />
return 0;<br />

Output:<br />

Next token is FOR_STATEMENT Next lexeme is for<br />
Next token is LPARANT Next lexeme is (<br />
Next token is INT_TYPE Next lexeme is int<br />
Next token is identifier Next lexeme is i<br />
Next token is ASSIGNM Next lexeme is =<br />
Next token is INT_LIT Next lexeme is 0<br />
Next token is SEMICOLON Next lexeme is ;<br />
Next token is identifier Next lexeme is i<br />
Next token is LESS Next lexeme is <<br />
Next token is INT_LIT Next lexeme is 10<br />
Next token is SEMICOLON Next lexeme is ;<br />
Next token is identifier Next lexeme is i<br />
Next token is ASSIGNM Next lexeme is =<br />
Next token is identifier Next lexeme is i<br />
Next token is ADD Next lexeme is +<br />
Next token is INT_LIT Next lexeme is 1<br />
Next token is RPARANT Next lexeme is )<br />
Next token is LCURLYB Next lexeme is {<br />
Next token is identifier Next lexeme is a<br />
Next token is ASSIGNM Next lexeme is =<br />
Next token is identifier Next lexeme is f<br />
Next token is SEMICOLON Next lexeme is ;<br />
Next token is CHAR_TYPE Next lexeme is char<br />
Next token is identifier Next lexeme is c<br />
Next token is SEMICOLON Next lexeme is ;<br />
Next token is RCURLYB Next lexeme is }<br />
Next token is RETURN_STMT Next lexeme is return<br />
Next token is INT_LIT Next lexeme is 0<br />
Next token is SEMICOLON Next lexeme is ;<br />

If you want to know whether a particular source code can be given as an input to your
program, please try it in a Java compiler. If it is accepted by Java then it can be given
as an input. In any case, make sure you use only lexemes given in the table.<br />

Your program should also check for errors. However, your first goal is to make sure
that if a valid source code is given to your program as an input then it is correctly
analyzed by your program.<br />

Error types that could exist:<br />

● Unknown operator: This occurs when an operator other than the ones given in
the table is scanned. For example, !, @, #, $, % are all unknown operators<br />
● Unknown identifier: This occurs when an identifier consisting of more than a
single char exists in the input. For example, ab, ab1, xyz, while, do are
unknown identifiers<br />

Sample Run:<br />

Your code will take input and output files from the console as given in the following
example where the source code, input and output files are named
hw1_firstname_lastname.java, input.txt, and output.txt,
respectively:<br />

java hw1_firstname_lastname.java input.txt output1.txt<br />
