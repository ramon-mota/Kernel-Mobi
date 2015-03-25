package mobi.language;

import mobi.core.Mobi;
import mobi.core.common.Relation;
import mobi.core.concept.Class;
import mobi.core.concept.Instance;
import mobi.core.concept.Tale;
import mobi.core.relation.CompositionRelation;
import mobi.core.relation.EquivalenceRelation;
import mobi.core.relation.GenericRelation;
import mobi.core.relation.InheritanceRelation;
import mobi.core.relation.SymmetricRelation;
import mobi.exception.ExceptionURI;

/**
 * @author Ramon
 */
public class CreateDomain extends Expression {
  
	private Mobi mobi;
	private Class classA;
	private Class classB;
	private Instance instanceA;
	private Instance instanceB;
	private GenericRelation genericRelation;
	private String typeNameRelation;
	private String propertyA;
	private String propertyB;
	private Tale tale;
	private String nameTaleRelation;
	
	public CreateDomain(String nameDomain) {
		this.mobi = new Mobi(nameDomain);
	}
	
	public Mobi pupulatedDomain() throws Exception {
		return this.mobi;
	}
	
	public void createClassA(String uri)throws Exception {
		 if(mobi.getAllClasses().containsKey(uri)){
			 this.classA = this.mobi.getClass(uri);
		 }else{
			 this.classA = new Class(uri);
			 this.mobi.addConcept(this.classA);
		 }
	}
	
	public void createClassB(String uri)throws Exception {
		 if(this.mobi.getAllClasses().containsKey(uri)){
			 this.classB = this.mobi.getClass(uri);
		 }else{
			 this.classB = new Class(uri);
			 this.mobi.addConcept(this.classB);
		 }
	}
	
	public void createInstanceA(String uri) throws Exception {
		if(!uri.equals("-")){
			if(this.mobi.getAllInstances().containsKey(uri)){
				this.instanceA = this.mobi.getInstance(uri);
			}else{
				this.instanceA = new Instance(uri);
				this.mobi.addConcept(instanceA);
			}
		}else{
			this.instanceA = null;
		}
			
	}
	
	public void createInstanceB(String uri) throws Exception {
		if(!uri.equals("-")){
			if(this.mobi.getAllInstances().containsKey(uri)){
				this.instanceB = this.mobi.getInstance(uri);
			}else{
				this.instanceB = new Instance(uri);
				this.mobi.addConcept(instanceB);
			}
		}else{
			this.instanceB = null;
		}
	}
	
	public void createIsOneOfA(String uri) throws ExceptionURI{
	 if((this.mobi.getAllInstanceClassRelation().get(uri) == null) 
	   ||(this.mobi.getAllClassInstanceRelation().get(this.classA.getUri())== null)
	   || (!this.mobi.getAllClassInstanceRelation().get(this.classA.getUri()).contains(this.instanceA))){ 
		 this.mobi.isOneOf(this.instanceA, this.classA);
	   }
	}
	
