package contrats;

import services.BlocService;
import utils.Contractor;
import utils.BlocType;
import utils.BlocReward;
import delegates.BlocDecorator;

public class BlocContrat extends BlocDecorator{

	public BlocContrat(BlocService bloc) {
		super(bloc);
	}
	
	private void checkInvariant(){
		//Aucun
	}
	
	@Override
	public void init(BlocType type, BlocReward reward){
		//PRE
		if( !(reward != null))
			Contractor.defaultContractor().preconditionError("Bloc", "init", "reward == null");
		if( !(type != null))
			Contractor.defaultContractor().preconditionError("Bloc", "init", "type == null");
		
		//APPEL METHODE
		super.init(type, reward);
		
		//POST
		if( !(getType()==type) )
			Contractor.defaultContractor().postconditionError("Bloc", "init", "getType(init(t,r)) != t");
		if( !(getReward()==reward) )
			Contractor.defaultContractor().postconditionError("Bloc", "init", "getReward(init(t,r)) != r");
		checkInvariant();
	}
	
	@Override
	public BlocType getType(){
		return super.getType();
	}
	
	@Override
	public BlocReward getReward(){
		return super.getReward();
	}
	
	@Override
	public boolean isDestructible() {
		return super.isDestructible();
	}
	
	@Override
	public void setType(BlocType type){
		//PRE
		if( !(type != null))
			Contractor.defaultContractor().preconditionError("Bloc", "setType", "type == null");
		
		//SAVE
		BlocReward reward = getReward();
		boolean dest = isDestructible();
		
		//APPEL METHODE
		checkInvariant();
		super.setType(type);
		checkInvariant();
		
		//POST
		if( !(getType()==type) )
			Contractor.defaultContractor().postconditionError("Bloc", "setType", "getType(setType(t,B)) != t");
		if( !(getReward()==reward) )
			Contractor.defaultContractor().postconditionError("Bloc", "setType", "getReward(setType(t,B)) != getRewardt(B)");
		if( !(isDestructible()==dest) )
			Contractor.defaultContractor().postconditionError("Bloc", "setType", "isDestructible(setType(r,B)) != isDestructible(B)");
	}

	@Override
	public void setReward(BlocReward reward){
		//PRE
		if( !(reward != null))
			Contractor.defaultContractor().preconditionError("Bloc", "setReward", "reward == null");
		
		//SAVE
		BlocType type = getType();
		boolean dest = isDestructible();
		
		//APPEL METHODE
		checkInvariant();
		super.setReward(reward);
		checkInvariant();
		
		//POST
		if( !(getType()==type) )
			Contractor.defaultContractor().postconditionError("Bloc", "setReward", "getType(setReward(t)) != t");
		if( !(getReward()==reward) )
			Contractor.defaultContractor().postconditionError("Bloc", "setReward", "getReward(setReward(r)) != r");
		if( !(isDestructible()==dest) )
			Contractor.defaultContractor().postconditionError("Bloc", "setReward", "isDestructible(setReward(r,B)) != isDestructible(B)");
	}
}
