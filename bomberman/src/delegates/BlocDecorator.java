package delegates;

import services.BlocService;
import utils.BlocType;
import utils.BlocReward;

/**
 * 
 * @author Barbier & Deluze.
 *
 */
public class BlocDecorator implements BlocService {
	
	private BlocService delegate;
	
	public BlocDecorator(BlocService bloc){
		delegate = bloc;
	}
	
	@Override
	public void init(BlocType type, BlocReward reward) {
		delegate.init(type, reward);
	}
	
	@Override
	public BlocType getType() {
		return delegate.getType();
	}

	@Override
	public void setType(BlocType type) {
		delegate.setType(type);
	}
	
	@Override
	public BlocReward getReward() {
		return delegate.getReward();
	}

	@Override
	public void setReward(BlocReward reward) {
		delegate.setReward(reward);
	}

	@Override
	public boolean isDestructible() {
		return delegate.isDestructible();
	}
}
