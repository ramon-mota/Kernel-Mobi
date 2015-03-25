package mobi.language;

import java.io.InputStream;
import mobi.core.relation.GenericRelation;

/**
  * @author Ramon Mota
 */

public class Parser {
	
	private Lexer lexer;
	private String tokenString;
	private int token;
	private CreateDomain mobiLanguage;
	
	public Parser(InputStream in) {
		this.lexer = new Lexer(in);
		this.mobiLanguage = new CreateDomain("DefaultDomain");
		this.tokenString = "";
	}
	
	public Expression parse() throws Exception {
		nextToken();
		Expression exp = expression();
		//expect( Lexer.EOF, Lexer.EOL);
		return exp;
	}

	private Expression expression() throws Exception {
		
		//Enquanto não achar o ponto e virgula fassa
		while(this.token != Lexer.FINAL_RELATION){
		
			if (this.token == Lexer.CREATE_RELATION){ 
					this.createRelation();
					this.mobiLanguage.getGenericRelation().processCardinality();
					this.mobiLanguage.getMobi().addConcept(this.mobiLanguage.getGenericRelation());
			}
			nextToken();
			
		}
		return this.mobiLanguage;
	}
	
	private void createRelation()throws Exception{
		
	if (this.token == Lexer.CREATE_RELATION){	
		
		nextToken(); //Palavra reservada CREATE_RELATION
		nextToken(); //Cracter abre chave;
		nextToken(); //Nome da relação 
		
		this.mobiLanguage.setGenericRelation((GenericRelation) this.mobiLanguage.getMobi().createGenericRelation(this.tokenString));
		
		nextToken(); //Caracter virgula
		nextToken(); //Fim de linha
		
		nextToken(); //ClASSA
		nextToken(); //Caracter abre parêntese
		nextToken(); //Nome da CLASSA		
		this.mobiLanguage.createClassA(this.tokenString);
		
        nextToken(); //Caracter fecha parêntese 
		nextToken(); //Caracter virgula
        nextToken(); //Fim de linha

		nextToken(); //ClASSB
		nextToken(); //Caracter abre parêntese
		nextToken(); //Nome da CLASSB
     	this.mobiLanguage.createClassB(this.tokenString);
     	
     	this.mobiLanguage.getGenericRelation().setClassA(this.mobiLanguage.getClassA());
     	this.mobiLanguage.getGenericRelation().setClassB(this.mobiLanguage.getClassB());
		
		nextToken(); //Caracter fecha parêntese 
		nextToken(); //Caracter virgula
	    nextToken(); //Fim de linha
	    nextToken(); //AddRelation
	}
	if(this.token == Lexer.ADDINSTACE_RELATION) { //token igual a addRelation
		
		while(this.token == Lexer.ADDINSTACE_RELATION){
			   
			nextToken(); // Caracter abre parêntese após o add Relation
			nextToken(); // Nome da InstanciaA ou menos
			 
			String nameInstanceA = this.tokenString;
			this.mobiLanguage.createInstanceA(nameInstanceA);
			if(this.mobiLanguage.getInstanceA() != null){	
				this.mobiLanguage.createIsOneOfA(nameInstanceA);
			}
		    
			nextToken(); //Caracter virgula
			nextToken(); //Nome da InstanciaB 
		     
			String nameInstanceB = this.tokenString;
			this.mobiLanguage.createInstanceB(nameInstanceB);
			if(this.mobiLanguage.getInstanceB() != null){	
				this.mobiLanguage.createIsOneOfB(nameInstanceB);
			}
			
			nextToken(); //Caracter fecha parêntese após o add Relation
			nextToken();
		     
			if(this.tokenString.equals(",")){ //Há mais addRelation
					nextToken(); //fim de linha
					nextToken(); //addRalation
			}
			
			this.mobiLanguage.addGenericRelation();
		   }   
		}
	}
	
	private void nextToken() throws Exception {
		this.token = lexer.nextToken();
		this.tokenString = Lexer.tokenCurrent;
		System.out.println("Token atual -> " + this.tokenName(this.token));
	}

	private String tokenName(int token2) {
		String token = "";
		switch(token2) {
		case Lexer.EOF:
			token = "fim de arquivo";
			break;
			
		case Lexer.FINAL_RELATION:
			token = "fim de linha";
			break;
			
		default:
			token = "default";
			break;
		}
		return token;
	}
}
