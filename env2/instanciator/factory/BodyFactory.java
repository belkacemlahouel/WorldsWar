package env2.instanciator.factory;

import java.util.HashMap;

import env2.instanciator.bodies.AbstractBodyInstanciator;
import env2.instanciator.bodies.ant.AntGathererInstanciator;
import env2.instanciator.bodies.ant.AntMotherInstanciator;
import env2.instanciator.bodies.ant.AntNurseInstanciator;
import env2.instanciator.bodies.ant.AntSoldierInstanciator;
import env2.instanciator.bodies.ant.AntUndertakerInstanciator;
import env2.instanciator.bodies.spider.SpiderInstanciator;
import env2.instanciator.bodies.termite.TermiteGathererInstanciator;
import env2.instanciator.bodies.termite.TermiteMotherInstanciator;
import env2.instanciator.bodies.termite.TermiteNurseInstanciator;
import env2.instanciator.bodies.termite.TermiteSoldierInstanciator;
import env2.instanciator.bodies.termite.TermiteUndertakerInstanciator;
import env2.type.WorldObjectType;

public class BodyFactory {

	public static final HashMap<WorldObjectType, AbstractBodyInstanciator> BODY_INSTANCIATOR;
	
	static {
		BODY_INSTANCIATOR = new HashMap<>();
		BODY_INSTANCIATOR.put(WorldObjectType.ANTGATHERERBODY, new AntGathererInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.ANTMOTHERBODY, new AntMotherInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.ANTNURSEBODY, new AntNurseInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.ANTSOLDIERBODY, new AntSoldierInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.ANTUNDERTAKERBODY, new AntUndertakerInstanciator());
		
		BODY_INSTANCIATOR.put(WorldObjectType.SPIDERBODY, new SpiderInstanciator());
		
		BODY_INSTANCIATOR.put(WorldObjectType.TERMITEGATHERERBODY, new TermiteGathererInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.TERMITEMOTHERBODY, new TermiteMotherInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.TERMITENURSEBODY, new TermiteNurseInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.TERMITESOLDIERBODY, new TermiteSoldierInstanciator());
		BODY_INSTANCIATOR.put(WorldObjectType.TERMITEUNDERTAKERBODY, new TermiteUndertakerInstanciator());
	}
}
