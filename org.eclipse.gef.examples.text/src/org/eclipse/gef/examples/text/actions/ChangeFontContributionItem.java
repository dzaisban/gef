/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.gef.examples.text.actions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

import org.eclipse.ui.IPartService;

import org.eclipse.gef.ui.actions.GEFActionConstants;

/**
 * @author Pratik Shah
 * @since 3.1
 */
public class ChangeFontContributionItem 
	extends StyleComboContributionItem
{
	
private static final String[] FONT_NAMES;
static {
	Set set = new HashSet();
	FontData[] fonts = Display.getCurrent().getFontList(null, true);
	for (int i = 0; i < fonts.length; i++)
		set.add(fonts[i].getName());
	FONT_NAMES = new String[set.size()];
	set.toArray(FONT_NAMES);
	Arrays.sort(FONT_NAMES);
}

public ChangeFontContributionItem(IPartService service) {
	super(service);
}

protected int findIndexOf(String fontName) {
	for (int i = 0; i < getItems().length; i++) {
		if (getItems()[i].equalsIgnoreCase(fontName))
			return i;
	}
	return -1;
}

protected String[] getItems() {
	return FONT_NAMES;
}

protected String getStyleID() {
	return GEFActionConstants.STYLE_FONT_NAME;
}

protected void handleWidgetSelected(SelectionEvent e) {
	int index = findIndexOf(combo.getText());
	if (index >= 0 && !getItems()[index].equals(styleService.getStyle(getStyleID())))
		styleService.setStyle(getStyleID(), getItems()[index]);
	else
		refresh();
}

}