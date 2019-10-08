
package org.slf4j.impl;

import org.slf4j.helpers.NOPMDCAdapter;
import org.slf4j.spi.MDCAdapter;

/**
 * This implementation is bound to {@link NOPMDCAdapter}.
 *
 * @author Ceki G&uuml;lc&uuml;
 * @author Thorsten M&ouml;ler
 * @version $Rev:$; $Author:$; $Date:$
 */
public class StaticMDCBinder
{

	/**
	 * The unique instance of this class.
	 */
	public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();

	private StaticMDCBinder()
	{
	}

	/**
	 * Currently this method always returns an instance of
	 * {@link StaticMDCBinder}.
	 */
	public MDCAdapter getMDCA()
	{
		return new NOPMDCAdapter();
	}

	public String getMDCAdapterClassStr()
	{
		return NOPMDCAdapter.class.getName();
	}
}
