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

package org.wso2.siddhi.plugins.idea.project;

import com.intellij.ide.util.importProject.ModuleDescriptor;
import com.intellij.ide.util.importProject.ProjectDescriptor;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.ProjectJdkForModuleStep;
import com.intellij.ide.util.projectWizard.importSources.DetectedProjectRoot;
import com.intellij.ide.util.projectWizard.importSources.DetectedSourceRoot;
import com.intellij.ide.util.projectWizard.importSources.ProjectFromSourcesBuilder;
import com.intellij.ide.util.projectWizard.importSources.ProjectStructureDetector;
import org.wso2.siddhi.plugins.idea.SiddhiModuleType;
import org.wso2.siddhi.plugins.idea.sdk.SiddhiSdkType;
import javax.annotation.Nonnull;

import javax.swing.*;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SiddhiProjectStructureDetector extends ProjectStructureDetector {

    @Nonnull
    @Override
    public DirectoryProcessingResult detectRoots(@Nonnull File dir, @Nonnull File[] children, @Nonnull File base,
                                                 @Nonnull List<DetectedProjectRoot> result) {
        // There can be only one project root, it is the directory that the user selects.
        result.add(new DetectedProjectRoot(base) {

            @Nonnull
            @Override
            public String getRootTypeName() {
                return "Siddhi";
            }
        });
        return DirectoryProcessingResult.SKIP_CHILDREN;
    }

    @Override
    public void setupProjectStructure(@Nonnull Collection<DetectedProjectRoot> roots,
                                      @Nonnull ProjectDescriptor projectDescriptor,
                                      @Nonnull ProjectFromSourcesBuilder builder) {
        // If there are no roots detected, we don't need to process anything.
        if (roots.isEmpty()) {
            return;
        }

        // Detected project root will be the first element in the collection.
        DetectedProjectRoot projectRoot = roots.iterator().next();
        // We need to create a source root element as well. This is used when we create the module descriptor.
        DetectedSourceRoot detectedSourceRoot = new DetectedSourceRoot(projectRoot.getDirectory(), "") {

            @Nonnull
            @Override
            public String getRootTypeName() {
                return "Siddhi";
            }
        };

        // Create a new module descriptor.
        ModuleDescriptor rootModuleDescriptor = new ModuleDescriptor(projectRoot.getDirectory(),
                SiddhiModuleType.getInstance(), detectedSourceRoot);
        List<ModuleDescriptor> moduleDescriptors = new LinkedList<>();
        moduleDescriptors.add(rootModuleDescriptor);
        // Set the module list. This will be displayed to the user.
        projectDescriptor.setModules(moduleDescriptors);
    }

    @Nonnull
    @Override
    public List<ModuleWizardStep> createWizardSteps(@Nonnull ProjectFromSourcesBuilder builder,
                                                    ProjectDescriptor projectDescriptor, Icon stepIcon) {
        ProjectJdkForModuleStep projectJdkForModuleStep = new ProjectJdkForModuleStep(builder.getContext(),
                SiddhiSdkType.getInstance());
        return Collections.singletonList(projectJdkForModuleStep);
    }
}
