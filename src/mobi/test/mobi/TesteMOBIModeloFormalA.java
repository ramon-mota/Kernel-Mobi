package mobi.test.mobi;

import java.io.IOException;
import java.util.Collection;

import mobi.core.Mobi;
import mobi.core.common.Relation;
import mobi.core.concept.Class;
import mobi.core.concept.Instance;
import mobi.core.relation.CompositionRelation;
import mobi.core.relation.EquivalenceRelation;
import mobi.core.relation.GenericRelation;
import mobi.core.relation.InheritanceRelation;
import mobi.core.relation.SymmetricRelation;

public class TesteMOBIModeloFormalA {
	
	public static void main(String[] args) throws IOException,
	ClassNotFoundException {
		TesteMOBIModeloFormalA.carregaDominioTesteMOBIModeloFormalA();
	}
	
	public static Mobi carregaDominioTesteMOBIModeloFormalA(){
		
		
        //Instanciando a classe mobi, passando o nome do domínio como parâmetro. 
		 
		Mobi mobi = new Mobi("Nome_do_dominio");
		
		try{
			
		    //Criação das classes do domínio.
		 
			Class cClasseC = new Class ("cClasseC");
			Class cClasseD = new Class ("cClasseD");
			Class cClasseE = new Class ("cClasseE");
			Class cClasseF = new Class ("cClasseF");
			
			Class cClasseX = new Class ("cClasseX");
			Class cClasseB = new Class ("cClasseB");
			Class cClasseZ = new Class ("cClasseZ");
		
            //Criação das instâncias do domínio.
			
			Instance iInstanciaA1 = new Instance("iInstanciaA1"); 
			Instance iInstanciaA2 = new Instance("iInstanciaA2");
			Instance iInstanciaA3 = new Instance("iInstanciaA3");
			
			Instance iInstanciaB1 = new Instance("iInstanciaB1");
			Instance iInstanciaB2 = new Instance("iInstanciaB2");
			Instance iInstanciaB3 = new Instance("iInstanciaB3");
			
			Instance iInstanciaZ1 = new Instance("iInstanciaZ1");
			Instance iInstanciaZ2 = new Instance("iInstanciaZ2");
			Instance iInstanciaZ3 = new Instance("iInstanciaZ3");
			
	       
            //Adicionando as classes ao domínio.
			
			mobi.addConcept(cClasseC);
			mobi.addConcept(cClasseD);
			mobi.addConcept(cClasseE);
			mobi.addConcept(cClasseF);
			mobi.addConcept(cClasseX);
			mobi.addConcept(cClasseB);
			mobi.addConcept(cClasseZ);
			
            //Adicionando as instâncias do domínio.
			
			mobi.addConcept(iInstanciaA1);
			mobi.addConcept(iInstanciaA2);
			mobi.addConcept(iInstanciaA3);
			mobi.addConcept(iInstanciaB1);
			mobi.addConcept(iInstanciaB2);
			mobi.addConcept(iInstanciaB3);
			mobi.addConcept(iInstanciaZ1);
			mobi.addConcept(iInstanciaZ2);
			mobi.addConcept(iInstanciaZ3);
			
            //Adicionando cada instância a uma classe no domínio.
			
			mobi.isOneOf(iInstanciaA1, cClasseC);			
			mobi.isOneOf(iInstanciaA2, cClasseC);
			mobi.isOneOf(iInstanciaA3, cClasseC);
			
			mobi.isOneOf(iInstanciaA1, cClasseD);
			mobi.isOneOf(iInstanciaA2, cClasseD);
			
			mobi.isOneOf(iInstanciaA3, cClasseE);
			
			mobi.isOneOf(iInstanciaA2, cClasseF);
			mobi.isOneOf(iInstanciaA3, cClasseF);
			
			mobi.isOneOf(iInstanciaA2, cClasseX);
			mobi.isOneOf(iInstanciaA3, cClasseX);
			
			mobi.isOneOf(iInstanciaB1, cClasseB);
			mobi.isOneOf(iInstanciaB2, cClasseB);
			mobi.isOneOf(iInstanciaB3, cClasseB);
			
			mobi.isOneOf(iInstanciaZ1, cClasseZ);
			mobi.isOneOf(iInstanciaZ2, cClasseZ);
			mobi.isOneOf(iInstanciaZ3, cClasseZ);
			
            //Relação C & D
			
		    GenericRelation genericRelation = (GenericRelation)mobi.createGenericRelation("genericModeloFormalA");
			genericRelation.setClassA(cClasseC);
			genericRelation.setClassB(cClasseD);			
			genericRelation.addInstanceRelation(iInstanciaA1, iInstanciaA1);
			genericRelation.addInstanceRelation(iInstanciaA2, iInstanciaA2);
			genericRelation.addInstanceRelation(iInstanciaA3, null);

			genericRelation.processCardinality();
			mobi.addConcept(genericRelation);
			
            //Relação C & E
			
			GenericRelation genericRelation2 = (GenericRelation)mobi.createGenericRelation("genericModeloFormalA2");
			genericRelation2.setClassA(cClasseC);
			genericRelation2.setClassB(cClasseE);
			genericRelation2.addInstanceRelation(iInstanciaA1, null);
			genericRelation2.addInstanceRelation(iInstanciaA2, null);
			genericRelation2.addInstanceRelation(iInstanciaA3, iInstanciaA3);
			genericRelation2.processCardinality();
			mobi.addConcept(genericRelation2);
			
            //Relação C & F
			
			GenericRelation genericRelation3 = (GenericRelation)mobi.createGenericRelation("genericModeloFormalA3");
			genericRelation3.setClassA(cClasseC);
			genericRelation3.setClassB(cClasseF);
			
			genericRelation3.addInstanceRelation(iInstanciaA1,  null);
			genericRelation3.addInstanceRelation(iInstanciaA2, iInstanciaA2);
			genericRelation3.addInstanceRelation(iInstanciaA3, iInstanciaA3);
			genericRelation3.processCardinality();
			mobi.addConcept(genericRelation3);
			
            //Relação X & F
			
			GenericRelation genericRelation4 = (GenericRelation)mobi.createGenericRelation("genericModeloFormalA4");
			genericRelation4.setClassA(cClasseX);
			genericRelation4.setClassB(cClasseF);
					
			genericRelation4.addInstanceRelation(iInstanciaA2, iInstanciaA2);
			genericRelation4.addInstanceRelation(iInstanciaA3, iInstanciaA3);
			genericRelation4.processCardinality();
			mobi.addConcept(genericRelation4);
			
            //Relação C & B
			
			GenericRelation genericRelation5 = (GenericRelation)mobi.createGenericRelation("genericModeloFormalA5");
				genericRelation5.setClassA(cClasseC);
				genericRelation5.setClassB(cClasseB);
						
				genericRelation5.addInstanceRelation(iInstanciaA1, iInstanciaB1);
				genericRelation5.addInstanceRelation(iInstanciaA1, iInstanciaB2);
				genericRelation5.addInstanceRelation(iInstanciaA1, iInstanciaB3);
				genericRelation5.addInstanceRelation(iInstanciaA2, null);
				genericRelation5.addInstanceRelation(iInstanciaA3, null);
				
				genericRelation5.processCardinality();
				mobi.addConcept(genericRelation5);
				
            //Relação C & Z
				
			GenericRelation genericRelation6 = (GenericRelation)mobi.createGenericRelation("genericModeloFormalA6");
				genericRelation6.setClassA(cClasseC);
				genericRelation6.setClassB(cClasseZ);
							
				genericRelation6.addInstanceRelation(iInstanciaA1, iInstanciaZ1);
				genericRelation6.addInstanceRelation(iInstanciaA2, iInstanciaZ2);
				genericRelation6.addInstanceRelation(iInstanciaA3, iInstanciaZ3);
				
				genericRelation6.processCardinality();
				mobi.addConcept(genericRelation6);	
				
		
            //Inferindo as relações criadas
		
			Collection<Integer> possibilities =  mobi.infereRelation(genericRelation);
			Collection<Integer> possibilities2 = mobi.infereRelation(genericRelation2);
			Collection<Integer> possibilities3 = mobi.infereRelation(genericRelation3);
			Collection<Integer> possibilities4 = mobi.infereRelation(genericRelation4);
			Collection<Integer> possibilities5 = mobi.infereRelation(genericRelation5);
			Collection<Integer> possibilities6 = mobi.infereRelation(genericRelation6);



            //Definição genérica da Relação C & D como equivalência
			
		    if (possibilities.contains(Relation.EQUIVALENCE))  
			{
				EquivalenceRelation equivalenceRelation = (EquivalenceRelation)mobi.convertToEquivalenceRelation(genericRelation,"equals"); 
				 mobi.addConcept(equivalenceRelation);   
			}else //Choice
			{
				InheritanceRelation inheritanceRelation = (InheritanceRelation)mobi.convertToInheritanceRelation(genericRelation,"inheritance");
				 mobi.addConcept(inheritanceRelation);
			}
			
            //Definição genérica da Relação C & E como equivalência
		    
			if (possibilities2.contains(Relation.EQUIVALENCE))  
			{
				EquivalenceRelation equivalenceRelation = (EquivalenceRelation)mobi.convertToEquivalenceRelation(genericRelation2,"equals"); 
				 mobi.addConcept(equivalenceRelation);  
			}else //Choice
			{
				InheritanceRelation inheritanceRelation = (InheritanceRelation)mobi.convertToInheritanceRelation(genericRelation2,"inheritance");
				 mobi.addConcept(inheritanceRelation);
			}
			
            //Definição genérica da Relação C & F como equivalência			
			
			if (possibilities3.contains(Relation.EQUIVALENCE))  
			{
				EquivalenceRelation equivalenceRelation = (EquivalenceRelation)mobi.convertToEquivalenceRelation(genericRelation3,"equals"); 
				 mobi.addConcept(equivalenceRelation);   
			}else 
			{
				InheritanceRelation inheritanceRelation = (InheritanceRelation)mobi.convertToInheritanceRelation(genericRelation3,"inheritance");
				 mobi.addConcept(inheritanceRelation);
			}
			
            //Definição genérica da Relação X & F como equivalência	
			
			if (possibilities4.contains(Relation.EQUIVALENCE))  
			{
				EquivalenceRelation equivalenceRelation = (EquivalenceRelation)mobi.convertToEquivalenceRelation(genericRelation4,"equals"); 
				 mobi.addConcept(equivalenceRelation);   
			}else 
			{
				InheritanceRelation inheritanceRelation = (InheritanceRelation)mobi.convertToInheritanceRelation(genericRelation4,"inheritance");
				 mobi.addConcept(inheritanceRelation);
			}
			
            //Definição genérica da Relação de composição bidirecional C & B 
			
			if (possibilities5.contains(Relation.BIDIRECIONAL_COMPOSITION))  
			{
				CompositionRelation composition = (CompositionRelation)mobi.convertToBidirecionalCompositionRelationship(genericRelation5, "tem", "pertence");
				mobi.addConcept(composition);   
			}
			
            //Definição genérica da Relação de composição simétrica C & Z
			
			if (possibilities6.contains(Relation.SYMMETRIC_COMPOSITION))  
			{
				SymmetricRelation symmetric = (SymmetricRelation)mobi.convertToSymmetricRelation(genericRelation6, "fazFronteira");
				mobi.addConcept(symmetric);

			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mobi; //retorno do objeto mobi populado (instâncias, classes, gupos, relações e cardinalidades). 
	}

}
