package mobi.test.mobi;

import java.io.IOException;
import java.util.Collection;

import mobi.core.Mobi;
import mobi.core.common.Relation;
import mobi.core.concept.Class;
import mobi.core.concept.Instance;
import mobi.core.relation.EquivalenceRelation;
import mobi.core.relation.GenericRelation;

public class TesteMOBIGenericoHerancaEquivalencia {
	
	public static void main(String[] args) throws IOException,
	ClassNotFoundException {
		TesteMOBIGenericoHerancaEquivalencia.carregaDominioGenerico();
	}

	public static Mobi carregaDominioGenerico() {
		Mobi mobi = new Mobi("DominioGenerico"); //Cria��o do dom�nio da Ontologia 
		
		try
		{
			Class CDoscente1 	 = new Class("cDoscente1"); 
			mobi.addConcept(CDoscente1);
			
			Class CDoscente 	 = new Class("cDoscente1"); //Cria��o da classe Doscente
			Class cProfessor 	 = new Class("cProfessor"); //Cria��o da classe Professor
			
			Instance iThiago = new Instance("iThiago"); //Cria��o da inst�ncia Thiago
			Instance iDanisio = new Instance("iDanisio"); //Cria��o da classe Danisio 
			
			mobi.addConcept(CDoscente); //Adicionando a classe Doscente ao mobi 
			mobi.addConcept(cProfessor); //Adicionando a classe Pofessor ao mobi
			
			mobi.addConcept(iThiago); //Adicionando a inst�ncia Thiago ao mobi  
			mobi.addConcept(iDanisio); //Adicionando a inst�ncia Danisio ao mobi
			
			mobi.isOneOf(iThiago, CDoscente); //Adicionando a inst�ncia Thiago a classe Doscente
			mobi.isOneOf(iThiago, cProfessor); //Adicionando a inst�ncia Thiago a classe Professor
			mobi.isOneOf(iDanisio, cProfessor); //Adicionando a inst�ncia Danisio a classe Professor
			mobi.isOneOf(iDanisio, CDoscente); //Adicionando a inst�ncia Danisio a classe Doscente
			
			GenericRelation genericRelation = (GenericRelation)mobi.createGenericRelation("generic1"); 
			genericRelation.setClassA(cProfessor); //Adicionando a classe Professor ao grupo A da rela��o
			genericRelation.setClassB(CDoscente);  //Adicionando a classe Doscente ao grupo B da rela��o
			genericRelation.addInstanceRelation(iThiago, iThiago); //Cria��o da rela��o entre as inst�ncias de Professor e Doscente.    
			genericRelation.addInstanceRelation(iDanisio, iDanisio); //Cria��o da rela��o entre as inst�ncias de Professor e Doscente.
			genericRelation.processCardinality(); //Inferindo a carnidalidade da rela��o
			mobi.addConcept(genericRelation); //Adcionando a rela��o entre os dois grupos (A e B) ao MOBI
			
			Collection<Integer> possibilities = mobi.infereRelation(genericRelation); //Crian��o de uma Collection a partir da rela��o inferida 
			
			if (possibilities.contains(Relation.EQUIVALENCE)) //Verificando se a rela��o foi classificada como Equival�ncia 
			{
				//Cria��o de uma rela��o de equival�ncia convertida a partir da rela��o inferida
				EquivalenceRelation equivalenceRelation = (EquivalenceRelation)mobi.convertToEquivalenceRelation(genericRelation,"equals"); 
				mobi.addConcept(equivalenceRelation); //Adicionando a rela��o de equival�ncia convertida ao MOBI  
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mobi; //retorno do objeto mobi populado (inst�ncias, classes, gupos, rela��o e cardinalidade). 
	}
}
