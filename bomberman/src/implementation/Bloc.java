package implementation;

import utils.BlocReward;
import utils.BlocType;
import services.BlocService;

/**
 * Implementation du Service Bloc
 * @author Barbier & Deluze.
 *
 */
public class Bloc implements BlocService {

	private BlocType type;
	private BlocReward reward;
	
	public Bloc(){}
	
	@Override
	public void init(BlocType type, BlocReward reward){
		if(type == null || reward == null){
			throw new IllegalArgumentException();
		}
		this.type = type;
		this.reward = reward;
	}
	@Override
	public boolean isDestructible() {
		return (type == BlocType.Vide ||
				type == BlocType.MurBrique ||
				type == BlocType.Explosion);
	}
	@Override
	public BlocType getType() {
		return type;
	}
	@Override
	public void setType(BlocType type) {
		if(type == null){
			throw new IllegalArgumentException();
		}
		this.type = type;
	}
	@Override
	public BlocReward getReward() {
		return reward;
	}
	@Override
	public void setReward(BlocReward reward) {
		if(reward == null){
			throw new IllegalArgumentException();
		}
		this.reward = reward;
	}
}
