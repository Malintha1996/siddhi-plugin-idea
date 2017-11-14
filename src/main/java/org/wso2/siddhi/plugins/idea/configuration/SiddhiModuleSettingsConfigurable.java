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

package org.wso2.siddhi.plugins.idea.configuration;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurableBase;
import javax.annotation.Nonnull;
import org.wso2.siddhi.plugins.idea.project.SiddhiModuleSettings;

public class SiddhiModuleSettingsConfigurable extends
        ConfigurableBase<SiddhiModuleSettingsUI, SiddhiModuleSettings> {

    @Nonnull
    private final Module myModule;
    private final boolean myDialogMode;

    public SiddhiModuleSettingsConfigurable(@Nonnull Module module, boolean dialogMode) {
        super("Siddhi.project.settings", "Siddhi Project Settings", null);
        myModule = module;
        myDialogMode = dialogMode;
    }

    @Nonnull
    @Override
    protected SiddhiModuleSettings getSettings() {
        return SiddhiModuleSettings.getInstance(myModule);
    }

    @Override
    protected SiddhiModuleSettingsUI createUi() {
        return new SiddhiModuleSettingsUI(myModule, myDialogMode);
    }
}
