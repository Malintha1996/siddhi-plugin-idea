/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.siddhi.plugins.idea;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.LayeredIcon;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SiddhiIcons {
    public static final Icon ICON = IconLoader.findIcon("/icons/siddhi.png");
    public static final Icon APPLICATION_RUN = createIconWithShift(ICON, AllIcons.Nodes.RunnableMark);
    public static final Icon PARAMETER = AllIcons.Nodes.Parameter;
    public static final Icon FIELD = AllIcons.Nodes.Field;
    public static final Icon METHOD = AllIcons.Nodes.Method;
    public static final Icon PROPERTY = AllIcons.Nodes.Property;

    private SiddhiIcons() {

    }

    @NotNull
    private static LayeredIcon createIconWithShift(@NotNull Icon base, Icon mark) {
        LayeredIcon icon = new LayeredIcon(2) {
            @Override
            public int getIconHeight() {
                return base.getIconHeight();
            }
        };
        icon.setIcon(base, 0);
        icon.setIcon(mark, 1, 0, base.getIconWidth() / 2);
        return icon;
    }
}
