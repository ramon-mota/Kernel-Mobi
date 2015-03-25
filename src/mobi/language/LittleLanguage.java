package mobi.language;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;

import mobi.core.Mobi;
import mobi.core.common.Relation;
import mobi.core.concept.Tale;
import mobi.core.relation.CompositionRelation;
import mobi.core.relation.EquivalenceRelation;
import mobi.core.relation.GenericRelation;
import mobi.core.relation.InheritanceRelation;
import mobi.core.relation.SymmetricRelation;

public class LittleLanguage {
	
	public static void main(String args[]){
		LittleLanguage.carregaDominioLanguage();
	}
 
	public static Mobi carregaDominioLanguage (){
		
		Mobi mobi = null;
	    //File file = new File("C:/mobi/readFileInh.txt");
	    //File file = new File("C:/mobi/Eleicao.txt");
		File file = new File("C:/mobi/EleicaoGenericaArtigo.txt");
		FileInputStream fis = null;
		
		Mobi mobi2 = null;
		//File fileTypeRelation = new File ("C:/mobi/readFileTypeRelation.txt");
		//File fileTypeRelation = new File ("C:/mobi/readFileTale.txt");
		//File fileTypeRelation = new File("C:/mobi/readFileInhTale.txt");
         File fileTypeRelation = new File("C:/mobi/EleicaoEspecificaArtigo.txt");
         //File fileTypeRelation = new File("C:/mobi/readFileRelationsTale.txt");
		//File fileTypeRelation = new File("C:/mobi/readFileCompTale.txt");
		FileInputStream fisEspecificRelation = null;
		
		try {
				fis = new FileInputStream(file);
				Parser p = new Parser(fis);
				Expression exp = p.parse();
				mobi = exp.pupulatedDomain();
				System.out.println("\n ==============================================LOG==========================================================");
				
				int cont = 0; // N�O VAI FICAR AQUI
				Iterator<GenericRelation> it = mobi.getAllGenericRelations().values().iterator();
				while (it.hasNext()){
					cont++;
					GenericRelation genericRelation = it.next();
					Collection<Integer> possibilities = mobi.infereRelation(genericRelation);
					//System.out.println(mobi.getRelationPossibilitiesString(genericRelation) +"  "+ cont );
				    if(possibilities.contains(Relation.INHERITANCE)){
				    	
				    	if(possibilities.contains(Relation.EQUIVALENCE)){
				    		  System.out.println ("The Relation " + genericRelation.getUri().toUpperCase() + " can be an Equivalence Relation  an or Inheritance  Relation \n");
				    		//System.out.println("A Rela��o " + genericRelation.getUri().toUpperCase() + " pode ser uma Rela��o de Equival�ncia ou uma Rela��o de Heran�a \n");
				    	}else{
				    		System.out.println ("The Relation " + genericRelation.getUri().toUpperCase() + " can be an inheritance relation \n");	
				    		//System.out.println("A Rela��o " + genericRelation.getUri().toUpperCase() + " pode ser uma Rela��o de Heran�a \n");
				    	}
				    }
				    
				    if ((possibilities.contains(Relation.BIDIRECIONAL_COMPOSITION))
				    		&& (possibilities.contains(Relation.SYMMETRIC_COMPOSITION))
				    		&& (possibilities.contains(Relation.UNIDIRECIONAL_COMPOSITION))){

				    	System.out.println ("The Relation " + genericRelation.getUri().toUpperCase() + " can be a Relation of Unidirectional Composition \n or a Relation of Bidirectional Composition or a Relation of Symmetrical Composition \n");
				    	//System.out.println("A Rela��o " +  genericRelation.getUri().toUpperCase() + " pode ser uma Rela��o de Composi��o Unidirecional \n ou uma Rela��o de Composi��o Bidirecional ou uma Rela��o de Composi��o Sim�trica \n");
				    }
				    
				    System.out.println ("Cardinality of the group A is " + genericRelation.getCardinalityA() + " in relation to group B");
				    //System.out.println("Cardinalidade do grupo A � " + genericRelation.getCardinalityA() + " em rela��o ao grupo B");
				    System.out.println ("Cardinality of the group B is " + genericRelation.getCardinalityB() + " in relation to group A \n");
				    //System.out.println("Cardinalidade do grupo B � " + genericRelation.getCardinalityA() + " em rela��o ao grupo A \n");
				    
				   
				}
				
				System.out.println("==============================================LOG==========================================================");
				
				fisEspecificRelation = new FileInputStream(fileTypeRelation);
				ParserEspecificRelation parserEspecificRelation = new ParserEspecificRelation(fisEspecificRelation); 
				Expression expression = parserEspecificRelation.parse();
				mobi2 = expression.pupulatedDomain();

				System.out.println("==============================================HISTORIES OF THE DOMAIN==========================================================");
				if(mobi2.getAllTales().size() > 0){
					Iterator<Tale> iterator = mobi2.getAllTales().values().iterator();
					while(iterator.hasNext()){
						Tale tale = iterator.next();
						System.out.println("History Name: " + tale.getUri());
					  //System.out.println("Nome da Hist�ria: " + tale.getUri());
						System.out.println("Content of History: " + tale.getText());
					 //System.out.println("Conte�do da Hist�ria: " + tale.getText());
						System.out.println("History relations  " + tale.getUri() +":");
					 //System.out.println("Rela��o(�es) da Hist�ria " + tale.getUri() +":");
						
						
						Iterator<Relation> itr = tale.getRelations().iterator(); 
						 while(itr.hasNext()){
							 Relation relation = itr.next();
							if(relation instanceof CompositionRelation) {
								CompositionRelation compositionRelation = (CompositionRelation) relation;
								System.out.println("\r * " + compositionRelation.getUri());
							
							}else if(relation instanceof EquivalenceRelation){
								EquivalenceRelation equivalenceRelation = (EquivalenceRelation) relation;
								System.out.println("\r * " + equivalenceRelation.getUri());
							
							}else if(relation instanceof InheritanceRelation){
								InheritanceRelation inheritanceRelation  = 	(InheritanceRelation) relation;	
								System.out.println("\r * " + inheritanceRelation.getUri());
							
							}else{
								SymmetricRelation symmetricRelation = (SymmetricRelation) relation;	
								System.out.println("\r * " + symmetricRelation.getUri());
							}
						 }
						 System.out.println();
					}
				}else{
					//System.out.println("N�o h� hist�ria no dom�no modelado");
					System.out.println("There is no history in the domain modeled");
				}
				//System.out.println("==============================================HIST�RIAS DO DOM�NIO==========================================================");
				System.out.println("==============================================HISTORIES OF THE DOMAIN==========================================================");
				
				//System.out.println("==============================================RELA��ES DO DOM�NIO==========================================================");
				System.out.println("==============================================RELATIONS OF THE DOMAIN==========================================================");
				cont=0;
				if(mobi2.getAllCompositionRelations().size() > 0){
					//cont++;
					Iterator<CompositionRelation> iterator = mobi2.getAllCompositionRelations().values().iterator();
					while(iterator.hasNext()){
						CompositionRelation composition = iterator.next();
						//System.out.println("A" +cont+ "� Rela��o � uma Rela��o de Composi��o \n");
						//System.out.println("Rela��o: " + composition.getUri());
						System.out.println ("Relation: " + composition.getUri ());
						//System.out.println("Rela��o Bidirecional:  ida -> " + composition.getNameA() + " / volta -> " + composition.getNameB());
						System.out.println("Bidirectional Relation:  gone -> " + composition.getNameA() + " / comes back -> " + composition.getNameB());
//						System.out.println("Rela��o ClasseA: " + composition.getNameA() + " Rela��o ClasseB: " + composition.getNameB());
						
						//System.out.println("Cardinalidade do grupo A � " + composition.getCardinalityA() + " em rela��o ao grupo B");
						System.out.println ("Cardinality of the group A is " + composition.getCardinalityA() + " in relation to group B");
						System.out.println ("Cardinality of the group B is " + composition.getCardinalityB() + " in relation to group A \n");
						//System.out.println("Cardinalidade do grupo B � " + composition.getCardinalityB() + " em rela��o ao grupo A \n");
					}
					
				}
				if(mobi2.getAllEquivalenceRelations().size() > 0){
					cont++;
					Iterator<EquivalenceRelation> iterator = mobi2.getAllEquivalenceRelations().values().iterator();
					while(iterator.hasNext()){
						EquivalenceRelation equivalenceRelation = iterator.next();
						//System.out.println("Rela��o: " + equivalenceRelation.getUri());
						System.out.println("Relation: " + equivalenceRelation.getUri());
						System.out.println("Equivalence Relation");
						System.out.println ("Cardinality of the group A is " + equivalenceRelation.getCardinalityA() + " in relation to group B");
						//System.out.println("Cardinalidade do grupo A � " + equivalenceRelation.getCardinalityA() + " em rela��o ao grupo B");
						System.out.println ("Cardinality of the group B is " + equivalenceRelation.getCardinalityB() + " in relation to group A \n");
						//System.out.println("Cardinalidade do grupo B � " + equivalenceRelation.getCardinalityB() + " em rela��o ao grupo A \n");
					}
					
				}
				
				if(mobi2.getAllInheritanceRelations().size() > 0){
					cont++;
					Iterator<InheritanceRelation> iterator = mobi2.getAllInheritanceRelations().values().iterator();
					while(iterator.hasNext()){
						InheritanceRelation inheritanceRelation = iterator.next();
						//System.out.println("Rela��o: " + inheritanceRelation.getUri());
						System.out.println("Relation: " + inheritanceRelation.getUri());
						//System.out.println("Rela��o de Heran�a"); 
						System.out.println("Inheritance Relation "); 
						System.out.println ("Cardinality of the group A is " + inheritanceRelation.getCardinalityA() + " in relation to group B");
						//System.out.println("Cardinalidade do grupo A � " + inheritanceRelation.getCardinalityA() + " em rela��o ao grupo B");
						System.out.println ("Cardinality of the group B is " + inheritanceRelation.getCardinalityB() + " in relation to group A \n");
						//System.out.println("Cardinalidade do grupo B � " + inheritanceRelation.getCardinalityB() + " em rela��o ao grupo A \n");
					}
				}
				
				if(mobi2.getAllSymmetricRelations().size() > 0){
					cont++;
					Iterator<SymmetricRelation> iterator = mobi2.getAllSymmetricRelations().values().iterator();
					while(iterator.hasNext()){
						SymmetricRelation symmetricRelation = iterator.next();
						System.out.println("Relation: " + symmetricRelation.getUri());
						System.out.println("Symmetric Relation");
						System.out.println ("Cardinality of the group A is " + symmetricRelation.getCardinalityA() + " in relation to group B");
						//System.out.println("Cardinalidade do grupo A � " + symmetricRelation.getCardinalityA() + " em rela��o ao grupo B");
						System.out.println ("Cardinality of the group B is " + symmetricRelation.getCardinalityB() + " in relation to group A \n");
						//System.out.println("Cardinalidade do grupo B � " + symmetricRelation.getCardinalityB() + " em rela��o ao grupo A \n");
					}
				}
				System.out.println("==============================================RELATIONS OF THE DOMAIN==========================================================");
				
	  } catch (Exception e) {
	      e.printStackTrace();
	    
	    }
//	  return mobi;
	  return mobi2;
	}
}
