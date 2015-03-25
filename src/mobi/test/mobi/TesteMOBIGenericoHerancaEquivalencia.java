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
		Mobi mobi = new Mobi("DominioGenerico"); //Criação do domínio da Ontologia 
		
		try
		{
			Class CDoscente1 	 = new Class("cDoscente1"); 
			mobi.addConcept(CDoscente1);
			
			Class CDoscente 	 = new Class("cDoscente1"); //Criação da classe Doscente
			Class cProfessor 	 = new Class("cProfessor"); //Criação da classe Professor
			
			Instance iThiago = new Instance("iThiago"); //Criação da instância Thiago
			Instance iDanisio = new Instance("iDanisio"); //Criação da classe Danisio 
			
			mobi.addConcept(CDoscente); //Adicionando a classe Doscente ao mobi 
			mobi.addConcept(cProfessor); //Adicionando a classe Pofessor ao mobi
			
			mobi.addConcept(iThiago); //Adicionando a instância Thiago ao mobi  
			mobi.addConcept(iDanisio); //Adicionando a instância Danisio ao mobi
			
			mobi.isOneOf(iThiago, CDoscente); //Adicionando a instância Thiago a classe Doscente
			mobi.isOneOf(iThiago, cProfessor); //Adicionando a instância Thiago a classe Professor
			mobi.isOneOf(iDanisio, cProfessor); //Adicionando a instância Danisio a classe Professor
			mobi.isOneOf(iDanisio, CDoscente); //Adicionando a instância Danisio a classe Doscente
			
			GenericRelation genericRelation = (GenericRelation)mobi.createGenericRelation("generic1"); 
			genericRelation.setClassA(cProfessor); //Adicionando a classe Professor ao grupo A da relação
			genericRelation.setClassB(CDoscente);  //Adicionando a classe Doscente ao grupo B da relação
			genericRelation.addInstanceRelation(iThiago, iThiago); //Criação da relação entre as instâncias de Professor e Doscente.    
			genericRelation.addInstanceRelation(iDanisio, iDanisio); //Criação da relação entre as instâncias de Professor e Doscente.
			genericRelation.processCardinality(); //Inferindo a carnidalidade da relação
			mobi.addConcept(genericRelation); //Adcionando a relação entre os dois grupos (A e B) ao MOBI
			
			Collection<Integer> possibilities = mobi.infereRelation(genericRelation); //Crianção de uma Collection a partir da relação inferida 
			
			if (possibilities.contains(Relation.EQUIVALENCE)) //Verificando se a relação foi classificada como Equivalência 
			{
				//Criação de uma relação de equivalência convertida a partir da relação inferida
				EquivalenceRelation equivalenceRelation = (EquivalenceRelation)mobi.convertToEquivalenceRelation(genericRelation,"equals"); 
				mobi.addConcept(equivalenceRelation); //Adicionando a relação de equivalência convertida ao MOBI  
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mobi; //retorno do objeto mobi populado (instâncias, classes, gupos, relação e cardinalidade). 
	}
}
