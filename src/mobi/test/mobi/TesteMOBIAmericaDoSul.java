package mobi.test.mobi;

import java.io.IOException;

import mobi.core.Mobi;
import mobi.core.common.Relation;
import mobi.core.concept.Class;
import mobi.core.concept.Instance;

public class TesteMOBIAmericaDoSul {

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		TesteMOBIAmericaDoSul.carregaDominioAmericaDoSul();
	}

	public static Mobi carregaDominioAmericaDoSul() {
		
//		Instanciando a classe mobi, passando o nome do domínio como parâmetro.
		Mobi mobi = new Mobi("AmericaDoSul");
		
//	    Criação das classes do domínio.
		Class cPais      = new Class("Pais");
		Class cCapital   = new Class("Capital");
		Class cRegiao    = new Class("Regiao");
		Class cVegetacao = new Class("Vegetacao");
		
//	    Criação das instâncias do domínio.
		Instance iBrasil    = new Instance("Brasil");
		Instance iChile     = new Instance("Chile");
		Instance iArgentina = new Instance("Argentina");
		
		Instance iBrasilia    = new Instance("Brasilia");
		Instance iSantiago    = new Instance("Santiago");
		Instance iBuenosAires = new Instance("BuenosAires");

		Instance iOeste = new Instance("Oeste");
		Instance iSul   = new Instance("Sul");
		Instance iLeste = new Instance("Leste");
		
		Instance iSavana    = new Instance("Savana");
		Instance iSemiArido = new Instance("SemiArido");
		Instance iTropical  = new Instance("Tropical");
		
		try {
//		    Adicionando as instâncias do domínio.
			
			mobi.addConcept(iBrasil);
			mobi.addConcept(iChile);
			mobi.addConcept(iArgentina);
			
			mobi.addConcept(iBrasilia);
			mobi.addConcept(iSantiago);
			mobi.addConcept(iBuenosAires);
			
			mobi.addConcept(iOeste);
			mobi.addConcept(iSul);
			mobi.addConcept(iLeste);
			
			mobi.addConcept(iSavana);
			mobi.addConcept(iSemiArido);
			mobi.addConcept(iTropical);
			
//		    Adicionando as classes ao domínio.
			
			mobi.addConcept(cPais);
			mobi.addConcept(cRegiao);
			mobi.addConcept(cCapital);
			mobi.addConcept(cVegetacao);
			
//			Adicionando cada instância a uma classe no domínio.			
			
			mobi.isOneOf(iBrasil,    cPais);
			mobi.isOneOf(iChile,     cPais);
			mobi.isOneOf(iArgentina, cPais);
			
			mobi.isOneOf(iOeste, cRegiao);
			mobi.isOneOf(iSul,   cRegiao);
			mobi.isOneOf(iLeste, cRegiao);
			
			mobi.isOneOf(iBrasilia,    cCapital);
			mobi.isOneOf(iSantiago,    cCapital);
			mobi.isOneOf(iBuenosAires, cCapital);
			
			mobi.isOneOf(iSavana,    cVegetacao);
			mobi.isOneOf(iSemiArido, cVegetacao);
			mobi.isOneOf(iTropical,  cVegetacao);
			
//            Definição de uma relação de composição bidirecional tem/pertence.		
			Relation rTemCapital = mobi.createBidirecionalCompositionRelationship("tem", "pertence");
			rTemCapital.setClassA(cPais);
			rTemCapital.setClassB(cCapital);
			rTemCapital.addInstanceRelation(iBrasil,    iBrasilia);
			rTemCapital.addInstanceRelation(iChile,     iSantiago);
			rTemCapital.addInstanceRelation(iArgentina, iBuenosAires);
			rTemCapital.processCardinality();
			mobi.addConcept(rTemCapital);

//          Definição de uma relação de composição bidirecional possui/esta_contido.
			Relation rEstaContido = mobi.createBidirecionalCompositionRelationship("possui", "esta_contido");
			rEstaContido.setClassA(cRegiao);
			rEstaContido.setClassB(cPais);
			rEstaContido.addInstanceRelation(iOeste, iChile);
			rEstaContido.addInstanceRelation(iSul,   iArgentina);
			rEstaContido.addInstanceRelation(iLeste, iBrasil);
			rEstaContido.addInstanceRelation(iSul, iBrasil);
			rEstaContido.addInstanceRelation(iLeste, iArgentina);
			
			rEstaContido.processCardinality();
			mobi.addConcept(rEstaContido);
			
//          Definição de uma relação de composição bidirecional contem/esta_na.
			Relation rTemTipoVegetacao = mobi.createBidirecionalCompositionRelationship("contem", "esta_na");
			rTemTipoVegetacao.setClassA(cRegiao);
			rTemTipoVegetacao.setClassB(cVegetacao);
			rTemTipoVegetacao.addInstanceRelation(iOeste, iSavana);
			rTemTipoVegetacao.addInstanceRelation(iOeste, iSemiArido);
			rTemTipoVegetacao.addInstanceRelation(iSul,   iSavana);
			rTemTipoVegetacao.addInstanceRelation(iLeste, iSemiArido);
			rTemTipoVegetacao.addInstanceRelation(iLeste, iTropical);
			rTemTipoVegetacao.processCardinality();
			mobi.addConcept(rTemTipoVegetacao);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return mobi;//retorno do objeto mobi populado (instâncias, classes, gupos, relações e cardinalidades). 
	}
}