	public void createIsOneOfB(String uri) throws ExceptionURI{
		 if((this.mobi.getAllInstanceClassRelation().get(uri) == null) 
		    		||(this.mobi.getAllClassInstanceRelation().get(this.classB.getUri())== null)
		    			|| (this.mobi.getAllClassInstanceRelation().get(this.classB.getUri()).contains(this.instanceB) == false)){ 
				this.mobi.isOneOf(this.instanceB, this.classB);
			}
	}
	
	
    public void addTypeRelation() throws Exception{
    	if(this.typeNameRelation != null){
    		if (this.propertyB != null){
    				CompositionRelation composition = (CompositionRelation)this.mobi.convertToBidirecionalCompositionRelationship(this.genericRelation, this.propertyA, this.propertyB);
    				if((this.nameTaleRelation != null) && (!this.nameTaleRelation.isEmpty())){
    					this.addTaleRelation(this.nameTaleRelation, composition);
    					this.nameTaleRelation = "";
    				}
    				
    				composition.processCardinality();
    				this.mobi.addConcept(composition);   
    		}else{
    			if(this.typeNameRelation.equals("INHERITANCE")){
    				InheritanceRelation inheritanceRelation = (InheritanceRelation)this.mobi.convertToInheritanceRelation(this.genericRelation, this.propertyA);
    				if((this.nameTaleRelation != null) && (!this.nameTaleRelation.isEmpty())){
    					this.addTaleRelation(this.nameTaleRelation, inheritanceRelation);
    					this.nameTaleRelation = "";
    				}
    				
    				inheritanceRelation.processCardinality();
    				this.mobi.addConcept(inheritanceRelation);
    				
    			}else if (this.typeNameRelation.equals("EQUIVALENCE")){
        			EquivalenceRelation equivalenceRelation = (EquivalenceRelation)this.mobi.convertToEquivalenceRelation(this.genericRelation, this.propertyA); 
        			if((this.nameTaleRelation != null) && (!this.nameTaleRelation.isEmpty())){
    					this.addTaleRelation(this.nameTaleRelation, equivalenceRelation);
    					this.nameTaleRelation = "";
    				}
        			
        			equivalenceRelation.processCardinality();
        			this.mobi.addConcept(equivalenceRelation);   
        		
    			}else if (this.typeNameRelation.equals("SYMMETRIC")){
        			SymmetricRelation symmetric = (SymmetricRelation)this.mobi.convertToSymmetricRelation(this.genericRelation, this.propertyA);
        			if((this.nameTaleRelation != null) && (!this.nameTaleRelation.isEmpty())){
    					this.addTaleRelation(this.nameTaleRelation, symmetric);
    					this.nameTaleRelation = "";
    				}
        			
        			symmetric.processCardinality();
        			this.mobi.addConcept(symmetric);
        		
    			}else{
        			CompositionRelation composition = (CompositionRelation)this.mobi.convertToUnidirecionalCompositionRelationship(this.genericRelation, this.propertyA);
        			if((this.nameTaleRelation != null) && (!this.nameTaleRelation.isEmpty())){
    					this.addTaleRelation(this.nameTaleRelation, composition);
    					this.nameTaleRelation = "";
    				}
        			
        			composition.processCardinality();
        			this.mobi.addConcept(composition);
        		}
        	}
    	}
    }
    
    public void addGenericRelation(){
    	this.genericRelation.addInstanceRelation(this.instanceA, this.instanceB);
    }
    
    public void addTaleRelation(String uri, Relation relation){
    	if(this.mobi.getAllTales().containsKey(uri)){
    		this.mobi.getAllTales().get(uri).getRelations().add(relation); //Insere a relação na história criada
    	}
    }
	
    
	public Mobi getMobi() {
		return mobi;
	}

	public Instance getInstanceA() {
		return instanceA;
	}

	public Instance getInstanceB() {
		return instanceB;
	}

	public Class getClassA() {
		return classA;
	}

	public Class getClassB() {
		return classB;
	}

	public GenericRelation getGenericRelation() {
		return genericRelation;
	}

	public void setGenericRelation(GenericRelation genericRelation) {
		this.genericRelation = genericRelation;
	}

	public String getTypeNameRelation() {
		return typeNameRelation;
	}

	public void setTypeNameRelation(String typeNameRelation) {
		this.typeNameRelation = typeNameRelation;
	}

	public String getPropertyA() {
		return propertyA;
	}

	public void setPropertyA(String propertyA) {
		this.propertyA = propertyA;
	}

	public String getPropertyB() {
		return propertyB;
	}

	public void setPropertyB(String propertyB) {
		this.propertyB = propertyB;
	}

	public Tale getTale() {
		return tale;
	}

	public void setTale(Tale tale) {
		this.tale = tale;
	}

	public String getNameTaleRelation() {
		return nameTaleRelation;
	}

	public void setNameTaleRelation(String nameTaleRelation) {
		this.nameTaleRelation = nameTaleRelation;
		
	}
}
