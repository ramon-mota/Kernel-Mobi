package mobi.test.mobi;

import java.io.IOException;

import mobi.core.Mobi;
import mobi.core.common.Relation;
import mobi.core.concept.Class;
import mobi.core.concept.Instance;

public class TesteMOBIEleicao2 {

	public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {
		TesteMOBIEleicao.carregaDominioEleicao();
	}

	public static Mobi carregaDominioEleicao() throws Exception {
		Mobi mobi = new Mobi("Eleicao");

		Class cPartido = new Class("Partido");
		Class cGrupoPolitico  = new Class("GrupoPolitico");
//		Class cCandidato  = new Class("Candidato");
//		Class cPrefeito =  new Class("Prefeito");
		
		Instance iPartidoA = new Instance("PartidoA");
		Instance iPartidoB = new Instance("PartidoB");
		Instance iPartidoC = new Instance("PartidoC");
		
//		Instance iViceA = new Instance("ViceA");
//		Instance iViceB = new Instance("ViceB");
//		Instance iPrefeitoA = new Instance("PrefeitoA");
//		Instance iPrefeitoB = new Instance("PrefeitoB");
						
		try {
//			mobi.addConcept(cPartido);
//			mobi.addConcept(cGrupoPolitico);
//			
//			mobi.isOneOf(iPartidoA, cPartido);
//			mobi.isOneOf(iPartidoB,  cPartido);
//			mobi.isOneOf(iMacaco2,  cPartido);
//			mobi.isOneOf(iMacaco,  cMacaco);
//			
//			Relation rTemFilho = mobi.createUnidirecionalCompositionRelationship("TemFilho");
//			rTemFilho.setClassA(cPessoa);
//			rTemFilho.setClassB(cPessoa);
//			rTemFilho.addInstanceRelation(iThiago, iLeticia);
//			rTemFilho.processCardinality();
//			mobi.addConcept(rTemFilho);
//			
//			Relation rTemFilho2 = mobi.createUnidirecionalCompositionRelationship("TemFilho");
//			rTemFilho2.setClassA(cMacaco);
//			rTemFilho2.setClassB(cMacaco);
//			rTemFilho2.addInstanceRelation(iMacaco, iMacaco2);
//			rTemFilho2.processCardinality();
//			mobi.addConcept(rTemFilho2);
			
		} catch (Exception e) {
			throw e;
		}
		return mobi;

	}
}