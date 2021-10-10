Tiangson, Cyrena V.


<Program> -> Begin<body>End
<body> -> <statement> | <statement><body>
<statement> -> <var> = <expr>;
<expr> -> (<expr>)| <var> * <var> | <expr> + <expr> | <var>
<var> -> A | B | C 



Derivation:
 <Program>	-> Begin <body> End
			-> Begin <statement> <body> End
			-> Begin <var> = <expr>; <body> End
			-> Begin B = <expr>; <body> End
			-> Begin B = <expr> + <expr>; <body> End
			-> Begin B = <var>*<var> + <expr>; <body> End
			-> Begin B = C * A + <expr>; <body> End
			-> Begin B = C * A + <var> * <var>; <body> End
			-> Begin B = C * A + B * B; <body> End
			-> Begin B = C * A + B * B; <statement> End
			-> Begin B = C * A + B * B; <var> = <expr>; End
			-> Begin B = C * A + B * B; C = <expr>; End
			-> Begin B = C * A + B * B; C = ( <expr> ); End
			-> Begin B = C * A + B * B; C = ( <expr> + <expr> ); End
			-> Begin B = C * A + B * B; C = ( <var> * <var> + <expr> ); End
			-> Begin B = C * A + B * B; C = ( B * C + <expr> ); End
			-> Begin B = C * A + B * B; C = ( B * C + <var> ); End
			-> Begin B = C * A + B * B; C = ( B * C + A ); End