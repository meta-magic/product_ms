package com.metamagic.productms;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class PMFConfig {

	private static final PersistenceManagerFactory PERSISTENCE_MANAGER_FACTORY = JDOHelper.getPersistenceManagerFactory("PersistenceUnit");

	public static PersistenceManagerFactory getPersistenceManagerFactory() {
		return PERSISTENCE_MANAGER_FACTORY;
	}
}
