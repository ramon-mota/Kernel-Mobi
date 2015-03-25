package mobi.language;

import java.io.InputStream;
import mobi.core.concept.Tale;
import mobi.core.relation.GenericRelation;

/**
 * @author Ramon
 */
public class ParserEspecificRelation {
	
	private Lexer lexer = null;
	private String tokenString = "";
	private int token;
	private CreateDomain mobiLanguage;
	
	public ParserEspecificRelation(InputStream in) {
		this.lexer = new Lexer(in);
		this.mobiLanguage = new CreateDomain("DefaultDomain");
	}
	
	public Expression parse() throws Exception {
		nextToken();
		Expression exp = expression();
		//expect( Lexer.EOF, Lexer.EOL);
		return exp;
	}


	private Expression expression() throws Exception {
		
		//Enquanto n�o achar o ponto e virgula fassa
		while(this.token != Lexer.FINAL_RELATION){
			
			if(this.token == Lexer.CREATE_HISTORY){
				this.createTale();
			}
			if (this.token == Lexer.CREATE_RELATION){ 
				this.createRelation();
				this.mobiLanguage.addTypeRelation(); //adicionando a rela��o criada ao mobi				 
			}
			nextToken();
			
		}
		return this.mobiLanguage;
	}
	
	private void createTale() throws Exception{
		nextToken(); //Caracter abre par�nteses
		nextToken(); //Caracter abre aspas
		nextToken(); //Nome da hist�ria
		System.out.println(this.tokenString);
		
		this.mobiLanguage.setTale(new Tale(this.tokenString));
		
		nextToken(); //Caracter fecha aspas
		nextToken(); //Caracter virgula
		nextToken(); //Caracter abre aspas, inicio da hist�ria 
		nextToken(); //Primeira palavra da hist�ria
		
		/*Percorre todas as palavras da hist�ria digitada at� encontrar o 
		tokenString igual ao caracter fecha aspas*/
		
		while (!this.tokenString.equals("\"")){   
			if(!this.tokenString.equals("fim de linha")){
				if(this.mobiLanguage.getTale().getText()!= null){ //J� foi setada alguma palavra da hist�ria
					this.mobiLanguage.getTale().setText(this.mobiLanguage.getTale().getText() + " " + this.tokenString); //Setando a hist�ria criada
				}else{
					//N�o foi setada nenhuma palavra da hist�ria.
					this.mobiLanguage.getTale().setText(this.tokenString);
				}
					
			}else{ //tokenString = final de linha, foi pulada uma linha na esrita da hist�ria    
				this.mobiLanguage.getTale().setText(this.mobiLanguage.getTale().getText() + "\n" ); //Insere-se um quebra de linha no texto  
			}
			
			nextToken(); //Proxima palavra da hist�ria
		}
		
		nextToken(); //Caracter fecha par�ntese
		nextToken(); //Caracter virgula
		nextToken(); //Caracter fim de linha
		nextToken(); //Fim de linha(espa�o entre a senten�a HISTORY e o CREATE_RELATION)
			
		this.mobiLanguage.getMobi().addConcept(this.mobiLanguage.getTale()); //adiciona a hist�ria criada ao mobi
	}
	
	private void createRelation()throws Exception{
		
		if (this.token == Lexer.CREATE_RELATION){	
			
			nextToken(); //Palavra reservada CREATE_RELATION
			nextToken(); //Cracter abre chave;
			nextToken(); //Nome da rela��o 
			System.out.println("Nome relacao:"+this.tokenString);
			
			this.mobiLanguage.setGenericRelation((GenericRelation) this.mobiLanguage.getMobi().createGenericRelation(this.tokenString));
			
			nextToken(); //Caracter virgula
			nextToken(); //Fim de linha
			
			nextToken(); //Tipo da rela��o
			nextToken(); //Caracter abre par�nteses
			nextToken(); //Nome do tipo da rela��o
			this.mobiLanguage.setTypeNameRelation(this.tokenString.toUpperCase());
			
			nextToken(); //Caracter virgula
			nextToken(); //nome da propriedade de ida
			this.mobiLanguage.setPropertyA(this.tokenString);
			
			nextToken(); //Caracter fecha par�ntese ou virgula
			if(this.tokenString.equals(",")){ //Composi��o � sim�trica ou � bidirecional
				nextToken(); //nome da propriedade de volta
				this.mobiLanguage.setPropertyB(this.tokenString);
				nextToken(); //Caracter fecha par�ntese 
			}
			nextToken(); //Caracter virgula
			nextToken(); //Fim de linha
			
			nextToken(); //Histoty ou ClassA
			if(!this.tokenString.equals("CLASSA")){ //Foi inserida a hist�ria da rela��o
				nextToken(); //Caracter abre par�ntese
				nextToken(); //Caracter abre aspas
				nextToken(); //Nome da hist�ria da rela��o 
				this.mobiLanguage.setNameTaleRelation(this.tokenString);
				
				nextToken(); //Caracter fecha aspas
				nextToken(); //Caracter fecha par�ntese
				nextToken(); //Caracter virgula
				nextToken(); //Fim de linha
				nextToken(); //ClASSA
			}
			
			nextToken(); //Caracter abre par�ntese
			nextToken(); //Nome da ClASSA		
			System.out.println("Nome ClasseA:"+this.tokenString);
			this.mobiLanguage.createClassA(this.tokenString);
			
	        nextToken(); //Caracter fecha par�ntese 
			nextToken(); //Caracter virgula
	        nextToken(); //Fim de linha

			nextToken(); //ClASSB
			nextToken(); //Caracter abre par�ntese
			nextToken(); //Nome da ClASSB
			
	     	this.mobiLanguage.createClassB(this.tokenString);
	     	this.mobiLanguage.getGenericRelation().setClassA(this.mobiLanguage.getClassA());
	     	this.mobiLanguage.getGenericRelation().setClassB(this.mobiLanguage.getClassB());
			
			nextToken(); //Caracter fecha par�ntese 
			nextToken(); //Caracter virgula
		    nextToken(); //Fim de linha
		    nextToken(); //ADDINSTACE_RELATION
		}
		if(this.token == Lexer.ADDINSTACE_RELATION) { //token igual a addRelation
			System.out.println("Token AddRrelation pan +" + tokenString); 
			
			while(this.token == Lexer.ADDINSTACE_RELATION){
				   
				nextToken(); //Caracter abre par�ntese ap�s o add Relation
				nextToken(); //Nome da InstanciaA ou caracter menos
				 
				String nameInstanceA = this.tokenString;
				this.mobiLanguage.createInstanceA(nameInstanceA);
				if(this.mobiLanguage.getInstanceA() != null){	
					this.mobiLanguage.createIsOneOfA(nameInstanceA);
				}
			    
				nextToken(); //Caracter virgula
				nextToken(); //Nome da InstanciaB ou caracter menos
			     
				String nameInstanceB = this.tokenString;
				this.mobiLanguage.createInstanceB(nameInstanceB);
				if(this.mobiLanguage.getInstanceB() != null){	
					this.mobiLanguage.createIsOneOfB(nameInstanceB);
				}
			     
				nextToken(); //Caracter fecha par�ntese ap�s o add Relation
				nextToken();
				System.out.println("TOOOKENNNNN" + this.tokenString);
			     
				if(this.tokenString.equals(",")){ //H� mais addRelation
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
