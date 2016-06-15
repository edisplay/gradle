/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.internal.project.taskfactory;

import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.OutputFiles;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.Collection;

import static org.gradle.api.internal.tasks.TaskOutputsUtil.ensureParentDirectoryExists;

@SuppressWarnings("deprecation")
public class OutputFilesPropertyAnnotationHandler extends AbstractPluralOutputPropertyAnnotationHandler {

    private static final String DEPRECATION_MESSAGE = String.format(
        "Please use separate properties for each file annotated with @%s, "
        + "reorganize output files under a single output directory annotated with @%s, "
        + "or change the property type to Map.",
        OutputFile.class.getSimpleName(), OutputDirectory.class.getSimpleName()
    );

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return OutputFiles.class;
    }

    @Override
    protected String getDeprecatedIterableMessage() {
        return DEPRECATION_MESSAGE;
    }

    @Override
    protected void doValidate(String propertyName, File file, Collection<String> messages) {
        OutputPropertyAnnotationUtil.validateFile(propertyName, file, messages);
    }

    @Override
    protected void doEnsureExists(File file) {
        ensureParentDirectoryExists(file);
    }
}